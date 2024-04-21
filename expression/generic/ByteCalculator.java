package expression.generic;

import expression.exceptions.MathException;

public class ByteCalculator implements GenericCalculator <Byte> {

    @Override
    public Byte add(Byte a, Byte b) { return (byte) (a + b); }

    @Override
    public Byte subtract(Byte a, Byte b) { return (byte) (a - b); }

    @Override
    public Byte multiply(Byte a, Byte b) { return (byte) (a * b); }

    @Override
    public Byte divide(Byte a, Byte b) {
        if (b == 0)
            throw new MathException("division by zero", "integerCheck");
        return (byte) (a / b);
    }

    @Override
    public Byte negate(Byte a) { return (byte) -a;}

    @Override
    public Byte min(Byte a, Byte b) { return (byte) Math.min(a, b); }

    @Override
    public Byte max(Byte a, Byte b) { return (byte) Math.max(a, b); }

    @Override
    public Byte count(Byte a) { return (byte) Integer.bitCount(Byte.toUnsignedInt(a)); }

    @Override
    public Byte toT(int a) {
        return (byte) a;
    }
}
