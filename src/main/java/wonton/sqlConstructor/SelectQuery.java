package wonton.sqlConstructor;

import wonton.interfaces.ISelect;
import wonton.interfaces.ISql;

import static java.lang.String.*;
import static wonton.types.Statements.*;

public class SelectQuery implements ISql, ISelect {
    private StringBuilder query;
    private String[] columns, tables, joins;

    SelectQuery(){
        this.query = new StringBuilder();
    }


    @Override
    public void addColumn(String... columns) {
        this.columns = columns;
    }

    @Override
    public void addTable(String... tables) {
        this.tables = tables;
    }

    @Override
    public void addJoin(String... joins) {
        this.joins = joins;
    }

    @Override
    public String build() {
        String columnsString    = "",
                tablesString    = "",
                joinsString     = "";

        if(this.columns != null) columnsString = join(", ", this.columns);
        if(this.tables != null) tablesString = join(" ", this.tables);
        if(this.joins != null) joinsString = join(" ", this.joins);
        this.query.append(join(" ", SELECT.toString(), columnsString, FROM.toString(),  tablesString));
        return query.toString();
    }
}
