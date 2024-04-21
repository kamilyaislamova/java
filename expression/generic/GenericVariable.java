package expression.generic;

public class GenericVariable <T> implements GenericPartOfExpression <T> {

    public String v;

    public GenericVariable(String s) {
        this.v = s;
    }

    @Override
    public T evaluate(T x, T y, T z, GenericCalculator <T> calc) {
        return switch (v) {
            case ("x") -> x;
            case ("y") -> y;
            case ("z") -> z;
            default -> throw new IllegalStateException("Your variable isn't supported");
        };
    }
}
