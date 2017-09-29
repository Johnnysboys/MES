package dao;

import dto.OrderDTO;
import connector.IERPConnector;

import java.util.ArrayList;
import java.util.List;

public interface IOrderDAO {
    OrderDTO getOrder(String productionID);
    void updateOrder(OrderDTO order);
    public ArrayList<OrderDTO> getAllOrders();
}

