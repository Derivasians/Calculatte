package io.github.derivasians.calculatte;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatteTest {
    @Test
    public void tangentLineOfXSquaredXIntercept() {
        Function XSquared = x -> Math.pow(x, 2);
        Function TangentLine = Calculatte.tangentLine(2, XSquared);
        assertEquals(0, TangentLine.f(1));
    }

    @Test
    public void tangentLineOfXSquaredSlope() {
        Function XSquared = x -> Math.pow(x, 2);
        Function TangentLine = Calculatte.tangentLine(2, XSquared);
        assertEquals(4, Calculatte.derivate(2, TangentLine));
    }

    @Test
    public void integrateTwoXFrom0To2() {
        Function TwoX = x -> 2 * x;
        double area = Calculatte.integrate(0, 2, TwoX);
        assertEquals(3.9999997333333286, area);
    }

    @Test
    public void derivativeOfXSquaredAtXEquals2() {
        Function XSquared = x -> Math.pow(x, 2);
        double slope = Calculatte.derivate(2, XSquared);
        assertEquals(4, slope);
    }

    @Test
    public void revolveXSquaredFrom0To2() {
        Function XSquared = x -> Math.pow(x, 2);
        Function X = x -> 0;
        double volume = Calculatte.revolve(0, 2, 0, XSquared, X);
        assertEquals(20.10618963194089, volume);
    }

    @Test
    public void roundingNear0() {
        assertEquals(0, Calculatte.round(0.0000000001));
    }
}
