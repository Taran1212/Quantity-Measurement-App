package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {

    public double EPSILON = 1e-6;

    @Test
    public void test_equality_LitretoLitre_SameValue(){

        Quantity<VolumeUnit> q1 = new Quantity<>(1.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1.0,VolumeUnit.LITRE);

        assertEquals(q1.getValue(),q2.getValue());
    }

    @Test
    public void test_equality_LitretoLitre_DifferentValue(){
        Quantity<VolumeUnit> q1 = new Quantity<>(1.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(2.0,VolumeUnit.LITRE);

        assertFalse(q1.equals(q2));
    }

    @Test
    public void testEquality_LitreToMillilitre_EquivalentValue(){

        Quantity<VolumeUnit> q1 = new Quantity<>(1.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1000.0,VolumeUnit.MILLILITRE);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_MillilitreToLitre_EquivalentValue(){
        Quantity<VolumeUnit> q1 = new Quantity<>(1000.0,VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1.0,VolumeUnit.LITRE);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_GallonToLitre_EquivalentValue() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> q2 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_VolumeVsLength_Incompatible() {
        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<LengthUnit> l = new Quantity<>(1.0, LengthUnit.FEET);
        assertFalse(v.equals(l));
    }

    @Test
    void testEquality_VolumeVsWeight_Incompatible() {
        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<WeightUnit> w = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertFalse(v.equals(w));
    }

    @Test
    void testEquality_NullComparison() {
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertFalse(q.equals(null));
    }

    @Test
    void testEquality_SameReference() {
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(q.equals(q));
    }

    @Test
    void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    @Test
    void testEquality_ZeroValue() {
        Quantity<VolumeUnit> q1 = new Quantity<>(0.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(0.0, VolumeUnit.MILLILITRE);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_NegativeVolume() {
        Quantity<VolumeUnit> q1 = new Quantity<>(-1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(-1000.0, VolumeUnit.MILLILITRE);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_LargeVolumeValue() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1000000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1000.0, VolumeUnit.LITRE);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_SmallVolumeValue() {
        Quantity<VolumeUnit> q1 = new Quantity<>(0.001, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1.0, VolumeUnit.MILLILITRE);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testConversion_LitreToMillilitre() {
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertEquals(1000.0, q.convertTo(VolumeUnit.MILLILITRE), EPSILON);
    }

    @Test
    void testConversion_MillilitreToLitre() {
        Quantity<VolumeUnit> q = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertEquals(1.0, q.convertTo(VolumeUnit.LITRE), EPSILON);
    }

    @Test
    void testConversion_GallonToLitre() {
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.GALLON);
        assertEquals(3.78541, q.convertTo(VolumeUnit.LITRE), EPSILON);
    }

    @Test
    void testConversion_LitreToGallon() {
        Quantity<VolumeUnit> q = new Quantity<>(3.78541, VolumeUnit.LITRE);
        assertEquals(1.0, q.convertTo(VolumeUnit.GALLON), EPSILON);
    }

    @Test
    void testConversion_MillilitreToGallon() {
        Quantity<VolumeUnit> q = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertEquals(0.264172, q.convertTo(VolumeUnit.GALLON), EPSILON);
    }

    @Test
    void testConversion_SameUnit() {
        Quantity<VolumeUnit> q = new Quantity<>(5.0, VolumeUnit.LITRE);
        assertEquals(5.0, q.convertTo(VolumeUnit.LITRE), EPSILON);
    }

    @Test
    void testConversion_ZeroValue() {
        Quantity<VolumeUnit> q = new Quantity<>(0.0, VolumeUnit.LITRE);
        assertEquals(0.0, q.convertTo(VolumeUnit.MILLILITRE), EPSILON);
    }

    @Test
    void testConversion_NegativeValue() {
        Quantity<VolumeUnit> q = new Quantity<>(-1.0, VolumeUnit.LITRE);
        assertEquals(-1000.0, q.convertTo(VolumeUnit.MILLILITRE), EPSILON);
    }

    @Test
    void testConversion_RoundTrip() {
        Quantity<VolumeUnit> q = new Quantity<>(1.5, VolumeUnit.LITRE);
        double ml = q.convertTo(VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> back = new Quantity<>(ml, VolumeUnit.MILLILITRE);
        assertEquals(1.5, back.convertTo(VolumeUnit.LITRE), EPSILON);
    }

    @Test
    void testAddition_SameUnit_LitrePlusLitre() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(2.0, VolumeUnit.LITRE);
        assertEquals(3.0, q1.add(q2).getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit_LitrePlusMillilitre() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertEquals(2.0, q1.add(q2).getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit_MillilitrePlusLitre() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertEquals(2000.0, q1.add(q2).getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Gallon() {
        Quantity<VolumeUnit> q1 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        assertEquals(2.0, q1.add(q2, VolumeUnit.GALLON).getValue(), EPSILON);
    }

    @Test
    void testAddition_WithZero() {
        Quantity<VolumeUnit> q1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> zero = new Quantity<>(0.0, VolumeUnit.MILLILITRE);
        assertEquals(5.0, q1.add(zero).getValue(), EPSILON);
    }

    @Test
    void testAddition_NegativeValues() {
        Quantity<VolumeUnit> q1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(-2000.0, VolumeUnit.MILLILITRE);
        assertEquals(3.0, q1.add(q2).getValue(), EPSILON);
    }

    @Test
    void testVolumeUnitEnum_LitreConstant() {
        assertEquals(1.0, VolumeUnit.LITRE.getConversionFactor(), EPSILON);
    }

    @Test
    void testVolumeUnitEnum_MillilitreConstant() {
        assertEquals(0.001, VolumeUnit.MILLILITRE.getConversionFactor(), EPSILON);
    }

    @Test
    void testVolumeUnitEnum_GallonConstant() {
        assertEquals(3.78541, VolumeUnit.GALLON.getConversionFactor(), EPSILON);
    }
}