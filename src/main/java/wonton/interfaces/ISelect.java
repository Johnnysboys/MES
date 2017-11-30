package wonton.interfaces;

public interface ISelect {
    void addColumn  (String... columns);
    void addTable   (String... tables);
    void addJoin    (String... joins);
}
