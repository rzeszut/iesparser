package org.iesparser.output;

import java.io.File;
import java.io.IOException;

import org.iesparser.data.PhotometricData;


/**
 *
 * @author mateusz
 *
 */
public interface Output {
    /**
     * Metoda dostaje dane i zapisuje je do pliku, w okreslonym formacie.
     *
     * @param data
     *            dane fotometryczne
     * @param file
     *            plik
     * @throws IOException
     */
    void saveToFile(PhotometricData data, File file) throws IOException;

    /**
     * Analogiczna metoda do tej powyzej.
     *
     * @param data
     *            dane
     * @param filename
     *            nazwa pliku
     * @throws IOException
     */
    void saveToFile(PhotometricData data, String filename) throws IOException;
}
