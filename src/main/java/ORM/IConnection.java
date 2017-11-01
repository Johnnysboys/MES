package ORM;

import java.sql.ResultSet;
import java.util.List;

public interface IConnection {
    public void querySql(String query);
    public ResultSet insertData(String query, List<Data> data);
    public List<Data> queryModel(String query, Model model);
}
