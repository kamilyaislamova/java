package expression.exceptions;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface CharSource {
    boolean hasNext();
    char next();
    char getBack(int i);
    int getPosition();
    public String getLeftData();
    IllegalArgumentException error(String message);
}
