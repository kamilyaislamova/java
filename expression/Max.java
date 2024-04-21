package expression;

public class Max extends BinaryOperations{
    public Max(Expression l, Expression r) {
        super(l, r, " max ", Operation.MAX);
    }

    @Override
    public int count(int a, int b) {
        if (a > b)
            return a;
        return b;
    }

    @Override
    protected boolean bracketsChecker(Operation op, boolean order) {
        return (op == Operation.MIN && order) || (op != Operation.MAX && op != Operation.MIN);
    }
}
