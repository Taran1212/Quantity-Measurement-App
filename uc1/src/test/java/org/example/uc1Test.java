package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class uc1Test {
    @Test
    void testEquality() {
        uc1.Feet f1 = new uc1.Feet(1.0);
        uc1.Feet f2 = new uc1.Feet(1.0);
        assertTrue(f1.equals(f2));
    }

    @Test
    void testDifferentFeet() {
        uc1.Feet f1 = new uc1.Feet(1.0);
        uc1.Feet f2 = new uc1.Feet(2.0);

        assertFalse(f1.equals(f2));
    }

    @Test
    void isNull() {
        uc1.Feet f1 = new uc1.Feet(1.0);

        assertFalse(f1.equals(null));
    }

    @Test
    void isNumericValue() {
        uc1.Feet f1 = new uc1.Feet(1.0);
        String str = "Hello";
        assertFalse(f1.equals(str));
    }

}