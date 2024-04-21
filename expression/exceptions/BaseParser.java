package expression.exceptions;


public class BaseParser {
    private static final char END = '\0';
    protected final CharSource source;
    protected char ch = 0xffff;

    protected BaseParser(final CharSource source) {
        this.source = source;
        take();
    }

    protected char take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    private boolean isItVariable(char ch) {
        return Character.isLetter(ch) || between('0', '9') || ch == '$' || ch == '_';
    }

    protected boolean take(final String expected) {
        for (int i = 0; i < expected.length(); i++) {
            if (!take(expected.charAt(i))) {
                ch = source.getBack(i);
                return false;
            }
        }
        if ((expected.equals("min") || expected.equals("max")) && isItVariable(ch)) {
            ch = source.getBack(expected.length());
            return false;
        }
        return true;
    }


    protected void expect(final char expected) {
        if (!take(expected)) {
            throw new ParsingException(source.getPosition(), "Expected '" + expected + "', found '" + ch + "'");
        }
    }

    protected void expect(final String value) {
        for (final char c : value.toCharArray()) {
            expect(c);
        }
    }

    public boolean eof() {
        return take(END);
    }
    public String getLeftData() {
        return source.getLeftData();
    }

    protected IllegalArgumentException error(final String message) {
//        System.err.println(message);
        return source.error(message);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }
}
