package expression.exceptions;

import expression.*;

import java.util.List;

public class CheckedNegate extends UnaryMinus {
    public CheckedNegate(Expression f) {
        super(f);
    }


    protected void checkOverflow(int check) {
        if (check == Integer.MIN_VALUE) {
            throw new MathException("Overflow: greater than the maximum value of the integer", "Negate");
        }
    }
    @Override
    public int evaluate(int x, int y, int z) {
        int check = field.evaluate(x, y, z);
        checkOverflow(check);
        return super.count(check);
    }
    @Override
    public int evaluate(List<Integer> variables) {
        int check = field.evaluate(variables);
        checkOverflow(check);
        return super.count(check);
    }

    @Override
    protected boolean bracketsChecker (PartOfExpression field){
        return field.getClass() != Const.class && field.getClass() != Variable.class
                && field.getClass() != UnaryMinus.class && field.getClass() != CheckedNegate.class;
    }
}
