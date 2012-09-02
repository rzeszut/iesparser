package org.iesparser.parser.ies;


import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.Scanner;

import org.iesparser.data.PhotometricData;
import org.iesparser.data.PhotometricType;
import org.iesparser.data.UnitsType;
import org.iesparser.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

public class IESParserTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testParseTilt() {
        // TODO
    }

    @Test
    public void testParseTiltFails() {
        // given
        Scanner input = new Scanner("asdfasdfASDf\r\n");
        IES86Parser parser = new IES86Parser(input);
        PhotometricData data = new PhotometricData();
        ParseException exception = null;

        // when
        try {
            parser.parseTilt(data);
        } catch (ParseException e) {
            exception = e;
        }
        input.close();

        // then
        if (exception != null) {
            assertThat(exception.getMessage()).isEqualTo("TILT directive not found. IES file is invalid.");
        } else {
            fail();
        }
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
    public void parserFloatList() {
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

}
