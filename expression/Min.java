package expression;



public class Min extends BinaryOperations{
    public Min(Expression l, Expression r) {
        super(l, r, " min ", Operation.MIN);
    }

    @Override
    protected int count(int a, int b) {
        if (a < b)
            return a;
        return b;
    }

    @Override
    protected boolean bracketsChecker(Operation op, boolean order) {
        return (op == Operation.MAX && order) || (op != Operation.MAX && op != Operation.MIN);
    }

}
