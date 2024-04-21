package expression.exceptions;


import expression.PartOfExpression;

import java.lang.reflect.ParameterizedType;

public class Main {
    public static void main(String[] args) {
        PartOfExpression Ex = new ExpressionParser().parse("5min5");
        System.out.println(Ex);
    }
}