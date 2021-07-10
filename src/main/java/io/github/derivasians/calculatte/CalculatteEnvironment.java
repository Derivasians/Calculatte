package io.github.derivasians.calculatte;

/**
 * Contains all instance variables required for Calculatte to run.
 * These mainly consist of values used for rounding and other accuracy
 * controls within calculations.
 *
 * @version 1.0 Snapshot
 * @author Matthew Okashita
 */
public class CalculatteEnvironment {
    // Rounding values. These values represent how many decimal places
    // each of their respective calculations should be rounded too.
    // Only the final calculation is rounded, intermediate values are
    // never rounded.
    //
    // Note: Setting any of these values to -1 will prevent that group
    // of calculations from being rounded. This can be useful if you
    // would prefer to use your own rounding method or none at all.

    /**
     * @see io.github.derivasians.calculatte.Calculatte#factorial(double) 
     */
    public static int FACTORIAL_ROUNDING_DECIMAL_PLACES = 0;

    /**
     * @see io.github.derivasians.calculatte.Calculatte#integrate(double, double, Function) 
     */
    public static int INTEGRATION_ROUNDING_DECIMAL_PLACES = 3;

    /**
     * @see io.github.derivasians.calculatte.Calculatte#derivate(double, Function) 
     */
    public static int DERIVATION_ROUNDING_DECIMAL_PLACES = 3;

    /**
     * @see io.github.derivasians.calculatte.Calculatte#leftRiemannSum(double, double, Function, int)  
     */
    public static int LEFT_RIEMANN_SUM_ROUNDING_DECIMAL_PLACES = 3;

    /**
     * @see io.github.derivasians.calculatte.Calculatte#rightRiemannSum(double, double, Function, int) 
     */
    public static int RIGHT_RIEMANN_SUM_ROUNDING_DECIMAL_PLACES = 3;

    /**
     * @see io.github.derivasians.calculatte.Calculatte#trapezoidalSum(double, double, Function, int) 
     */
    public static int TRAPEZOIDAL_SUM_ROUNDING_DECIMAL_PLACES = 3;

    /**
     * @see io.github.derivasians.calculatte.Calculatte#revolve(double, double, double, Function, Function) 
     */
    public static int REVOLUTION_ROUNDING_DECIMAL_PLACES = 3;

    /**
     * @see io.github.derivasians.calculatte.Calculatte#crossSection(double, double, Function, Function, int) 
     */
    public static int CROSS_SECTIONS_ROUNDING_DECIMAL_PLACES = 3;

    /**
     * @see io.github.derivasians.calculatte.Calculatte#limit(double, Function) 
     * @see io.github.derivasians.calculatte.Calculatte#leftLimit(double, Function) 
     * @see io.github.derivasians.calculatte.Calculatte#rightLimit(double, Function) 
     */
    public static int LIMIT_ROUNDING_DECIMAL_PLACES = 3;

    /**
     * All values greater than <code>CalculatteEnvironment.POSITIVE_INFINITY</code>
     * will be rounded up to <code>Double.POSITIVE_INFINITY</code> by
     * <code>Calculatte.round()</code>.
     *
     * <p>Note: Setting <code>CalculatteEnvironment.POSITIVE_INFINITY</code> to
     * <code>Double.MAX_VALUE</code> will prevent rounding values up to
     * <code>Double.POSITIVE_INFINITY</code>.</p>
     * 
     * @see io.github.derivasians.calculatte.Calculatte#round(double, int) 
     */
    public static double POSITIVE_INFINITY = Double.MAX_VALUE;

    /**
     * All values less than to <code>CalculatteEnvironment.NEGATIVE_INFINITY</code>
     * will be rounded down to <code>Double.NEGATIVE_INFINITY</code> by
     * <code>Calculatte.round()</code>.
     *
     * <p>Note: Setting <code>CalculatteEnvironment.NEGATIVE_INFINITY</code> to
     * <code>Double.MIN_VALUE</code> will prevent rounding values down to
     * <code>Double.NEGATIVE_INFINITY</code>.</p>
     * 
     * @see io.github.derivasians.calculatte.Calculatte#round(double, int)  
     */
    public static double NEGATIVE_INFINITY = Double.MIN_VALUE;

    /**
     * Accuracy value for integration calculations. The larger the more accurate.
     * 
     * @see io.github.derivasians.calculatte.Calculatte#integrate(double, double, Function) 
     */
    public static int N = 64000;

    /**
     * Accuracy value for derivation calculations. The smaller the more accurate.
     * 
     * @see io.github.derivasians.calculatte.Calculatte#derivate(double, Function) 
     */
    public static double H = 0.000000001;

    /**
     * Largest distance between the left and right limit before the limit does not
     * exist.
     * 
     * @see io.github.derivasians.calculatte.Calculatte#limit(double, Function) 
     */
    public static double LIMIT_TOLERANCE = 0.000000001;

    /**
     * How far the x-value should be offset left and right to find the left and right limits.
     * 
     * @see io.github.derivasians.calculatte.Calculatte#leftLimit(double, Function) 
     * @see io.github.derivasians.calculatte.Calculatte#rightLimit(double, Function) 
     */
    public static double LIMIT_OFFSET = 0.000000001;

    /**
     * These static constants define what known cross section
     * is being used to calculate a volume of cross section.
     */
    public static final int
    SQUARE = 0,
    EQUILATERAL_TRIANGLE = 1,
    ISOSCELES_TRIANGLE = 2,
    RIGHT_TRIANGLE = 3,
    SEMICRICLE = 4;
}
