/*
 * Copyright 2021 Matthew Okashita, Joseph Benigno
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.github.derivasians.calculatte;

/**
 * Contains all properties required for Calculatte to run.
 * These mainly consist of values used for rounding and other accuracy
 * controls within calculations.
 *
 * @author <a href="mailto:okashita.matthew@gmail.com">Matthew Okashita</a>
 * @author <a href="mailto:benigno.joseph.s@gmail.com">Joseph Benigno</a>
 * @version 1.0.0
 * @see Calculatte
 */
public final class CalculatteEnvironment {
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
     * would prefer to use your own rounding method or none at all.
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
     * <p>All values greater than <code>CalculatteEnvironment.positiveInfinity</code>
     * will be rounded up to <code>Double.POSITIVE_INFINITY</code> by
     * <code>Calculatte.round()</code>.
     *
     * <p>Note: Setting <code>CalculatteEnvironment.positiveInfinity</code> to
     * <code>Double.MAX_VALUE</code> will prevent rounding values up to
     * <code>Double.POSITIVE_INFINITY</code>.
     * 
     * @see io.github.derivasians.calculatte.Calculatte#round(double, int) 
     */
    public static double positiveInfinity = Double.MAX_VALUE;

    /**
     * <p>All values less than to <code>CalculatteEnvironment.negativeInfinity</code>
     * will be rounded down to <code>Double.NEGATIVE_INFINITY</code> by
     * <code>Calculatte.round()</code>.
     *
     * <p>Note: Setting <code>CalculatteEnvironment.negativeInfinity</code> to
     * <code>Double.MIN_VALUE</code> will prevent rounding values down to
     * <code>Double.NEGATIVE_INFINITY</code>.
     * 
     * @see io.github.derivasians.calculatte.Calculatte#round(double, int)  
     */
    public static double negativeInfinity = -Double.MAX_VALUE;

    /**
     * Represents accuracy value for integration calculations. The larger the more accurate.
     * 
     * @see io.github.derivasians.calculatte.Calculatte#integrate(double, double, Function) 
     */
    public static int n = 64000;

    /**
     * Represents accuracy value for derivation calculations. The smaller the more accurate.
     * 
     * @see io.github.derivasians.calculatte.Calculatte#derivate(double, Function) 
     */
    public static double h = 0.000000001;

    /**
     * Represents the largest difference between the left and right derivative before the derivative
     * does not exist.
     *
     * @see io.github.derivasians.calculatte.Calculatte#derivate(double, Function) 
     */
    public static double derivativeTolerance = 0.000000001;

    /**
     * Represents how far the x-value should be offset left and right to find the left and right
     * derivatives.
     *
     * @see io.github.derivasians.calculatte.Calculatte#leftDerivative(double, Function) 
     * @see io.github.derivasians.calculatte.Calculatte#rightDerivative(double, Function) 
     */
    public static double derivativeOffset = 0.000000001;
    
    /**
     * Represents the largest difference between the left and right limit before the limit does not
     * exist.
     * 
     * @see io.github.derivasians.calculatte.Calculatte#limit(double, Function) 
     */
    public static double limitTolerance = 0.000000001;

    /**
     * Represents how far the x-value should be offset left and right to find the left and right limits.
     * 
     * @see io.github.derivasians.calculatte.Calculatte#leftLimit(double, Function) 
     * @see io.github.derivasians.calculatte.Calculatte#rightLimit(double, Function) 
     */
    public static double limitOffset = 0.000000001;

    /**
     * Represents common known cross-sections types.
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
