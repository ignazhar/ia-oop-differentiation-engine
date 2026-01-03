package differentiation_engine.expressions.unary;

import java.util.HashMap;

import differentiation_engine.expressions.*;

public class Negate extends Expression {
    private Expression argument;

    public Negate(Expression argument) {
        this.argument = argument;
    }

    @Override
    public Expression differentiate(Variable var) {
        return new Negate(argument.differentiate(var));
    }

    @Override
    public double evaluate(HashMap<Variable, Double> values) {
        return -1 * argument.evaluate(values);
    }

    @Override
    public String toString() {
        return "~" + argument.toString();
    }

    @Override
    public Expression Simplify() {
        // TODO: Simplify using multiplication
        argument = argument.Simplify();
        return this;
    }
}
