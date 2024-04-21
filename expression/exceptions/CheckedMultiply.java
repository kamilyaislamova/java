package expression.exceptions;

import expression.Expression;
import expression.Multiply;

import java.util.List;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(Expression l, Expression r) {
        super(l, r);
    }

    @Override
    protected void checkOverflow(int checkLeft, int checkRight) {
        if ((checkRight == -1 && checkLeft == Integer.MIN_VALUE)
                || (checkRight == Integer.MIN_VALUE && checkLeft == -1)) {
            throw new MathException("Overflow: greater than the maximum value of the integer", "Multiply");
        }
        if (checkLeft != 0 && checkRight * checkLeft / checkLeft != checkRight) {
            throw new MathException("Overflow: greater or less than the extreme value of the integer", "Multiply");
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
