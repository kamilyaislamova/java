package expression.exceptions;

import expression.Divide;
import expression.Expression;

import java.util.List;

public class CheckedDivide extends Divide {
    public CheckedDivide(Expression l, Expression r) {
        super(l, r);
    }
    @Override
    protected void checkOverflow(int checkLeft, int checkRight) {
        if (checkRight == 0) {
            throw new MathException("Division by zero", "Divide");
        }
        if (checkRight == -1 && checkLeft == Integer.MIN_VALUE) {
            throw new MathException("Overflow: greater than the maximum value of the integer", "Divide");
        }
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return super.safeEvaluate(x, y, z);
    }
    @Override
    public int evaluate(List<Integer> variables) {
        return super.safeEvaluate(variables);
    }
}
