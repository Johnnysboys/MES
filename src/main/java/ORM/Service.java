package ORM;

import java.util.HashMap;

public class Service implements IService {
    private SQLConstructor sqlConstructor;
    private IModel model;
    public Service(IModel model){
        this.model = model;
        this.sqlConstructor = new SQLConstructor();
    }

    public int create(HashMap<DataTypes, String> data) {
        this.sqlConstructor.insert(this.model, new HashMap<DataTypes, String>(){{ put(DataTypes.INTEGER, "2");}});
        return 0;
    }

    @Override
    public int create() {
        return 0;
    }

    public int update() {
        return 0;
    }

    public int find() {
        return 0;
    }

    public int get() {
        return 0;
    }

    public int delete() {
        return 0;
    }
}
