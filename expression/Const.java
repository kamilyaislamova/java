package expression;

import java.math.BigInteger;
import java.util.List;

public class Const implements PartOfExpression{
    private final Number value;
    public Const(int x) {
        this.value = x;
    }
    public Const(BigInteger x) {
        this.value = x;
    }
    @Override
    public String toString() {
        return value.toString();
    }
    @Override
    public String toMiniString(Operation op, boolean order) {
        return value.toString();
    }
    @Override
    public String toMiniString() {
        return value.toString();
    }
    @Override
    public int evaluate(int x) {
        return value.intValue();
    }


    @Override
    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }

    @Override
    public boolean equals(Object q) {
        if (q == null || q.getClass() != this.getClass())
            return false;
        Const q1 = (Const)q;
        return this.value.equals(q1.value);
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public int evaluate(List<Integer> variables) {
        return value.intValue();
    }
}