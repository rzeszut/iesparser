package org.iesparser.data;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class TiltData implements Serializable {
    private static final long serialVersionUID = -681414208602290952L;

    private int lampToLuminaire;
    private float[] angles;
    private float[] multiplyingFactors;


    public int getLampToLuminaire() {
        return lampToLuminaire;
    }

    public void setLampToLuminaire(int lampToLuminaire) {
        this.lampToLuminaire = lampToLuminaire;
    }

    public float[] getAngles() {
        return angles;
    }

    public void setAngles(float[] angles) {
        this.angles = angles;
    }

    public float[] getMultiplyingFactors() {
        return multiplyingFactors;
    }

    public void setMultiplyingFactors(float[] multiplyingFactors) {
        this.multiplyingFactors = multiplyingFactors;
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
