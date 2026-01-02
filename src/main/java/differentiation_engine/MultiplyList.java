package differentiation_engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class MultiplyList extends Expression {
    private ArrayList<Expression> list;

    public MultiplyList(ArrayList<Expression> list) throws IllegalArgumentException {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List of arguments is empty");
        }
        this.list = list;
    }

    public ArrayList<Expression> getList() {
        // TODO: bad code!!!
        // I give access to the expressions themselves
        // revisit!!!
        return list;
    }

    @Override
    public Expression differentiate(Variable var) {
        // O(n) -> O(n^2)
        // (fgh)' = fgh' + fg'h + f'gh
        ArrayList<Expression> addList = new ArrayList<>();
        for (int i = 0; i < list.size(); i ++) {
            Expression expr = list.get(0);
            list.remove(0);
            list.add(expr.differentiate(var));
            addList.add(new MultiplyList(new ArrayList<>(list)));
            list.remove(list.size() - 1);
            list.add(expr);
        }
        // TODO: try with streams
        return new AddList(addList);
    }

    @Override
    public double evaluate(HashMap<Variable, Double> values) {
        return list.stream().mapToDouble(expr -> expr.evaluate(values)).reduce(1, (a, b) -> a * b);
    }

    @Override
    public String toString() {
        return list.stream().map(expr -> expr.toString()).collect(Collectors.joining(" * "));
    }

    @Override
    public Expression Simplify() {
        // simplify all children
        list = list.stream().map(expr -> expr.Simplify()).collect(Collectors.toCollection(ArrayList::new));
        
        // if there are nested multiplications - unnest them
        ArrayList<Expression> updatedList = new ArrayList<Expression>();
        for (Expression expr : list) {
            if (expr instanceof Multiply m) {
                updatedList.add(m.getLhs());
                updatedList.add(m.getRhs());
            } else if (expr instanceof MultiplyList m) {
                updatedList.addAll(m.getList());
            } else {
                updatedList.add(expr);
            }
        }
        // TODO: using streams?
        list = updatedList;

        // sum all const values to one
        double constProduct = list.stream().filter(expr -> expr instanceof Const).mapToDouble(c -> ((Const)c).getValue()).reduce(1, (a, b) -> a * b);
        list = list.stream().filter(expr -> !(expr instanceof Const)).collect(Collectors.toCollection(ArrayList::new));
        if (constProduct == 0.0) return new Const(0);
        else if (constProduct != 1.0) list.add(0, new Const(constProduct));
        return this;
    }

    @Override
    public boolean isAtomic() {
        return false;
    }
}
