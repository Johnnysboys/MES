package ORM;

public enum DataTypes  {
    STRING("STRING"),
    INTEGER("INTEGER"),
    BIGINT("BIGINTEGER"),
    FLOAT("FLOAT"),
    DOUBLE("DOUBLE"),
    DATE("DATE"),
    DATENOW("now()"),
    TEXT("now()"),
    ENUM("ENUM");


    private final String dataType;

    DataTypes(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString(){
        return this.dataType;
    }
}