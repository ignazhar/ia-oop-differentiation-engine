package differentiation_engine.expressions.unary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import differentiation_engine.expressions.*;
import differentiation_engine.expressions.n_ary.MultiplyList;

public class Negate extends Expression {
    private Expression argument;

    public Negate(Expression argument) {
        this.argument = argument;
    }

    @Override
    public Expression differentiate(Variable var) {
        return new Negate(argument.differentiate(var));
    }

    @Override
    public double evaluate(HashMap<Variable, Double> values) {
        return -1 * argument.evaluate(values);
    }

    @Override
    public String toString() {
        return "~" + argument.toString();
    }

    @Override
    public Expression Simplify() {
        argument = argument.Simplify();
        return new MultiplyList(new ArrayList<>(List.of(new Const(-1), argument))).Simplify();
    }
}
