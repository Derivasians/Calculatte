package com.derivasians.calculatte;

public class Calculatte {
    private static double H = 0.0000000000001;
    private static int N = 10000000;

    public static void setH(double h) { H = h; }
    public static void setN(int n) { N = n; }

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
     * @param x The point on the function to find the derivative.
     * @param function The function to find the derivative of.
     * @return The derivative of the function at point, x.
     */
    public static double derivate(double x, Function function) {
        return (function.f(x + H) - function.f(x)) / ((x + H) - x);
    }

    /**
     * Calculates the volume of revolution for the top function and bottom function
     * of this instance of RevolutionSoup from a to b about the x-axis.
     * of this instance of AreaSoup from a to b about the x-axis.
     *
     * @param a    The lower limit of integration.
     * @param b    The upper limit of integration.
     * @param axis The y/x value of the axis of rotation where 0 is about the x/y-axis.
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

    /**
     * Main method for debugging.
     */
    public static void main(String[] args) {
        Function TwoX = x -> 2 * x;

        double a = Calculatte.integrate(0, 2, TwoX);
        System.out.println(a);
        // OUT: 3.9999997333333286
        // EXPECTED: 4

        Function XSquared = x -> Math.pow(x, 2);
        Function X = x -> 0;
        double v = Calculatte.revolution(0, 2, 0, XSquared, X);
        System.out.println(v);
        // OUT: 20.10618963194089
        // EXPECTED: 20.10618963194089

        // TODO: Make an in-house rounding system to correct errors in Simpson's rule.
    }
}
