import Models.LogModel;
import ORM.DataTypes;
import ORM.Service;

import java.util.HashMap;

public class TestORM {
    public static void main(String[] args) {
        Service logService = new Service(new LogModel());
        logService.create(new HashMap<DataTypes, String>(){{ put(DataTypes.INTEGER, "2");}});

    }
}
