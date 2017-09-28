package dao;

import dto.OrderDTO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Properties;
import connector.IERPConnector;

public class OrderDAO implements IOrderDAO{
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
    public OrderDTO getOrder(String productionID, IERPConnector connection) {

        String query = MessageFormat.format(getStatement("table"), productionID);
        ResultSet res;
        try {
            res =connection.doQuery(query);
            return new OrderDTO(res.getString(0),res.getInt(1),res.getInt(6),res.getDate(8));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateOrder(OrderDTO order, IERPConnector connection) {

    }

    @Override
    public ArrayList<OrderDTO> getAllOrders(IConnector connection) {
        ArrayList<OrderDTO> r = new ArrayList<OrderDTO>();
        String query = getStatement("table");
        ResultSet res;
        try {
            res=connection.doQuery(query);
            while(res.next()){
                r.add(new OrderDTO(res.getString(0),res.getInt(1),res.getInt(6),res.getDate(8)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }
}
