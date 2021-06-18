package io.github.derivasians.calculatte;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatteTest {
    @Test
    void tangentLineOfXSquaredXIntercept() {
        Function XSquared = x -> Math.pow(x, 2);
        Function TangentLine = Calculatte.tangentLine(2, XSquared);
        assertEquals(0, TangentLine.f(1));
    }

    @Test
    void tangentLineOfXSquaredSlope() {
        Function XSquared = x -> Math.pow(x, 2);
        Function TangentLine = Calculatte.tangentLine(2, XSquared);
        assertEquals(4, Calculatte.derivate(2, TangentLine));
    }

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
        assertEquals(4, slope);
    }

    @Test
    void revolveXSquaredFrom0To2() {
        Function XSquared = x -> Math.pow(x, 2);
        Function X = x -> 0;
        double volume = Calculatte.revolve(0, 2, 0, XSquared, X);
        assertEquals(20.10618963194089, volume);
    }

    @Test
    void roundingNear0() {
        Function SinX = x -> Math.sin(Math.toRadians(x));
        double area = Calculatte.integrate(0, 360, SinX);
        assertEquals(0, Calculatte.round(area));
    }
}