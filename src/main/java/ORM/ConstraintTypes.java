package ORM;

public enum ConstraintTypes {
    AUTOINCREMENT("AUTOINCREMENT"),
    NOTNULL("NOT NULL"),
    PRIMARYKEY("PRIMARY KEY"),
    FOREIGNKEY("FOREIGNKEY"),
    DEFAULTVALUE("DEFAULT"),
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
