package org.example;

import jdk.swing.interop.LightweightContentWrapper;
import org.junit.jupiter.api.Test;

import java.awt.desktop.QuitEvent;
import java.awt.event.KeyListener;

import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {

    @Test
    public void testSubtraction_SameUnit_FeetMinusFeet(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0,LengthUnit.FEET);

        Quantity<LengthUnit> res = q1.subtract(q2);

        assertEquals(res.getValue(),5.0);
    }

    @Test
     public void testSubtraction_SameUnit_LitreMinusLitre(){
        Quantity <VolumeUnit> q1 = new Quantity<>(10.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(7.0,VolumeUnit.LITRE);

        Quantity<VolumeUnit> res = q1.subtract(q2);

        assertEquals(res.getValue(),3.0);
    }

    @Test
    public void testSubtraction_CrossUnit_FeetMinusInches(){

        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0,LengthUnit.INCHES);

        Quantity<LengthUnit> res = q1.subtract(q2);

        assertEquals(res.getValue(),9.5);
        assertEquals(res.getUnit(),LengthUnit.FEET);
    }

    @Test
    public void testSubtraction_CrossUnit_InchesMinusFeet(){
        Quantity<LengthUnit> q1 = new Quantity<>(120.0,LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0,LengthUnit.FEET);

        Quantity<LengthUnit> res = q1.subtract(q2);

        assertEquals(res.getValue(),60.0);
    }

    @Test
    public void testSubtraction_ExplicitTargetUnit_Feet(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0,LengthUnit.INCHES);

        Quantity<LengthUnit> res = q1.subtract(q2);

        assertEquals(res.getValue(),9.5);
    }

    @Test
    public void testSubtraction_ExplicitTargetUnit_Inches(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0,LengthUnit.INCHES);

        Quantity<LengthUnit> res = q1.subtract(q2,LengthUnit.INCHES);

        assertEquals(res.getValue(),114.0);

    }

    @Test
    public void testSubtraction_ExplicitTargetUnit_Millilitre(){
        Quantity<VolumeUnit> q1 = new Quantity<>(5.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(2.0,VolumeUnit.LITRE);

        Quantity<VolumeUnit> res = q1.subtract(q2,VolumeUnit.MILLILITRE);

        assertEquals(res.getValue(),3000.0);
    }

    @Test
    public void testSubtraction_ResultingInNegative(){
        Quantity<LengthUnit> q1 = new Quantity<>(5.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(10.0,LengthUnit.FEET);

       assertEquals(-5.0,q1.subtract(q2).getValue());
    }

    @Test
    public void testSubtraction_ResultingInZero(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(120.0,LengthUnit.INCHES);

        assertEquals(0,q1.subtract(q2).getValue());
    }

    @Test
    public void testSubtraction_WithNegativeValues(){
        Quantity<LengthUnit> q1 = new Quantity<>(5.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(-2.0,LengthUnit.FEET);

        Quantity<LengthUnit> res = new Quantity<>(7.0,LengthUnit.FEET);

        assertEquals(q1.subtract(q2).getValue(),7.0);
    }

    @Test
    public void testSubtraction_NonCommutative(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity q2 = new Quantity(5.0,LengthUnit.FEET);

        assertTrue(q1.subtract(q2).getValue() != q2.subtract(q1).getValue());
    }

    @Test
    public void testSubtraction_WithLargeValues(){
        Quantity q1 = new Quantity(1e6,WeightUnit.KILOGRAM);
        Quantity q2 = new Quantity(5e5,WeightUnit.KILOGRAM);

        assertEquals(q1.subtract(q2).getValue(),5e5);
    }

    @Test
    public void testSubtraction_NullOperand(){
        Quantity q1 = new Quantity(10.0,LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,()->{
            q1.subtract(null);
        });
    }

    @Test
    public void testSubtraction_NullTargetUnit(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.00,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0,LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,()->{
            q1.subtract(q2,null);
        });
    }

    public void testSubtraction_ChainedOperations(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(q1.subtract(q2).subtract(new Quantity<>(1.0,LengthUnit.FEET)).getValue(),7.0);
    }

    @Test
    public void testDivision_SameUnit_FeetDividedByFeet(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0,LengthUnit.FEET);

        assertEquals(q1.divide(q2).getValue(),5.0);
    }

    @Test
    public void testDivision_CrossUnit_FeetDividedByInches(){
        Quantity<LengthUnit> q1 = new Quantity<>(24.0,LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0,LengthUnit.FEET);

        assertEquals(q1.divide(q2).getValue(),1.0);
    }

    @Test
    public void testDivision_RatioLessThanOne(){
        Quantity<LengthUnit> q1 = new Quantity<>(5.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(10.0,LengthUnit.FEET);

        assertTrue(q1.divide(q2).getValue() < 1.0);
    }

    @Test
    public void testDivision_RatioEqualToOne() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(10.0,LengthUnit.FEET);

        assertTrue(q1.divide(q2).getValue() == 1.0);
    }

    @Test
    public void testDivision_WithSmallRatio(){
        Quantity<WeightUnit> q1 = new Quantity<>(1.0,WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1e6,WeightUnit.KILOGRAM);

        assertEquals(1e-6, q1.divide(q2).getValue(), 1e-12);
    }


}