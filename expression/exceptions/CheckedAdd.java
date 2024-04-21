package expression.exceptions;

import expression.Add;
import expression.Expression;
import expression.Operation;

import java.util.List;

public class CheckedAdd extends Add {
    public CheckedAdd(Expression l, Expression r) {
        super(l, r);
    }

    @Override
    protected void checkOverflow(int checkLeft, int checkRight) {
        if (checkLeft < 0 && checkRight < 0) {
            if (Integer.MIN_VALUE - checkLeft > checkRight) {
                throw new MathException("Overflow: less than the minimum value of the integer", "Add");
            }
        }
        if (checkLeft > 0 && checkRight > 0) {
            if (Integer.MAX_VALUE - checkLeft < checkRight) {
                throw new MathException("Overflow: greater than the maximum value of the integer", "Add");
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
