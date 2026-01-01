package differentiation_engine;

import java.util.HashMap;

public class Const extends Expression {
    private double value;

    public Const(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public Expression differentiate(Variable var) {
        return new Const(0.0);
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public double evaluate(HashMap<Variable, Double> values) {
        return value;
    }
}
