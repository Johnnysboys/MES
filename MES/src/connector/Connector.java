package connector;

import java.sql.*;

public class Connector implements IERPConnector {
    private Connection conn;
    private Statement stm;

    public void connectToDatabase()
            throws InstantiationException, IllegalAccessException,
            ClassNotFoundException, SQLException
    {
        // call the driver class' no argument constructor
        //Class.forName("com.mysql.jdbc.Driver").newInstance();

        // get Connection-object via DriverManager
        conn = (Connection) DriverManager.getConnection(Constant.connectionURL);
        stm		= conn.createStatement();
    }

    public ResultSet doQuery(String cmd) throws SQLException
    {
        return stm.executeQuery(cmd);
    }

    public int doUpdate(String cmd) throws SQLException
    {
        return stm.executeUpdate(cmd);
    }
}
