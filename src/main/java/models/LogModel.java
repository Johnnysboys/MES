package models;

import wonton.*;
import wonton.types.ConstraintTypes;
import wonton.types.DataTypes;

import java.util.ArrayList;

public class LogModel extends Model {
    public LogModel() {
        // Define the table name
        super("Logs");
    }

    @Override
    protected void define() {
        try {
            // id column definition
            Definition idDif = new Definition("id", DataTypes.SERIAL);
            idDif.setConstraints(new ArrayList<Constraint>() {{
                add(new Constraint<>(ConstraintTypes.PRIMARYKEY));
            }});

            // OrderID column definition
            Definition orderIdDef = new Definition("order_id", DataTypes.TEXT);
            orderIdDef.setDefaultValue(DataTypes.TEXT);
            orderIdDef.setConstraints(new Constraint<>(ConstraintTypes.NOTNULL));

            // CreatedAt column definition
            Definition createdAtDif = new Definition("created_at", DataTypes.DATE);
            createdAtDif.setDefaultValue(DataTypes.DATENOW);

            // articleNumberDef column definition
            Definition articleNumberDif = new Definition("article_number", DataTypes.TEXT);
            articleNumberDif.setConstraints(new Constraint<>(ConstraintTypes.NOTNULL));
            
            // quantity column definition
            Definition quantityDif = new Definition("quantity", DataTypes.INTEGER);
            quantityDif.setConstraints(new Constraint<>(ConstraintTypes.NOTNULL));
            
            // Orderedfor coloumn Definition
            Definition orderedForDif = new Definition ("ordered_for", DataTypes.DATE);
            orderedForDif.setConstraints(new Constraint<>(ConstraintTypes.NOTNULL));
            
            // toBeDeliveredon  coloumn Definition
            Definition toBeDeliveredOnDif = new Definition ("to_be_delivered_on", DataTypes.DATE);
            toBeDeliveredOnDif.setConstraints(new Constraint<>(ConstraintTypes.NOTNULL));
            
            // status  coloumn Definition
            Definition statusDif = new Definition ("status", DataTypes.ENUM);
            statusDif.setConstraints(new Constraint<>(ConstraintTypes.NOTNULL));
            
            Definition datePlanted = new Definition ("date_planted", DataTypes.DATE);
            
            Definition dateHarvested = new Definition ("date_harvested", DataTypes.DATE);
           
            
            Definition amountToBePlanted = new Definition ("amount_planted", DataTypes.INTEGER);
            amountToBePlanted.setConstraints(new Constraint<>(ConstraintTypes.NOTNULL));
            
            Definition amountDiscarded = new Definition ("amount_discarded", DataTypes.INTEGER);
            
            

            // Lastly add all definitions to an the array list
            this.setDefinitions(idDif, orderIdDef, createdAtDif,articleNumberDif, quantityDif, orderedForDif, toBeDeliveredOnDif, statusDif, datePlanted, dateHarvested, amountToBePlanted, amountDiscarded);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
