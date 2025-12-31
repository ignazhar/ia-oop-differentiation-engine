package differentiation_engine;

import java.util.HashMap;

public class Multiply extends Expression {
    private Expression lhs, rhs;

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
        String lhsString = lhs.toString();
        if (!lhs.isAtomic()) lhsString = "(" + lhsString + ")";
        String rhsString = rhs.toString();
        if (!rhs.isAtomic()) rhsString = "(" + rhsString + ")";
        return lhsString + "*" + rhsString;
    }

    @Override
    public Expression Simplify() {
        lhs = lhs.Simplify();
        rhs = rhs.Simplify();
        if (lhs instanceof Const && ((Const)lhs).getValue() == 0.0) {
            return new Const(0);
        } else if (rhs instanceof Const && ((Const)rhs).getValue() == 0.0) {
            return new Const(0);
        } else if (lhs instanceof Const && ((Const)lhs).getValue() == 1.0) {
            return rhs;
        } else if (rhs instanceof Const && ((Const)rhs).getValue() == 1.0) {
            return lhs;
        } else {
            return this;
        }
    }

    @Override
    public boolean isAtomic() {
        return true;
    }
}
