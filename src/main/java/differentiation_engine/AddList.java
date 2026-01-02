package differentiation_engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class AddList extends Expression {
    private ArrayList<Expression> list;

    public AddList(ArrayList<Expression> list) throws IllegalArgumentException {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List of arguments is empty");
        }
        this.list = list;
    }

    @Override
    public Expression differentiate(Variable var) {
        return new AddList(list.stream().map(expr -> expr.differentiate(var)).collect(Collectors.toCollection(ArrayList::new)));
    }

    @Override
    public double evaluate(HashMap<Variable, Double> values) {
        return list.stream().mapToDouble(expr -> expr.evaluate(values)).sum();
    }

    @Override
    public String toString() {
        return list.stream().map(expr -> expr.toString()).collect(Collectors.joining(" + "));
    }

    @Override
    public Expression Simplify() {
        list = list.stream().map(expr -> expr.Simplify()).collect(Collectors.toCollection(ArrayList::new));
        // sum all const values to one
        double constSum = list.stream().filter(expr -> expr instanceof Const).mapToDouble(c -> ((Const)c).getValue()).sum();
        list = list.stream().filter(expr -> !(expr instanceof Const)).collect(Collectors.toCollection(ArrayList::new));
        if (constSum != 0.0) list.add(new Const(constSum));
        return this;
    }

    @Override
    public boolean isAtomic() {
        return false;
    }
}
