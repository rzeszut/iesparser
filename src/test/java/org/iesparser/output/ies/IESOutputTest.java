package org.iesparser.output.ies;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.PrintWriter;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.iesparser.data.PhotometricData;
import org.iesparser.data.PhotometricType;
import org.iesparser.data.TiltData;
import org.iesparser.data.UnitsType;
import org.junit.Before;
import org.junit.Test;

public class IESOutputTest {
    private IESOutput output;

    @Before
    public void setUp() throws Exception {
        output = new IESOutput() {

            @Override
            protected void writeIdentifierAndKeywords(PrintWriter out, PhotometricData data) {
            }
        };
    }

    @Test
    public void testWriteCandelaValues() {
        // given
        PrintWriter mockWriter = mock(PrintWriter.class);
        PhotometricData data = new PhotometricData();
        float[][] candela = new float[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        data.setCandela(candela);

        // when
        output.writeCandelaValues(mockWriter, data);

        // then
        verify(mockWriter).print("1.0 2.0 3.0\r\n");
        verify(mockWriter).print("4.0 5.0 6.0\r\n");
        verify(mockWriter).print("7.0 8.0 9.0\r\n");
    }

    @Test
    public void testWriteFloatList() {
        // given
        PrintWriter mockWriter = mock(PrintWriter.class);
        float[] arr = new float[] {0, 0.5f, 1, 1.5f, 2, 2.5f};
        String str = StringUtils.join(ArrayUtils.toObject(arr), ' ') + "\r\n";

        // when
        output.writeFloatList(mockWriter, arr);

        // then
        verify(mockWriter).print(str);
    }

    @Test
    public void testWriteLine11() {
        // given
        PrintWriter mockWriter = mock(PrintWriter.class);
        PhotometricData data = new PhotometricData();
        data.setBallastData(1);
        data.setBallastLampPhotometricFactor(2);
        data.setInputWatts(3);

        // when
        output.writeLine11(mockWriter, data);

        // then
        verify(mockWriter).print("1.0 2.0 3.0\r\n");
    }

    @Test
    public void testWriteLine10() {
        // given
        PrintWriter mockWriter = mock(PrintWriter.class);
        PhotometricData data = new PhotometricData();
        data.setLumensPerLamp(1f);
        data.setCandelaMultiplier(1f);
        data.setHorizontalAngles(new float[] {1f, 2f});
        data.setVerticalAngles(new float[] {1f, 2f});
        data.setType(PhotometricType.A);
        data.setUnitsType(UnitsType.FEET);
        data.setWidth(1f);
        data.setLength(2f);
        data.setHeight(2f);

        // when
        output.writeLine10(mockWriter, data);

        // then
        verify(mockWriter).print("1.0 1.0 2 2 3 1 1.0 2.0 2.0\r\n");
    }

    @Test
    public void testWriteTilt_none() {
        // given
        PrintWriter mockWriter = mock(PrintWriter.class);
        PhotometricData data = new PhotometricData();

        // when
        output.writeTilt(mockWriter, data);

        // then
        verify(mockWriter).print("TILT=NONE\r\n");
    }

    @Test
    public void testWriteTilt_included() {
        // given
        PrintWriter mockWriter = mock(PrintWriter.class);
        PhotometricData data = new PhotometricData();
        TiltData tilt = new TiltData();
        data.setTiltData(tilt);
        tilt.setLampToLuminaire(1);
        float[] angles = new float[] {1, 2};
        tilt.setAngles(angles);
        float[] factors = new float[] {3, 4};
        tilt.setMultiplyingFactors(factors);

        // when
        output.writeTilt(mockWriter, data);

        // then
        verify(mockWriter).print("TILT=INCLUDE\r\n1\r\n2\r\n");
        verify(mockWriter).print("1.0 2.0\r\n");
        verify(mockWriter).print("3.0 4.0\r\n");
    }

    @Test
    public void testWrapLine_wrapsLinesWithCorrectDelimiter() {
        // given
        String line = "some line";

        // when
        String out = output.wrapLine(line, 6);

        // then
        assertEquals("some\r\nline\r\n", out);
    }

    @Test
    public void testWrapLine_addSeparator() {
        // given
        String line = "some line";

        // when
        String out = output.wrapLine(line, 50);

        // then
        assertEquals("some line\r\n", out);
    }

}
