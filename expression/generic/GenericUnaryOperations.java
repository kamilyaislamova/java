package expression.generic;

public abstract class GenericUnaryOperations <T> implements GenericPartOfExpression <T> {
    protected GenericPartOfExpression <T> field;

    protected abstract T count(T a, GenericCalculator <T> calc);

    public GenericUnaryOperations(GenericPartOfExpression <T> f) {
        this.field = f;

    }

    @Override
    public T evaluate(T x, T y, T z, GenericCalculator <T> calc) {
        return this.count(field.evaluate(x, y, z, calc), calc);
    }


}
