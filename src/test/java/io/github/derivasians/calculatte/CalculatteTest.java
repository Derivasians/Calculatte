/*
 * Copyright 2021 Matthew Okashita, Joseph Benigno
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.github.derivasians.calculatte;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatteTest {
    Calculatte c = new Calculatte();

    // Integration
    @Test
    @DisplayName("Integrate 2x from 0 to 2")
    public void integrateTwoXFrom0To2() {
        final Function twoX = x -> 2 * x;
        final double area = c.integrate(0, 2, twoX);
        Assertions.assertEquals(4, area);
    }

    @Test
    @DisplayName("Integrate x^3 from -2 to 2")
    public void integrateXCubedFromNegativeTwoToTwo() {
        final Function xCubed = x -> Math.pow(x, 3);
        final double area = c.integrate(-2, 2, xCubed);
        Assertions.assertEquals(0, area);
    }

    @Test
    @DisplayName("Integrate x = 4 from 0 to 2")
    public void integrateXEquals4From0To2() {
        final Function xEquals4 = x -> 4;
        final double area = c.integrate(0, 2, xEquals4);
        Assertions.assertEquals(8, area);
    }

    // Derivation
    @Test
    @DisplayName("Derivative of x^2 at x = 2")
    public void derivativeOfXSquaredAtXEquals2() {
        final Function xSquared = x -> Math.pow(x, 2);
        final double slope = c.derivate(2, xSquared);
        Assertions.assertEquals(4, slope);
    }

    @Test
    @DisplayName("Derivative DNE of |x| at x = 0")
    public void derivativeDoesNotExistOfAbsoluteValueOfXAtXEquals0() {
        final Function function = Math::abs;
        Assertions.assertEquals(Double.NaN, c.derivate(0, function));
    }

    @Test
    @DisplayName("Left derivative of |x| at x = 0")
    public void leftDerivativeOfAbsoluteValueOfXAtXEquals0() {
        final Function function = Math::abs;
        Assertions.assertEquals(-1, c.leftDerivative(0, function));
    }

    @Test
    @DisplayName("Right derivative of |x| at x = 0")
    public void rightDerivativeOfAbsoluteValueOfXAtXEquals0() {
        final Function function = Math::abs;
        Assertions.assertEquals(1, c.rightDerivative(0, function));
    }

    // Tangent Lines
    @Test
    @DisplayName("Tangent line of x^2 x-intercept")
    public void tangentLineOfXSquaredXIntercept() {
        final Function xSquared = x -> Math.pow(x, 2);
        final Function tangentLine = c.tangentLine(2, xSquared);
        Assertions.assertEquals(0, tangentLine.f(1));
    }

    @Test
    @DisplayName("Tangent line of x^2 slope at x = 2")
    public void tangentLineOfXSquaredSlope() {
        final Function xSquared = x -> Math.pow(x, 2);
        final Function tangentLine = c.tangentLine(2, xSquared);
        Assertions.assertEquals(4, c.derivate(2, tangentLine));
    }

    // Left Riemann Sums
    @Test
    @DisplayName("Left Riemann sum of x^2 from 0 to 16 with n = 4")
    public void leftRiemannSumOfXSquaredFrom0To2WithNOf4() {
        final Function xSquared = x -> Math.pow(x, 2);
        final double approxArea = c.leftRiemannSum(0, 16, xSquared, 4);
        Assertions.assertEquals(896, approxArea);
    }

    @Test
    @DisplayName("Left Riemann sum with invalid number of rectangles")
    public void leftRiemannSumWithInvalidNumberOfRectangles() {
        final Function xSquared = x -> x;

        final Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> c.leftRiemannSum(0, 16, xSquared, 0));

        final String expectedMessage = "There must be at least one rectangle.";
        final String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    // Right Riemann Sums
    @Test
    @DisplayName("Right Riemann sum of x^2 from 0 to 16 with n = 4")
    public void rightRiemannSumOfXSquaredFrom0To2WithNOf4() {
        final Function xSquared = x -> Math.pow(x, 2);
        final double approxArea = c.rightRiemannSum(0, 16, xSquared, 4);
        Assertions.assertEquals(1920, approxArea);
    }

    @Test
    @DisplayName("Right Riemann sum with invalid number of rectangles")
    public void rightRiemannSumWithInvalidNumberOfRectangles() {
        final Function xSquared = x -> x;

        final Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> c.rightRiemannSum(0, 16, xSquared, 0));

        final String expectedMessage = "There must be at least one rectangle.";
        final String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    // Midpoint Rule
    @Test
    @DisplayName("Midpoint rule of x^2 from 0 to 16 with n = 4")
    public void midpointRuleOfXSquaredFrom0To2WithNOf4() {
        final Function xSquared = x -> Math.pow(x, 2);
        final double approxArea = c.midpointRule(0, 16, xSquared, 4);
        Assertions.assertEquals(1344, approxArea);
    }

    @Test
    @DisplayName("Midpoint rule with invalid number of rectangles")
    public void midpointRuleWithInvalidNumberOfRectangles() {
        final Function xSquared = x -> x;

        final Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> c.midpointRule(0, 16, xSquared, 0));

        final String expectedMessage = "There must be at least one rectangle.";
        final String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    // Trapezoidal Sums
    @Test
    @DisplayName("Trapezoidal sum of x^2 from 0 to 16 with n = 4")
    public void trapezoidalSumOfXSquaredFrom0To2WithNOf4() {
        final Function xSquared = x -> Math.pow(x, 2);
        final double approxArea = c.trapezoidalSum(0, 16, xSquared, 4);
        Assertions.assertEquals(1408, approxArea);
    }

    @Test
    @DisplayName("Trapezoidal sum with invalid number of rectangles")
    public void trapezoidalSumWithInvalidNumberOfRectangles() {
        final Function xSquared = x -> x;

        final Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> c.trapezoidalSum(0, 16, xSquared, 0));

        final String expectedMessage = "There must be at least one trapezoid.";
        final String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    // Volume of Revolutions
    @Test
    @DisplayName("Revolve x^2 from 0 to 2")
    public void revolveXSquaredFrom0To2() {
        final Function xSquared = x -> Math.pow(x, 2);
        final Function xAxis = x -> 0;
        final double volume = c.revolve(0, 2, 0, xSquared, xAxis);
        Assertions.assertEquals(20.106, volume);
    }

    // Volume of Known Cross-Sections
    @Test
    @DisplayName("Cross section of an equilateral triangle")
    public void crossSectionOfAnEquilateralTriangle() {
        final Function functionTop = x -> 1 - (x / 2);
        final Function functionBottom = x -> -1 + (x / 2);
        final double volume = c.crossSection(0, 2, functionTop, functionBottom,
                Calculatte.EQUILATERAL_TRIANGLE);
        Assertions.assertEquals(1.155, volume);
    }

    @Test
    @DisplayName("Invalid cross-section type")
    public void invalidCrossSectionType() {
        final Function functionTop = x -> 1 - (x / 2);
        final Function functionBottom = x -> -1 + (x / 2);

        final Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> c.crossSection(0, 2, functionTop, functionBottom, 5));

        final String expectedMessage = "<5> is not a valid cross-section type. Please enter a valid cross-section type (0 - 4).";
        final String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    // Limits
    @Test
    @DisplayName("Limit of 1 over x^2 at x = 0")
    public void limitOf1OverXSquaredAtXEquals0() {
        final Function function = x -> 1 / Math.pow(x, 2);
        Assertions.assertEquals(9.9999999999999987E17, c.limit(0, function));
    }

    @Test
    @DisplayName("Limit of x^2 at x = 2")
    public void limitOfXSquaredAtXEquals2() {
        final Function function = x -> Math.pow(x, 2);
        Assertions.assertEquals(4, c.limit(2, function));
    }

    @Test
    @DisplayName("Limit at a removable discontinuity")
    public void limitAtARemovableDiscontinuity() {
        final Function function = x -> (Math.pow(x, 2) - (2 * x) - 8) / (x - 4);
        Assertions.assertEquals(6, c.limit(4, function));
    }

    @Test
    @DisplayName("Limit at a violent oscillation")
    public void limitAtAViolentOscillation() {
        final Function function = x -> Math.sin(1 / x);
        Assertions.assertEquals(Double.NaN, c.limit(0, function));
    }

    @Test
    @DisplayName("Limit at an asymptote")
    public void limitAtAnAsymptote() {
        final Function function = x -> (x + 2) / x;
        Assertions.assertEquals(Double.NaN, c.limit(0, function));
    }

    @Test
    @DisplayName("Limits at positive infinity")
    public void limitsAtPositiveInfinity() {
        final Function function = x -> 5 + (3 / Math.pow(x, 2));
        Assertions.assertEquals(5.0, c.leftLimit(Double.POSITIVE_INFINITY, function));
        Assertions.assertEquals(5.0, c.rightLimit(Double.POSITIVE_INFINITY, function));
        Assertions.assertEquals(5.0, c.limit(Double.POSITIVE_INFINITY, function));
    }

    @Test
    @DisplayName("Limits at max value")
    public void limitsAtMaxValue() {
        final Function function = x -> 5 + (3 / Math.pow(x, 2));
        Assertions.assertEquals(5.0, c.leftLimit(Double.MAX_VALUE, function));
        Assertions.assertEquals(5.0, c.rightLimit(Double.MAX_VALUE, function));
        Assertions.assertEquals(5.0, c.limit(Double.MAX_VALUE, function));
    }

    // Polar Integration
    @Test
    @DisplayName("Polar integrate sin(x) from 0 to Pi")
    public void polarIntegrateSineXFrom0ToPi() {
        final Function function = Math::sin;
        final double area = c.polarArea(0, Math.PI, function);
        Assertions.assertEquals(0.785, area);
    }

    @Test
    @DisplayName("Polar integrate sin(x) from 0 to 2Pi")
    public void polarIntegrateSineXFrom0To2Pi() {
        final Function function = Math::sin;
        final double area = c.polarArea(0, 2 * Math.PI, function);
        Assertions.assertEquals(1.571, area);
    }

    @Test
    @DisplayName("Polar integrate 2cos(3x) from 0 to Pi")
    public void polarIntegrate2Cosine3XFrom0ToPi() {
        final Function function = x -> 2 * Math.cos(3 * x);
        final double area = c.polarArea(0, Math.PI, function);
        Assertions.assertEquals(3.142, area);
    }
}
