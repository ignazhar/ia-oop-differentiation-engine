package differentiation_engine;

import java.util.HashMap;

public class Logarithm extends Expression {
    private Expression argument;

    public Logarithm(Expression argument) {
        this.argument = argument;
    }

    public Expression getArgument() {
        return argument;
    }

    public Expression differentiate(Variable var) {
        return new Multiply(new Exponent(argument, new Const(-1)), argument.differentiate(var));
    }

    public double evaluate(HashMap<Variable, Double> values) {
        return Math.log(this.argument.evaluate(values));
    }

    public String toString() {
        return "ln(" + argument.toString() + ")";
    }

    @Override
    public Expression Simplify() {
        argument = argument.Simplify();
        return this;
    }
}
