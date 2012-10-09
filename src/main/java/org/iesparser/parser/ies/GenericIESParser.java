package org.iesparser.parser.ies;

import java.util.Scanner;

import org.iesparser.data.PhotometricData;
import org.iesparser.parser.ParseException;

/**
 * Generic parser class.
 *
 * @author mateusz
 *
 */
public class GenericIESParser extends IESParser {

    protected GenericIESParser(Scanner in) {
        super(in);
    }

    /* (non-Javadoc)
     * @see org.iesparser.parser.ies.IESParser#parseKeywords(org.iesparser.data.PhotometricData)
     */
    @Override
    protected void parseKeywords(PhotometricData data) throws ParseException {
        // I-dont-know-what-to-do-with-labels
        Scanner in = getInput();
        while (!in.hasNext("TILT=.*")) {
            in.nextLine();
        }

    }

}
