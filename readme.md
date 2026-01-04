# ia-oop-differentiation-engine
Differentiation engine repo for IA OOP class

## Features:
### Expressions
* Constant value
* Variable
* Addition of 2 or more(list of) expressions
* Multiplication of 2 or more(list of) expressions
* Sin, Cos, Tan functions
* General exponentiation f(x)^g(x)
* Natural Logarithm
* Unary negate
### Other
* Easy simplifaction steps for multiplication and addition expressions
* A bit of more complex simplification for composite multiplication and addition expressions
* Easy bracket optimization using atomicity of expressions
* Added simple command line communication(enter expression -> enter differentiation variable -> get result)

## TODO:
*** fix all TODO's!!!
* Add specific exponentiation for c^f(x) and f(x)^c
* Add proper testing list of examples


## Compilation

`mvn exec:java`

* Be in a directory `cd differentiaion_engine`

## Example work

```
Yuliya@Aspire3 MINGW64 /d/Ignat/cam/ia/oop/differentiation_engine (development) $ mvn exec:java
[INFO] -------------------------<Maven build info>------------------------------
Enter expression for differentiation:
5x*ln(10y)*2^(5x*ln(10y)*(0-1))
Parsed expression: 5.0 * x * ln(10.0 * y) * 2.0^(-5.0 * x * ln(10.0 * y))
Enter variable for differentiation:
x
5.0 * ln(10.0 * y) * 2.0^(-5.0 * x * ln(10.0 * y)) + -25.0 * x * ln(10.0 * y) * 2.0^(-5.0 * x * ln(10.0 * y)) * ln(10.0 * y) * ln(2.0)
[INFO] -------------------------<Maven build info>------------------------------
```