package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    public void testFeetEquality(){

        QuantityMeasurementApp q1 = new QuantityMeasurementApp(1.0,QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(2.0,QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp result = q1.add(q2);

        assertEquals(3.0,result.getLenght());
    }

    @Test
    public void testInchesEquality(){

        QuantityMeasurementApp q1 = new QuantityMeasurementApp(6.0, QuantityMeasurementApp.LengthUnit.INCH);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(6.0, QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp result = q1.add(q2);

        assertEquals(12.0,result.getLenght());
    }

    @Test
    public void testFeetInchesComparison(){
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(1.0,QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp result = q1.add(q2);

        assertEquals(2.0,result.getLenght());
        assertEquals(QuantityMeasurementApp.LengthUnit.FEET,result.getUnit());
    }

    @Test
    public void testFeetInequality(){
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(12.0,QuantityMeasurementApp.LengthUnit.INCH);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(1.0,QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp result = q1.add(q2);

        assertEquals(24.0,result.getLenght());

    }

    @Test
    public void testInchesInequality(){
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(3.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp result = q1.add(q2);

        System.out.println(result.getLenght());
        assertEquals(2.0,result.getLenght());

    }

    @Test
    public void testCrossUnitInEquality(){
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(2.54,QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(1.0,QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp result = q1.add(q2);


        assertEquals(5.079999918720003,result.getLenght());
    }

    @Test
    public void testMultipleFeetComparison(){
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp result = q1.add(q2);

        QuantityMeasurementApp q3 = new QuantityMeasurementApp(12.0, QuantityMeasurementApp.LengthUnit.INCH);
        QuantityMeasurementApp q4 = new QuantityMeasurementApp(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp r2 = q3.add(q4);

        assertEquals(result.convertToBaseUnit(),r2.convertToBaseUnit());
    }

    @Test
    public void test_Addition_Withzero(){
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(5.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(0.0, QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp result = q1.add(q2);

        assertEquals(5.0,result.getLenght());
    }

    @Test
    public void testAddition_NegativeValues(){
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(5.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(-3.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp result = q1.add(q2);

        assertEquals(2.0,result.getLenght());
    }

    @Test
    public void testAddition_NullSecondOperand(){
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,() -> {
            QuantityMeasurementApp result = q1.add(null);
        });
    }

    @Test
    public void testAddition_LargeValue(){
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(1e6, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(1e6, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp result = q1.add(q2);

        assertEquals(2e6,result.getLenght());
    }

    private static final double EPSILON = 1e-6;

    @Test
    public void testAddition_smallValues(){
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(0.0001, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(0.0002, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp result = q1.add(q2);

        assertEquals(0.0003,result.getLenght(),EPSILON);
    }
}