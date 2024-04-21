package expression;


import java.math.BigInteger;
import java.util.List;

public abstract class BinaryOperations implements PartOfExpression{
    protected PartOfExpression leftField;
    protected PartOfExpression rightField;
    protected String sym;
    protected Operation symbol;
    protected abstract int count(int a, int b);
    protected abstract boolean bracketsChecker(Operation op, boolean order);
    public BinaryOperations(Expression l, Expression r, String sym1, Operation symbol1) {
        this.leftField = (PartOfExpression)l;
        this.rightField = (PartOfExpression)r;
        this.sym = sym1;
        this.symbol = symbol1;
    }

    @Override
    public String toString() {
        StringBuilder answ = new StringBuilder();
        answ.append('(');
        answ.append(leftField.toString());
        answ.append(sym);
        answ.append(rightField.toString());
        answ.append(')');
        return answ.toString();
    }

    @Override
    public String toMiniString() {
        return leftField.toMiniString(symbol, false) + sym + rightField.toMiniString(symbol, true);
    }

    @Override
    public String toMiniString(Operation op, boolean order) {
        if(bracketsChecker(op, order)) {
            return "(" + this.toMiniString() + ")";
        }
        return this.toMiniString();
    }


    @Override
    public int evaluate(int x) { return this.count(leftField.evaluate(x), rightField.evaluate(x)); }
    @Override
    public int evaluate(int x, int y, int z) {
        return this.count(leftField.evaluate(x, y, z), rightField.evaluate(x, y, z));
    }

    @Override
    public boolean equals(Object q) {
        if (q == null || q.getClass() != this.getClass())
            return false;
        BinaryOperations s = (BinaryOperations)q;
        return this.rightField.equals(s.rightField) && this.sym.equals(s.sym)
                && this.leftField.equals(s.leftField);
    }

    @Override
    public int hashCode() {
        final int mod = 1000000007;
        final int b = 53;
        return (leftField.hashCode() * b * b * b % mod
                + sym.hashCode() * b * b % mod
                + rightField.hashCode() * b % mod) % mod;
    }

    @Override
    public int evaluate(List<Integer> variables) {
        return this.count(leftField.evaluate(variables), rightField.evaluate(variables));
    }

    public int safeEvaluate(int x, int y, int z) {
        int checkLeft = leftField.evaluate(x, y, z);
        int checkRight = rightField.evaluate(x, y, z);
        checkOverflow(checkLeft, checkRight);
        return count(checkLeft, checkRight);
    }
    public int safeEvaluate(List<Integer> variables) {
        int checkLeft = leftField.evaluate(variables);
        int checkRight = rightField.evaluate(variables);
        checkOverflow(checkLeft, checkRight);
        return count(checkLeft, checkRight);
    }

    protected void checkOverflow(int checkLeft, int checkRight) {
    }
}
