package connector;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        IConnector conn;
        try {
            conn = new Connector();
            conn.connectToDatabase();
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
