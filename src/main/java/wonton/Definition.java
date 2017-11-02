package wonton;

import wonton.types.DataTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Definition {
    private String fieldName;
    private DataTypes type;
    private String defaultValue;
    private List allowedValues;
    private ArrayList<Constraint> contraints;

    public Definition(String fieldName, DataTypes type) {
        this.fieldName = fieldName;
        this.type = type;
    }

    public String getFieldName() {
        return fieldName;
    }
    public DataTypes getType() {
        return type;
    }
    public ArrayList<Constraint> getConstraints() {
        return contraints;
    }
    public String getDefaultValue() {
        return defaultValue;
    }
    public boolean hasAllowedValues() {
        return allowedValues != null;
    }

    public void setConstraints(ArrayList<Constraint> contraints) {
        this.contraints = contraints;
    }
    public void setDefaultValue(DataTypes defaultValue) {
        this.defaultValue = defaultValue.toString();
    }
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
    public <T> boolean isAllowedValue(T value){
        return this.allowedValues.contains(value);
    }
    public <T> void setAllowedValues(T... allowedValues) {
        this.allowedValues = Arrays.asList(allowedValues);
    }

}
