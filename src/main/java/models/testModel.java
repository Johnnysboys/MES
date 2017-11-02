package models;

import wonton.*;
import wonton.types.ConstraintTypes;
import wonton.types.DataTypes;

import java.util.ArrayList;

public class testModel extends Model{

    public testModel() {
        super("test");
    }

    @Override
    public void define() {
        Definition id = new Definition("id", DataTypes.SERIAL);
        id.setConstraints(new ArrayList<Constraint>(){{
            add(new Constraint(ConstraintTypes.PRIMARYKEY));
        }});

        Definition ints = new Definition("int", DataTypes.INTEGER);
        ints.setConstraints(new ArrayList<Constraint>(){{
            add(new Constraint(ConstraintTypes.NOTNULL));
        }});
        ints.setAllowedValues(1,2,4,5);
        Definition strings = new Definition("strings", DataTypes.STRING);

        this.setDefinitions(id, ints, strings);
    }
}
