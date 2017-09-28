package dao;

import connector.IConnector;
import dto.OrderDTO;

import java.util.ArrayList;
import java.util.List;

public interface IOrderDAO {
    OrderDTO getOrder(String productionID, IConnector connection);
    void updateOrder(OrderDTO order, IConnector connection);
    ArrayList<OrderDTO> getAllOrders(IConnector connection);
}
