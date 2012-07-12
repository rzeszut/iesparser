/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.File;

/**
 * Klasa, której zadaniem jest przechowywanie danych fotometrycznych wczytanych
 * z pliku IES (albo jakiegoś innego formatu). Coś tutaj jeszcze będzie w
 * środku. To się w ogóle może zmienić na interfejs, czy coś - zależy od
 * formatów, które będzie ten parser obsługiwał, i takich tam.
 *
 * @author mateusz
 */
public class PhotometricData {

    /**
     * Domyślny konstruktor.
     */
    public PhotometricData() {
    }
    // dane

    /**
     * Metoda zapisuje dane fotometryczne do pliku XML. Na razie wrzuciłem ją do
     * tej klasy, bo nie miałem pomysłu gdzie to wsadzić, ale później pewnie
     * stąd wyleci w jakieś sensowniejsze miejsce.
     *
     * @param file plik, do którego zapisuje dane
     */
    public void saveToXMLFile(File file) {
        // TODO
    }
}
