package dao;

import dbConnector.ERPConnector;
import dto.OrderDTO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.*;

import dbConnector.IERPConnector;

public class OrderDAO {
    private IERPConnector connection;
    private static OrderDAO instance;
    private Map<String,OrderDTO> orders;

    public static synchronized OrderDAO get(){
        if(instance==null)
            instance=new OrderDAO();
        return instance;
    }

    private OrderDAO(){
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

    public OrderDTO getOrder(String orderID) {
        if(orders.containsKey(orderID))
            return orders.get(orderID);

        String query = MessageFormat.format(getStatement("table"), orderID);
        OrderDTO ret=null;
        ResultSet res;
        try {
            connection.connectToDatabase();
            res =connection.doQuery(query);
            connection.closeConnection();
            ret= new OrderDTO(res.getString(0),res.getInt(1),res.getInt(6),res.getDate(8));
            orders.put(ret.getOrderNumber(),ret);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * NOT IMPLEMENTED
     * @param order
     */
    public void updateOrder(OrderDTO order) {
        /**
         * @TODO implement SQL call.
         */
    }

    public List<OrderDTO> getAllOrders() {
        if(orders==null)
            orders=new HashMap<String, OrderDTO>();
        String query = getStatement("allOrders");
        ResultSet res;
        try {
            connection.connectToDatabase();
            res=connection.doQuery(query);
            connection.closeConnection();
            while(res.next()){
                if(!orders.containsKey(res.getString(0)))
                    orders.put(res.getString(0),new OrderDTO(res.getString(0),res.getInt(1),res.getInt(6),res.getDate(8)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(orders.values());
    }
}
