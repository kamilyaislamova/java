package expression.generic;

import expression.*;

public interface GenericPartOfExpression<T> {
    T evaluate(T x, T y, T z, GenericCalculator<T> calc);

}
