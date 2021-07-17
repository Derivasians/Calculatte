package io.github.derivasians.calculatte;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Holds all methods and properties to perform basic calculus operations.
 *
 * @version 1.0 Snapshot
 * @author Matthew Okashita
 * @author Joseph Benigno
 */
public class Calculatte {
    /**
     * Rounds doubles according to the IEEE 754 standard of rounding half to even.
     *
     * <p>Note: If <code>decimalPlaces</code> is set to <code>-1</code>, <code>x</code>
     * will not be rounded.</p>
     *
     * @param x The value to be rounded.
     * @param decimalPlaces The number of decimal places to round to.
     * @return The rounded value.
     */
    public static double round(double x, int decimalPlaces) {
        if (decimalPlaces < 0) {
            return x;
        }

        if (x > CalculatteEnvironment.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        }

        if (x < CalculatteEnvironment.NEGATIVE_INFINITY) {
            return Double.NEGATIVE_INFINITY;
        }

        BigDecimal bd = new BigDecimal(Double.toString(x));
        bd = bd.setScale(decimalPlaces, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }

    /**
     * Finds the factorial of double <code>n</code> using Stirling's approximation.
     *
     * @param n The value to take the factorial of.
     * @return The factorial of the value.
     */
    public static double factorial(double n) {
        if (n < 0) {
            return Double.NaN;
        }

        if (n == 0) {
            return 1;
        }

        double stirling = Math.pow(n, n) * Math.pow(Math.E, -n) * Math.sqrt(2 * Math.PI * n) * (1 + (1 / (12 * n)));
        return round(stirling, CalculatteEnvironment.FACTORIAL_ROUNDING_DECIMAL_PLACES);
    }

    /**
     * Integrates the function from a to b using Simpson's rule.
     *
     * @param a        The lower limit of integration in degrees.
     * @param b        The upper limit of integration in degrees.
     * @param function The function to integrate.
     * @return The area under the curve from a to b.
     */
    public static double integrate(double a, double b, Function function) {
        double h = (b - a) / (CalculatteEnvironment.N - 1); // Step size.

        // 1/3 terms.
        double sum = 1.0 / 3.0 * (function.f(a) + function.f(b));

        // 4/3 terms.
        for (int i = 1; i < CalculatteEnvironment.N - 1; i += 2) {
            sum += 4.0 / 3.0 * function.f(a + h * i);
        }

        // 2/3 terms.
        for (int i = 2; i < CalculatteEnvironment.N - 1; i += 2) {
            sum += 2.0 / 3.0 * function.f(a + h * i);
        }

        return round(sum * h, CalculatteEnvironment.INTEGRATION_ROUNDING_DECIMAL_PLACES);
    }

    /**
     * Finds the derivate of the function at point, x.
     *
     * @param x        The point on the function to find the derivative.
     * @param function The function to find the derivative of.
     * @return The derivative of the function at point, x.
     */
    public static double derivate(double x, Function function) {
        double slope =
                (function.f(x + CalculatteEnvironment.H) - function.f(x)) / ((x + CalculatteEnvironment.H) - x);
        return round(slope, CalculatteEnvironment.DERIVATION_ROUNDING_DECIMAL_PLACES);
    }

    /**
     * Finds the tangent line of <code>function</code> at point, <code>x</code>.
     *
     * @param x        The x-value at which to find the tangent line of.
     * @param function The function to find the tangent line of.
     * @return The tangent line.
     */
    public static Function tangentLine(double x, Function function) {
        double m = derivate(x, function);
        double b = function.f(x) - (m * x); // b = y - mx
        return x1 -> (m * x1) + b; // y = mx + b
    }

    /**
     * Returns the approximate area under the curve using the left Riemann sum rule with
     * <code>n</code> rectangles.
     *
     * @param a        The lower limit of integration.
     * @param b        The upper limit of integration.
     * @param function The function being used to calculate the left Riemann sum.
     * @param n        The number of rectangles being used to estimate the area under the curve.
     * @return The approximate area under the curve by the left Riemann sum rule.
     */
    public static double leftRiemannSum(double a, double b, Function function, int n) {
        if (n < 1) {
            throw new IllegalArgumentException("There must be at least one rectangle.");
        }

        double sum = 0;
        double deltaX = (b - a) / n;
        for (double x = a; x < b; x += deltaX) {
            sum += function.f(x);
        }

        return round(deltaX * sum, CalculatteEnvironment.LEFT_RIEMANN_SUM_ROUNDING_DECIMAL_PLACES);
    }

    /**
     * Returns the approximate area under the curve using the right Riemann sum rule with
     * <code>n</code> rectangles.
     *
     * @param a        The lower limit of integration.
     * @param b        The upper limit of integration.
     * @param function The function being used to calculate the right Riemann sum.
     * @param n        The number of rectangles being used to estimate the area under the curve.
     * @return The approximate area under the curve by the right Riemann sum rule.
     */
    public static double rightRiemannSum(double a, double b, Function function, int n) {
        if (n < 1) {
            throw new IllegalArgumentException("There must be at least one rectangle.");
        }

        double sum = 0;
        double deltaX = (b - a) / n;
        for (double x = (a + deltaX); x <= b + 0.00001; x += deltaX) {
            sum += function.f(x);
        }

        return round(deltaX * sum, CalculatteEnvironment.RIGHT_RIEMANN_SUM_ROUNDING_DECIMAL_PLACES);
    }

    /**
     * Returns the approximate area under the curve using the midpoint rule with
     * <code>n</code> rectangles.
     *
     * @param a        The lower limit of integration.
     * @param b        The upper limit of integration.
     * @param function The function being used to calculate the midpoint rule.
     * @param n        The number of rectangles being used to estimate the area under the curve.
     * @return The approximate area under the curve by the midpoint rule.
     */
    public static double midpointRule(double a, double b, Function function, int n) {
        if (n < 1) {
            throw new IllegalArgumentException("There must be at least one rectangle.");
        }

        double sum = 0;
        double deltaX = (b - a) / n;
        for (double x = a; x < b; x += deltaX) {
            sum += function.f((x + (x + deltaX)) / 2);
        }

        return round(deltaX * sum, CalculatteEnvironment.MIDPOINT_RULE_ROUNDING_DECIMAL_PLACES);
    }

    /**
     * Returns the approximate area under the curve using the trapezoidal sum rule with
     * <code>n</code> trapezoids.
     *
     * @param a        The lower limit of integration.
     * @param b        The upper limit of integration.
     * @param function The function being used to calculate the trapezoidal sum.
     * @param n        The number of trapezoids being used to estimate the area under the curve.
     * @return The approximate area under the curve by the trapezoidal sum rule.
     */
    public static double trapezoidalSum(double a, double b, Function function, int n) {
        if (n < 1) {
            throw new IllegalArgumentException("There must be at least one rectangle.");
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

        return round(((b - a) / (2 * n)) * sum, CalculatteEnvironment.TRAPEZOIDAL_SUM_ROUNDING_DECIMAL_PLACES);
    }

    /**
     * Calculates the volume of revolution for the region bounded by <code>functionTop</code>,
     * <code>functionBottom</code>, x = <code>a</code>, and x = <code>b</code>, about y =
     * <code>axis</code>.
     *
     * <p>Note: Vertical revolutions can also be made with this function. Input your data as if
     * the data was rotated 90 degrees and to be rotated horizontally. There should be no
     * mathematical difference between the two problems.</p>
     *
     * @param a              The lower limit of integration.
     * @param b              The upper limit of integration.
     * @param axis           The y value of the axis of rotation, where 0 is about the x-axis.
     * @param functionTop    The top function defining the bounded region.
     * @param functionBottom The bottom function defining the bounded region.
     * @return The volume of revolution.
     */
    public static double revolve(double a, double b, double axis, Function functionTop, Function functionBottom) {
        // The top function with the axis offset, squared.
        Function squaredFunctionTop = x -> Math.pow(axis - functionTop.f(x), 2);

        // The bottom function with the axis offset, squared.
        Function squaredFunctionBottom = x -> Math.pow(axis - functionBottom.f(x), 2);

        // Split the volume of revolution formula into two separate integrals.
        double volume = Math.PI * (integrate(a, b, squaredFunctionTop) - integrate(a, b, squaredFunctionBottom));
        return round(volume, CalculatteEnvironment.REVOLUTION_ROUNDING_DECIMAL_PLACES);
    }

    /**
     * Finds the volume of a known cross-section for any of the 5 common known
     * cross-sections: square, equilateral triangle, isosceles triangle, right,
     * triangle, and semicircle.
     *
     * <p>Note: Constants have been defined in <code>CalculatteEnvironment</code>
     * for your ease of use when defining what <code>type</code> of cross-section
     * you are solving for.
     *
     * @param a              The lower limit of integration.
     * @param b              The upper limit of integration.
     * @param functionTop    The top function defining the bounded region.
     * @param functionBottom The bottom function defining the bounded region.
     * @param type           The type of cross-section.
     * @return The volume of the known cross-section.
     * @see io.github.derivasians.calculatte.CalculatteEnvironment#SQUARE
     * @see io.github.derivasians.calculatte.CalculatteEnvironment#EQUILATERAL_TRIANGLE
     * @see io.github.derivasians.calculatte.CalculatteEnvironment#ISOSCELES_TRIANGLE
     * @see io.github.derivasians.calculatte.CalculatteEnvironment#RIGHT_TRIANGLE
     * @see io.github.derivasians.calculatte.CalculatteEnvironment#SEMICIRCLE
     */
    public static double crossSection(double a, double b, Function functionTop, Function functionBottom, int type) {
        Function cross = switch (type) {
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
            default -> // Error
                    throw new IllegalArgumentException("Please enter a valid cross-section type (0 - 4).");
        };

        return round(integrate(a, b, cross), CalculatteEnvironment.CROSS_SECTIONS_ROUNDING_DECIMAL_PLACES);
    }

    /**
     * Finds the volume of a known cross-section for a custom made cross-section
     * formula.
     *
     * @param a     The lower limit of integration.
     * @param b     The upper limit of integration.
     * @param cross The integrand of the integral when taking the volume of a
     *              known cross-section.
     * @return The volume of the known cross-section.
     * @see io.github.derivasians.calculatte.Calculatte#crossSection(double, double, Function, Function, int) 
     */
    public static double crossSection(double a, double b, Function cross) {
        return round(integrate(a, b, cross), CalculatteEnvironment.CROSS_SECTIONS_ROUNDING_DECIMAL_PLACES);
    }

    /**
     * Finds the limit of <code>function</code> at point <code>x</code>. Returns <code>Double.NaN</code>
     * if the limit does not exist.
     *
     * @param x        The x-value to find the limit at.
     * @param function The function to find the limit of.
     * @return The value of the limit.
     */
    public static double limit(double x, Function function) {
        if ((leftLimit(x, function) > rightLimit(x, function) + CalculatteEnvironment.LIMIT_TOLERANCE) ||
                (leftLimit(x, function) < rightLimit(x, function) - CalculatteEnvironment.LIMIT_TOLERANCE)) {
            return Double.NaN;
        }

        return round(function.f(x + CalculatteEnvironment.LIMIT_OFFSET),
                CalculatteEnvironment.LIMIT_ROUNDING_DECIMAL_PLACES);
    }

    /**
     * Finds the left limit of <code>function</code> at point <code>x</code>.
     *
     * @param x        The x-value to find the limit at.
     * @param function The function to find the limit of.
     * @return The value of the left limit.
     */
    public static double leftLimit(double x, Function function) {
        return round(function.f(x - CalculatteEnvironment.LIMIT_OFFSET),
                CalculatteEnvironment.LIMIT_ROUNDING_DECIMAL_PLACES);
    }

    /**
     * Finds the right limit of <code>function</code> at point <code>x</code>.
     *
     * @param x        The x-value to find the limit at.
     * @param function The function to find the limit of.
     * @return The value of the right limit.
     */
    public static double rightLimit(double x, Function function) {
        return round(function.f(x + CalculatteEnvironment.LIMIT_OFFSET),
                CalculatteEnvironment.LIMIT_ROUNDING_DECIMAL_PLACES);
    }
}
