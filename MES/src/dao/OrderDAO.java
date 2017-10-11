package dao;

import dbConnector.ERPConnector;
import dto.OrderDTO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import dbConnector.IERPConnector;

public class OrderDAO implements IOrderDAO{
    IERPConnector connection;
    public OrderDAO(){
        connection = new ERPConnector();
    }

    private String getStatement(String text){
        Properties props = new Properties();
        try {
            File file = new File("SQLStatements.txt");
            FileInputStream in = new FileInputStream(file);
            props.load(in);
            String res = props.getProperty(text);
            return res;
        } catch (IOException e) {
            throw new IllegalStateException("Properties failed to load");
        }
    }

    @Override
    public OrderDTO getOrder(String productionID) {
        String query = MessageFormat.format(getStatement("table"), productionID);
        ResultSet res;
        try {
            connection.connectToDatabase();
            res =connection.doQuery(query);
            connection.closeConnection();
            return new OrderDTO(res.getString(0),res.getInt(1),res.getInt(6),res.getDate(8));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * NOT IMPLEMENTED
     * @param order
     */
    @Override
    public void updateOrder(OrderDTO order) {

    }

    @Override
    public List<OrderDTO> getAllOrders() {
        ArrayList<OrderDTO> r = new ArrayList<OrderDTO>();
        String query = getStatement("table");
        ResultSet res;
        try {
            connection.connectToDatabase();
            res=connection.doQuery(query);
            connection.closeConnection();
            while(res.next()){
                r.add(new OrderDTO(res.getString(0),res.getInt(1),res.getInt(6),res.getDate(8)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }
}
