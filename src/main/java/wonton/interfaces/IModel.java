package ORM.interfaces;

import ORM.Definition;

import java.util.ArrayList;

public interface IModel {
    String getTableName();
    ArrayList<Definition> getDefinitions();
}
