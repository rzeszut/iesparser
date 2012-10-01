package org.iesparser.parser.ies;


import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.util.Scanner;

import org.iesparser.data.PhotometricData;
import org.iesparser.data.PhotometricType;
import org.iesparser.data.TiltData;
import org.iesparser.data.UnitsType;
import org.iesparser.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

public class IESParserTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test(expected=ParseException.class)
    public void testParseTilt_fileDoesNotExist() throws ParseException {
        // given
        Scanner input = new Scanner("TILT=somefilethatdoesntexist.txt");
        IES86Parser parser = new IES86Parser(input);
        PhotometricData data = new PhotometricData();

        // when
        try {
            parser.parseTilt(data);
        } finally {
            input.close();
        }

        // then
        // file with that name doesn't exist - an exception is throwm
    }

    @Test
    public void testParseTilt_none() throws ParseException {
        // given
        Scanner input = new Scanner("TILT=NONE");
        IES86Parser parser = new IES86Parser(input);
        PhotometricData data = new PhotometricData();

        // when
        try {
            parser.parseTilt(data);
        } finally {
            input.close();
        }

        // then
        assertNull(data.getTiltData());
    }

    @Test(expected=ParseException.class)
    public void testParseTilt_fails() throws ParseException {
        // given
        Scanner input = new Scanner("asdfasdfASDf\r\n");
        IES86Parser parser = new IES86Parser(input);
        PhotometricData data = new PhotometricData();

        // when
        try {
            parser.parseTilt(data);
        } finally {
            input.close();
        }

        // then
        // invalid input - an exception is thrown
    }

    @Test
    public void testParseTiltData() {
        // given
        StringBuilder buf = new StringBuilder();
        buf.append("1\r\n");
        buf.append("5\r\n");
        buf.append("0 90 180 270 360\r\n");
        buf.append("1 .95 .9 .95 1\r\n");
        Scanner input = new Scanner(buf.toString());
        IES86Parser parser = new IES86Parser(input);

        // when
        TiltData data = parser.parseTiltData(input);

        // then
        assertThat(data.getLampToLuminaire()).isEqualTo(1);
        assertThat(data.getAngles()).isEqualTo(new float[] {0, 90, 180, 270, 360});
        assertThat(data.getMultiplyingFactors()).isEqualTo(new float[] {1, .95f, .9f, .95f, 1});
    }

    @Test
    public void testParseLine10() {
        // given
        Scanner input = new Scanner("1 50000 1 5 3 1 1 .3 .4 .5\r\n");
        IES86Parser parser = new IES86Parser(input); // na potrzeby tego testu równie dobrze może być 91 czy 95
        PhotometricData data = new PhotometricData();

        // when
        parser.parseLine10(data);
        input.close();

        // then
        assertThat(data.getNumberOfLamps()).isEqualTo(1);
        assertThat(data.getLumensPerLamp()).isEqualTo(50000f);
        assertThat(data.getCandelaMultiplier()).isEqualTo(1f);
        assertThat(data.getType()).isEqualTo(PhotometricType.C);
        assertThat(data.getUnitsType()).isEqualTo(UnitsType.FEET);
        assertThat(data.getWidth()).isEqualTo(.3f);
        assertThat(data.getLength()).isEqualTo(.4f);
        assertThat(data.getHeight()).isEqualTo(.5f);
    }

    @Test
    public void testParseLine11() {
        // given
        Scanner input = new Scanner("1.0 1.5 500\r\n");
        IES86Parser parser = new IES86Parser(input); // na potrzeby tego testu równie dobrze może być 91 czy 95
        PhotometricData data = new PhotometricData();

        // when
        parser.parseLine11(data);
        input.close();

        // then
        assertThat(data.getBallastData()).isEqualTo(1f);
        assertThat(data.getBallastLampPhotometricFactor()).isEqualTo(1.5f);
        assertThat(data.getInputWatts()).isEqualTo(500f);
    }

    @Test
    public void testParseFloatList() {
        // given
        Scanner input = new Scanner("0 .5 1 1.5 2 2.5\r\n");
        IES86Parser parser = new IES86Parser(input); // na potrzeby tego testu równie dobrze może być 91 czy 95

        // when
        float[] ret = parser.parseFloatList(6);
        input.close();

        // then
        for (int i = 0; i < 6; ++i) {
            assertThat(ret[i]).isEqualTo(0.5f * i);
        }
    }

    @Test
    public void testParseFloatList_multiLine() {
        // given
        Scanner input = new Scanner("0 .5 1 1.5 2 2.5\r\n3.0 3.5\r\n");
        IES86Parser parser = new IES86Parser(input); // na potrzeby tego testu równie dobrze może być 91 czy 95

        // when
        float[] ret = parser.parseFloatList(8);
        input.close();

        // then
        for (int i = 0; i < 8; ++i) {
            assertThat(ret[i]).isEqualTo(0.5f * i);
        }
    }

    @Test
    public void testParseCandelaValues() {
        // given
        StringBuilder buffer = new StringBuilder();
        buffer.append("0 0 0 3 3 1 1 0 0 0\r\n");
        buffer.append("1 2 3\r\n");
        buffer.append("4 5 6\r\n");
        buffer.append("7 8 9\r\n");
        Scanner input = new Scanner(buffer.toString());
        IES86Parser parser = new IES86Parser(input);
        PhotometricData data = new PhotometricData();
        parser.parseLine10(data);

        // when
        parser.parseCandelaValues(data);

        // then
        assertThat(data.getCandela()).isEqualTo(new float[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    }

}
