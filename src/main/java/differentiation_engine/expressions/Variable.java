package differentiation_engine.expressions;

import java.util.HashMap;

public class Variable extends Expression {
    public final String symbol;

    public Variable(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public Expression differentiate(Variable var) {
        if (this.symbol == var.symbol) {
            return new Const(1);
        } else {
            return new Const(0);
        }
    }

    @Override
    public double evaluate(HashMap<Variable, Double> values) throws IllegalArgumentException {
        if (values.containsKey(this)) {
            return values.get(this);
        } else {
            throw new IllegalArgumentException("Variable " + this.symbol + " not found.");
        }
    }

    @Override
    public String toString() {
        return symbol;
    }
}
