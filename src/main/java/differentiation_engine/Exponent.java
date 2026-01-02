package differentiation_engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// General exponentiation class for f(x)^g(x)
public class Exponent extends Expression {
    private Expression base, power;

    public Exponent(Expression base, Expression power) {
        this.base = base;
        this.power = power;
    }

    @Override
    public Expression differentiate(Variable var) {
        // let f(x)=base, g(x)=power, find d/dx f(x)^g(x)
        // = f^g(g'ln(f) + g*f'/f)
        // let A = g'ln(f), B=g*f'/f
        Expression A = new Multiply(power.differentiate(var), new Logarithm(base));
        Expression B = new MultiplyList(new ArrayList<>(List.of(power, base.differentiate(var), new Exponent(base, new Const(-1)))));
        return new Multiply(this, new Add(A, B));
    }

    @Override
    public double evaluate(HashMap<Variable, Double> values) {
        return Math.pow(base.evaluate(values), power.evaluate(values));
    }

    @Override
    public String toString() {
        String baseString = base.isAtomic() ? base.toString() : "(" + base.toString() + ")";
        String powerString = power.isAtomic() ? power.toString() : "(" + power.toString() + ")";
        return baseString + "^" + powerString;
    }

    @Override
    public Expression Simplify() {
        base = base.Simplify();
        power = power.Simplify();
        // e^ln(f) -> f
        if (base instanceof Const && ((Const)base).getValue() == Math.E && power instanceof Logarithm) {
            return ((Logarithm)power).getArgument();
        }
        return this;
    }

    @Override
    public boolean isAtomic() {
        // returns false cause of weirdness of evaluating a^b^c,
        // and having different order then multiplication.
        // TODO: dig into this further
        return false;
    }
}
