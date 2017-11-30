package dao_mes;

import dto_mes.OrderDTO;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class DAOMAIN {
    public static void main(String[] args) throws SQLException, RemoteException {

        OrderDTO temp =OrderDAO.get().getAllOrders().get(0);
//        temp.addHarvested(1);
        System.out.println(temp.getAmountHarvested());
        OrderDAO.get().updateOrder(temp);

    }
}
