package ORM;

import java.util.ArrayList;

public interface IModel {
    void define();
    String getTableName();
    ArrayList<Definition> getDefinitions();
}
