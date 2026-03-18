package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;
    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(12.0, QuantityMeasurementApp.LengthUnit.INCH);
        QuantityMeasurementApp result = q1.add(q2, QuantityMeasurementApp.LengthUnit.FEET);
        assertEquals(2.0, result.getLenght(), EPSILON);
        assertEquals(QuantityMeasurementApp.LengthUnit.FEET, result.getUnit());
    }

   
    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(12.0, QuantityMeasurementApp.LengthUnit.INCH);
        QuantityMeasurementApp result = q1.add(q2, QuantityMeasurementApp.LengthUnit.INCH);
        assertEquals(24.0, result.getLenght(), EPSILON);
        assertEquals(QuantityMeasurementApp.LengthUnit.INCH, result.getUnit());
    }


    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(12.0, QuantityMeasurementApp.LengthUnit.INCH);
        QuantityMeasurementApp result = q1.add(q2, QuantityMeasurementApp.LengthUnit.YARDS);
        assertEquals(2.0 / 3.0, result.getLenght(), EPSILON); // ~0.667 yards
        assertEquals(QuantityMeasurementApp.LengthUnit.YARDS, result.getUnit());
    }


    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(1.0, QuantityMeasurementApp.LengthUnit.INCH);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(1.0, QuantityMeasurementApp.LengthUnit.INCH);
        QuantityMeasurementApp result = q1.add(q2, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        assertEquals(2.0 * 2.54, result.getLenght(), EPSILON); // 2 inches = 5.08 cm
        assertEquals(QuantityMeasurementApp.LengthUnit.CENTIMETERS, result.getUnit());
    }


    @Test
    void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(2.0, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(3.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp result = q1.add(q2, QuantityMeasurementApp.LengthUnit.YARDS);
        assertEquals(3.0, result.getLenght(), EPSILON);
        assertEquals(QuantityMeasurementApp.LengthUnit.YARDS, result.getUnit());
    }


    @Test
    void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(2.0, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(3.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp result = q1.add(q2, QuantityMeasurementApp.LengthUnit.FEET);
        assertEquals(9.0, result.getLenght(), EPSILON);
        assertEquals(QuantityMeasurementApp.LengthUnit.FEET, result.getUnit());
    }


    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(12.0, QuantityMeasurementApp.LengthUnit.INCH);
        QuantityMeasurementApp r1 = q1.add(q2, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp r2 = q2.add(q1, QuantityMeasurementApp.LengthUnit.YARDS);
        assertEquals(r1.getLenght(), r2.getLenght(), EPSILON);
        assertEquals(QuantityMeasurementApp.LengthUnit.YARDS, r1.getUnit());
    }


    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(5.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(0.0, QuantityMeasurementApp.LengthUnit.INCH);
        QuantityMeasurementApp result = q1.add(q2, QuantityMeasurementApp.LengthUnit.YARDS);
        assertEquals(5.0 / 3.0, result.getLenght(), EPSILON); // 1.6667 yards
        assertEquals(QuantityMeasurementApp.LengthUnit.YARDS, result.getUnit());
    }


    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(5.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(-2.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp result = q1.add(q2, QuantityMeasurementApp.LengthUnit.INCH);
        assertEquals(36.0, result.getLenght(), EPSILON); // 3 feet = 36 inches
        assertEquals(QuantityMeasurementApp.LengthUnit.INCH, result.getUnit());
    }


    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(12.0, QuantityMeasurementApp.LengthUnit.INCH);
        assertThrows(IllegalArgumentException.class, () -> q1.add(q2, null));
    }


    @Test
    void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(1000.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(500.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp result = q1.add(q2, QuantityMeasurementApp.LengthUnit.INCH);
        assertEquals(18000.0, result.getLenght(), EPSILON);
        assertEquals(QuantityMeasurementApp.LengthUnit.INCH, result.getUnit());
    }


    @Test
    void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(12.0, QuantityMeasurementApp.LengthUnit.INCH);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(12.0, QuantityMeasurementApp.LengthUnit.INCH);
        QuantityMeasurementApp result = q1.add(q2, QuantityMeasurementApp.LengthUnit.YARDS);
        assertEquals(2.0 / 3.0, result.getLenght(), EPSILON); // 0.667 yards
        assertEquals(QuantityMeasurementApp.LengthUnit.YARDS, result.getUnit());
    }

}