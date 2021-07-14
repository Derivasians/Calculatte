# Functions
The backbone of calculus are algebraic functions. Almost every method in Calculatte requires the use of a function. 
Whether you are integrating a function, taking a volume of revolution, or finding the volume of a known 
cross-section—you are using an algebraic function.

In Calculatte, all functions `implements` the `Function` interface. The `Function` interface is defined under 
[Function.java][function] as
```java
/**
 * The Function interface for all other functions to be based off of.
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
```

To create your own functions, you will need to create a new `Function` object. You can achieve this through three
main methods: lambdas within your code, overrides within your code, or creating a new class in a separate file. Which
method you choose is up to your project and its needs. Examples of the three methods are shown below, defining y = 2x.
```java
// With lambdas
Function TwoX = x -> 2 * x;

// Without lambdas
Function TwoX = new Function() {
    @Override
    public double f(double x) {
        return 2 * x;
    }
};
```

or

```java
public class TwoX implements Function {
    @Override
    public double f(double x) {
        return 2 * x;
    }
}
```

Note that you can embed functions within functions. An example of this can be seen in volume of revolutions 
calculations. In the source code of Calculatte, you can see how the [`revolve()`][revolve] method works by embedding the
top and bottom function into the squared and offset version of themselves.
```java
public static double revolve(double a, double b, double axis, Function functionTop, Function functionBottom) {
    // The top function with the axis offset, squared.
    Function squaredFunctionTop = x -> Math.pow(axis - functionTop.f(x), 2);

    // The bottom function with the axis offset, squared.
    Function squaredFunctionBottom = x -> Math.pow(axis - functionBottom.f(x), 2);

    // Split the volume of revolution formula into two separate integrals.
    double volume = Math.PI * (integrate(a, b, squaredFunctionTop) - integrate(a, b, squaredFunctionBottom));
    return round(volume, CalculatteEnvironment.REVOLUTION_ROUNDING_DECIMAL_PLACES);
}
```

Make sure to actually return the y-value of the embedded function. 
```java
Function function = x -> embeddedFunction.f(x);
```

[function]: https://github.com/Derivasians/Calculatte/blob/main/src/main/java/io/github/derivasians/calculatte/Function.java
[revolve]: https://github.com/Derivasians/Calculatte/blob/main/src/main/java/io/github/derivasians/calculatte/Calculatte.java#L170