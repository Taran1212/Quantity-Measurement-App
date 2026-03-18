package org.example;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class uc2Test {

    @Test
    void testSameFeet() {
        uc2.Feet f1 = new uc2.Feet(1.0);
        uc2.Feet f2 = new uc2.Feet(1.0);
        assertTrue(f1.equals(f2));
    }

    @Test
    void testDifferentFeet() {
        uc2.Feet f1 = new uc2.Feet(1.0);
        uc2.Feet f2 = new uc2.Feet(2.0);
        assertFalse(f1.equals(f2));
    }

    @Test
    void testNullFeet() {
        uc2.Feet f1 = new uc2.Feet(1.0);
        assertFalse(f1.equals(null));
    }

    @Test
    void testSameReferenceFeet() {
        uc2.Feet f1 = new uc2.Feet(1.0);
        assertTrue(f1.equals(f1));
    }

    @Test
    void testDifferentTypeFeet() {
        uc2.Feet f1 = new uc2.Feet(1.0);
        String str = "hello";
        assertFalse(f1.equals(str));
    }

    @Test
    void testSameInch() {
        uc2.Inch i1 = new uc2.Inch(1.0);
        uc2.Inch i2 = new uc2.Inch(1.0);
        assertTrue(i1.equals(i2));
    }

    @Test
    void testDifferentInch() {
        uc2.Inch i1 = new uc2.Inch(1.0);
        uc2.Inch i2 = new uc2.Inch(2.0);
        assertFalse(i1.equals(i2));
    }

    @Test
    void testNullInch() {
        uc2.Inch i1 = new uc2.Inch(1.0);
        assertFalse(i1.equals(null));
    }

    @Test
    void testSameReferenceInch() {
        uc2.Inch i1 = new uc2.Inch(1.0);
        assertTrue(i1.equals(i1));
    }

    @Test
    void testDifferentTypeInch() {
        uc2.Inch i1 = new uc2.Inch(1.0);
        String str = "hello";
        assertFalse(i1.equals(str));
    }
}