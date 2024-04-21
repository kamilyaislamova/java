package expression;

import java.math.BigInteger;

public class Divide extends BinaryOperations{

    public Divide(Expression l, Expression r) {
        super(l, r, " / ", Operation.DIVIDE);
    }
    @Override
    public boolean bracketsChecker(Operation op, boolean order) {
        return (op == Operation.MULTIPLY || op == Operation.DIVIDE) && order;
    }
    @Override
    public int count (int a, int b) {
        return a / b;
    }
}
