package org.example;

public enum LengthUnit implements IMeasurable{
    FEET(1.0),INCHES(1/12.0),YARDS(3.0),CENTIMETERS(0.0328084);

    private final double converstionFactor;

    LengthUnit(double conversionFactor){
        this.converstionFactor = conversionFactor;
    }

    public double getConversionFactor(){
        return this.converstionFactor;
    }

    public double convertToBaseUnit(double value){
        return this.converstionFactor * value;
    }

    public double convertFromBaseUnit(double value){
        return value / converstionFactor;
    }
}