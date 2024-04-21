package expression;

import java.math.BigInteger;
import java.util.List;

public class UnaryMinus extends UnaryOperations{

    public UnaryMinus(Expression f) {
        super(f, "-");
    }
    @Override
    protected int count(int a) {
        return -a;
    }
    @Override
    protected boolean bracketsChecker (PartOfExpression field){
        return field.getClass() != Const.class && field.getClass() != Variable.class
                && field.getClass() != UnaryMinus.class;
    }

    @Override
    public int evaluate(List<Integer> variables) {
        return 0;
    }
}
