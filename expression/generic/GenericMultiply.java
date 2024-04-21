package expression.generic;

public class GenericMultiply <T> extends GenericBinaryOperations <T> {

    public GenericMultiply(GenericPartOfExpression <T> l, GenericPartOfExpression <T> r) {
        super(l, r);
    }

    @Override
    public T count (T a, T b, GenericCalculator <T> calc) {
        return calc.multiply(a, b);
    }
}
