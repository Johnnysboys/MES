package dao_mes;

import dto_mes.OrderDTO;

import java.util.List;

public class DAOMAIN {
    public static void main(String[] args) {
        OrderDAO.get();
        List<OrderDTO> list = OrderDAO.get().getAllOrders();
        for(OrderDTO d:list) {
            System.out.println(d.getOrderNumber());
            System.out.println(d.getArticleNumber());
        }
    }
}
