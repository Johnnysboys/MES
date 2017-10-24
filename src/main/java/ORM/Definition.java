package ORM;

import ORM.Exceptions.NotEnumTypeException;
import com.sun.prism.PixelFormat;

import java.util.HashMap;

public class Definition {
    private String fieldName;
    private DataTypes type;
    private String defaultValue;
    private String[] allowedValues;
    private HashMap<ConstraintTypes, String> contraints;

    public Definition(String fieldName, DataTypes type, HashMap<ConstraintTypes, String> contraints) {
        this.fieldName = fieldName;
        this.type = type;
        this.contraints = contraints;
    }
    public Definition(String fieldName, DataTypes type) {
        this.fieldName = fieldName;
        this.type = type;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public DataTypes getType() {
        return type;
    }

    public void setType(DataTypes type) {
        this.type = type;
    }

    public HashMap getContraints() {
        return contraints;
    }

    public void setContraints(HashMap<ConstraintTypes, String> contraints) {
        System.out.println(this.fieldName + " constraints:");
        for (ConstraintTypes s : contraints.keySet()) {
            System.out.println("   " + s.toString() + " " + contraints.get(s));
        }
        this.contraints = contraints;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(DataTypes defaultValue) {
        this.defaultValue = defaultValue.toString();
    }
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String[] getAllowedValues() {
        return allowedValues;
    }

    public void setAllowedValues(String[] allowedValues) throws NotEnumTypeException {
        if(this.type != DataTypes.ENUM){
            throw new NotEnumTypeException();
        }
        this.allowedValues = allowedValues;
    }
}
