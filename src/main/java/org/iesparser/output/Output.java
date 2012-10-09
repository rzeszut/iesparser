package org.iesparser.output;

import java.io.File;
import java.io.IOException;

import org.iesparser.data.PhotometricData;

/**
 *
 * @author mateusz
 *
 */
public abstract class Output {
    /**
     * Saves the passed {@link PhotometricData} instance to a file.
     *
     * @param data
     *            ans instance of {@link PhotometricData}
     * @param filename
     *            filename
     * @throws IOException
     *             filesystem exception
     * @throws OutputException
     *             invalid {@link PhotometricData} instance passed
     */
    public abstract void saveToFile(PhotometricData data, File file)
            throws IOException, OutputException;

    /**
     * The same as the one up.
     *
     * @param data
     *            ans instance of {@link PhotometricData}
     * @param filename
     *            filename
     * @throws IOException
     *             filesystem exception
     * @throws OutputException
     *             invalid {@link PhotometricData} instance passed
     */
    public void saveToFile(PhotometricData data, String filename)
            throws IOException, OutputException {
        saveToFile(data, new File(filename));
    }
}
