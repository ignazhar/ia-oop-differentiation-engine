package differentiation_engine.parser;

final class SignItem implements Item {
    private char sign;
    public SignItem(char sign) {this.sign = sign;}
    public char getSign() {return this.sign;}
}