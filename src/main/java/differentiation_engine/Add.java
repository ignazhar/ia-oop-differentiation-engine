package differentiation_engine;

import java.util.HashMap;

public class Add extends Expression {
    public final Expression lhs, rhs;

    public Add(Expression lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Expression differentiate(Variable var) {
        return new Add(lhs.differentiate(var), rhs.differentiate(var));
    }

    public double evaluate(HashMap<Variable, Double> values) {
        return lhs.evaluate(values) + rhs.evaluate(values);
    }

    public String toString() {
        return lhs.toString() + " + " + rhs.toString();
    }
}
