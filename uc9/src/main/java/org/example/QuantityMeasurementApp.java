package org.example;


public class QuantityMeasurementApp {
    private double value;
    private LengthUnit unit;

    public QuantityMeasurementApp(double value, LengthUnit unit){
        if(unit == null || !Double.isFinite(value) || Double.isNaN(value)){
            throw new IllegalArgumentException("Invalide input");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue(){
        return this.value;
    }

    public LengthUnit getUnit(){
        return this.unit;
    }

    public boolean equals(Object obj){
        if(obj == this) return true;

        if(obj == null) return false;

        if(getClass() == obj.getClass()){
            QuantityMeasurementApp other = (QuantityMeasurementApp)obj;

            double thisBase = this.unit.convertToBaseUnit(this.value);
            double otherBase = other.unit.convertToBaseUnit(other.value);

            return Double.compare(thisBase,otherBase) == 0;
        }

        return false;
    }

    public QuantityMeasurementApp convertTo(LengthUnit targetUnit){

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double thisToTarget = targetUnit.convertFromBaseUnit(thisBase);

        return new QuantityMeasurementApp(thisToTarget,targetUnit);
    }

    public QuantityMeasurementApp add(QuantityMeasurementApp thatLength){

        if(thatLength == null) throw new IllegalArgumentException("Null input");

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double thatBase = thatLength.unit.convertToBaseUnit(thatLength.value);

        double sum = thisBase + thatBase;

        double sumToBase = this.unit.convertFromBaseUnit(sum);

        return new QuantityMeasurementApp(sum,this.unit);

    }

    public QuantityMeasurementApp add(QuantityMeasurementApp thatLength, LengthUnit targetUnit){

        if(thatLength == null) throw new IllegalArgumentException("Null input");

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double thatBase = thatLength.unit.convertToBaseUnit(thatLength.value);

        double sum = thisBase + thatBase;

        double sumToTarget = targetUnit.convertFromBaseUnit(sum);

        return new QuantityMeasurementApp(sumToTarget,targetUnit);


    }




}