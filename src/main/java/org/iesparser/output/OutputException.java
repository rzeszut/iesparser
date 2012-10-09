package org.iesparser.output;

/**
 * @author mateusz
 *
 */
public class OutputException extends Exception {

    private static final long serialVersionUID = 1329225515201095909L;

    public OutputException(String message) {
        super(message);
    }

    public OutputException(String message, Exception e) {
        super(message, e);
    }

}
