package expression.generic;


public class GenericAdd <T> extends GenericBinaryOperations <T> {

    public GenericAdd(GenericPartOfExpression <T> l, GenericPartOfExpression <T> r) {
        super(l, r);
    }

    @Override
    public T count (T a, T b, GenericCalculator <T> calc) {
        return calc.add(a, b);
    }

}
