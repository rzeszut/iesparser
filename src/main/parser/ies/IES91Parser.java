/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser.ies;

import data.PhotometricData;
import parser.ParseException;
import parser.Parser;

/**
 *
 * @author mateusz
 */
public class IES91Parser implements Parser {

    /**
     * Chroniony konstruktor, tworzenie obiektu tylko za pomocą
     * IESParserFactory.
     *
     * @param filename nazwa pliku w standardzie IES LM-63-1991.
     */
    protected IES91Parser(String filename) {
        // TODO
    }

    @Override
    public PhotometricData parse() throws ParseException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
