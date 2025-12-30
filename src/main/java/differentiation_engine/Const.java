package differentiation_engine;

public class Const extends Expression {
    private double value;

    public Const(double value) {
        this.value = value;
    }

    public Expression differentiate(Variable var) {
        return new Const(0.0);
    }

    public String toString() {
        return Double.toString(value);
    }

    public double evaluate() {
        return value;
    }
}
