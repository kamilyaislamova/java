package expression.generic;

public class GenericCount <T> extends GenericUnaryOperations <T> {

    public GenericCount(GenericPartOfExpression <T> f) {
        super(f);
    }
    @Override
    protected T count(T a, GenericCalculator <T> calc) {
        return calc.count(a);
    }

}
