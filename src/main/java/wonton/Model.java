package wonton;

import wonton.exceptions.DoesNotExistsInModelException;
import wonton.exceptions.IsNotAnAllowedValueException;
import wonton.interfaces.IModel;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Model implements IModel {
    private ArrayList<Definition> definitions;
    private String tableName;

    public Model(String tableName) {
        this.tableName = tableName;
        this.definitions = new ArrayList<>();
        this.define();
    }

    public String getTableName() {
        return tableName;
    }

    public ArrayList<Definition> getDefinitions() {
        return definitions;
    }
    public int countFields() {
        return this.definitions.size();
    }

    protected void setDefinitions(Definition... definitions ){
        this.definitions.addAll(Arrays.asList(definitions));
    }

    protected void setDefinitions(List<Definition> definitions){
        this.definitions.addAll(definitions);
    }

    public Definition getDefintion(String field){
        for(Definition definition: this.definitions){
            if(definition.getFieldName().equals(field)){
                return definition;
            }
        }
        return null;
    }
    public boolean hasColumn(String field){
        for(Definition definition : this.definitions ){
            if(definition.getFieldName().equals(field)) return true;
        }
        return false;
    }
    public void validateData(List<Data> data) throws DoesNotExistsInModelException, IsNotAnAllowedValueException {
        for(Data d : data){
            if(!this.hasColumn(d.getField())){
                throw new DoesNotExistsInModelException("Model does not contain " + d.getField());
            }
            Definition definition = this.getDefintion(d.getField());
            if(definition.hasAllowedValues()){
                if(!definition.isAllowedValue(d.getData())){
                    throw new IsNotAnAllowedValueException(d.getData() + " Is not an allowed values of " + d.getField());
                }
            }
        }
    }
    public void extractDataFromResultSet(ResultSet resultSet){

    }
    protected abstract void define();
}
