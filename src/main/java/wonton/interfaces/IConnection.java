package wonton.interfaces;

import wonton.Data;
import wonton.Model;
import wonton.Row;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IConnection {
    void querySql(String query) throws SQLException;
    ResultSet queryData(String query, List<Data> data);
    List<Row> queryModel(String query, Model model);
}
