package org.iesparser.parser.ies;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.iesparser.data.PhotometricData;
import org.iesparser.data.PhotometricType;
import org.iesparser.data.TiltData;
import org.iesparser.data.UnitsType;
import org.iesparser.parser.ParseException;
import org.iesparser.parser.Parser;

/**
 * Base IES parser - a lot of funcitonality is being repeated.
 *
 * @author mateusz
 *
 */
public abstract class IESParser implements Parser {
    private Scanner in;

    private int vert, hor;

    /**
     * Instantiating a parser is possible only through IESParserFactory.
     *
     * @param in
     *            IES file stream.
     */
    protected IESParser(Scanner in) {
        this.in = in;
    }

    public PhotometricData parse() throws ParseException {
        PhotometricData data = new PhotometricData();

        try {
            parseIdentifierAndKeywords(data);

            parseTilt(data);

            parseLine10(data);
            parseLine11(data);

            data.setVerticalAngles(parseFloatList(vert));
            data.setHorizontalAngles(parseFloatList(hor));

            parseCandelaValues(data);
        } catch (NoSuchElementException e) {
            throw new ParseException("An error occured.", e);
        } catch (NumberFormatException e) {
            throw new ParseException("Wrong number format.", e);
        } finally {
            if (in != null) {
                in.close();
            }
        }

        return data;
    }

    protected void parseCandelaValues(PhotometricData data) {
        float[][] candela = new float[hor][];
        for (int i = 0; i < hor; ++i) {
            candela[i] = parseFloatList(vert);
        }
        data.setCandela(candela);
    }

    protected float[] parseFloatList(int size) {
        float[] numbers = new float[size];
        for (int i = 0; i < size; ++i) {
            numbers[i] = Float.valueOf(in.next());
        }
        return numbers;
    }

    protected void parseLine11(PhotometricData data) {
        data.setBallastData(Float.valueOf(in.next()));
        data.setBallastLampPhotometricFactor(Float.valueOf(in.next()));
        data.setInputWatts(Float.valueOf(in.next()));
    }

    protected void parseLine10(PhotometricData data) {
        data.setNumberOfLamps(Integer.valueOf(in.next()));
        data.setLumensPerLamp(Float.valueOf(in.next()));
        data.setCandelaMultiplier(Float.valueOf(in.next()));
        vert = Integer.valueOf(in.next());
        hor = Integer.valueOf(in.next());
        data.setType(PhotometricType.fromInt(Integer.valueOf(in.next())));
        data.setUnitsType(UnitsType.fromInt(Integer.valueOf(in.next())));
        data.setWidth(Float.valueOf(in.next()));
        data.setLength(Float.valueOf(in.next()));
        data.setHeight(Float.valueOf(in.next()));
    }

    protected void parseTilt(PhotometricData data) throws ParseException {
        if (in.hasNext("TILT=.*")) {
            String line = in.nextLine();
            String tilt = line.replaceAll("TILT=(.*)", "$1").trim();

            if (tilt.equals("NONE")) {
                return;
            } else if (tilt.equals("INCLUDE")) {
                data.setTiltData(parseTiltData(in));
            } else {
                data.setTiltData(parseTiltFile(tilt));
            }
        } else {
            throw new ParseException(
                    "TILT directive not found. IES file is invalid.");
        }
    }

    protected TiltData parseTiltFile(String tilt) throws ParseException {
        // behold, new Java 7 feature: auto-closing resources
        // present in Lisp since ancient times
        try (Scanner input = new Scanner(new File(tilt))) {
            return parseTiltData(input);
        } catch (FileNotFoundException e) {
            throw new ParseException("Invalid TILT file specified.", e);
        }
    }

    protected TiltData parseTiltData(Scanner input) {
        TiltData data = new TiltData();

        data.setLampToLuminaire(Integer.valueOf(input.next().trim()));
        int pairs = Integer.valueOf(input.next().trim());

        data.setAngles(parseFloatList(pairs));
        data.setMultiplyingFactors(parseFloatList(pairs));

        return data;
    }

    /**
     * Method parses the idetifier (the first line of the IES fiel, ex.
     * IESNA:LM-63-1995. Identifier is not always present) and keywords/labels
     * (IES metadata).
     *
     * @param data
     *            an instance of {@link PhotometricData}
     * @throws ParseException
     *             some error happened during parsing of the keywords
     */
    protected abstract void parseIdentifierAndKeywords(PhotometricData data)
            throws ParseException;

    protected Scanner getInput() {
        return in;
    }
}
