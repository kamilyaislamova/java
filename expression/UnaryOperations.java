package expression;

import java.math.BigInteger;

public abstract class UnaryOperations implements PartOfExpression{
    protected PartOfExpression field;
    protected String sym;

    protected abstract int count(int a);
    protected abstract boolean bracketsChecker(PartOfExpression field);

    public UnaryOperations(Expression f, String sym1) {
        this.field = (PartOfExpression)f;
        this.sym = sym1;
    }
    @Override
    public int evaluate(int x) {
        return count(field.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return this.count(field.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return sym + '(' + field.toString() + ')';
    }
    @Override
    public String toMiniString(Operation op, boolean order) {
        return this.toMiniString();
    }

    public String toMiniString() {
        if (bracketsChecker(field)) {
            return sym + '(' + field.toMiniString() + ')';
        }
        return sym + ' ' + field.toMiniString();
    }
}
