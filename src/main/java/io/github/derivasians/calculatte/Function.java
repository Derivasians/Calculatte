package io.github.derivasians.calculatte;

/**
 * The Function interface for all other functions to be based off of.
 *
 *  @author <a href="mailto:okashita.matthew@gmail.com">Matthew Okashita</a>
 *  @version 1.0.0
 */
public interface Function {
    /**
     * Calculates the y-value for a given x-value of this function.
     *
     * @param x The x-value to find the y-value for.
     * @return The y-value for the given x-value.
     */
    double f(double x);
}
