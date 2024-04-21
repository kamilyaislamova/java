package expression.generic;

import expression.exceptions.MathException;
import expression.exceptions.ParsingException;

import java.math.BigInteger;

public class GenericTabulator implements Tabulator {
    @Override
    public Object[][][] tabulate(String mode, String expression,
                                 int x1, int x2, int y1, int y2, int z1, int z2) throws ParsingException {
        Object[][][] answer = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        switch (mode) {
            case ("i") -> tabulation(x1, y1, z1, answer, new IntegerCheckedCalculator(), expression);
            case ("d") -> tabulation(x1, y1, z1, answer, new DoubleCalculator(), expression);
            case ("bi") -> tabulation(x1, y1, z1, answer, new BigIntegerCalculator(), expression);
            case ("u") -> tabulation(x1, y1, z1, answer, new IntegerCalculator(), expression);
            case ("b") -> tabulation(x1, y1, z1, answer, new ByteCalculator(), expression);
            case ("bool") -> tabulation(x1, y1, z1, answer, new BooleanCalculator(), expression);
        }
        return answer;
    }

    private <T> void tabulation(int xStart, int yStart,
                                int zStart, Object[][][] answer, GenericCalculator<T> calc, String expression) {
        GenericExpressionParser<T> parser = new GenericExpressionParser<>();
        GenericPartOfExpression<T> parsed = parser.parse(expression, calc);
        for (int i = 0; i < answer.length; i++) {
            for (int j = 0; j < answer[i].length; j++) {
                for (int k = 0; k < answer[i][j].length; k++) {
                    try {
                        answer[i][j][k] = parsed.evaluate(calc.toT(i + xStart),
                                calc.toT(j + yStart), calc.toT(k + zStart), calc);
                    } catch (MathException e) {
                        answer[i][j][k] = null;
                    }
                }
            }
        }
    }
}
