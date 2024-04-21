package expression.exceptions;

public class ParsingException extends RuntimeException {

    public ParsingException(int pos, String messageFromCode) {
        super(pos + ": " + messageFromCode);
    }

    public ParsingException(String messageFromCode) {
        super(messageFromCode);
    }

}
