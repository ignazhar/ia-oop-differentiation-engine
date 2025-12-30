package differentiation_engine;

import java.util.HashMap;

public class Variable extends Expression {
    public final String symbol;

    public Variable(String symbol) {
        this.symbol = symbol;
    }

    public Expression differentiate(Variable var) {
        if (this.symbol == var.symbol) {
            return this;
        } else {
            return new Const(0);
        }
    }

    public double evaluate(HashMap<Variable, Double> values) throws IllegalArgumentException {
        if (values.containsKey(this)) {
            return values.get(this);
        } else {
            throw new IllegalArgumentException("Variable " + this.symbol + " not found.");
        }
    }

    public String toString() {
        return symbol;
    }
}
