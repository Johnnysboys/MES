package models;

import wonton.Constraint;
import wonton.Definition;
import wonton.Model;

import static wonton.types.ConstraintTypes.*;
import static wonton.types.DataTypes.*;

public class ordersModel extends Model {

    public ordersModel() {
        super("orders");
    }

    @Override
    protected void define() {
        Definition id = new Definition("id", SERIAL);
        id.setConstraints(new Constraint<>(PRIMARYKEY));

        Definition itemNumber = new Definition("itemnumber", STRING);
        Definition production = new Definition("production", STRING);
        production.setConstraints(new Constraint(NOTNULL));

        Definition site = new Definition("site", STRING);
        Definition warehouse = new Definition("warehouse", INTEGER);
        Definition location = new Definition("location", STRING);
        Definition serialNumber = new Definition("serialnumber", STRING);

        Definition quantity = new Definition("quantity", INTEGER);
        quantity.setConstraints(new Constraint(NOTNULL));

        Definition reportremainderasfinish = new Definition("reportremainderasfinish", STRING);
        Definition delivery = new Definition("delivery", DATE);
        delivery.setConstraints(new Constraint(NOTNULL));

        Definition status = new Definition("status", STRING);
        status.setConstraints(new Constraint(NOTNULL));
        status.setDefaultValue("Scheduled");
        status.setAllowedValues("Scheduled", "Ended", "Started");

        Definition remainstatus = new Definition("remainstatus", STRING);
        Definition referenceType = new Definition("refereneceType", STRING);

        this.setDefinitions(id,itemNumber,production,site,warehouse,location,serialNumber,quantity,reportremainderasfinish
        ,delivery,status,remainstatus,referenceType);
    }
}
