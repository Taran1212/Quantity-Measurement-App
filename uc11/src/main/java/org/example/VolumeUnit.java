package org.example;

public enum VolumeUnit implements IMeasurable{

    LITRE(1.0), MILLILITRE(1/1000.0), GALLON(3.78541);

    private final double conversionFactor;

    VolumeUnit(double value){
        this.conversionFactor = value;
    }

    public double getConversionFactor(){
        return this.conversionFactor;
    }

    public double convertToBaseUnit(double value){
        return value * this.conversionFactor;
    }

    public double convertFromBaseUnit(double value){
        return value / this.conversionFactor;
    }
}
