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
    public static double round(double x, int decimalPlaces) {
        if (decimalPlaces < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(x));
        bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
        return bd.doubleValue();
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

        return sum * h;
    }

    /**
     * Finds the derivate of the function at point, x.
     *
     * @param x        The point on the function to find the derivative.
     * @param function The function to find the derivative of.
     * @return The derivative of the function at point, x.
     */
    public static double derivate(double x, Function function) {
        return (function.f(x + CalculatteEnvironment.H) - function.f(x)) / ((x + CalculatteEnvironment.H) - x);
    }

    /**
     * Finds the tangent line of function at point, x.
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
        double sum = 0;
        for (double x = a; x < b; x += ((b - a) / n)) {
            sum += function.f(x);
        }

        return ((b - a) / n) * sum;
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
    public static double rightRiemannSum( double a, double b, Function function, int n ) {
        double sum = 0;
        for (double x = (a + ((b - a) / n)); x <= b + 0.00001; x += ((b - a) / n)) {
            sum += function.f(x);
        }

        return ((b - a) / n) * sum;
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
    public static double trapezoidalSum( double a, double b, Function function, int n ) {
        double sum = 0;
        for (double x = a; x <= b + 0.00001; x += ((b - a) / n)) {
            if( x == a || x >= b ) {
                sum += function.f(x);
            } else {
                sum += 2 * function.f(x);
            }
        }

        return ((b - a) / (2 * n)) * sum;
    }

    /**
     * Finds the limit of <code>function</code> at point <code>x</code>. Returns <code>Double.NaN</code>
     * if the limit does not exist.
     *
     * @param x The x-value to find the limit at.
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
     * Finds the left limit of <code>function</code> at point <code>x</code>. Returns <code>Double.NaN</code>
     * if the limit does not exist.
     *
     * @param x The x-value to find the limit at.
     * @param function The function to find the limit of.
     * @return The value of the limit.
     */
    public static double leftLimit(double x, Function function) {
        return round(function.f(x - CalculatteEnvironment.LIMIT_OFFSET),
                CalculatteEnvironment.LIMIT_ROUNDING_DECIMAL_PLACES);
    }

    /**
     * Finds the right limit of <code>function</code> at point <code>x</code>. Returns <code>Double.NaN</code>
     * if the limit does not exist.
     *
     * @param x The x-value to find the limit at.
     * @param function The function to find the limit of.
     * @return The value of the limit.
     */
    public static double rightLimit(double x, Function function) {
        return round(function.f(x + CalculatteEnvironment.LIMIT_OFFSET),
                CalculatteEnvironment.LIMIT_ROUNDING_DECIMAL_PLACES);
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
     * @param a    The lower limit of integration.
     * @param b    The upper limit of integration.
     * @param axis The y value of the axis of rotation, where 0 is about the x-axis.
     * @return The volume of revolution.
     */
    public static double revolve(double a, double b, double axis, Function functionTop, Function functionBottom) {
        // The top function with the axis offset, squared.
        Function squaredFunctionTop = x -> Math.pow(axis - functionTop.f(x), 2);

        // The bottom function with the axis offset, squared.
        Function squaredFunctionBottom = x -> Math.pow(axis - functionBottom.f(x), 2);

        // Split the volume of revolution formula into two separate integrals.
        return Math.PI * (integrate(a, b, squaredFunctionTop) - integrate(a, b, squaredFunctionBottom));
    }
}
