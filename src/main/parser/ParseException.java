/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

/**
 * Wyjątek parsowania. Nie wiem co tutaj wsadzić.
 * @author mateusz
 */
public class ParseException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -644519857973827281L;

	public ParseException(String message) {
        super(message);
    }
    
}
