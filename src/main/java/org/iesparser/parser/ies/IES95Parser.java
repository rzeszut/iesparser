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

    /* (non-Javadoc)
     * @see org.iesparser.parser.ies.IESParser#parseKeywords(org.iesparser.data.PhotometricData)
     */
    @Override
    protected void parseKeywords(PhotometricData data) {
        // includes ies standard specifier, e.g. IESNA:LM-63-1995
        // TODO add label processing
        Scanner in = getInput();
        while (!in.hasNext("TILT=.*")) {
            in.nextLine();
        }
    }
}
