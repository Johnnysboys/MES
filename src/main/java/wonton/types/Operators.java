package wonton.types;

public enum Operators {
    GT(">"),
    LT("<"),
    GTE(">="),
    LTE("<="),
    EQ("=");

    private final String operator;
    Operators(String op){
        this.operator = op;
    }
    @Override
    public String toString(){
        return this.operator;
    }
}
