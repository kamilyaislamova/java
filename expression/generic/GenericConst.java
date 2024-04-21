package expression.generic;


public class GenericConst<T> implements GenericPartOfExpression<T> {
    private final T value;

    public GenericConst(T x) {
        this.value = x;
    }

    @Override
    public T evaluate(T x, T y, T z, GenericCalculator<T> calc) {
        return value;
    }

}
