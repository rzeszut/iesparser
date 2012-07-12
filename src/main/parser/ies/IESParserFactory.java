/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser.ies;

import java.io.IOException;
import parser.Parser;
import parser.ParserFactory;

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
     * @param filename nazwa pliku IES
     * @return parser
     */
    @Override
    public Parser newParser(String filename)  throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
