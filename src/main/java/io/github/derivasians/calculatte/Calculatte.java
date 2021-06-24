package io.github.derivasians.calculatte;

public class Calculatte {
    private static double ROUND_FLOOR = 0.000000001;
    private static double H = 0.000000001;
    private static int N = 10000000;

    /**
     * Sets the new <code>ROUND_FLOOR</code> value. Any value smaller than this
     * value will be rounded down to zero.
     *
     * @param roundFloor The new <code>ROUND_FLOOR</code> value.
     * @see #ROUND_FLOOR
     * @see #round(double)
     */
    public static void setRoundFloor(double roundFloor) { ROUND_FLOOR = roundFloor; }

    /**
     * Sets the accuracy value for derivation calculations.
     * @param h The new offset value for B. The smaller the more accurate.
     * @see #H
     * @see #derivate(double, Function) 
     */
    public static void setH(double h) { H = h; }

    /**
     * Sets the accuracy value for integration calculations.
     * @param n The new <code>N</code> value in Simpson's rule. The larger the more accurate.
     * @see #N
     * @see #integrate(double, double, Function) 
     */
    public static void setN(int n) { N = n; }

    /**
     * Gets the <code>ROUND_FLOOR</code> value. Any value smaller than this
     * value will be rounded down to zero.
     * 
     * @return The <code>ROUND_FLOOR</code> value.
     * @see #ROUND_FLOOR
     * @see #round(double) 
     */
    public static double getRoundFloor() { return ROUND_FLOOR; }

    /**
     * Gets the accuracy value for derivation calculations.
     *
     * @return The offset value for B. The smaller the more accurate.
     * @see #H
     * @see #derivate(double, Function)
     */
    public static double getH() { return H; }

    /**
     * Gets the accuracy value for integration calculations.
     *
     * @return The N value in Simpson's rule. The larger the more accurate.
     * @see #N
     * @see #integrate(double, double, Function)
     */
    public static int getN() { return N; }

    /**
     * Rounds near zero values to zero. It is typical that Calculatte returns near
     * zero values that should be zero, but are not due to accuracy issues. Any value
     * smaller than <code>ROUND_FLOOR</code> will be rounded down to zero.
     *
     * @param x The value to be rounded.
     * @return The rounded value.
     * @see #ROUND_FLOOR
     */
    public static double round(double x) {
        if (x < ROUND_FLOOR) {
            return 0;
        }

        return x;
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
        double h = (b - a) / (N - 1); // Step size.

        // 1/3 terms.
        double sum = 1.0 / 3.0 * (function.f(a) + function.f(b));

        // 4/3 terms.
        for (int i = 1; i < N - 1; i += 2) {
            sum += 4.0 / 3.0 * function.f(a + h * i);
        }

        // 2/3 terms.
        for (int i = 2; i < N - 1; i += 2) {
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
        return (function.f(x + H) - function.f(x)) / ((x + H) - x);
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

    public static double crossSection(double a, double b, Function functionTop, Function functionBottom, Function cross) {
        // TODO: Remove temporary return statement.
        return 0;
    }

    /**
     * Calculates the volume of revolution for the top function and bottom function,
     * functionTop and functionBottom respectively, from a to b about the axis, y = axis.
     *
     * @param a    The lower limit of integration.
     * @param b    The upper limit of integration.
     * @param axis The y/x value of the axis of rotation, where 0 is about the x/y-axis.
     * @return The volume of revolution.
     */
    public static double revolve(double a, double b, double axis, Function functionTop, Function functionBottom) {
        // The top function with the axis offset squared.
        Function squaredFunctionTop = new Squared() {
            @Override
            public double f(double x) {
                return Math.pow(axis - functionTop.f(x), 2);
            }
        };

        // The bottom function with the axis offset squared.
        Function squaredFunctionBottom = new Squared() {
            @Override
            public double f(double x) {
                return Math.pow(axis - functionBottom.f(x), 2);
            }
        };

        // Split the volume of revolution formula into two separate integrals.
        return Math.PI * (integrate(a, b, squaredFunctionTop) - integrate(a, b, squaredFunctionBottom));
    }
}
