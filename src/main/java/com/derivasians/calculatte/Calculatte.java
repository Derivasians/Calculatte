package com.derivasians.calculatte;

public class Calculatte {
    private static final int N = 10000000;

    /**
     * Integrates the function, f, from a to b using Simpson's rule.
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
     * Main method for debugging.
     */
    public static void main(String[] args) {
        Function TwoX = x -> 2 * x;

        double a = Calculatte.integrate(0, 2, TwoX);
        System.out.println(a);
        // OUT: 3.9999997333333286
        // EXPECTED: 4

        // TODO: Make an in-house rounding system to correct errors in Simpson's rule.
    }
}
