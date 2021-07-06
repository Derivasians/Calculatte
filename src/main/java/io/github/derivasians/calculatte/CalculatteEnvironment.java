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
    //
    // Setting any of these values to -1 will prevent that group of
    // calculations from being rounded. This can be useful if you
    // would prefer to use your own rounding method or none at all.
    
    /**
     * @see io.github.derivasians.calculatte.Calculatte#integrate(double, double, Function) 
     */
    public static int INTEGRATION_ROUNDING_DECIMAL_PLACES = 8;

    /**
     * @see io.github.derivasians.calculatte.Calculatte#derivate(double, Function) 
     */
    public static int DERIVATION_ROUNDING_DECIMAL_PLACES = 8;

    /**
     * @see io.github.derivasians.calculatte.Calculatte#leftRiemannSum(double, double, Function, int)  
     */
    public static int LEFT_RIEMANN_SUM_ROUNDING_DECIMAL_PLACES = 8;

    /**
     * @see io.github.derivasians.calculatte.Calculatte#rightRiemannSum(double, double, Function, int) 
     */
    public static int RIGHT_RIEMANN_SUM_ROUNDING_DECIMAL_PLACES = 8;

    /**
     * @see io.github.derivasians.calculatte.Calculatte#trapezoidalSum(double, double, Function, int) 
     */
    public static int TRAPEZOIDAL_SUM_ROUNDING_DECIMAL_PLACES = 8;

    /**
     * @see io.github.derivasians.calculatte.Calculatte#round(double, int) 
     */
    public static int REVOLUTION_ROUNDING_DECIMAL_PLACES = 8;

    /**
     * @see io.github.derivasians.calculatte.Calculatte#limit(double, Function) 
     * @see io.github.derivasians.calculatte.Calculatte#leftLimit(double, Function) 
     * @see io.github.derivasians.calculatte.Calculatte#rightLimit(double, Function) 
     */
    public static int LIMIT_ROUNDING_DECIMAL_PLACES = 8;

    /**
     * All values greater than or equal to <code>POSITIVE_INFINITY</code> will be rounded up to
     * <code>Double.POSITIVE_INFINITY</code> by <code>Calculatte.round()</code>.
     * 
     * @see io.github.derivasians.calculatte.Calculatte#round(double, int) 
     */
    public static double POSITIVE_INFINITY = 9.0E17;

    /**
     * All values less than or equal to <code>NEGATIVE_INFINITY</code> will be rounded down to
     * <code>Double.NEGATIVE_INFINITY</code> by <code>Calculatte.round()</code>.
     * 
     * @see io.github.derivasians.calculatte.Calculatte#round(double, int)  
     */
    public static double NEGATIVE_INFINITY = -9.0E17;

    /**
     * Accuracy value for integration calculations. The larger the more accurate.
     * 
     * @see io.github.derivasians.calculatte.Calculatte#integrate(double, double, Function) 
     */
    public static int N = 10000000;

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
}
