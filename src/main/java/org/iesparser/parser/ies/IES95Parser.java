/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesparser.parser.ies;

import org.iesparser.data.PhotometricData;
import org.iesparser.parser.ParseException;
import org.iesparser.parser.Parser;


/**
 *
 * @author mateusz
 */
public class IES95Parser implements Parser {

    /**
     * Chroniony konstruktor, tworzenie obiektu tylko za pomocą
     * IESParserFactory.
     *
     * @param filename nazwa pliku w standardzie IES LM-63-1995
     */
    protected IES95Parser(String filename) {
        // TODO
    }

    public PhotometricData parse() throws ParseException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
