package differentiation_engine;

import java.util.HashMap;

/*
I chose abstract class, not interface for Expression, 
because Expression itself has defined behaviour(can be evaluated, differentiated, printed).
*/
public abstract class Expression {
    public abstract Expression differentiate(Variable var);

    public abstract double evaluate(HashMap<Variable, Double> values);
    
    public abstract String toString();

    public Expression Simplify() {
        return this;
    }
}
