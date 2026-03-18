package org.example;

public class QuantityMeasurementApp {
    private double length;
    private LengthUnit unit;



    public enum LengthUnit{
        FEET(1.0), INCH(1.0/12.0), YARDS(3.0), CENTIMETERS(0.0328084);

        private final double conversionFactor;

        LengthUnit(double conversionUnit){
            this.conversionFactor = conversionUnit;
        }

        public double getConversionFactor(){
            return this.conversionFactor;
        }
    }

    public QuantityMeasurementApp(double length, LengthUnit unit){
        if(unit == null || !Double.isFinite(this.length)){
            throw new IllegalArgumentException("Invalid input");
        }

        this.length = length;
        this.unit = unit;
    }

    public double getLenght(){
        return this.length;
    }

    public LengthUnit getUnit(){
        return this.unit;
    }

    public double convertToBaseUnit(){
        double conversionFactor = this.unit.getConversionFactor();

        return this.length * conversionFactor;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == this) return true;

        if(obj == null) return false;

        if(obj.getClass() == getClass()){
            QuantityMeasurementApp other = (QuantityMeasurementApp)obj;

            double otherToBaseUnit = other.convertToBaseUnit();
            double thisToBaseUnit = this.convertToBaseUnit();

            return Double.compare(otherToBaseUnit,thisToBaseUnit) == 0;
        }

        return false;

    }

    public QuantityMeasurementApp convertTo(LengthUnit target){

        double thisBase = this.convertToBaseUnit();
        double convertedValue = thisBase / target.getConversionFactor();

        return new QuantityMeasurementApp(convertedValue,target);
    }

    public QuantityMeasurementApp add(QuantityMeasurementApp thatUnit){

        if(thatUnit == null) throw new IllegalArgumentException("null input");
        double thisToBase = this.convertToBaseUnit();
        double otherToBase = thatUnit.convertToBaseUnit();

        double sum = thisToBase + otherToBase;

        double sumInThisUnit = sum / this.unit.getConversionFactor();

        return new QuantityMeasurementApp(sumInThisUnit, this.unit);
    }

    @Override
    public String toString(){
        return new String(this.length + " " + this.unit);
    }
}
