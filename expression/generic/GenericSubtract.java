package expression.generic;

public class GenericSubtract <T> extends GenericBinaryOperations <T> {

    public GenericSubtract(GenericPartOfExpression <T> l, GenericPartOfExpression <T> r) {
        super(l, r);
    }

    @Override
    public T count (T a, T b, GenericCalculator <T> calc) {
        return calc.subtract(a, b);
    }


}
