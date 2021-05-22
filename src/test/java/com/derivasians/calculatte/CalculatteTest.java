package com.derivasians.calculatte;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatteTest {
    @Test
    void integrateTwoXFrom0To2() {
        Function TwoX = x -> 2 * x;
        double area = Calculatte.integrate(0, 2, TwoX);
        assertEquals(3.9999997333333286, area);
    }

    @Test
    void derivativeOfXSquaredAtXEquals2() {
        Function XSquared = x -> Math.pow(x, 2);
        double slope = Calculatte.derivate(2, XSquared);
        assertEquals(slope, 4);
    }

    @Test
    void revolveXSquaredFrom0To2() {
        Function XSquared = x -> Math.pow(x, 2);
        Function X = x -> 0;
        double volume = Calculatte.revolution(0, 2, 0, XSquared, X);
        assertEquals(20.10618963194089, volume);
    }
}