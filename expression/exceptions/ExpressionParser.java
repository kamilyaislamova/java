package expression.exceptions;

import expression.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressionParser implements TripleParser, ListParser {
    public ExpressionParser() {
    }

    protected static class ExParser extends BaseParser {

        protected boolean isList;

        protected List<String> variables;

        protected ExParser(CharSource source) {
            super(source);
        }

        public PartOfExpression parseExpression(List<String> var, boolean isItList) {
            isList = isItList;
            variables = var;
            skipWhitespace();
            final PartOfExpression result = parseOperation("min");
            skipWhitespace();
            return result;
        }

        private final static Map<String, String> NEXT = Map.of("min", "+", "+", "*");
        private final static Map<String, String> PAIRS = Map.of("min", "max", "+", "-", "*", "/");
        private final static Map<Character, Character> BRACKETS = Map.of('(', ')', '[', ']', '{', '}');

        private PartOfExpression parseOperation(String op1) {
            skipWhitespace();
            PartOfExpression result = parseNext(op1);
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

        private PartOfExpression parseNext(String op) {
            if (op.equals("*")) {
                return parseUnary();
            } else {
                return parseOperation(NEXT.get(op));
            }
        }

        private PartOfExpression createNewOperation(PartOfExpression left, PartOfExpression right, String op) {
            return switch (op) {
                case "min" -> new Min(left, right);
                case "max" -> new Max(left, right);
                case "+" -> new CheckedAdd(left, right);
                case "-" -> new CheckedSubtract(left, right);
                case "*" -> new CheckedMultiply(left, right);
                case "/" -> new CheckedDivide(left, right);
                default -> throw new ParsingException(source.getPosition(), "No such binary operator");
            };
        }


        public PartOfExpression parseUnary() {
            skipWhitespace();
            PartOfExpression result;
            if (take('-')) {
                if (between('0', '9')) {
                    StringBuilder number = new StringBuilder("-");
                    takeInteger(number);
                    return parseConst(number);
                } else {
                    return new CheckedNegate(parseUnary());
                }
            }
            skipWhitespace();
            if (between('0', '9')) {
                StringBuilder number = new StringBuilder();
                takeInteger(number);
                skipWhitespace();
                result = parseConst(number);
            } else if (between('x', 'z') && !isList) {
                result = parseVariable();
            } else if (isList && (Character.isLetter(ch) || ch == '$' || ch == '_')) {
                result = parseListVariables();
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

        public PartOfExpression parseListVariables() {
            StringBuilder nameSb = new StringBuilder();
            while (isItVariable()) {
                nameSb.append(ch);
                take();
            }
            String name = nameSb.toString();
            skipWhitespace();
            if (!variables.contains(name)) {
                throw new ParsingException(source.getPosition(), "No such variable in this list " + name);
            }
            Variable result = new Variable(variables.indexOf(name));
            result.v = name;
            return result;
        }

        public PartOfExpression parseConst(StringBuilder number) {
            int realNumber;
            try {
                realNumber = Integer.parseInt(String.valueOf(number));
            } catch (NumberFormatException err) {
                throw new ParsingException(source.getPosition(), "Cannot be parsed as integer");
            }
            skipWhitespace();
            return new Const(realNumber);
        }

        public PartOfExpression parseVariable() {
            PartOfExpression result = switch (ch) {
                case ('x') -> new Variable("x");
                case ('y') -> new Variable("y");
                case ('z') -> new Variable("z");
                default -> throw new ParsingException(source.getPosition(), "wrong variable: " + ch);
            };
            take();
            skipWhitespace();
            return result;
        }

        private boolean isItVariable() {
            return Character.isLetter(ch) || between('0', '9') || ch == '$' || ch == '_';
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

    @Override
    public PartOfExpression parse(String expression) {
        ExParser a = new ExParser(new StringSource(expression));
        PartOfExpression result = a.parseExpression((List.of("x", "y", "z")), false);
        if (!a.eof()) {
            throw new ParsingException("Left object cannot be parsed as expression: " + a.getLeftData());
        }
        return result;
    }

    @Override
    public ListExpression parse(String expression, List<String> var) {
        ExParser a = new ExParser(new StringSource(expression));
        PartOfExpression result = a.parseExpression(var, true);
        if (!a.eof()) {
            throw new ParsingException("Left object cannot be parsed as expression: " + a.getLeftData());
        }
        return result;
    }
}
