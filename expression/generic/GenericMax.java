package expression.generic;



public class GenericMax <T> extends GenericBinaryOperations <T> {
    public GenericMax(GenericPartOfExpression <T> l, GenericPartOfExpression <T> r) {
        super(l, r);
    }

    @Override
    public T count(T a, T b, GenericCalculator <T> calc) {
        return calc.max(a, b);
    }

}
