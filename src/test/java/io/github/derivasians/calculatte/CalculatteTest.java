package io.github.derivasians.calculatte;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatteTest {
    // factorial() Unit Tests
    @Test
    @DisplayName("6!")
    public void factorialOf6() {
        assertEquals(720, Calculatte.factorial(6));
    }

    @Test
    @DisplayName("0!")
    public void factorialOf0() {
        assertEquals(1, Calculatte.factorial(0));
    }

    @Test
    @DisplayName("-1!")
    public void factorialOfNegative1() {
        assertEquals(Double.NaN, Calculatte.factorial(-1));
    }

    // integrate() Unit Tests
    @Test
    @DisplayName("Integrate 2x from 0 to 2")
    public void integrateTwoXFrom0To2() {
        Function TwoX = x -> 2 * x;
        double area = Calculatte.integrate(0, 2, TwoX);
        assertEquals(4, area);
    }

    @Test
    @DisplayName("Integrate x^3 from -2 to 2")
    public void integrateXCubedFromNegativeTwoToTwo() {
        Function XCubed = x -> Math.pow(x, 3);
        double area = Calculatte.integrate(-2, 2, XCubed);
        assertEquals(0, area);
    }

    @Test
    @DisplayName("Integrate x = 4 from 0 to 2")
    public void integrateXEquals4From0To2() {
        Function XEquals4 = x -> 4;
        double area = Calculatte.integrate(0, 2, XEquals4);
        assertEquals(8, area);
    }

    // derivate() Unit Test
    @Test
    @DisplayName("Derivative of x^2 at x = 2")
    public void derivativeOfXSquaredAtXEquals2() {
        Function XSquared = x -> Math.pow(x, 2);
        double slope = Calculatte.derivate(2, XSquared);
        assertEquals(4, slope);
    }

    @Test
    @DisplayName("Derivative DNE of d/dx [|x|] at x = 0")
    public void derivativeDoesNotExistOfAbsoluteValueOfXAtXEquals0() {
        Function function = Math::abs;
        assertEquals(Double.NaN, Calculatte.derivate(0, function));
    }

    @Test
    @DisplayName("Left derivative of |x| at x = 0")
    public void leftDerivativeOfAbsoluteValueOfXAtXEquals0() {
        Function function = Math::abs;
        assertEquals(-1, Calculatte.leftDerivative(0, function));
    }

    @Test
    @DisplayName("Right derivative of |x| at x = 0")
    public void rightDerivativeOfAbsoluteValueOfXAtXEquals0() {
        Function function = Math::abs;
        assertEquals(1, Calculatte.rightDerivative(0, function));
    }

    // tangentLine() Unit Tests
    @Test
    @DisplayName("Tangent line of x^2 Intercept")
    public void tangentLineOfXSquaredXIntercept() {
        Function XSquared = x -> Math.pow(x, 2);
        Function TangentLine = Calculatte.tangentLine(2, XSquared);
        assertEquals(0, TangentLine.f(1));
    }

    @Test
    @DisplayName("Tangent line of x^2 Slope")
    public void tangentLineOfXSquaredSlope() {
        Function XSquared = x -> Math.pow(x, 2);
        Function TangentLine = Calculatte.tangentLine(2, XSquared);
        assertEquals(4, Calculatte.derivate(2, TangentLine));
    }

    // leftRiemannSum() Unit Test
    @Test
    @DisplayName("Left Riemann sum of x^2 from 0 to 16 with n = 4")
    public void leftRiemannSumOfXSquaredFrom0To2WithNOf4() {
        Function XSquared = x -> Math.pow(x, 2);
        double approxArea = Calculatte.leftRiemannSum(0, 16, XSquared, 4);
        assertEquals(896, approxArea);
    }

    @Test
    @DisplayName("Left Riemann sum with invalid number of rectangles")
    public void leftRiemannSumWithInvalidNumberOfRectangles() {
        Function XSquared = x -> x;

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Calculatte.leftRiemannSum(0, 16, XSquared, 0));

        String expectedMessage = "There must be at least one rectangle.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // rightRiemannSum() Unit Test
    @Test
    @DisplayName("Right Riemann sum of x^2 from 0 to 16 with n = 4")
    public void rightRiemannSumOfXSquaredFrom0To2WithNOf4() {
        Function XSquared = x -> Math.pow(x, 2);
        double approxArea = Calculatte.rightRiemannSum(0, 16, XSquared, 4);
        assertEquals(1920, approxArea);
    }

    @Test
    @DisplayName("Right Riemann sum with invalid number of rectangles")
    public void rightRiemannSumWithInvalidNumberOfRectangles() {
        Function XSquared = x -> x;

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Calculatte.rightRiemannSum(0, 16, XSquared, 0));

        String expectedMessage = "There must be at least one rectangle.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // midpointRule() Unit Test
    @Test
    @DisplayName("Midpoint rule of x^2 from 0 to 16 with n = 4")
    public void midpointRuleOfXSquaredFrom0To2WithNOf4() {
        Function XSquared = x -> Math.pow(x, 2);
        double approxArea = Calculatte.midpointRule(0, 16, XSquared, 4);
        assertEquals(1344, approxArea);
    }

    @Test
    @DisplayName("Midpoint rule with invalid number of rectangles")
    public void midpointRuleWithInvalidNumberOfRectangles() {
        Function XSquared = x -> x;

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Calculatte.midpointRule(0, 16, XSquared, 0));

        String expectedMessage = "There must be at least one rectangle.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // trapezoidalSum() Unit Test
    @Test
    @DisplayName("Trapezoidal sum of x^2 from 0 to 16 with n = 4")
    public void trapezoidalSumOfXSquaredFrom0To2WithNOf4() {
        Function XSquared = x -> Math.pow(x, 2);
        double approxArea = Calculatte.trapezoidalSum(0, 16, XSquared, 4);
        assertEquals(1408, approxArea);
    }

    @Test
    @DisplayName("Trapezoidal sum with invalid number of rectangles")
    public void trapezoidalSumWithInvalidNumberOfRectangles() {
        Function XSquared = x -> x;

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Calculatte.trapezoidalSum(0, 16, XSquared, 0));

        String expectedMessage = "There must be at least one trapezoid.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // revolve() Unit Test
    @Test
    @DisplayName("Revolve x^2 from 0 to 2")
    public void revolveXSquaredFrom0To2() {
        Function XSquared = x -> Math.pow(x, 2);
        Function X = x -> 0;
        double volume = Calculatte.revolve(0, 2, 0, XSquared, X);
        assertEquals(20.106, volume);
    }

    // crossSection() Unit Tests
    @Test
    @DisplayName("Cross section of an equilateral triangle")
    public void crossSectionOfAnEquilateralTriangle() {
        Function functionTop = x -> 1 - (x / 2);
        Function functionBottom = x -> -1 + (x / 2);
        double volume = Calculatte.crossSection(0, 2, functionTop, functionBottom,
                CalculatteEnvironment.EQUILATERAL_TRIANGLE);
        assertEquals(1.155, volume);
    }

    @Test
    @DisplayName("Invalid cross-section type")
    public void invalidCrossSectionType() {
        Function functionTop = x -> 1 - (x / 2);
        Function functionBottom = x -> -1 + (x / 2);

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Calculatte.crossSection(0, 2, functionTop, functionBottom, 5));

        String expectedMessage = "Please enter a valid cross-section type (0 - 4).";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // limit() Unit Tests
    @Test
    @DisplayName("Limit of 1 over x^2 at x = 0")
    public void limitOf1OverXSquaredAtXEquals0() {
        Function function = x -> 1 / Math.pow(x, 2);
        assertEquals(9.9999999999999987E17, Calculatte.limit(0, function));
    }

    @Test
    @DisplayName("Limit of x^2 at x = 2")
    public void limitOfXSquaredAtXEquals2() {
        Function function = x -> Math.pow(x, 2);
        assertEquals(4, Calculatte.limit(2, function));
    }

    @Test
    @DisplayName("Limit at a removable discontinuity")
    public void limitAtARemovableDiscontinuity() {
        Function function = x -> (Math.pow(x, 2) - (2 * x) - 8) / (x - 4);
        assertEquals(6, Calculatte.limit(4, function));
    }

    @Test
    @DisplayName("Limit at a violent oscillation")
    public void limitAtAViolentOscillation() {
        Function function = x -> Math.sin(1 / x);
        assertEquals(Double.NaN, Calculatte.limit(0, function));
    }

    @Test
    @DisplayName("Limit at an asymptote")
    public void limitAtAnAsymptote() {
        Function function = x -> (x + 2) / x;
        assertEquals(Double.NaN, Calculatte.limit(0, function));
    }

    @Test
    @DisplayName("Limits at positive infinity")
    public void limitsAtPositiveInfinity() {
        Function function = x -> 5 + (3 / Math.pow(x, 2));
        assertEquals(5.0, Calculatte.leftLimit(Double.POSITIVE_INFINITY, function));
        assertEquals(5.0, Calculatte.rightLimit(Double.POSITIVE_INFINITY, function));
        assertEquals(5.0, Calculatte.limit(Double.POSITIVE_INFINITY, function));
    }

    @Test
    @DisplayName("Limits at max value")
    public void limitsAtMaxValue() {
        Function function = x -> 5 + (3 / Math.pow(x, 2));
        assertEquals(5.0, Calculatte.leftLimit(Double.MAX_VALUE, function));
        assertEquals(5.0, Calculatte.rightLimit(Double.MAX_VALUE, function));
        assertEquals(5.0, Calculatte.limit(Double.MAX_VALUE, function));
    }
}
