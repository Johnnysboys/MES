package dbConnector;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        ERPConnector connector = new ERPConnector();
        connector.connectToDatabase();
        connector.closeConnection();
    }

}