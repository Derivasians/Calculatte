package io.github.derivasians.calculatte;

/**
 * Contains all instance variables required for Calculatte to run.
 * These mainly consist of values used for rounding and other accuracy
 * controls within calculations.
 */
public class CalculatteEnvironment {
    // Rounding values. These values represent how many decimal places
    // each of their respective calculations should be rounded too.
    // Only the final calculation is rounded, intermediate values are
    // never rounded.
    public static int INTEGRATION_ROUNDING_DECIMAL_PLACES = 8;
    public static int DERIVATION_ROUNDING_DECIMAL_PLACES = 8;
    public static int LEFT_RIEMANN_SUM_ROUNDING_DECIMAL_PLACES = 8;
    public static int RIGHT_RIEMANN_SUM_ROUNDING_DECIMAL_PLACES = 8;
    public static int TRAPEZOIDAL_SUM_ROUNDING_DECIMAL_PLACES = 8;
    public static int REVOLUTION_ROUNDING_DECIMAL_PLACES = 8;
    public static int LIMIT_ROUNDING_DECIMAL_PLACES = 8;

    /**
     * All values greater than or equal to <code>POSITIVE_INFINITY</code> will be rounded up to
     * <code>Double.POSITIVE_INFINITY</code> by <code>Calculatte.round()</code>.
     */
    public static double POSITIVE_INFINITY = 9.0E17;

    /**
     * All values less than or equal to <code>NEGATIVE_INFINITY</code> will be rounded down to
     * <code>Double.NEGATIVE_INFINITY</code> by <code>Calculatte.round()</code>.
     */
    public static double NEGATIVE_INFINITY = -9.0E17;

    /**
     * Accuracy value for integration calculations. The larger the more accurate.
     */
    public static int N = 10000000;

    /**
     * Accuracy value for derivation calculations. The smaller the more accurate.
     */
    public static double H = 0.000000001;

    /**
     * Largest distance between the left and right limit before the limit does not
     * exist.
     */
    public static double LIMIT_TOLERANCE = 0.000000001;

    /**
     * How far the x-value should be offset left and right to find the left and right limits.
     */
    public static double LIMIT_OFFSET = 0.000000001;
}
