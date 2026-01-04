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

## Overview of the project structure

`main/java/differentiation_engine/`

* `expressions/`
    * `Expression.java` --- general abstract class for a mathematical expression
    * `Const.java` --- atomic constant
    * `Variable.java` --- atomic variable
    * `unary/`
        * `Negate.java` --- -expr
        * `Sin.java` --- sin(arg)
        * `Cos.java` --- cos(arg)
        * `Tan.java` --- tan(arg)
        * `Logarithm.java` --- ln(arg)
    * `binary/`
        * `Add.java` -- lhs + rhs
        * `Multiply.java` -- lhs * rhs
        * `Exponent.java` -- base ^ power
    * `n_ary/`
        * `AddList.java` --- a1 + a2 + ... + an
        * `MultiplyList.java` --- a1 * a2 * ... * an
* `parser/`
    * `Parser.java` --- main logic of a parsing user's input string into a composite expression. Consult the code for more information on implementation
    * `MutableInteger.java` --- mutable integer reference helper class for parser
    * `Item.java` --- item sealed interface, that simulates an enumerator of sign and expression classes
    * `SignItem.java` --- sign class that implements item
    * `ExpressionItem.java` --- expression class that implements item
* `App.java` --- main program execution logic

`test/java/differentiation_engine/AppTest.java` - sample tests to run (TODO, improve)
