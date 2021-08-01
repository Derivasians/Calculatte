package io.github.derivasians.calculatte;

/**
 * Thrown to indicate that an invalid cross-section type has been passed into
 * {@link Calculatte#crossSection(double, double, Function, Function, int)}.
 * 
 * @author <a href="mailto:okashita.matthew@gmail.com">Matthew Okashita</a>
 * @see Calculatte#crossSection(double, double, Function, Function, int) 
 */
public class InvalidCrossSectionTypeException extends RuntimeException {
    /**
     * Constructs an <code>InvalidCrossSectionTypeException</code> with the specified detail message.
     *
     * @param invalidType The invalid type that was passed into
     *                    {@link Calculatte#crossSection(double, double, Function, Function, int)}.
     */
    public InvalidCrossSectionTypeException(String invalidType) {
        super(String.format("<%s> is not a valid cross-section type. Please enter a valid cross-section type (0 - 4).",
                invalidType));
    }
}
