package wonton;

import wonton.types.DataTypes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Definition {
    private String fieldName;
    private DataTypes type;
    private String defaultValue;
    private List allowedValues;
    private ArrayList<Constraint> constraints;

    public Definition(String fieldName, DataTypes type) {
        this.fieldName = fieldName;
        this.type = type;
    }

    public String getFieldName() {
        return fieldName;
    }
    DataTypes getType() {
        return type;
    }
    ArrayList<Constraint> getConstraints() {
        return constraints;
    }
    String getDefaultValue() {
        return defaultValue;
    }
    boolean hasAllowedValues() {
        return allowedValues != null;
    }

    public void setConstraints(ArrayList<Constraint> constraints) {
        this.constraints = constraints;
    }
    public void setConstraints(Constraint... constraints) {
        this.constraints = new ArrayList<>();
        this.constraints.addAll(Arrays.asList(constraints));
    }
    public void setDefaultValue(DataTypes defaultValue) {
        this.defaultValue = defaultValue.toString();
    }
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
    <T> boolean isAllowedValue(T value){
        return this.allowedValues.contains(value);
    }
    public <T> void setAllowedValues(T... allowedValues) {
        this.allowedValues = Arrays.asList(allowedValues);
    }

}
