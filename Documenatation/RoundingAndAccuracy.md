# Rounding and Accuracy
Computers are notorious for issues with floating point arithmetic and rounding. Due to these issues, Calculatte will 
encounter many accuracy issues. To allow for a tailored experience for each user, Calculatte offers many ways to 
control rounding and accuracy. 

## Rounding
In the [`CalculatteEnvironment.java`][env] file, you will find every customizable "setting" and many other static 
variables. Here, you will see many static variables ending in `_ROUNDING_DECIMAL_PLACES`. These "rounding variables" 
define how many decimal places the final value of their respective calculations should be rounded too. By default, all 
rounding is set to 3 decimal places. 

Setting any of these rounding variables to `-1` will cause that rounding for that operation to not occur. This can be
useful if you would prefer to see the raw value or implement your own rounding method. Note that the `round()` method 
utilizes a modified `RoundingMode.HALF_EVEN`. This choice was made as the IEEE 754 standard suggests the use of 
rounding to half even, or banker's rounding. 

### Rounding to Inifnity
The `round()` method also offers the options to round very high values up to `Double.POSITIVE_INFINITY` and very low 
values down to `Double.NEGATIVE_INFINITY`. Whether a value gets rounded up or down to infinity is determined by the 
variables `CalculatteEnvironment.POSITIVE_INFINITY` and `CalculatteEnvironment.NEGATIVE_INFINITY` respectively. If a 
value is greater than `CalculatteEnvironment.POSITIVE_INFINITY`, it will be rounded up to `Double.POSITIVE_INFINITY` by 
`round()`. Likewise, if a value is less than `CalculatteEnvironment.NEGATIVE_INFINITY`, it will be rounded down to 
`Double.NEGATIVE_INFINITY` by `round()`.

As a result of this behaviour, setting `CalculatteEnvironment.POSITIVE_INFINITY` to `Double.MAX_VALUE` or 
`CalculatteEnvironment.NEGATIVE_INFINITY` to `Double.MIN_VALUE` will disable rounding up to positive or negative 
infinity, respectively. It should be noted that rounding to infinities are off by default.


## Accuracy
Many of the methods in Calculatte approximate certain values rather than actually solve for them, as usually the 
guesses are good enough. However, if the accuracy is not high enough, or too high and causes lag, they can be changed. 
Typically, certain variables in [`CalculatteEnvironment.java`][env] determine how accurate a calculation is. For 
example, 

By looking at the source code for the method you'd like to alter, with your knowledge of calculus and the present javadoc
in both the method and accuracy variables, you should be able to alter the methods to your liking. All accuracy 
variables are present in the [`CalculatteEnvironment.java`][env] file.

[env]: https://github.com/Derivasians/Calculatte/blob/main/src/main/java/io/github/derivasians/calculatte/CalculatteEnvironment.java
