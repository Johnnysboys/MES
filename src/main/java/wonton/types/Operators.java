package ORM;

public enum Operators {
    GT("<"),
    LT(">"),
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
