package org.iesparser.parser;

/**
 * Wyjątek parsowania. Nie wiem co tutaj wsadzić.
 * @author mateusz
 */
public class ParseException extends Exception {

    private static final long serialVersionUID = -644519857973827281L;

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Exception e) {
        super(message, e);
    }
}
