package expression;

import java.math.BigInteger;
import java.util.List;

public class Subtract extends BinaryOperations{

    public Subtract(Expression l, Expression r) {
        super(l, r, " - ", Operation.SUBTRACT);
    }
    @Override
    public boolean bracketsChecker(Operation op, boolean order) {
        return op == Operation.MULTIPLY || op == Operation.DIVIDE
                || (op == Operation.SUBTRACT && order);
    }
    @Override
    public int count (int a, int b) {
        return a - b;
    }


}
