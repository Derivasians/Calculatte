package io.github.derivasians.calculatte;

/**
 * Contains all instance variables required for Calculatte to run.
 * These mainly consist of values used for rounding and other accuracy
 * controls within calculations.
 *
 * @author <a href="mailto:okashita.matthew@gmail.com">Matthew Okashita</a>
 * @author <a href="mailto:benigno.joseph.s@gmail.com">Joseph Benigno</a>
 * @version 0.1.0
 */
public class CalculatteEnvironment {
    /**
     * Prevent instantiation of this class.
     */
    private CalculatteEnvironment() {}

    /**
     * Represents how many decimal places this variable's respective
     * calculation(s) should be rounded too. Only values returned by a method
     * are rounded. Intermediate values within a method are never rounded,
     * unless by another method; e.g., the <code>revolve()</code> method's
     * calculations get rounded when the integral is taken with
     * <code>integrate()</code> and once more when the final value is
     * returned.
     *
     * <p>Note: Setting any of these values to -1 will prevent that group
     * of calculations from being rounded. This can be useful if you
     * would prefer to use your own rounding method or none at all.</p>
     */
    public static int
    integrationRoundingDecimalPlaces = 3,
    derivationRoundingDecimalPlaces = 3,
    leftRiemannSumRoundingDecimalPlaces = 3,
    rightRiemannSumRoundingDecimalPlaces = 3,
    midpointRuleRoundingDecimalPlaces = 3,
    trapezoidalSumRoundingDecimalPlaces = 3,
    revolutionRoundingDecimalPlaces = 3,
    crossSectionsRoundingDecimalPlaces = 3,
    limitRoundingDecimalPlaces = 3,
    leftLimitRoundingDecimalPlaces = 3,
    rightLimitRoundingDecimalPlaces = 3,
    polarAreaRoundingDecimalPlaces = 3;

    /**
     * All values greater than <code>CalculatteEnvironment.positiveInfinity</code>
     * will be rounded up to <code>Double.POSITIVE_INFINITY</code> by
     * <code>Calculatte.round()</code>.
     *
     * <p>Note: Setting <code>CalculatteEnvironment.positiveInfinity</code> to
     * <code>Double.MAX_VALUE</code> will prevent rounding values up to
     * <code>Double.POSITIVE_INFINITY</code>.</p>
     * 
     * @see io.github.derivasians.calculatte.Calculatte#round(double, int) 
     */
    public static double positiveInfinity = Double.MAX_VALUE;

    /**
     * All values less than to <code>CalculatteEnvironment.negativeInfinity</code>
     * will be rounded down to <code>Double.NEGATIVE_INFINITY</code> by
     * <code>Calculatte.round()</code>.
     *
     * <p>Note: Setting <code>CalculatteEnvironment.negativeInfinity</code> to
     * <code>Double.MIN_VALUE</code> will prevent rounding values down to
     * <code>Double.NEGATIVE_INFINITY</code>.</p>
     * 
     * @see io.github.derivasians.calculatte.Calculatte#round(double, int)  
     */
    public static double negativeInfinity = -Double.MAX_VALUE;

    /**
     * Accuracy value for integration calculations. The larger the more accurate.
     * 
     * @see io.github.derivasians.calculatte.Calculatte#integrate(double, double, Function) 
     */
    public static int n = 64000;

    /**
     * Accuracy value for derivation calculations. The smaller the more accurate.
     * 
     * @see io.github.derivasians.calculatte.Calculatte#derivate(double, Function) 
     */
    public static double h = 0.000000001;

    /**
     * Largest difference between the left and right derivative before the derivative
     * does not exist.
     *
     * @see io.github.derivasians.calculatte.Calculatte#derivate(double, Function) 
     */
    public static double derivativeTolerance = 0.000000001;

    /**
     * How far the x-value should be offset left and right to find the left and right
     * derivatives.
     *
     * @see io.github.derivasians.calculatte.Calculatte#leftDerivative(double, Function) 
     * @see io.github.derivasians.calculatte.Calculatte#rightDerivative(double, Function) 
     */
    public static double derivativeOffset = 0.000000001;
    
    /**
     * Largest difference between the left and right limit before the limit does not
     * exist.
     * 
     * @see io.github.derivasians.calculatte.Calculatte#limit(double, Function) 
     */
    public static double limitTolerance = 0.000000001;

    /**
     * How far the x-value should be offset left and right to find the left and right limits.
     * 
     * @see io.github.derivasians.calculatte.Calculatte#leftLimit(double, Function) 
     * @see io.github.derivasians.calculatte.Calculatte#rightLimit(double, Function) 
     */
    public static double limitOffset = 0.000000001;

    /**
     * These static constants define what known cross section
     * is being used to calculate a volume of cross section.
     * 
     * @see io.github.derivasians.calculatte.Calculatte#crossSection(double, double, Function, Function, int)
     */
    public static final int
    SQUARE = 0,
    EQUILATERAL_TRIANGLE = 1,
    ISOSCELES_TRIANGLE = 2,
    RIGHT_TRIANGLE = 3,
    SEMICIRCLE = 4;
}
