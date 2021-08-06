package io.github.derivasians.calculatte;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Holds all methods and properties to perform basic calculus operations.
 *
 * @author <a href="mailto:okashita.matthew@gmail.com">Matthew Okashita</a>
 * @author <a href="mailto:benigno.joseph.s@gmail.com">Joseph Benigno</a>
 * @version 1.0.0
 */
public final class Calculatte {
    /**
     * Prevent instantiation of this class.
     */
    private Calculatte() {}

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
    public static double round(double x, int decimalPlaces) {
        if (decimalPlaces < 0) {
            return x;
        }

        if (x > CalculatteEnvironment.positiveInfinity) {
            return Double.POSITIVE_INFINITY;
        }

        if (x < CalculatteEnvironment.negativeInfinity) {
            return Double.NEGATIVE_INFINITY;
        }

        BigDecimal bd = new BigDecimal(Double.toString(x));
        bd = bd.setScale(decimalPlaces, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }

    /**
     * Integrates the function from a to b using Simpson's rule.
     *
     * @param a        The lower limit of integration.
     * @param b        The upper limit of integration.
     * @param function The function to integrate.
     * @return The area under the curve from a to b.
     */
    public static double integrate(double a, double b, Function function) {
        double h = (b - a) / (CalculatteEnvironment.n - 1); // Step size.

        // 1/3 terms.
        double sum = 1.0 / 3.0 * (function.f(a) + function.f(b));

        // 4/3 terms.
        for (int i = 1; i < CalculatteEnvironment.n - 1; i += 2) {
            sum += 4.0 / 3.0 * function.f(a + h * i);
        }

        // 2/3 terms.
        for (int i = 2; i < CalculatteEnvironment.n - 1; i += 2) {
            sum += 2.0 / 3.0 * function.f(a + h * i);
        }

        return round(sum * h, CalculatteEnvironment.integrationRoundingDecimalPlaces);
    }

    /**
     * Integrates the function from a to b using Simpson's rule, without rounding.
     *
     * @param a        The lower limit of integration.
     * @param b        The upper limit of integration.
     * @param function The function to integrate.
     * @return The area under the curve from a to b, not rounded.
     */
    private static double integrateRaw(double a, double b, Function function) {
        double h = (b - a) / (CalculatteEnvironment.n - 1); // Step size.

        // 1/3 terms.
        double sum = 1.0 / 3.0 * (function.f(a) + function.f(b));

        // 4/3 terms.
        for (int i = 1; i < CalculatteEnvironment.n - 1; i += 2) {
            sum += 4.0 / 3.0 * function.f(a + h * i);
        }

        // 2/3 terms.
        for (int i = 2; i < CalculatteEnvironment.n - 1; i += 2) {
            sum += 2.0 / 3.0 * function.f(a + h * i);
        }

        return sum * h;
    }

    /**
     * Finds the derivate of the function at point, x.
     *
     * @param x        The point on the function to find the derivative.
     * @param function The function to find the derivative of.
     * @return The derivative of the function at point, x or <code>Double.Nan</code>
     * if the derivative DNE.
     */
    public static double derivate(double x, Function function) {
        if ((leftDerivative(x, function) > rightDerivative(x, function) + CalculatteEnvironment.derivativeTolerance) ||
                (leftDerivative(x, function) < rightDerivative(x, function) - CalculatteEnvironment.derivativeTolerance)) {
            return Double.NaN;
        }

        double slope =
                (function.f(x + CalculatteEnvironment.h) - function.f(x)) / ((x + CalculatteEnvironment.h) - x);
        return round(slope, CalculatteEnvironment.derivationRoundingDecimalPlaces);
    }

    /**
     * Finds the left derivate of the function at point, x.
     *
     * @param x        The point on the function to find the derivative.
     * @param function The function to find the derivative of.
     * @return The derivative of the function at point, x.
     */
    public static double leftDerivative(double x, Function function) {
        x -= CalculatteEnvironment.derivativeOffset;
        return (function.f(x + CalculatteEnvironment.h) - function.f(x)) / ((x + CalculatteEnvironment.h) - x);
    }

    /**
     * Finds the right derivate of the function at point, x.
     *
     * @param x        The point on the function to find the derivative.
     * @param function The function to find the derivative of.
     * @return The derivative of the function at point, x.
     */
    public static double rightDerivative(double x, Function function) {
        x += CalculatteEnvironment.derivativeOffset;
        return (function.f(x + CalculatteEnvironment.h) - function.f(x)) / ((x + CalculatteEnvironment.h) - x);
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
     * Finds the approximate area under the curve using the left Riemann sum rule with
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

        return round(deltaX * sum, CalculatteEnvironment.leftRiemannSumRoundingDecimalPlaces);
    }

    /**
     * Finds the approximate area under the curve using the right Riemann sum rule with
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

        return round(deltaX * sum, CalculatteEnvironment.rightRiemannSumRoundingDecimalPlaces);
    }

    /**
     * Finds the approximate area under the curve using the midpoint rule with
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

        return round(deltaX * sum, CalculatteEnvironment.midpointRuleRoundingDecimalPlaces);
    }

    /**
     * Finds the approximate area under the curve using the trapezoidal sum rule with
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

        return round(((b - a) / (2 * n)) * sum, CalculatteEnvironment.trapezoidalSumRoundingDecimalPlaces);
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
        double volume = Math.PI * (integrateRaw(a, b, squaredFunctionTop) - integrate(a, b, squaredFunctionBottom));
        return round(volume, CalculatteEnvironment.revolutionRoundingDecimalPlaces);
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
            default -> // Error
                    throw new InvalidCrossSectionTypeException(Integer.toString(type));
        };

        return round(integrateRaw(a, b, integrand), CalculatteEnvironment.crossSectionsRoundingDecimalPlaces);
    }

    /**
     * Finds the volume of a known cross-section for a custom made cross-section
     * formula.
     *
     * @param a         The lower limit of integration.
     * @param b         The upper limit of integration.
     * @param integrand The integrand of the integral when taking the volume of a
     *                  known cross-section.
     * @return The volume of the known cross-section.
     * @see io.github.derivasians.calculatte.Calculatte#crossSection(double, double, Function, Function, int) 
     */
    public static double crossSection(double a, double b, Function integrand) {
        return round(integrateRaw(a, b, integrand), CalculatteEnvironment.crossSectionsRoundingDecimalPlaces);
    }

    /**
     * Finds the limit of <code>function</code> at point <code>x</code>. Returns <code>Double.NaN</code>
     * if the limit does not exist.
     *
     * @param x        The x-value to find the limit at.
     * @param function The function to find the limit of.
     * @return The value of the limit or <code>Double.Nan</code>
     * if the limit DNE.
     */
    public static double limit(double x, Function function) {
        if ((leftLimit(x, function) > rightLimit(x, function) + CalculatteEnvironment.limitTolerance) ||
                (leftLimit(x, function) < rightLimit(x, function) - CalculatteEnvironment.limitTolerance)) {
            return Double.NaN;
        }

        return round(function.f(x + CalculatteEnvironment.limitOffset),
                CalculatteEnvironment.limitRoundingDecimalPlaces);
    }

    /**
     * Finds the left limit of <code>function</code> at point <code>x</code>.
     *
     * @param x        The x-value to find the limit at.
     * @param function The function to find the limit of.
     * @return The value of the left limit.
     */
    public static double leftLimit(double x, Function function) {
        return round(function.f(x - CalculatteEnvironment.limitOffset),
                CalculatteEnvironment.leftLimitRoundingDecimalPlaces);
    }

    /**
     * Finds the right limit of <code>function</code> at point <code>x</code>.
     *
     * @param x        The x-value to find the limit at.
     * @param function The function to find the limit of.
     * @return The value of the right limit.
     */
    public static double rightLimit(double x, Function function) {
        return round(function.f(x + CalculatteEnvironment.limitOffset),
                CalculatteEnvironment.rightLimitRoundingDecimalPlaces);
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
    public static double polarArea(double a, double b, Function r) {
        Function squaredR = x -> Math.pow(r.f(x), 2);
        double area = 0.5 * integrateRaw(a, b, squaredR);
        return round(area, CalculatteEnvironment.polarAreaRoundingDecimalPlaces);
    }
}
