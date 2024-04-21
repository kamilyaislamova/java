package expression.generic;


public class GenericUnaryMinus <T> extends GenericUnaryOperations <T> {

    public GenericUnaryMinus(GenericPartOfExpression <T> f) {
        super(f);
    }
    @Override
    protected T count(T a, GenericCalculator <T> calc) {
        return calc.negate(a);
    }

}
