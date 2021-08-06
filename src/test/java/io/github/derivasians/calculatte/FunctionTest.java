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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionTest {
    @Test
    @DisplayName("Take 2x at x = 4")
    public void TwoXAtXEquals4() {
        final Function twoX = x -> 2 * x;
        final double y = twoX.f(4);
        assertEquals(8, y);
    }

    @Test
    @DisplayName("Piecewise function y-values")
    public void PiecewiseFunctionYValues() {
        final Function piecewise = x -> {
            if (x < 0) {                // x < 0
                return 1;                   // y = 1
            } else if (x < 3) {         // 0 <= x < 3
                return x;                   // y = x
            } else {                    // x >= 3
                return Math.pow(x, 2);      // y = x^2
            }
        };

        assertEquals(1, piecewise.f(-1));   // x < 0
        assertEquals(0, piecewise.f(0));    // x = 0
        assertEquals(1, piecewise.f(1));    // 0 < x < 3
        assertEquals(9, piecewise.f(3));    // x = 3
        assertEquals(16, piecewise.f(4));   // x > 3
    }
}