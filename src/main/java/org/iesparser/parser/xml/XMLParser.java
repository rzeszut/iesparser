package org.iesparser.parser.xml;

import org.iesparser.data.PhotometricData;
import org.iesparser.parser.ParseException;
import org.iesparser.parser.Parser;


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

    public PhotometricData parse() throws ParseException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
