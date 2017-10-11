package dbConnector;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello SQL");

        try{
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (Exception e) {
            System.err.println("Got an exception when registering driver!");
            System.err.println(e.getMessage());
        }

        try {
            String url = "jdbc:sqlserver://sam-sql1.sam.c.sdu.dk:1433;databaseName=Dynamics09;user=AXReader;password=AXReader";
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM prodtable");
            while ( rs.next() ) {
                String lastName = rs.getString("DATAAREAID");
                System.out.println(lastName);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

}