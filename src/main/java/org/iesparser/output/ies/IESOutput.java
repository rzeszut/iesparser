package org.iesparser.output.ies;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.iesparser.data.PhotometricData;
import org.iesparser.data.TiltData;
import org.iesparser.output.Output;
import org.iesparser.output.OutputException;

/**
 * Base IES output class.
 *
 * @author mateusz
 *
 */
public abstract class IESOutput extends Output {
    public static final char WORD_SEPARATOR = ' ';
    public static final int NUMBERS_LINE_MAX_LENGTH = 130;
    public static final int KEYWORDS_LINE_MAX_LENGTH = 80;
    public static final String LINE_SEPARATOR = "\r\n";

    public void saveToFile(PhotometricData data, File file) throws IOException,
            OutputException {
        try (PrintWriter out = new PrintWriter(file)) {
            writeIdentifierAndKeywords(out, data);

            writeTilt(out, data);

            writeLine10(out, data);
            writeLine11(out, data);

            writeFloatList(out, data.getHorizontalAngles());
            writeFloatList(out, data.getVerticalAngles());

            writeCandelaValues(out, data);
        }
    }

    protected void writeCandelaValues(PrintWriter out, PhotometricData data) {
        float[][] cand = data.getCandela();
        for (float[] horLines : cand) {
            writeFloatList(out, horLines);
        }
    }

    protected void writeFloatList(PrintWriter out, float[] horizontalAngles) {
        Float[] a = ArrayUtils.toObject(horizontalAngles);
        String str = StringUtils.join(a, WORD_SEPARATOR);
        out.print(wrapLine(str, NUMBERS_LINE_MAX_LENGTH));
    }

    protected void writeLine11(PrintWriter out, PhotometricData data) {
        String line11 = new StringBuilder().append(data.getBallastData())
                .append(WORD_SEPARATOR)
                .append(data.getBallastLampPhotometricFactor())
                .append(WORD_SEPARATOR).append(data.getInputWatts()).toString();
        out.print(wrapLine(line11, NUMBERS_LINE_MAX_LENGTH));
    }

    protected void writeLine10(PrintWriter out, PhotometricData data) {
        String line10 = new StringBuilder().append(data.getLumensPerLamp())
                .append(WORD_SEPARATOR).append(data.getCandelaMultiplier())
                .append(WORD_SEPARATOR).append(data.getVerticalAngles().length)
                .append(WORD_SEPARATOR)
                .append(data.getHorizontalAngles().length)
                .append(WORD_SEPARATOR).append(data.getType().getType())
                .append(WORD_SEPARATOR).append(data.getUnitsType().getType())
                .append(WORD_SEPARATOR).append(data.getWidth())
                .append(WORD_SEPARATOR).append(data.getLength())
                .append(WORD_SEPARATOR).append(data.getHeight()).toString();
        out.print(wrapLine(line10, NUMBERS_LINE_MAX_LENGTH));
    }

    protected void writeTilt(PrintWriter out, PhotometricData data) {
        TiltData tilt = data.getTiltData();
        if (tilt == null) {
            out.print("TILT=NONE" + LINE_SEPARATOR);
        } else {
            StringBuffer buf = new StringBuffer().append("TILT=INCLUDE")
                    .append(LINE_SEPARATOR).append(tilt.getLampToLuminaire())
                    .append(LINE_SEPARATOR).append(tilt.getAngles().length)
                    .append(LINE_SEPARATOR);
            out.print(buf.toString());
            writeFloatList(out, tilt.getAngles());
            writeFloatList(out, tilt.getMultiplyingFactors());
        }
    }

    /**
     * Method writes out the identifier (the first line of the IES fiel, ex.
     * IESNA:LM-63-1995. Identifier is not always present) and keywords/labels
     * (IES metadata) to the {@link PrintWriter} passed as parameter. When implementing
     * this method, you must manually add the "\r\n" line separator.
     *
     * @param out
     *            output
     * @param data
     *            an instance of {@link PhotometricData}
     * @throws OutputException
     *             some error happened during writing out an IES file
     */
    protected abstract void writeIdentifierAndKeywords(PrintWriter out,
            PhotometricData data) throws OutputException;

    protected String wrapLine(String line, int lineLength) {
        return WordUtils.wrap(line, lineLength, LINE_SEPARATOR, false)
                + LINE_SEPARATOR;
    }
}
