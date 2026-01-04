package differentiation_engine.expressions.parser;

public class MutableInteger {
    private int value;
    public MutableInteger(int value) {this.value = value;}
    public int getValue() {return this.value;}
    public MutableInteger inc() {this.value ++; return this; }
    public MutableInteger inc(int add) {this.value += add; return this; }
}
