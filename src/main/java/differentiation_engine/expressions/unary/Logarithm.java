package differentiation_engine.expressions.unary;

import java.util.HashMap;

import differentiation_engine.expressions.*;
import differentiation_engine.expressions.binary.Exponent;
import differentiation_engine.expressions.binary.Multiply;

public class Logarithm extends Expression {
    private Expression argument;

    public Logarithm(Expression argument) {
        this.argument = argument;
    }

    public Expression getArgument() {
        return argument;
    }

    @Override
    public Expression differentiate(Variable var) {
        return new Multiply(new Exponent(argument, new Const(-1)), argument.differentiate(var));
    }

    @Override
    public double evaluate(HashMap<Variable, Double> values) {
        return Math.log(this.argument.evaluate(values));
    }

    @Override
    public String toString() {
        return "ln(" + argument.toString() + ")";
    }

    @Override
    public Expression Simplify() {
        argument = argument.Simplify();
        return this;
    }
}
