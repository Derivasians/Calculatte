package io.github.derivasians.calculatte;

public class CalculatteEnvironment {
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
     * Accuracy value for derivation calculations. The smaller the more accurate.
     */
    public static double H = 0.000000001;

    /**
     * Accuracy value for integration calculations. The larger the more accurate.
     */
    public static int N = 10000000;

    /**
     * Largest distance between the left and right limit before the limit does not
     * exist.
     */
    public static double LIMIT_TOLERANCE = 0.000000001;

    /**
     * How far the x-value should be offset left and right to find the left and right limits.
     */
    public static double LIMIT_OFFSET = 0.000000001;

    /**
     * The number of decimal places all limit calculations should round too.
     */
    public static int LIMIT_ROUNDING_DECIMAL_PLACES = 8;
}
