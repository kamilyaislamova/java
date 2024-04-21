package expression.generic;

import expression.*;
import expression.exceptions.*;

import java.util.List;
import java.util.Map;

public class GenericExpressionParser <T>{
    public GenericExpressionParser() {
    }

    protected static class ExParser <T> extends BaseParser {
        public GenericCalculator<T> calc;
        protected boolean isList;

        protected List<String> variables;

        protected ExParser(CharSource source, GenericCalculator<T> calc) {
            super(source);
            this.calc = calc;
        }

        public GenericPartOfExpression <T> parseExpression(List<String> var, boolean isItList) {
            isList = isItList;
            variables = var;
            skipWhitespace();
            final GenericPartOfExpression <T> result = parseOperation("min");
            skipWhitespace();
            return result;
        }

        private final static Map<String, String> NEXT = Map.of("min", "+", "+", "*");
        private final static Map<String, String> PAIRS = Map.of("min", "max", "+", "-", "*", "/");
        private final static Map<Character, Character> BRACKETS = Map.of('(', ')', '[', ']', '{', '}');

        private GenericPartOfExpression <T> parseOperation(String op1) {
            skipWhitespace();
            GenericPartOfExpression <T> result = parseNext(op1);
            String op2 = PAIRS.get(op1);
            skipWhitespace();
            while (!eof()) {
                if (take(op1)) {
                    result = createNewOperation(result, parseNext(op1), op1);
                } else if (take(op2)) {
                    result = createNewOperation(result, parseNext(op1), op2);
                } else {
                    skipWhitespace();
                    return result;
                }
            }
            skipWhitespace();
            return result;
        }

        private GenericPartOfExpression <T> parseNext(String op) {
            if (op.equals("*")) {
                return parseUnary();
            } else {
                return parseOperation(NEXT.get(op));
            }
        }

        private GenericPartOfExpression <T> createNewOperation
                (GenericPartOfExpression <T> left, GenericPartOfExpression <T> right, String op) {
            return switch (op) {
                case "min" -> new GenericMin<T>(left, right);
                case "max" -> new GenericMax<T>(left, right);
                case "+" -> new GenericAdd<T>(left, right);
                case "-" -> new GenericSubtract<T>(left, right);
                case "*" -> new GenericMultiply<T>(left, right);
                case "/" -> new GenericDivide<T>(left, right);
                default -> throw new ParsingException(source.getPosition(), "No such binary operator");
            };
        }


        public GenericPartOfExpression <T> parseUnary() {
            skipWhitespace();
            GenericPartOfExpression <T> result;
            if (take('-')) {
                if (between('0', '9')) {
                    StringBuilder number = new StringBuilder("-");
                    takeInteger(number);
                    return parseConst(number);
                } else {
                    return new GenericUnaryMinus <T>(parseUnary());
                }
            }
            skipWhitespace();
            if (take("count")) {
                return new GenericCount<T> (parseUnary());
            }
            if (between('0', '9')) {
                StringBuilder number = new StringBuilder();
                takeInteger(number);
                skipWhitespace();
                result = parseConst(number);
            } else if (between('x', 'z') && !isList) {
                result = parseVariable();
            } else if (BRACKETS.containsKey(ch)) {
                char bracket = ch;
                take();
                skipWhitespace();
                result = parseExpression(variables, isList);
                skipWhitespace();
                expect(BRACKETS.get(bracket));
            } else {
                throw new ParsingException(source.getPosition(),
                        "We cannot parse it as a valid expression object: " + ch);
            }
            skipWhitespace();
            return result;
        }


        public GenericPartOfExpression <T> parseConst(StringBuilder number) {
            int realNumber;
            try {
                realNumber = Integer.parseInt(String.valueOf(number));
            } catch (NumberFormatException err) {
                throw new ParsingException(source.getPosition(), "Cannot be parsed as integer");
            }
            skipWhitespace();
            return new GenericConst<T>(calc.toT(realNumber));
        }

        public GenericPartOfExpression <T> parseVariable() {
            GenericPartOfExpression <T> result = switch (ch) {
                case ('x') -> new GenericVariable<T>("x");
                case ('y') -> new GenericVariable<T>("y");
                case ('z') -> new GenericVariable<T>("z");
                default -> throw new ParsingException(source.getPosition(), "wrong variable: " + ch);
            };
            take();
            skipWhitespace();
            return result;
        }

        private void takeDigits(final StringBuilder sb) {
            while (between('0', '9')) {
                sb.append(take());
            }
        }

        private void takeInteger(final StringBuilder sb) {
            if (take('-')) {
                sb.append('-');
            }
            if (take('0')) {
                sb.append('0');
            } else if (between('1', '9')) {
                takeDigits(sb);
            } else {
                throw new ParsingException(source.getPosition(), "Invalid number: " + ch);
            }
        }

        private void skipWhitespace() {
            while (Character.isWhitespace(ch)) {
                take();
            }
        }

    }


    public GenericPartOfExpression <T> parse(String expression, GenericCalculator<T> calc) {
        ExParser<T> a = new ExParser<>(new StringSource(expression), calc);
        GenericPartOfExpression<T> result = a.parseExpression((List.of("x", "y", "z")), false);
        if (!a.eof()) {
            throw new ParsingException("Left object cannot be parsed as expression: " + a.getLeftData());
        }
        return result;
    }

}
