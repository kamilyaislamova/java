package expression.generic;

import expression.exceptions.MathException;

import java.math.BigInteger;

public class BigIntegerCalculator implements GenericCalculator <BigInteger> {
    @Override
    public BigInteger add(BigInteger a, BigInteger b) { return a.add(b); }

    @Override
    public BigInteger subtract(BigInteger a, BigInteger b) { return a.subtract(b); }

    @Override
    public BigInteger multiply(BigInteger a, BigInteger b) { return a.multiply(b); }

    @Override
    public BigInteger divide(BigInteger a, BigInteger b) {
        if (b.compareTo(new BigInteger("0")) == 0)
            throw new MathException("division by zero", "integerCheck");
        return a.divide(b);
    }

    @Override
    public BigInteger negate(BigInteger a) { return a.negate() ;}

    @Override
    public BigInteger min(BigInteger a, BigInteger b) { return a.min(b); }

    @Override
    public BigInteger max(BigInteger a, BigInteger b) { return a.max(b); }

    @Override
    public BigInteger count(BigInteger a) { return BigInteger.valueOf(a.bitCount()); }

    @Override
    public BigInteger toT(int a) {
        return new BigInteger(Integer.toString(a));
    }
}
