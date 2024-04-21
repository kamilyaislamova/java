package expression.exceptions;

import expression.Expression;
import expression.Subtract;

import java.util.List;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(Expression l, Expression r) {
        super(l, r);
    }
    @Override
    protected void checkOverflow(int checkLeft, int checkRight) {
        if (checkLeft < 0 && checkRight > 0) {
            if (Integer.MIN_VALUE + checkRight > checkLeft) {
                throw new MathException("Overflow: less than the minimum value of the integer", "Subtract");
            }
        }
        if (checkLeft >= 0 && checkRight < 0) {
            if (Integer.MAX_VALUE + checkRight < checkLeft) {
                throw new MathException("Overflow: greater than the maximum value of the integer", "Subtract");
            }
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
