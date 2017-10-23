package dbConnector;



import java.sql.*;

public class ERPConnector implements IERPConnector {
    private Connection conn;
    private Statement stm;

    public void connectToDatabase()
    {
        //Properties props = new Properties();
        //props.put("username",Constant.user);
        //props.put("password",Constant.password);
        //props.put("databaseName",Constant.dbname);

        // call the driver class' no argument constructor
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (Exception e) {
            System.out.println("Exception when registering driver");
            System.out.println(e.getMessage());
        }

        // get Connection-object via DriverManager

        try {
            conn = DriverManager.getConnection(Constant.connURL);
            stm	= conn.createStatement();
        } catch (Exception e) {
            System.out.println("Error getting connection");
            System.out.println(e.getMessage());
        }

    }

    public ResultSet doQuery(String cmd) throws SQLException
    {
        return stm.executeQuery(cmd);
    }

    public int doUpdate(String cmd) throws SQLException
    {
        return stm.executeUpdate(cmd);
    }

    public void closeConnection() throws SQLException{
        stm.close();
        conn.close();
    }
}
