<p align="center">
  <img width="200" height="200" src="https://github.com/Derivasians/Calculatte/blob/main/imgs/CalculatteLogo.png" alt="Calculatte Logo">
</p>
<h1 align="center">Calculatte</h1>

<div align="center">
  <img src="https://img.shields.io/github/v/release/derivasians/calculatte?include_prereleases" alt="Release | v0.1.0">
  <img src="https://img.shields.io/github/workflow/status/derivasians/calculatte/JUnit%20Tests" alt="Build Badge">
  <img src="https://img.shields.io/github/license/derivasians/calculatte" alt="License | Apache-2.0">
</div>

A simple to use numerical Java calculus library.

*Note that this project is still in pre-release and may be unstable.*

### Example Usage
```java
// Integrate f(x) = 2x from 0 to 2.

import io.github.derivasians.calculatte.*;

Function TwoX = x -> 2 * x;

double a = Calculatte.integrate(0, 2, TwoX);
System.out.println(a);
```

### Documentation and Guides
Documentation can be found on our website [here][doc]. Guides on how to use Calculatte can be found in this 
repository's [wiki][wiki].

### Tag Release Code
The [`main` branch][main] is used as our development branch, with feature branches to separate larger feature 
implementations. Because of this, the code in the [`main` branch][main] may not reflect the code at the time of the 
latest tag release. Fortunately, GitHub automatically creates snapshots of repositories when a tag is released.

To find the source code at the time of a tag's release go to
`https://github.com/Derivasians/Calculatte/tree/v<SemVer>`. For example, to view the source code at the time of the 
[v0.1.0 tag release][v0.1.0 release], go to [`https://github.com/Derivasians/Calculatte/tree/v0.1.0`][v0.1.0 code].

[main]: https://github.com/Derivasians/Calculatte/tree/main
[release]: https://github.com/Derivasians/Calculatte/tree/main
[v0.1.0 release]: https://github.com/Derivasians/Calculatte/releases/tag/v0.1.0
[v0.1.0 code]: https://github.com/Derivasians/Calculatte/tree/v0.1.0

### Attribution
Calculatte was developed by [Matthew Okashita][soupyzinc] and [Joseph Benigno][jojongx] of [Derivasians][derivasians].

### License
```
Copyright 2021 Matthew Okashita, Joseph Benigno

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

[doc]: https://derivasians.github.io/Calculatte/
[wiki]: https://github.com/Derivasians/Calculatte/wiki
[soupyzinc]: https://github.com/SoupyzInc
[jojongx]: https://github.com/jojongx
[derivasians]: https://github.com/Derivasians
[license]: https://github.com/Derivasians/Calculatte/blob/main/LICENSE
