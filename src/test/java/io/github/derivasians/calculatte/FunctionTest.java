package io.github.derivasians.calculatte;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionTest {
    @Test
    @DisplayName("Take 2x at x = 4")
    public void TwoXAtXEquals4() {
        Function twoX = x -> 2 * x;
        double y = twoX.f(4);
        assertEquals(8, y);
    }

    @Test
    @DisplayName("Piecewise function y-values")
    public void PiecewiseFunctionYValues() {
        Function piecewise = x -> {
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