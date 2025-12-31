package differentiation_engine;

import java.util.HashMap;

public class Multiply extends Expression {
    public final Expression lhs, rhs;

    public Multiply(Expression lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public Expression differentiate(Variable var) {
        return new Add(new Multiply(lhs, rhs.differentiate(var)), new Multiply(lhs.differentiate(var), rhs));
    }

    @Override
    public double evaluate(HashMap<Variable, Double> values) {
        return lhs.evaluate(values) * rhs.evaluate(values);
    }

    @Override
    public String toString() {
        return "(" + lhs.toString() + ") * (" + rhs.toString() + ")";
    }
}
