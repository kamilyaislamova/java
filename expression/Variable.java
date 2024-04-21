package expression;

import java.math.BigInteger;
import java.util.List;

public class Variable implements PartOfExpression {
    public String v;
    public int ind;

    public Variable(String s) {
        this.v = s;
    }

    public Variable(int i) {
        this.ind = i;
        this.v = "var" + i;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public String toMiniString(Operation op, boolean order) {
        return v;
    }

    @Override
    public String toMiniString() {
        return v;
    }

    @Override
    public String toString() {
        return this.toMiniString();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (v) {
            case ("x"):
                return x;
            case ("y"):
                return y;
            case ("z"):
                return z;
        }
        throw new IllegalStateException("Your variable isn't supported");
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object q) {
        if (q == null || q.getClass() != this.getClass())
            return false;
        return this.v.equals(q.toString());
    }

    @Override
    public int evaluate(List<Integer> variables) {
        return variables.get(ind);
    }
}
