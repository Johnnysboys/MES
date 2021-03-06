package wonton.types;

public enum Statements {
    CREATE("CREATE"),
    TABLE("TABLE"),
    UPDATE("UPDATE"),
    IFNOT("IF NOT EXISTS"),
    WHERE("WHERE"),
    INSERT("INSERT"),
    DROP("DROP"),
    JOIN("JOIN"),
    SELECT("SELECT"),
    FROM("FROM"),
    VALUES("VALUES"),
    DELETE("DELETE"),
    DEFAULT("DEFAULT"),
    NOTNULL("NOT NULL");

    private final String type;

    Statements(String type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return this.type;
    }
}