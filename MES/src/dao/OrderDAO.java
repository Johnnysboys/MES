package dao;

import connector.IConnector;
import dto.OrderDTO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Properties;

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
    public OrderDTO getOrder(String productionID, IConnector connection) {

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
    public void updateOrder(OrderDTO order, IConnector connection) {

    }
}
