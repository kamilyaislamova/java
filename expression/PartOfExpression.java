package expression;

public interface PartOfExpression extends ToMiniString, Expression, TripleExpression, ListExpression {
    String toString();
    boolean equals(Object o);
    String toMiniString(Operation op, boolean order);
    int hashCode();
}
