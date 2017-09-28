package dao;

import connector.IConnector;
import dto.OrderDTO;

public interface IOrderDAO {
    OrderDTO getOrder(String productionID, IConnector connection);
    void updateOrder(OrderDTO order, IConnector connection);
}
