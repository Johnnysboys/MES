package Models;

import ORM.Definition;
import ORM.Exceptions.NotEnumTypeException;
import ORM.IModel;
import ORM.DataTypes;
import ORM.ConstraintTypes;

import java.util.ArrayList;
import java.util.HashMap;

public class LogModel implements IModel {
    // Definition for the table logs
    private String tableName = "Logs";
    private ArrayList<Definition> definitions = new ArrayList();

    public LogModel(){
        this.define();
    }

    public void define() {
        // id column definition
        Definition idDif = new Definition("id", DataTypes.INTEGER);
        idDif.setContraints(new HashMap<ConstraintTypes, String>() {{
            put(ConstraintTypes.AUTOINCREMENT, "true");
            put(ConstraintTypes.FOREIGNKEY, "true");
        }});
        definitions.add(idDif);

        // CreatedAt column definition
        Definition createdAtDif = new Definition("createdAt", DataTypes.DATE);
        createdAtDif.setDefaultValue(DataTypes.DATENOW);
        createdAtDif.setContraints(new HashMap<ConstraintTypes, String>() {{
            put(ConstraintTypes.ALLOWNULL, "true");
        }});
        definitions.add(createdAtDif);


        // logType column definition
        try {
            Definition logTypeDif = new Definition("logType", DataTypes.ENUM);
            logTypeDif.setAllowedValues(new String[]{"info", "error", "warning"});
            logTypeDif.setContraints(new HashMap<ConstraintTypes, String>(){{
                put(ConstraintTypes.ALLOWNULL, "true");
            }});
            logTypeDif.setDefaultValue("info");
            definitions.add(logTypeDif);
        } catch (NotEnumTypeException e) {
            e.printStackTrace();
        }

        // text column definition
        Definition textDif = new Definition("text", DataTypes.TEXT);
        textDif.setContraints(new HashMap<ConstraintTypes, String>(){{
            put(ConstraintTypes.ALLOWNULL, "false");
        }});

        definitions.add(textDif);


    }

    public String getTableName() {
        return tableName;
    }

    public ArrayList<Definition> getDefinitions() {
        return definitions;
    }
}
