package org.iesparser.parser.ies;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.iesparser.parser.ParserFactory;

/**
 * Fabryka parserów plików IES.
 *
 * @author mateusz
 */
public class IESParserFactory implements ParserFactory {

    /**
     * Tworzy odpowiedni parser (parser odpowiedniego standardu) pliku IES.
     * Implementacja pewnie będzie wyglądała tak, że ta metoda będzie podglądać
     * pierwszą linijkę pliku i na jej podstawie decydować, jaki to format.
     *
     * @param filename
     *            nazwa pliku IES
     * @return parser
     */
    public IESParser newParser(String filename) throws IOException {
        Scanner input = new Scanner(new File(filename));

        if (input.hasNext("IESNA91")) {
            return new IES91Parser(input);
        } else if (input.hasNext("IESNA:LM-63-1995")) {
            return new IES95Parser(input);
        } else {
            return new IES86Parser(input);
        }
        // TODO add generic ies parser, that applies to all format - may not
        // parse 100% correctly
    }
}
