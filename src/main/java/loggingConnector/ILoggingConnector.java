package loggingConnector;



import java.sql.ResultSet;
import java.sql.SQLException;

public interface ILoggingConnector {
    void connectToDatabase();
    ResultSet doQuery(String sqlQuery)throws SQLException;
    int doUpdate(String sqlUpdateStatement) throws SQLException;
    void closeConnection() throws SQLException;
}
