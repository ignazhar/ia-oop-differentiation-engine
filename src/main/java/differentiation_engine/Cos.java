package differentiation_engine;

import java.util.HashMap;

public class Cos extends Expression {
    private Expression argument;

    public Cos(Expression argument) {
        this.argument = argument;
    }

    @Override
    public Expression differentiate(Variable var) {
        return new Multiply(new Cos(argument), argument.differentiate(var));
    }

    @Override
    public double evaluate(HashMap<Variable, Double> values) {
        return Math.cos(this.argument.evaluate(values));
    }

    @Override
    public String toString() {
        return "cos(" + argument.toString() + ")";
    }

    @Override
    public Expression Simplify() {
        argument = argument.Simplify();
        return this;
    }
}
