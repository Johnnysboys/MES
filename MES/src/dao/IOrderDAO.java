package dao;

import dto.OrderDTO;
import connector.IERPConnector;

import java.util.ArrayList;
import java.util.List;

public interface IOrderDAO {
<<<<<<< Updated upstream
    OrderDTO getOrder(String productionID, IConnector connection);
    void updateOrder(OrderDTO order, IConnector connection);
    ArrayList<OrderDTO> getAllOrders(IConnector connection);
=======
    OrderDTO getOrder(String productionID, IERPConnector connection);
    void updateOrder(OrderDTO order, IERPConnector connection);
>>>>>>> Stashed changes
}
