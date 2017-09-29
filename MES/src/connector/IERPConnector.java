package connector;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IERPConnector {
    void connectToDatabase() throws InstantiationException, IllegalAccessException,
            ClassNotFoundException, SQLException;
    ResultSet doQuery(String sqlQuery)throws SQLException;
    int doUpdate(String sqlUpdateStatement) throws SQLException;
    void closeConnection() throws SQLException;
}
