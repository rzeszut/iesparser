/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser.xml;

import data.PhotometricData;
import parser.ParseException;
import parser.Parser;

/**
 * Parsuje plik XML zawierający dane fotometryczne. Format (DTD, Schema) pliku
 * jest jeszcze nieustalony.
 *
 * @author mateusz
 */
public class XMLParser implements Parser {

    /**
     * Chroniony konstruktor, tworzenie obiektu tylko za pomocą
     * XMLParserFactory.
     *
     * @param filename nazwa pliku
     */
    protected XMLParser(String filename) {
        // TODO
    }

    @Override
    public PhotometricData parse() throws ParseException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
