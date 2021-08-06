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
