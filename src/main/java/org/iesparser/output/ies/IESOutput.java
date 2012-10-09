package org.iesparser.output.ies;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.iesparser.data.PhotometricData;
import org.iesparser.data.TiltData;
import org.iesparser.output.Output;

public abstract class IESOutput extends Output {

    public void saveToFile(PhotometricData data, File file) throws IOException {
        try (PrintWriter out = new PrintWriter(file)) {
            // TODO "\r\n"
            // TODO test
            writeKeywords(out, data);

            writeTilt(out, data);

            writeLine10(out, data);
            writeLine11(out, data);

            writeFloatList(out, data.getHorizontalAngles());
            writeFloatList(out, data.getVerticalAngles());

            writeCandelaValues(out, data);
        }
    }

    void writeCandelaValues(PrintWriter out, PhotometricData data) {
        float[][] cand = data.getCandela();
        for (float[] horLines : cand) {
            writeFloatList(out, horLines);
        }
    }

    void writeFloatList(PrintWriter out, float[] horizontalAngles) {
        Float[] a = ArrayUtils.toObject(horizontalAngles);
        String str = StringUtils.join(a, ' ');
        out.println(str);
    }

    void writeLine11(PrintWriter out, PhotometricData data) {
        String line11 = new StringBuilder()
                .append(data.getBallastData()).append(' ')
                .append(data.getBallastLampPhotometricFactor()).append(' ')
                .append(data.getInputWatts()).toString();
        out.println(line11);
    }

    void writeLine10(PrintWriter out, PhotometricData data) {
        String line10 = new StringBuilder()
                .append(data.getLumensPerLamp()).append(' ')
                .append(data.getCandelaMultiplier()).append(' ')
                .append(data.getVerticalAngles().length).append(' ')
                .append(data.getHorizontalAngles().length).append(' ')
                .append(data.getType().getType()).append(' ')
                .append(data.getUnitsType().getType()).append(' ')
                .append(data.getWidth()).append(' ')
                .append(data.getLength()).append(' ')
                .append(data.getHeight()).toString();
        out.println(line10);
    }

    void writeTilt(PrintWriter out, PhotometricData data) {
        TiltData tilt = data.getTiltData();
        if (tilt == null) {
            out.println("TILT=NONE");
        } else {
            out.println("TILT=INCLUDE");
            out.println(tilt.getLampToLuminaire());
            out.println(tilt.getAngles().length);
            writeFloatList(out, tilt.getAngles());
            writeFloatList(out, tilt.getMultiplyingFactors());
        }
    }

    protected abstract void writeKeywords(PrintWriter out, PhotometricData data);

}
