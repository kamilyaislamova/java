package expression.generic;


public class GenericDivide <T> extends GenericBinaryOperations <T> {

    public GenericDivide(GenericPartOfExpression <T> l, GenericPartOfExpression <T> r) {
        super(l, r);
    }

    @Override
    public T count (T a, T b, GenericCalculator <T> calc) {
        return calc.divide(a, b);
    }
}
