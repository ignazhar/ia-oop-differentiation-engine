package differentiation_engine.parser;

import differentiation_engine.expressions.Expression;

final class ExpressionItem implements Item {
    private Expression expression;
    public ExpressionItem(Expression expression) {this.expression = expression;}
    public Expression getExpression() {return this.expression;}
}