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

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Contains all methods and properties to perform basic calculus operations.
 *
 * @author <a href="mailto:okashita.matthew@gmail.com">Matthew Okashita</a>
 * @author <a href="mailto:benigno.joseph.s@gmail.com">Joseph Benigno</a>
 * @version 0.2.0
 */
public final class Calculatte {
    /**
     * Represents how many decimal places this variable's respective
     * calculation(s) should be rounded too. Only values returned by a method
     * are rounded. Intermediate values within a method are never rounded,
     * unless by another method; e.g., the <code>revolve()</code> method's
     * calculations get rounded when the integral is taken with
     * <code>integrate()</code> and once more when the final value is
     * returned.
     *
     * <p>Note: Setting any of these values to -1 will prevent that group
     * of calculations from being rounded. This can be useful if you
     * would prefer to use your own rounding method or none at all.
     */
    public static int
    integrationRoundingDecimalPlaces = 3,
    derivationRoundingDecimalPlaces = 3,
    leftRiemannSumRoundingDecimalPlaces = 3,
    rightRiemannSumRoundingDecimalPlaces = 3,
    midpointRuleRoundingDecimalPlaces = 3,
    trapezoidalSumRoundingDecimalPlaces = 3,
    revolutionRoundingDecimalPlaces = 3,
    crossSectionsRoundingDecimalPlaces = 3,
    limitRoundingDecimalPlaces = 3,
    leftLimitRoundingDecimalPlaces = 3,
    rightLimitRoundingDecimalPlaces = 3,
    polarAreaRoundingDecimalPlaces = 3;

    /**
     * <p>All values greater than <code>positiveInfinity</code>
     * will be rounded up to <code>Double.POSITIVE_INFINITY</code> by
     * <code>Calculatte.round()</code>.
     *
     * <p>Note: Setting <code>positiveInfinity</code> to
     * <code>Double.MAX_VALUE</code> will prevent rounding values up to
     * <code>Double.POSITIVE_INFINITY</code>.
     *
     * @see io.github.derivasians.calculatte.Calculatte#round(double, int)
     */
    public static double positiveInfinity = Double.MAX_VALUE;

    /**
     * <p>All values less than to <code>negativeInfinity</code>
     * will be rounded down to <code>Double.NEGATIVE_INFINITY</code> by
     * <code>Calculatte.round()</code>.
     *
     * <p>Note: Setting <code>negativeInfinity</code> to
     * <code>Double.MIN_VALUE</code> will prevent rounding values down to
     * <code>Double.NEGATIVE_INFINITY</code>.
     *
     * @see io.github.derivasians.calculatte.Calculatte#round(double, int)
     */
    public static double negativeInfinity = -Double.MAX_VALUE;

    /**
     * Represents accuracy value for integration calculations. The larger the more accurate.
     *
     * @see io.github.derivasians.calculatte.Calculatte#integrate(double, double, Function)
     */
    public static int n = 64000;

    /**
     * Represents accuracy value for derivation calculations. The smaller the more accurate.
     *
     * @see io.github.derivasians.calculatte.Calculatte#derivate(double, Function)
     */
    public static double h = 0.000000001;

    /**
     * Represents the largest difference between the left and right derivative before the derivative
     * does not exist.
     *
     * @see io.github.derivasians.calculatte.Calculatte#derivate(double, Function)
     */
    public static double derivativeTolerance = 0.000000001;

    /**
     * Represents how far the x-value should be offset left and right to find the left and right
     * derivatives.
     *
     * @see io.github.derivasians.calculatte.Calculatte#leftDerivative(double, Function)
     * @see io.github.derivasians.calculatte.Calculatte#rightDerivative(double, Function)
     */
    public static double derivativeOffset = 0.000000001;

    /**
     * Represents the largest difference between the left and right limit before the limit does not
     * exist.
     *
     * @see io.github.derivasians.calculatte.Calculatte#limit(double, Function)
     */
    public static double limitTolerance = 0.000000001;

    /**
     * Represents how far the x-value should be offset left and right to find the left and right limits.
     *
     * @see io.github.derivasians.calculatte.Calculatte#leftLimit(double, Function)
     * @see io.github.derivasians.calculatte.Calculatte#rightLimit(double, Function)
     */
    public static double limitOffset = 0.000000001;

    /**
     * Represents common known cross-sections types.
     *
     * @see io.github.derivasians.calculatte.Calculatte#crossSection(double, double, Function, Function, int)
     */
    public static final int
    SQUARE = 0,
    EQUILATERAL_TRIANGLE = 1,
    ISOSCELES_TRIANGLE = 2,
    RIGHT_TRIANGLE = 3,
    SEMICIRCLE = 4;

    /**
     * Rounds doubles according to the IEEE 754 standard of rounding half to even.
     *
     * <p>Note: If <code>decimalPlaces</code> is set to <code>-1</code>, <code>x</code>
     * will not be rounded.
     *
     * @param x The value to be rounded.
     * @param decimalPlaces The number of decimal places to round to.
     * @return The rounded value.
     */
    public double round(double x, int decimalPlaces) {
        if (decimalPlaces < 0) {
            return x;
        }

        if (x > positiveInfinity) {
            return Double.POSITIVE_INFINITY;
        }

        if (x < negativeInfinity) {
            return Double.NEGATIVE_INFINITY;
        }

        BigDecimal bd = new BigDecimal(Double.toString(x));
        bd = bd.setScale(decimalPlaces, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }

    /**
     * Integrates the function from a to b using Simpson's rule.
     *
     * @param a The lower limit of integration.
     * @param b The upper limit of integration.
     * @param function The function to integrate.
     * @return The area under the curve from a to b.
     */
    public double integrate(double a, double b, Function function) {
        double h = (b - a) / (n - 1); // Step size.

        // 1/3 terms.
        double sum = 1.0 / 3.0 * (function.f(a) + function.f(b));

        // 4/3 terms.
        for (int i = 1; i < n - 1; i += 2) {
            sum += 4.0 / 3.0 * function.f(a + h * i);
        }

        // 2/3 terms.
        for (int i = 2; i < n - 1; i += 2) {
            sum += 2.0 / 3.0 * function.f(a + h * i);
        }

        return round(sum * h, integrationRoundingDecimalPlaces);
    }

    /**
     * Integrates the function from a to b using Simpson's rule, without rounding.
     *
     * @param a The lower limit of integration.
     * @param b The upper limit of integration.
     * @param function The function to integrate.
     * @return The area under the curve from a to b, not rounded.
     */
    private double integrateRaw(double a, double b, Function function) {
        double h = (b - a) / (n - 1); // Step size.

        // 1/3 terms.
        double sum = 1.0 / 3.0 * (function.f(a) + function.f(b));

        // 4/3 terms.
        for (int i = 1; i < n - 1; i += 2) {
            sum += 4.0 / 3.0 * function.f(a + h * i);
        }

        // 2/3 terms.
        for (int i = 2; i < n - 1; i += 2) {
            sum += 2.0 / 3.0 * function.f(a + h * i);
        }

        return sum * h;
    }

    /**
     * Finds the derivate of the function at point, x.
     *
     * @param x The point on the function to find the derivative.
     * @param function The function to find the derivative of.
     * @return The derivative of the function at point, x or <code>Double.Nan</code>
     * if the derivative DNE.
     */
    public double derivate(double x, Function function) {
        if ((leftDerivative(x, function) > rightDerivative(x, function) + derivativeTolerance) ||
                (leftDerivative(x, function) < rightDerivative(x, function) - derivativeTolerance)) {
            return Double.NaN;
        }

        double slope =
                (function.f(x + h) - function.f(x)) / ((x + h) - x);
        return round(slope, derivationRoundingDecimalPlaces);
    }

    /**
     * Finds the left derivate of the function at point, x.
     *
     * @param x The point on the function to find the derivative.
     * @param function The function to find the derivative of.
     * @return The derivative of the function at point, x.
     */
    public double leftDerivative(double x, Function function) {
        x -= derivativeOffset;
        return (function.f(x + h) - function.f(x)) / ((x + h) - x);
    }

    /**
     * Finds the right derivate of the function at point, x.
     *
     * @param x The point on the function to find the derivative.
     * @param function The function to find the derivative of.
     * @return The derivative of the function at point, x.
     */
    public double rightDerivative(double x, Function function) {
        x += derivativeOffset;
        return (function.f(x + h) - function.f(x)) / ((x + h) - x);
    }

    /**
     * Finds the tangent line of <code>function</code> at point, <code>x</code>.
     *
     * @param x The x-value at which to find the tangent line of.
     * @param function The function to find the tangent line of.
     * @return The tangent line.
     */
    public Function tangentLine(double x, Function function) {
        double m = derivate(x, function);
        double b = function.f(x) - (m * x); // b = y - mx
        return x1 -> (m * x1) + b; // y = mx + b
    }

    /**
     * Finds the approximate area under the curve using the left Riemann sum rule with
     * <code>n</code> rectangles.
     *
     * @param a The lower limit of integration.
     * @param b The upper limit of integration.
     * @param function The function being used to calculate the left Riemann sum.
     * @param n The number of rectangles being used to estimate the area under the curve.
     * @return The approximate area under the curve by the left Riemann sum rule.
     */
    public double leftRiemannSum(double a, double b, Function function, int n) {
        if (n < 1) {
            throw new IllegalArgumentException("There must be at least one rectangle.");
        }

        double sum = 0;
        double deltaX = (b - a) / n;
        for (double x = a; x < b; x += deltaX) {
            sum += function.f(x);
        }

        return round(deltaX * sum, leftRiemannSumRoundingDecimalPlaces);
    }

    /**
     * Finds the approximate area under the curve using the right Riemann sum rule with
     * <code>n</code> rectangles.
     *
     * @param a The lower limit of integration.
     * @param b The upper limit of integration.
     * @param function The function being used to calculate the right Riemann sum.
     * @param n The number of rectangles being used to estimate the area under the curve.
     * @return The approximate area under the curve by the right Riemann sum rule.
     */
    public double rightRiemannSum(double a, double b, Function function, int n) {
        if (n < 1) {
            throw new IllegalArgumentException("There must be at least one rectangle.");
        }

        double sum = 0;
        double deltaX = (b - a) / n;
        for (double x = (a + deltaX); x <= b + 0.00001; x += deltaX) {
            sum += function.f(x);
        }

        return round(deltaX * sum, rightRiemannSumRoundingDecimalPlaces);
    }

    /**
     * Finds the approximate area under the curve using the midpoint rule with
     * <code>n</code> rectangles.
     *
     * @param a The lower limit of integration.
     * @param b The upper limit of integration.
     * @param function The function being used to calculate the midpoint rule.
     * @param n The number of rectangles being used to estimate the area under the curve.
     * @return The approximate area under the curve by the midpoint rule.
     */
    public double midpointRule(double a, double b, Function function, int n) {
        if (n < 1) {
            throw new IllegalArgumentException("There must be at least one rectangle.");
        }

        double sum = 0;
        double deltaX = (b - a) / n;
        for (double x = a; x < b; x += deltaX) {
            sum += function.f((x + (x + deltaX)) / 2);
        }

        return round(deltaX * sum, midpointRuleRoundingDecimalPlaces);
    }

    /**
     * Finds the approximate area under the curve using the trapezoidal sum rule with
     * <code>n</code> trapezoids.
     *
     * @param a The lower limit of integration.
     * @param b The upper limit of integration.
     * @param function The function being used to calculate the trapezoidal sum.
     * @param n The number of trapezoids being used to estimate the area under the curve.
     * @return The approximate area under the curve by the trapezoidal sum rule.
     */
    public double trapezoidalSum(double a, double b, Function function, int n) {
        if (n < 1) {
            throw new IllegalArgumentException("There must be at least one trapezoid.");
        }

        double sum = 0;
        double deltaX = (b - a) / n;
        for (double x = a; x <= b + 0.00001; x += deltaX) {
            if(x == a || x >= b) {
                sum += function.f(x);
            } else {
                sum += 2 * function.f(x);
            }
        }

        return round(((b - a) / (2 * n)) * sum, trapezoidalSumRoundingDecimalPlaces);
    }

    /**
     * Finds the volume of revolution for the region bounded by <code>functionTop</code>,
     * <code>functionBottom</code>, x = <code>a</code>, and x = <code>b</code>, about y =
     * <code>axis</code>.
     *
     * <p>Note: Vertical revolutions can also be made with this function. Input your data as if
     * the data was rotated 90 degrees and to be rotated horizontally. There should be no
     * mathematical difference between the two problems.
     *
     * @param a The lower limit of integration.
     * @param b The upper limit of integration.
     * @param axis The y value of the axis of rotation, where 0 is about the x-axis.
     * @param functionTop The top function defining the bounded region.
     * @param functionBottom The bottom function defining the bounded region.
     * @return The volume of revolution.
     */
    public double revolve(double a, double b, double axis, Function functionTop, Function functionBottom) {
        // The top function with the axis offset, squared.
        Function squaredFunctionTop = x -> Math.pow(axis - functionTop.f(x), 2);

        // The bottom function with the axis offset, squared.
        Function squaredFunctionBottom = x -> Math.pow(axis - functionBottom.f(x), 2);

        // Split the volume of revolution formula into two separate integrals.
        double volume = Math.PI * (integrateRaw(a, b, squaredFunctionTop) - integrate(a, b, squaredFunctionBottom));
        return round(volume, revolutionRoundingDecimalPlaces);
    }

    /**
     * Finds the volume of a known cross-section for any of the 5 common known
     * cross-sections: square, equilateral triangle, isosceles triangle, right,
     * triangle, and semicircle.
     *
     * <p>Note: Constants have been defined in <code>Calculatte</code> for your
     * ease of use when defining what <code>type</code> of cross-section you are
     * solving for.
     *
     * @param a The lower limit of integration.
     * @param b The upper limit of integration.
     * @param functionTop The top function defining the bounded region.
     * @param functionBottom The bottom function defining the bounded region.
     * @param type The type of cross-section.
     * @return The volume of the known cross-section.
     * @see io.github.derivasians.calculatte.Calculatte#SQUARE
     * @see io.github.derivasians.calculatte.Calculatte#EQUILATERAL_TRIANGLE
     * @see io.github.derivasians.calculatte.Calculatte#ISOSCELES_TRIANGLE
     * @see io.github.derivasians.calculatte.Calculatte#RIGHT_TRIANGLE
     * @see io.github.derivasians.calculatte.Calculatte#SEMICIRCLE
     */
    public double crossSection(double a, double b, Function functionTop, Function functionBottom, int type) {
        Function integrand = switch (type) {
            case 0 -> // Square
                    x -> Math.pow(functionTop.f(x) - functionBottom.f(x), 2);
            case 1 -> // Equilateral triangle
                    x -> (Math.sqrt(3) / 4) * Math.pow(functionTop.f(x) - functionBottom.f(x), 2);
            case 2 -> // Isosceles triangle
                    x -> 0.75 * Math.pow(functionTop.f(x) - functionBottom.f(x), 2);
            case 3 -> // Right triangle
                    x -> 0.5 * Math.pow(functionTop.f(x) - functionBottom.f(x), 2);
            case 4 -> // Semicircle
                    x -> (Math.PI / 8) * Math.pow(functionTop.f(x) - functionBottom.f(x), 2);
            default -> { // Error
                final String errorMessage =
                        String.format(
                                "<%s> is not a valid cross-section type. Please enter a valid cross-section type (0 - 4).",
                                type
                        );
                throw new IllegalArgumentException(errorMessage);
            }
        };

        return round(integrateRaw(a, b, integrand), crossSectionsRoundingDecimalPlaces);
    }

    /**
     * Finds the volume of a known cross-section for a custom made cross-section
     * formula.
     *
     * @param a The lower limit of integration.
     * @param b The upper limit of integration.
     * @param integrand The integrand of the integral when taking the volume of a
     *                  known cross-section.
     * @return The volume of the known cross-section.
     * @see io.github.derivasians.calculatte.Calculatte#crossSection(double, double, Function, Function, int) 
     */
    public double crossSection(double a, double b, Function integrand) {
        return round(integrateRaw(a, b, integrand), crossSectionsRoundingDecimalPlaces);
    }

    /**
     * Finds the limit of <code>function</code> at point <code>x</code>. Returns <code>Double.NaN</code>
     * if the limit does not exist.
     *
     * @param x The x-value to find the limit at.
     * @param function The function to find the limit of.
     * @return The value of the limit or <code>Double.Nan</code>
     * if the limit DNE.
     */
    public double limit(double x, Function function) {
        if ((leftLimit(x, function) > rightLimit(x, function) + limitTolerance) ||
                (leftLimit(x, function) < rightLimit(x, function) - limitTolerance)) {
            return Double.NaN;
        }

        return round(function.f(x + limitOffset),
                limitRoundingDecimalPlaces);
    }

    /**
     * Finds the left limit of <code>function</code> at point <code>x</code>.
     *
     * @param x The x-value to find the limit at.
     * @param function The function to find the limit of.
     * @return The value of the left limit.
     */
    public double leftLimit(double x, Function function) {
        return round(function.f(x - limitOffset),
                leftLimitRoundingDecimalPlaces);
    }

    /**
     * Finds the right limit of <code>function</code> at point <code>x</code>.
     *
     * @param x The x-value to find the limit at.
     * @param function The function to find the limit of.
     * @return The value of the right limit.
     */
    public double rightLimit(double x, Function function) {
        return round(function.f(x + limitOffset),
                rightLimitRoundingDecimalPlaces);
    }

    /**
     * Finds the area bounded by a polar function, <code>r</code> of theta,
     * between two radian measures.
     *
     * @param a The lower limit of integration.
     * @param b The upper limit of integration.
     * @param r The polar function of theta bounding a specified area.
     * @return The area of the bounded region.
     */
    public double polarArea(double a, double b, Function r) {
        Function squaredR = x -> Math.pow(r.f(x), 2);
        double area = 0.5 * integrateRaw(a, b, squaredR);
        return round(area, polarAreaRoundingDecimalPlaces);
    }
}
