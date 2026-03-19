package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WeightTest {

    private static final double EPSILON = 0.0001;

    @Test
    void testEquality_KilogramToKilogram_SameValue() {
        assertEquals(new Weight(1.0, WeightUnit.KILOGRAM),
                new Weight(1.0, WeightUnit.KILOGRAM));
    }

    @Test
    void testEquality_KilogramToKilogram_DifferentValue() {
        assertNotEquals(new Weight(1.0, WeightUnit.KILOGRAM),
                new Weight(2.0, WeightUnit.KILOGRAM));
    }

    @Test
    void testEquality_KilogramToGram_EquivalentValue() {
        assertEquals(new Weight(1.0, WeightUnit.KILOGRAM),
                new Weight(1000.0, WeightUnit.GRAM));
    }

    @Test
    void testEquality_GramToKilogram_EquivalentValue() {
        assertEquals(new Weight(1000.0, WeightUnit.GRAM),
                new Weight(1.0, WeightUnit.KILOGRAM));
    }

    @Test
    void testEquality_NullComparison() {
        assertNotEquals(new Weight(1.0, WeightUnit.KILOGRAM), null);
    }

    @Test
    void testEquality_SameReference() {
        Weight w = new Weight(1.0, WeightUnit.KILOGRAM);
        assertEquals(w, w);
    }

    @Test
    void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Weight(1.0, null));
    }

    @Test
    void testEquality_TransitiveProperty() {
        Weight a = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight b = new Weight(1000.0, WeightUnit.GRAM);
        Weight c = new Weight(1.0, WeightUnit.KILOGRAM);

        assertEquals(a, b);
        assertEquals(b, c);
        assertEquals(a, c);
    }

    @Test
    void testEquality_ZeroValue() {
        assertEquals(new Weight(0.0, WeightUnit.KILOGRAM),
                new Weight(0.0, WeightUnit.GRAM));
    }

    @Test
    void testEquality_NegativeWeight() {
        assertEquals(new Weight(-1.0, WeightUnit.KILOGRAM),
                new Weight(-1000.0, WeightUnit.GRAM));
    }

    @Test
    void testEquality_LargeWeightValue() {
        assertEquals(new Weight(1000000.0, WeightUnit.GRAM),
                new Weight(1000.0, WeightUnit.KILOGRAM));
    }

    @Test
    void testEquality_SmallWeightValue() {
        assertEquals(new Weight(0.001, WeightUnit.KILOGRAM),
                new Weight(1.0, WeightUnit.GRAM));
    }


    @Test
    void testConversion_PoundToKilogram() {
        Weight result = new Weight(2.20462, WeightUnit.POUND)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_KilogramToPound() {
        double result = new Weight(1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.POUND)
                .getValue();

        assertEquals(2.20462, result, EPSILON);
    }

    @Test
    void testConversion_SameUnit() {
        Weight result = new Weight(5.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(new Weight(5.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testConversion_ZeroValue() {
        Weight result = new Weight(0.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);

        assertEquals(new Weight(0.0, WeightUnit.GRAM), result);
    }

    @Test
    void testConversion_NegativeValue() {
        Weight result = new Weight(-1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);

        assertEquals(new Weight(-1000.0, WeightUnit.GRAM), result);
    }

    @Test
    void testConversion_RoundTrip() {
        double result = new Weight(1.5, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM)
                .convertTo(WeightUnit.KILOGRAM)
                .getValue();

        assertEquals(1.5, result, EPSILON);
    }

    // ✅ Addition Tests

    @Test
    void testAddition_SameUnit_KilogramPlusKilogram() {
        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(2.0, WeightUnit.KILOGRAM), WeightUnit.KILOGRAM);

        assertEquals(new Weight(3.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testAddition_CrossUnit_KilogramPlusGram() {
        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(1000.0, WeightUnit.GRAM), WeightUnit.KILOGRAM);

        assertEquals(new Weight(2.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testAddition_CrossUnit_PoundPlusKilogram() {
        double result = new Weight(2.20462, WeightUnit.POUND)
                .add(new Weight(1.0, WeightUnit.KILOGRAM), WeightUnit.POUND)
                .getValue();

        assertEquals(4.40924, result, EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Kilogram() {
        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(1000.0, WeightUnit.GRAM), WeightUnit.GRAM);

        assertEquals(new Weight(2000.0, WeightUnit.GRAM), result);
    }

    @Test
    void testAddition_Commutativity() {
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(1000.0, WeightUnit.GRAM), WeightUnit.KILOGRAM);

        Weight w2 = new Weight(1000.0, WeightUnit.GRAM)
                .add(new Weight(1.0, WeightUnit.KILOGRAM), WeightUnit.KILOGRAM);

        assertEquals(w1, w2);
    }

    @Test
    void testAddition_WithZero() {
        Weight result = new Weight(5.0, WeightUnit.KILOGRAM)
                .add(new Weight(0.0, WeightUnit.GRAM), WeightUnit.KILOGRAM);

        assertEquals(new Weight(5.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testAddition_NegativeValues() {
        Weight result = new Weight(5.0, WeightUnit.KILOGRAM)
                .add(new Weight(-2000.0, WeightUnit.GRAM), WeightUnit.KILOGRAM);

        assertEquals(new Weight(3.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testAddition_LargeValues() {
        Weight result = new Weight(1e6, WeightUnit.KILOGRAM)
                .add(new Weight(1e6, WeightUnit.KILOGRAM), WeightUnit.KILOGRAM);

        assertEquals(new Weight(2e6, WeightUnit.KILOGRAM), result);
    }
}