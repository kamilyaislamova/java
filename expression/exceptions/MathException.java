package expression.exceptions;

public class MathException extends RuntimeException{

    public MathException(String messageFromCode, String className) {
        super(messageFromCode + " after operation: " + className);
    }

}
