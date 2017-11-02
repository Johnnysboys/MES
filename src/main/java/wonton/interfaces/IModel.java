package wonton.interfaces;

import wonton.Definition;

import java.util.ArrayList;

public interface IModel {
    String getTableName();
    ArrayList<Definition> getDefinitions();
}
