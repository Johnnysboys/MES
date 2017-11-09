package dao_mes;

import dto_mes.OrderDTO;
import models.ordersModel;
import scadaConnection.RMIServer;
import wonton.Data;
import wonton.Row;
import wonton.Wonton;
import wonton.connections.PGConnection;
import wonton.service.Service;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DAOMAIN {
    public static void main(String[] args) throws SQLException, RemoteException {
//        Random random = new Random();
//        int low=5;
//        int high=20;
//
//        Wonton wonton;
//        wonton = new Wonton(new PGConnection("admin_dhk", "admin_dhk", "code1234", "51.254.143.91"));
//        Service caller = wonton.createService(new ordersModel());
//
//        List<Row> list2= caller.find();
//        List<OrderDTO> list2= OrderDAO.get().getAllOrders();
//        OrderDTO dto = list2.get(0);
//        dto.setQuantity(14);
//        OrderDAO.get().updateOrder(dto);
        System.out.println(OrderDAO.get().getAllOrders().get(0).getQuantity());
//        System.out.println(list2.get(0).getOrderNumber());
//        System.out.println(list2.get(0).getArticleNumber());
//        System.out.println(list2.get(0).getQuantity());
//        System.out.println(list2.get(0).getOrderedFor().toString());
//        for (OrderDTO r:list2){
//            caller.update(
//                    (Integer)r.get("id").getData(),
//                    new ArrayList<Data>(){{
//                add(new Data("quantity",random.nextInt(high-low)+low));
//            }
//            });

//        }
//        RMIServer server = new RMIServer();
    }
}
