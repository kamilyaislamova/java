package expression;

import java.math.BigInteger;

public class Multiply extends BinaryOperations{

    public Multiply(Expression l, Expression r) {
        super(l, r, " * ", Operation.MULTIPLY);
    }
    @Override
    public boolean bracketsChecker(Operation op, boolean order) {
        return op == Operation.DIVIDE && order;
    }
    @Override
    public int count (int a, int b) {
        return a * b;
    }
}
