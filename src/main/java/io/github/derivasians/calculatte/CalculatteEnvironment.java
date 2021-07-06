package io.github.derivasians.calculatte;

public class CalculatteEnvironment {
    /**
     * Sets the accuracy value for derivation calculations. The smaller the more accurate.
     */
    public static double H = 0.000000001;

    /**
     * Sets the accuracy value for integration calculations. The larger the more accurate.
     */
    public static int N = 10000000;

    /**
     * Sets the largest distance between the left and right limit before the limit does not
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
