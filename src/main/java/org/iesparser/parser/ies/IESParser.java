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
 * Bazowy parser IES, zawiera większość metod, bo spora część się powtarza.
 *
 * @author mateusz
 *
 */
public abstract class IESParser implements Parser {
    private Scanner in;

    private int vert, hor;

    /**
     * Chroniony konstruktor, tworzenie obiektu tylko za pomocą
     * IESParserFactory.
     *
     * @param in Stream z plikiem IES.
     */
    protected IESParser(Scanner in) {
        this.in = in.useDelimiter("\r\n");
    }

    public PhotometricData parse() throws ParseException {
        PhotometricData data = new PhotometricData();

        try {
            parseKeywords(data);

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
        String line = in.next();
        Scanner lineParams = new Scanner(line);
        float[] numbers = new float[size];
        for (int i = 0; i < size; ++i) {
            numbers[i] = Float.valueOf(lineParams.next());
        }
        lineParams.close();
        return numbers;
    }

    protected void parseLine11(PhotometricData data) {
        String line = in.next();
        Scanner lineParams = new Scanner(line);
        data.setBallastData(Float.valueOf(lineParams.next()));
        data.setBallastLampPhotometricFactor(Float.valueOf(lineParams.next()));
        data.setInputWatts(Float.valueOf(lineParams.next()));
        lineParams.close();
    }

    protected void parseLine10(PhotometricData data) {
        String line = in.next();
        Scanner lineParams = new Scanner(line);
        data.setNumberOfLamps(Integer.valueOf(lineParams.next()));
        data.setLumensPerLamp(Float.valueOf(lineParams.next()));
        data.setCandelaMultiplier(Float.valueOf(lineParams.next()));
        vert = Integer.valueOf(lineParams.next());
        hor = Integer.valueOf(lineParams.next());
        data.setType(PhotometricType.fromInt(Integer.valueOf(lineParams.next())));
        data.setUnitsType(UnitsType.fromInt(Integer.valueOf(lineParams.next())));
        data.setWidth(Float.valueOf(lineParams.next()));
        data.setLength(Float.valueOf(lineParams.next()));
        data.setHeight(Float.valueOf(lineParams.next()));
        lineParams.close();
    }

    protected void parseTilt(PhotometricData data) throws ParseException {
        if (in.hasNext("TILT=.*")) {
            String line = in.next();
            String tilt = line.replaceAll("TILT=(.*)", "$1").trim();

            if (tilt.equals("NONE")) {
                return;
            } else if (tilt.equals("INCLUDE")) {
                data.setTiltData(parseTiltData(in));
            } else {
                parseTiltFile(data, tilt);
            }
        } else {
            throw new ParseException("TILT directive not found. IES file is invalid.");
        }
    }

    private void parseTiltFile(PhotometricData data, String tilt) throws ParseException {
        Scanner input = null;
        try {
            input = new Scanner(new File(tilt));
            data.setTiltData(parseTiltData(input));
        } catch (FileNotFoundException e) {
            throw new ParseException("Invalid TILT file specified.", e);
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    private TiltData parseTiltData(Scanner input) {
        TiltData data = new TiltData();

        data.setLampToLuminaire(Integer.valueOf(input.next().trim()));
        int pairs = Integer.valueOf(input.next().trim());

        data.setAngles(parseFloatList(pairs));
        data.setMultiplyingFactors(parseFloatList(pairs));

        return data;
    }

    protected abstract void parseKeywords(PhotometricData data) throws ParseException;

    protected Scanner getInput() {
        return in;
    }
}
