package dao;

import dto.OrderDTO;

import java.util.List;

public interface IOrderDAO {
    OrderDTO getOrder(String productionID);
    void updateOrder(OrderDTO order);
    public List<OrderDTO> getAllOrders();
}

