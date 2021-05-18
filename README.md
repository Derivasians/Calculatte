# Calculatte
A simple Java calculus library

### Example Usage
```java
// Integrate f(x) = 2x from 0 to 2.

import com.derivasians.calculatte.*;

Function TwoX = x -> 2 * x;

double a = Calculatte.integrate(0, 2, TwoX);
System.out.println(a);
```

### How to make new functions
To create a new function, either create a class that implements the `Function` interface or define your new function in
your code. The `Function` interface is defined under [Function.java][function] as
```java
public interface Function {
    double f(double x);
}
```

Implementation of the simple function, f(x) = 2x is provided below using both methods.

```java
public class TwoX implements Function {
    @Override
    public double f(double x) {
        return 2 * x;
    }
}
```

or

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

### Attribution
Calculatte was developed by [Matthew Okashita][soupyzinc] and [Joseph Benigno][jojongx] of the 
[Derivasian's Dev Team][derivasians]. Calculatte is licensed under the MIT License. See [`LICENSE`][license] 
for more information.


[function]: https://github.com/Derivasians/Calculatte/blob/main/src/main/java/com/derivasians/calculatte/Function.java

[soupyzinc]: https://github.com/SoupyzInc
[jojongx]: https://github.com/jojongx
[derivasians]: https://github.com/Derivasians
[license]: https://github.com/Derivasians/Calculatte/blob/main/LICENSE