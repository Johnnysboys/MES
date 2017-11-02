package wonton.types;

public enum DataTypes  {
    STRING("VARCHAR"),
    INTEGER("INTEGER"),
    SERIAL("SERIAL"),
    BIGINT("BIGINTEGER"),
    FLOAT("FLOAT"),
    DOUBLE("DOUBLE"),
    DATE("DATE"),
    DATENOW("now()"),
    TEXT("TEXT"),
    ENUM("VARCHAR");


    private final String dataType;

    DataTypes(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString(){
        return this.dataType;
    }
}