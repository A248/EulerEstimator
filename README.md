# EulerEstimator

EulerEstimator approximates the value of some y given a differential equation relating dy/dx, y, and x using Euler's approximative approach.

**Features**
* The function on which dy/dx is based need not be known.
* From an initial X value, initial Y value, and dy/dx, future y values are estimated.
* Customise estimation parameters
* Generate a table with x, y, and dy/dx at every step of estimation

**Example**

Suppose we know:
* `dy/dx = cos(x)y + sin(x)`
* `y(0) = 2`

We want to estimate `y(3)`

Using the traditional mathemtical approach and attempting to solve this first-order linear inseparable differential equation via the application of this formula, we receive:
y = e<sup>sin(x)</sup>∫e<sup>sin(x)</sup>sin(x)dx
which can't be solved using Calc II techniques. What to do?

*Solution:* Use EulerEstimator. Put dy/dx = `y*cos(x) + sin(x)`, initial X = `0`, initial Y = `2`. Configure estimation parameters: number of iterations, step value, and decimal precision to display. Hit Execute, and retrieve your results!

**Usage**
1. Make sure you have installed Java 8.
2. Download the [latest release](https://github.com/A248/EulerEstimator/releases)
3. Enjoy!

**Building**

1. Download/clone [repo source folder](https://github.com/A248/EulerEstimator/tree/master/Estimator)
2. In command prompt, navigate to folder and use `mvn clean install`.
