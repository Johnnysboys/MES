package Models;

import wonton.*;
import wonton.types.ConstraintTypes;
import wonton.types.DataTypes;

import java.util.ArrayList;

public class LogModel extends Model {
    public LogModel() {
        // Define the table name
        super("Logs");
    }

    public void define() {
        try {
            // id column definition
            Definition idDif = new Definition("id", DataTypes.SERIAL);
            idDif.setConstraints(new ArrayList<Constraint>() {{
                add(new Constraint<>(ConstraintTypes.PRIMARYKEY));
            }});

            // CreatedAt column definition
            Definition createdAtDif = new Definition("created_at", DataTypes.DATE);
            createdAtDif.setDefaultValue(DataTypes.DATENOW);

            // logType column definition
            Definition logTypeDif = new Definition("log_type", DataTypes.ENUM);
            logTypeDif.setAllowedValues("info", "error", "warning");
            logTypeDif.setDefaultValue("info");

            // text column definition
            Definition textDif = new Definition("text", DataTypes.TEXT);

            // Lastly add all definitions to an the array list
            this.setDefinitions(idDif, textDif, logTypeDif, createdAtDif);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
