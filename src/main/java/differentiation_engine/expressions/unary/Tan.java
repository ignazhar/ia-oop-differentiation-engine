package differentiation_engine.expressions.unary;

import java.util.HashMap;

import differentiation_engine.expressions.*;
import differentiation_engine.expressions.binary.Exponent;
import differentiation_engine.expressions.binary.Multiply;

public class Tan extends Expression {
    private Expression argument;

    public Tan(Expression argument) {
        this.argument = argument;
    }

    @Override
    public Expression differentiate(Variable var) {
        return new Multiply(new Exponent(new Cos(argument), new Const(-2)), argument.differentiate(var));
    }

    @Override
    public double evaluate(HashMap<Variable, Double> values) {
        return Math.tan(this.argument.evaluate(values));
    }

    @Override
    public String toString() {
        return "tan(" + argument.toString() + ")";
    }

    @Override
    public Expression Simplify() {
        argument = argument.Simplify();
        return this;
    }
}
