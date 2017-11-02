package wonton;

import wonton.types.ConstraintTypes;

public class Constraint<T> {
    private ConstraintTypes type;
    private T arg;
    public Constraint(ConstraintTypes type) {
        this.type = type;
    }

    public Constraint(ConstraintTypes type, T arg) {
        this.type = type;
        this.arg = arg;
    }

    @Override
    public String toString(){
        return this.arg != null ? this.type.toString() + " " + this.arg : this.type.toString();
    }
}
