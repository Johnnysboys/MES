package wonton.interfaces;

import wonton.Data;
import wonton.Model;

import java.sql.ResultSet;
import java.util.List;

public interface IConnection {
    public void querySql(String query);
    public ResultSet queryData(String query, List<Data> data);
    public List<Data> queryModel(String query, Model model);
}
