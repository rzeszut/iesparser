package org.iesparser.data;

import java.io.Serializable;

import javax.annotation.Nullable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Klasa, której zadaniem jest przechowywanie danych fotometrycznych wczytanych
 * z pliku IES (albo jakiegoś innego formatu). Coś tutaj jeszcze będzie w
 * środku. To się w ogóle może zmienić na interfejs, czy coś - zależy od
 * formatów, które będzie ten parser obsługiwał, i takich tam.
 *
 * @author mateusz
 */
public class PhotometricData implements Serializable {
    private static final long serialVersionUID = 2352305742920985366L;

    @Nullable
    private TiltData tiltData;

    // line 10 data
    private int numberOfLamps;
    private float lumensPerLamp;
    private float candelaMultiplier;
    private PhotometricType type;
    private UnitsType unitsType;
    private float width;
    private float length;
    private float height;

    // line 11 data
    private float ballastData;
    private float ballastLampPhotometricFactor;
    private float inputWatts;

    private float[] verticalAngles;
    private float[] horizontalAngles;

    private float[][] candela;


    public TiltData getTiltData() {
        return tiltData;
    }

    public void setTiltData(TiltData tiltData) {
        this.tiltData = tiltData;
    }

    public int getNumberOfLamps() {
        return numberOfLamps;
    }

    public void setNumberOfLamps(int numberOfLamps) {
        this.numberOfLamps = numberOfLamps;
    }

    public float getLumensPerLamp() {
        return lumensPerLamp;
    }

    public void setLumensPerLamp(float lumensPerLamp) {
        this.lumensPerLamp = lumensPerLamp;
    }

    public float getCandelaMultiplier() {
        return candelaMultiplier;
    }

    public void setCandelaMultiplier(float candelaMultiplier) {
        this.candelaMultiplier = candelaMultiplier;
    }

    public PhotometricType getType() {
        return type;
    }

    public void setType(PhotometricType type) {
        this.type = type;
    }

    public UnitsType getUnitsType() {
        return unitsType;
    }

    public void setUnitsType(UnitsType unitsType) {
        this.unitsType = unitsType;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getBallastData() {
        return ballastData;
    }

    public void setBallastData(float ballastData) {
        this.ballastData = ballastData;
    }

    public float getBallastLampPhotometricFactor() {
        return ballastLampPhotometricFactor;
    }

    public void setBallastLampPhotometricFactor(float ballastLampPhotometricFactor) {
        this.ballastLampPhotometricFactor = ballastLampPhotometricFactor;
    }

    public float getInputWatts() {
        return inputWatts;
    }

    public void setInputWatts(float inputWatts) {
        this.inputWatts = inputWatts;
    }

    public float[] getVerticalAngles() {
        return verticalAngles;
    }

    public void setVerticalAngles(float[] verticalAngles) {
        this.verticalAngles = verticalAngles;
    }

    public float[] getHorizontalAngles() {
        return horizontalAngles;
    }

    public void setHorizontalAngles(float[] horizontalAngles) {
        this.horizontalAngles = horizontalAngles;
    }

    public float[][] getCandela() {
        return candela;
    }

    public void setCandela(float[][] candela) {
        this.candela = candela;
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
