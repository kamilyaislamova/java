package expression.generic;


public abstract class GenericBinaryOperations<T> implements GenericPartOfExpression<T> {
    protected GenericPartOfExpression<T> leftField;
    protected GenericPartOfExpression<T> rightField;

    protected abstract T count(T a, T b, GenericCalculator<T> calc);

    public GenericBinaryOperations(GenericPartOfExpression<T> l, GenericPartOfExpression<T> r) {
        this.leftField = l;
        this.rightField = r;
    }

    @Override
    public T evaluate(T x, T y, T z, GenericCalculator<T> calc) {
        return this.count(leftField.evaluate(x, y, z, calc), rightField.evaluate(x, y, z, calc), calc);
    }

}
