
import dbConnector.Constant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import wonton.Wonton;
import wonton.connections.PGConnection;
import wonton.service.Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Troels
 */
public class OrderLogger implements ILoggingConnector {
    
 private Connection conn;
    private Statement stm;

    public void connectToDatabase()
    {
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
