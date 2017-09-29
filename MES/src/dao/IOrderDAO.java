package dao;

import dto.OrderDTO;
import connector.IERPConnector;

import java.util.ArrayList;
import java.util.List;

public interface IOrderDAO {
    OrderDTO getOrder(String productionID, IERPConnector connection);
    void updateOrder(OrderDTO order, IERPConnector connection);
}
