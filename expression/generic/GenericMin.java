package expression.generic;

public class GenericMin <T> extends GenericBinaryOperations <T> {
    public GenericMin(GenericPartOfExpression <T> l, GenericPartOfExpression <T> r) {
        super(l, r);
    }

    @Override
    protected T count(T a, T b, GenericCalculator <T> calc) {
        return calc.min(a, b);
    }


}
