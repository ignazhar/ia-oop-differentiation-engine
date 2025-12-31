package differentiation_engine;

import java.util.HashMap;

public class Sin extends Expression {
    public final Expression argument;

    public Sin(Expression argument) {
        this.argument = argument;
    }

    @Override
    public Expression differentiate(Variable var) {
        return new Multiply(new Cos(argument), argument.differentiate(var));
    }

    @Override
    public double evaluate(HashMap<Variable, Double> values) {
        return Math.sin(this.argument.evaluate(values));
    }

    @Override
    public String toString() {
        return "sin(" + argument.toString() + ")";
    }
}
