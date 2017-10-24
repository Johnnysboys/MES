package ORM;


import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class SQLConstructor {
    private Statement statement;
    private StringBuilder query;
    public String construct(){
        this.query = new StringBuilder();
        return "";
    }

    public SQLConstructor insert(IModel model, HashMap<DataTypes, String> data) {
        for (Definition dif : model.getDefinitions()){
            System.out.println(dif.getFieldName() + " Type: " + dif.getType());
        }
        return this;
    }
    public SQLConstructor into(String table){
        return this;
    }
}
