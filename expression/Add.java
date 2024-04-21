package expression;

import java.math.BigInteger;

public class Add extends BinaryOperations {

    public Add(Expression l, Expression r) {
        super(l, r, " + ", Operation.ADD);
    }

    @Override
    public boolean bracketsChecker(Operation op, boolean order) {
        return op == Operation.MULTIPLY || op == Operation.DIVIDE
                || (op == Operation.SUBTRACT && order);
    }

    @Override
    public int count(int a, int b) {
        return a + b;
    }

}
