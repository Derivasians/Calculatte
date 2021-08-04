package io.github.derivasians.calculatte;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatteTest {
    // Integration
    @Test
    @DisplayName("Integrate 2x from 0 to 2")
    public void integrateTwoXFrom0To2() {
        final Function twoX = x -> 2 * x;
        final double area = Calculatte.integrate(0, 2, twoX);
        assertEquals(4, area);
    }

    @Test
    @DisplayName("Integrate x^3 from -2 to 2")
    public void integrateXCubedFromNegativeTwoToTwo() {
        final Function xCubed = x -> Math.pow(x, 3);
        final double area = Calculatte.integrate(-2, 2, xCubed);
        assertEquals(0, area);
    }

    @Test
    @DisplayName("Integrate x = 4 from 0 to 2")
    public void integrateXEquals4From0To2() {
        final Function xEquals4 = x -> 4;
        final double area = Calculatte.integrate(0, 2, xEquals4);
        assertEquals(8, area);
    }

    // Derivatives
    @Test
    @DisplayName("Derivative of x^2 at x = 2")
    public void derivativeOfXSquaredAtXEquals2() {
        final Function xSquared = x -> Math.pow(x, 2);
        final double slope = Calculatte.derivate(2, xSquared);
        assertEquals(4, slope);
    }

    @Test
    @DisplayName("Derivative DNE of |x| at x = 0")
    public void derivativeDoesNotExistOfAbsoluteValueOfXAtXEquals0() {
        final Function function = Math::abs;
        assertEquals(Double.NaN, Calculatte.derivate(0, function));
    }

    @Test
    @DisplayName("Left derivative of |x| at x = 0")
    public void leftDerivativeOfAbsoluteValueOfXAtXEquals0() {
        final Function function = Math::abs;
        assertEquals(-1, Calculatte.leftDerivative(0, function));
    }

    @Test
    @DisplayName("Right derivative of |x| at x = 0")
    public void rightDerivativeOfAbsoluteValueOfXAtXEquals0() {
        final Function function = Math::abs;
        assertEquals(1, Calculatte.rightDerivative(0, function));
    }

    // Tangent Lines
    @Test
    @DisplayName("Tangent line of x^2 x-intercept")
    public void tangentLineOfXSquaredXIntercept() {
        final Function xSquared = x -> Math.pow(x, 2);
        final Function tangentLine = Calculatte.tangentLine(2, xSquared);
        assertEquals(0, tangentLine.f(1));
    }

    @Test
    @DisplayName("Tangent line of x^2 slope at x = 2")
    public void tangentLineOfXSquaredSlope() {
        final Function xSquared = x -> Math.pow(x, 2);
        final Function tangentLine = Calculatte.tangentLine(2, xSquared);
        assertEquals(4, Calculatte.derivate(2, tangentLine));
    }

    // Left Riemann Sums
    @Test
    @DisplayName("Left Riemann sum of x^2 from 0 to 16 with n = 4")
    public void leftRiemannSumOfXSquaredFrom0To2WithNOf4() {
        final Function xSquared = x -> Math.pow(x, 2);
        final double approxArea = Calculatte.leftRiemannSum(0, 16, xSquared, 4);
        assertEquals(896, approxArea);
    }

    @Test
    @DisplayName("Left Riemann sum with invalid number of rectangles")
    public void leftRiemannSumWithInvalidNumberOfRectangles() {
        final Function xSquared = x -> x;

        final Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Calculatte.leftRiemannSum(0, 16, xSquared, 0));

        final String expectedMessage = "There must be at least one rectangle.";
        final String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Right Riemann Sums
    @Test
    @DisplayName("Right Riemann sum of x^2 from 0 to 16 with n = 4")
    public void rightRiemannSumOfXSquaredFrom0To2WithNOf4() {
        final Function xSquared = x -> Math.pow(x, 2);
        final double approxArea = Calculatte.rightRiemannSum(0, 16, xSquared, 4);
        assertEquals(1920, approxArea);
    }

    @Test
    @DisplayName("Right Riemann sum with invalid number of rectangles")
    public void rightRiemannSumWithInvalidNumberOfRectangles() {
        final Function xSquared = x -> x;

        final Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Calculatte.rightRiemannSum(0, 16, xSquared, 0));

        final String expectedMessage = "There must be at least one rectangle.";
        final String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Midpoint rule
    @Test
    @DisplayName("Midpoint rule of x^2 from 0 to 16 with n = 4")
    public void midpointRuleOfXSquaredFrom0To2WithNOf4() {
        final Function xSquared = x -> Math.pow(x, 2);
        final double approxArea = Calculatte.midpointRule(0, 16, xSquared, 4);
        assertEquals(1344, approxArea);
    }

    @Test
    @DisplayName("Midpoint rule with invalid number of rectangles")
    public void midpointRuleWithInvalidNumberOfRectangles() {
        final Function xSquared = x -> x;

        final Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Calculatte.midpointRule(0, 16, xSquared, 0));

        final String expectedMessage = "There must be at least one rectangle.";
        final String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Trapezoidal Sums
    @Test
    @DisplayName("Trapezoidal sum of x^2 from 0 to 16 with n = 4")
    public void trapezoidalSumOfXSquaredFrom0To2WithNOf4() {
        final Function xSquared = x -> Math.pow(x, 2);
        final double approxArea = Calculatte.trapezoidalSum(0, 16, xSquared, 4);
        assertEquals(1408, approxArea);
    }

    @Test
    @DisplayName("Trapezoidal sum with invalid number of rectangles")
    public void trapezoidalSumWithInvalidNumberOfRectangles() {
        final Function xSquared = x -> x;

        final Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Calculatte.trapezoidalSum(0, 16, xSquared, 0));

        final String expectedMessage = "There must be at least one trapezoid.";
        final String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Volume of Revolutions
    @Test
    @DisplayName("Revolve x^2 from 0 to 2")
    public void revolveXSquaredFrom0To2() {
        final Function xSquared = x -> Math.pow(x, 2);
        final Function xAxis = x -> 0;
        final double volume = Calculatte.revolve(0, 2, 0, xSquared, xAxis);
        assertEquals(20.106, volume);
    }

    // Volume of Known Cross-Sections
    @Test
    @DisplayName("Cross section of an equilateral triangle")
    public void crossSectionOfAnEquilateralTriangle() {
        final Function functionTop = x -> 1 - (x / 2);
        final Function functionBottom = x -> -1 + (x / 2);
        final double volume = Calculatte.crossSection(0, 2, functionTop, functionBottom,
                CalculatteEnvironment.EQUILATERAL_TRIANGLE);
        assertEquals(1.155, volume);
    }

    @Test
    @DisplayName("Invalid cross-section type")
    public void invalidCrossSectionType() {
        final Function functionTop = x -> 1 - (x / 2);
        final Function functionBottom = x -> -1 + (x / 2);

        final Exception exception = assertThrows(InvalidCrossSectionTypeException.class,
                () -> Calculatte.crossSection(0, 2, functionTop, functionBottom, 5));

        final String expectedMessage = "<5> is not a valid cross-section type. Please enter a valid cross-section type (0 - 4).";
        final String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Limits
    @Test
    @DisplayName("Limit of 1 over x^2 at x = 0")
    public void limitOf1OverXSquaredAtXEquals0() {
        final Function function = x -> 1 / Math.pow(x, 2);
        assertEquals(9.9999999999999987E17, Calculatte.limit(0, function));
    }

    @Test
    @DisplayName("Limit of x^2 at x = 2")
    public void limitOfXSquaredAtXEquals2() {
        final Function function = x -> Math.pow(x, 2);
        assertEquals(4, Calculatte.limit(2, function));
    }

    @Test
    @DisplayName("Limit at a removable discontinuity")
    public void limitAtARemovableDiscontinuity() {
        final Function function = x -> (Math.pow(x, 2) - (2 * x) - 8) / (x - 4);
        assertEquals(6, Calculatte.limit(4, function));
    }

    @Test
    @DisplayName("Limit at a violent oscillation")
    public void limitAtAViolentOscillation() {
        final Function function = x -> Math.sin(1 / x);
        assertEquals(Double.NaN, Calculatte.limit(0, function));
    }

    @Test
    @DisplayName("Limit at an asymptote")
    public void limitAtAnAsymptote() {
        final Function function = x -> (x + 2) / x;
        assertEquals(Double.NaN, Calculatte.limit(0, function));
    }

    @Test
    @DisplayName("Limits at positive infinity")
    public void limitsAtPositiveInfinity() {
        final Function function = x -> 5 + (3 / Math.pow(x, 2));
        assertEquals(5.0, Calculatte.leftLimit(Double.POSITIVE_INFINITY, function));
        assertEquals(5.0, Calculatte.rightLimit(Double.POSITIVE_INFINITY, function));
        assertEquals(5.0, Calculatte.limit(Double.POSITIVE_INFINITY, function));
    }

    @Test
    @DisplayName("Limits at max value")
    public void limitsAtMaxValue() {
        final Function function = x -> 5 + (3 / Math.pow(x, 2));
        assertEquals(5.0, Calculatte.leftLimit(Double.MAX_VALUE, function));
        assertEquals(5.0, Calculatte.rightLimit(Double.MAX_VALUE, function));
        assertEquals(5.0, Calculatte.limit(Double.MAX_VALUE, function));
    }

    // Polar Integration
    @Test
    @DisplayName("Polar integrate sin(x) from 0 to Pi")
    public void polarIntegrateSineXFrom0ToPi() {
        final Function function = Math::sin;
        final double area = Calculatte.polarArea(0, Math.PI, function);
        assertEquals(0.785, area);
    }

    @Test
    @DisplayName("Polar integrate sin(x) from 0 to 2Pi")
    public void polarIntegrateSineXFrom0To2Pi() {
        final Function function = Math::sin;
        final double area = Calculatte.polarArea(0, 2 * Math.PI, function);
        assertEquals(1.571, area);
    }

    @Test
    @DisplayName("Polar integrate 2cos(3x) from 0 to Pi")
    public void polarIntegrate2Cosine3XFrom0ToPi() {
        final Function function = x -> 2 * Math.cos(3 * x);
        final double area = Calculatte.polarArea(0, Math.PI, function);
        assertEquals(3.142, area);
    }
}
