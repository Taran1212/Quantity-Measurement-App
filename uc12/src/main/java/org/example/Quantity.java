package org.example;

public class Quantity<U extends IMeasurable> {

    private double value;
    private U unit;

    public Quantity(double value, U unit) {
        if (unit == null || Double.isNaN(value) || !Double.isFinite(value)) {
            throw new IllegalArgumentException("invalid input");
        }

        this.unit = unit;
        this.value = value;
    }

    public U getUnit() {
        return this.unit;
    }

    public double getValue() {
        return this.value;
    }

    public double convertTo(U targetUnit) {
        double thisBase = this.unit.convertToBaseUnit(this.value);
        return targetUnit.convertFromBaseUnit(thisBase);
    }

    public Quantity<U> add(Quantity<U> other) {
        if (other == null) {
            throw new IllegalArgumentException("null input");
        }

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        double sum = thisBase + otherBase;
        double sumToTarget = this.unit.convertFromBaseUnit(sum);

        return new Quantity<>(sumToTarget, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        if (other == null || targetUnit == null)
            throw new IllegalArgumentException("null input");

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        double sum = thisBase + otherBase;
        double sumToTarget = targetUnit.convertFromBaseUnit(sum);

        return new Quantity<>(sumToTarget, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Quantity<?>)) return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (!this.unit.getClass().equals(other.unit.getClass())) {
            return false;
        }

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        return Double.compare(thisBase, otherBase) == 0;
    }

    @Override
    public int hashCode(){
        double base = this.unit.convertToBaseUnit(this.value);

        return Double.hashCode(base);

    }

    public Quantity<U> subtract(Quantity<U> obj){

        if(obj == null) throw new IllegalArgumentException("null input");

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double otherBase = obj.unit.convertToBaseUnit(obj.value);

        double sub = thisBase - otherBase;

        double subToTarget = this.unit.convertFromBaseUnit(sub);

        return new Quantity<>(subToTarget,this.unit);
        
    }

    public Quantity<U> subtract(Quantity<U> other, U target){

        if(other == null || target == null) throw new IllegalArgumentException("null input");

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        double sub = thisBase - otherBase;

        double subToTarget = target.convertFromBaseUnit(sub);

        return new Quantity<U>(subToTarget,target);
    }

    public Quantity<U> divide (Quantity<U> obj){
        if(obj == null) throw new IllegalArgumentException("null input");

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double otherBase = obj.unit.convertToBaseUnit(obj.value);

        double div = thisBase/otherBase;



        return new Quantity<U>(div,this.unit);

    }


}