package differentiation_engine.expressions.parser;

// TODO: dig into this further. This was a hint from ChatGPT to use this 
// instead of a direct full class for Enumeration of values
sealed interface Item permits SignItem, ExpressionItem {}

/*
public class Item {
    private enum ItemType {
        SIGN,
        EXPRESSION;
    };

    private final ItemType itemType;
    private final Character sign;
    private final Expression expression;
    
    private Item(ItemType itemType, char sign, Expression expression) {
        this.itemType = itemType;
        this.sign = sign;
        this.expression = expression;
    }

    public Item CreateSign(char sign) {
        return new Item(ItemType.SIGN, sign, null);
    }

    public Item CreateExpression(Expression expression) {
        return new Item(ItemType.EXPRESSION, null, expression);
    }
}*/