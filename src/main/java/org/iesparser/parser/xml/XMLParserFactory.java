/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesparser.parser.xml;

import java.io.IOException;

import org.iesparser.parser.Parser;
import org.iesparser.parser.ParserFactory;

/**
 * Fabryka tworząca parser XMLa. Tworzenie parsera jest dość proste, jak widać.
 *
 * @author mateusz
 */
public class XMLParserFactory implements ParserFactory {

    public Parser newParser(String filename) throws IOException {
        return new XMLParser(filename);
    }
}
