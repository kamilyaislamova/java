package expression.generic;


import expression.exceptions.MathException;

public class IntegerCheckedCalculator implements GenericCalculator <Integer>{
    @Override
    public Integer add(Integer a, Integer b) { return Math.addExact(a, b); }

    @Override
    public Integer subtract(Integer a, Integer b) { return Math.subtractExact(a, b); }

    @Override
    public Integer multiply(Integer a, Integer b) { return Math.multiplyExact(a, b); }

    @Override
    public Integer divide(Integer a, Integer b) {
        if (b == 0)
            throw new MathException("division by zero", "integerCheck");
        if (a == Integer.MIN_VALUE && b == -1)
            throw new MathException("Overflow: greater than the maximum value of the integer", "integerCheck");
        return a / b;
    }

    @Override
    public Integer negate(Integer a) { return Math.negateExact(a);}

    @Override
    public Integer min(Integer a, Integer b) { return Math.min(a, b); }

    @Override
    public Integer max(Integer a, Integer b) { return Math.max(a, b); }

    @Override
    public Integer count(Integer a) { return Integer.bitCount(a); }

    @Override
    public Integer toT(int a) {
        return a;
    }
}
