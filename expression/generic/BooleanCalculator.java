package expression.generic;

import expression.exceptions.MathException;

public class BooleanCalculator implements GenericCalculator <Boolean> {
    @Override
    public Boolean add(Boolean a, Boolean b) { return a | b; }

    @Override
    public Boolean subtract(Boolean a, Boolean b) { return a ^ b; }

    @Override
    public Boolean multiply(Boolean a, Boolean b) { return a & b; }

    @Override
    public Boolean divide(Boolean a, Boolean b) {
        if (!b)
            throw new MathException("division by zero", "boolean");
        return a;
    }

    @Override
    public Boolean negate(Boolean a) { return a;}

    @Override
    public Boolean min(Boolean a, Boolean b) { return a & b; }

    @Override
    public Boolean max(Boolean a, Boolean b) { return a | b; }

    @Override
    public Boolean count(Boolean a) { return a; }

    @Override
    public Boolean toT(int a) {
        return a != 0;
    }
}
