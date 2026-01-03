package differentiation_engine.expressions.binary;

import java.util.HashMap;

import differentiation_engine.expressions.*;

public class Add extends Expression {
    private Expression lhs, rhs;

    public Add(Expression lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public Expression differentiate(Variable var) {
        return new Add(lhs.differentiate(var), rhs.differentiate(var));
    }

    @Override
    public double evaluate(HashMap<Variable, Double> values) {
        return lhs.evaluate(values) + rhs.evaluate(values);
    }

    @Override
    public String toString() {
        return lhs.toString() + " + " + rhs.toString();
    }

    @Override
    public Expression Simplify() {
        lhs = lhs.Simplify();
        rhs = rhs.Simplify();
        if (lhs instanceof Const && ((Const)lhs).getValue() == 0.0) {
            return rhs;
        } else if (rhs instanceof Const && ((Const)rhs).getValue() == 0.0) {
            return lhs;
        } else {
            return this;
        }
    }

    @Override
    public boolean isAtomic() {
        return false;
    }
}
