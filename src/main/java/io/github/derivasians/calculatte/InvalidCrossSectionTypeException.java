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
 * Thrown to indicate that an invalid cross-section type has been passed into
 * {@link Calculatte#crossSection(double, double, Function, Function, int)}.
 * 
 * @author <a href="mailto:okashita.matthew@gmail.com">Matthew Okashita</a>
 * @version 1.0.0
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
