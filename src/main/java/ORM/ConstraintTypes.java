package ORM;

public enum ConstraintTypes {
    AUTOINCREMENT("AUTOINCREMENT"),
    ALLOWNULL("ALLOWNULL"),
    PRIMARYKEY("PRIMARYKEY"),
    FOREIGNKEY("FOREIGNKEY"),
    DEFAULTVALUE("DEFAULTVALUE"),
    ALLOWEDVALUES("ALLOWEDVALUES");

    private final String type;

    ConstraintTypes(String type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return this.type;
    }
}
