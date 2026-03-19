package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.0001;

    @Test
    void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor(), EPSILON);
    }

    @Test
    void testLengthUnitEnum_InchesConstant() {
        assertEquals(1.0 / 12.0, LengthUnit.INCHES.getConversionFactor(), EPSILON);
    }

    @Test
    void testLengthUnitEnum_YardsConstant() {
        assertEquals(3.0, LengthUnit.YARDS.getConversionFactor(), EPSILON);
    }

    @Test
    void testLengthUnitEnum_CentimetersConstant() {
        assertEquals(1.0 / 30.48, LengthUnit.CENTIMETERS.getConversionFactor(), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_FeetToFeet() {
        assertEquals(5.0, LengthUnit.FEET.convertToBaseUnit(5.0), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0, LengthUnit.INCHES.convertToBaseUnit(12.0), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_YardsToFeet() {
        assertEquals(3.0, LengthUnit.YARDS.convertToBaseUnit(1.0), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_CentimetersToFeet() {
        assertEquals(1.0, LengthUnit.CENTIMETERS.convertToBaseUnit(30.48), EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToFeet() {
        assertEquals(2.0, LengthUnit.FEET.convertFromBaseUnit(2.0), EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1.0), EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToYards() {
        assertEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(3.0), EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToCentimeters() {
        assertEquals(30.48, LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0), EPSILON);
    }





        @Test
        void testQuantityLengthRefactored_Equality() {
            QuantityMeasurementApp q1 = new QuantityMeasurementApp(1.0, LengthUnit.FEET);
            QuantityMeasurementApp q2 = new QuantityMeasurementApp(12.0, LengthUnit.INCHES);

            assertEquals(q1, q2);
        }

        @Test
        void testQuantityLengthRefactored_ConvertTo() {
            QuantityMeasurementApp q = new QuantityMeasurementApp(1.0, LengthUnit.FEET);
            QuantityMeasurementApp result = q.convertTo(LengthUnit.INCHES);

            assertEquals(new QuantityMeasurementApp(12.0, LengthUnit.INCHES), result);
        }

        @Test
        void testQuantityLengthRefactored_Add() {
            QuantityMeasurementApp q1 = new QuantityMeasurementApp(1.0, LengthUnit.FEET);
            QuantityMeasurementApp q2 = new QuantityMeasurementApp(12.0, LengthUnit.INCHES);

            QuantityMeasurementApp result = q1.add(q2, LengthUnit.FEET);

            assertEquals(new QuantityMeasurementApp(2.0, LengthUnit.FEET), result);
        }

        @Test
        void testQuantityLengthRefactored_AddWithTargetUnit() {
            QuantityMeasurementApp q1 = new QuantityMeasurementApp(1.0, LengthUnit.FEET);
            QuantityMeasurementApp q2 = new QuantityMeasurementApp(12.0, LengthUnit.INCHES);

            QuantityMeasurementApp result = q1.add(q2, LengthUnit.YARDS);

            assertEquals(0.6667, result.getValue(), EPSILON);
        }

        @Test
        void testQuantityLengthRefactored_NullUnit() {
            assertThrows(IllegalArgumentException.class,
                    () -> new QuantityMeasurementApp(1.0, null));
        }

        @Test
        void testQuantityLengthRefactored_InvalidValue() {
            assertThrows(IllegalArgumentException.class,
                    () -> new QuantityMeasurementApp(Double.NaN, LengthUnit.FEET));
        }
    }