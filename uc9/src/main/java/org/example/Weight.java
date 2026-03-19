package org.example;

public class Weight {
    private double value;
    private WeightUnit unit;

    public Weight(double value, WeightUnit unit){
        if(unit == null || Double.isNaN(value) || !Double.isFinite(value)) throw new IllegalArgumentException("Invalid input");

        this.unit = unit;
        this.value = value;
    }

    public double getValue(){
        return this.value;
    }

    public WeightUnit getUnit(){
        return this.unit;
    }

    public boolean equals(Object obj){
        if(obj == this) return true;

        if(obj == null) return false;

        if(obj.getClass() == getClass()){

            Weight other = (Weight)obj;
            double thisBase = this.unit.convertToBaseUnit(this.value);
            double otherBase = other.unit.convertToBaseUnit(other.value);

            return Double.compare(thisBase,otherBase) == 0;
        }

        return false;
    }

    public Weight convertTo(WeightUnit targetUnit){

        double thisBase = this.unit.convertToBaseUnit(this.value);
       double thisToTarget = targetUnit.convertFromBaseUnit(thisBase);
        return new Weight(thisToTarget,targetUnit);
    }

    public Weight add(Weight w1){

        if(w1 == null) throw new IllegalArgumentException("null input");
        double thisBase = this.unit.convertToBaseUnit(this.value);
        double w1Base = w1.unit.convertToBaseUnit(w1.value);

        double sum = thisBase + w1Base;

        double sumToThis = this.unit.convertFromBaseUnit(sum);

        return new Weight(sumToThis,this.unit);
    }

    public Weight add(Weight w1, WeightUnit targetUnit){
        if(targetUnit == null || w1 == null) throw new IllegalArgumentException("null input");

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double w1Base = w1.unit.convertToBaseUnit(w1.value);

        double sum = thisBase + w1Base;

        double sumToTarget = targetUnit.convertFromBaseUnit(sum);

        return new Weight(sumToTarget,targetUnit);
    }
}
