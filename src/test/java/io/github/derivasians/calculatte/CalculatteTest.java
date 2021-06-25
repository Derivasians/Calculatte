package io.github.derivasians.calculatte;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatteTest {
    @Test
    @DisplayName("Tangent line of x^2 Intercept")
    public void tangentLineOfXSquaredXIntercept() {
        Function XSquared = x -> Math.pow(x, 2);
        Function TangentLine = Calculatte.tangentLine(2, XSquared);
        assertEquals(0, TangentLine.f(1));
    }

    @Test
    @DisplayName("Tangent Line of x^2 Slope")
    public void tangentLineOfXSquaredSlope() {
        Function XSquared = x -> Math.pow(x, 2);
        Function TangentLine = Calculatte.tangentLine(2, XSquared);
        assertEquals(4, Calculatte.derivate(2, TangentLine));
    }

    @Test
    @DisplayName("Integrate 2x from 0 to 2")
    public void integrateTwoXFrom0To2() {
        Function TwoX = x -> 2 * x;
        double area = Calculatte.integrate(0, 2, TwoX);
        assertEquals(3.9999997333333286, area);
    }

    @Test
    @DisplayName("Derivative of x^2 at x = 2")
    public void derivativeOfXSquaredAtXEquals2() {
        Function XSquared = x -> Math.pow(x, 2);
        double slope = Calculatte.derivate(2, XSquared);
        assertEquals(4, slope);
    }

    @Test
    @DisplayName("Revolve x^2 from 0 to 2")
    public void revolveXSquaredFrom0To2() {
        Function XSquared = x -> Math.pow(x, 2);
        Function X = x -> 0;
        double volume = Calculatte.revolve(0, 2, 0, XSquared, X);
        assertEquals(20.10618963194089, volume);
    }

    @Test
    @DisplayName("Rounding near 0")
    public void roundingNear0() {
        assertEquals(0, Calculatte.round(0.0000000001));
    }

    @Test
    @DisplayName("Left Riemann sum of x^2 from 0 to 16 with n = 4")
    public void leftRiemannSumOfXSquaredFrom0To2WithNOf4() {
        Function XSquared = x -> Math.pow(x, 2);
        double approxArea = Calculatte.leftRiemannSum(0, 16, XSquared, 4);
        assertEquals(896, approxArea);
    }

    @Test
    @DisplayName("Right Riemann sum of x^2 from 0 to 16 with n = 4")
    public void rightRiemannSumOfXSquaredFrom0To2WithNOf4() {
        Function XSquared = x -> Math.pow(x, 2);
        double approxArea = Calculatte.rightRiemannSum(0, 16, XSquared, 4);
        assertEquals(1920, approxArea);
    }


    @Test
    @DisplayName("Trapezoidal sum of x^2 from 0 to 16 with n = 4")
    public void trapezoidalSumOfXSquaredFrom0To2WithNOf4() {
        Function XSquared = x -> Math.pow(x, 2);
        double approxArea = Calculatte.trapezoidalSum(0, 16, XSquared, 4);
        assertEquals(1408, approxArea);
    }
}
