# Calculatte
![v0.1.0](https://img.shields.io/badge/version-0.1.0-yellow)
![Build Badge](https://img.shields.io/github/workflow/status/derivasians/calculatte/JUnit%20Tests)
![License | Apache-2.0](https://img.shields.io/github/license/derivasians/calculatte)

A simple to use Java calculus library.

*Note that this project is still in pre-release v0.1.0.*

### Example Usage
```java
// Integrate f(x) = 2x from 0 to 2.

import io.github.derivasians.calculatte.*;

Function TwoX = x -> 2 * x;

double a = Calculatte.integrate(0, 2, TwoX);
System.out.println(a);
```

### Documentation and Guides
Documentation can be found on our website [here](https://derivasians.github.io/Calculatte/). Guides on how to use 
Calculatte can be found in the [Documentation][documentation] folder.

### Attribution
Calculatte was developed by [Matthew Okashita][soupyzinc] and [Joseph Benigno][jojongx] of [Derivasians][derivasians].

### License
```
Copyright 2021 Derivasians

Licensed under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License. You may obtain a copy of
the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
License for the specific language governing permissions and limitations under
the License.
```

[documentation]: https://github.com/Derivasians/Calculatte/tree/main/Documenatation
[soupyzinc]: https://github.com/SoupyzInc
[jojongx]: https://github.com/jojongx
[derivasians]: https://github.com/Derivasians
[license]: https://github.com/Derivasians/Calculatte/blob/main/LICENSE
