package connector;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        IERPConnector conn;
        try {
            conn = new ERPConnector();
            conn.connectToDatabase();
            System.out.println("Connected!");
            conn.closeConnection();
            System.exit(1);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
