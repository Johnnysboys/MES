package wonton;

import wonton.types.Operators;

public class Parameter<T> {
    String column;
    Operators operator;
    T data;

    public Parameter(String column, Operators operator, T data) {
        this.column = column;
        this.operator = operator;
        this.data = data;
    }

    public String getColumn() {
        return column;
    }

    public Operators getOperator() {
        return operator;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return String.join(" ", this.column, this.operator.toString(), this.data.toString());
    }
}
