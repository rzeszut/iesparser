package org.iesparser.parser.ies;

import java.util.Scanner;

import org.iesparser.data.PhotometricData;
import org.iesparser.parser.Parser;


/**
 *
 * @author mateusz
 */
public class IES95Parser extends IESParser implements Parser {

    /**
     * Chroniony konstruktor, tworzenie obiektu tylko za pomocą
     * IESParserFactory.
     *
     * @param in Stream z plikiem IES.
     */
    protected IES95Parser(Scanner in) {
        super(in);
    }

    @Override
    protected void parseKeywords(PhotometricData data) {
        // TODO zrobic przetwarzanie labeli
        Scanner in = getInput();
        while (!in.hasNext("TILT=.*")) {
            in.next();
        }
    }
}
