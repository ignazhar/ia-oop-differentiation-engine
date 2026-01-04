package differentiation_engine.expressions.parser;

final class SignItem implements Item {
    private char sign;
    public SignItem(char sign) {this.sign = sign;}
    public char getSign() {return this.sign;}
}