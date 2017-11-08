package dao_mes;
import dto_mes.OrderDTO;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import models.ordersModel;
import wonton.Row;
import wonton.Wonton;
import wonton.connections.PGConnection;
import wonton.exceptions.DoesNotExistsInModelException;
import wonton.service.Service;

public class OrderDAO {

    private Wonton wonton;
    private Service caller;
    private static OrderDAO instance;
    private Map<String,OrderDTO> orders;

    public static synchronized OrderDAO get(){
        if(instance==null)
            instance=new OrderDAO();
        return instance;
    }

    private OrderDAO(){
        try {
            wonton = new Wonton(new PGConnection("admin_dhk", "admin_dhk", "code1234", "51.254.143.91"));
            caller = wonton.createService(new ordersModel());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public OrderDTO getOrder(String orderID) {
        if(orders.containsKey(orderID))
            return orders.get(orderID);
        getAllOrders();
        return orders.get(orderID);
    }

    /**
     * NOT IMPLEMENTED
     * @param order
     */
    public void updateOrder(OrderDTO order) {
        /*
        *TODO
         */
    }

    public List<OrderDTO> getAllOrders() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        if(orders==null)
            orders=new HashMap<>();

        List<Row> list=caller.find();
        try {
            for (Row r : list) {
                if (orders.containsKey((String) r.get("production").getData()))
                    continue;
                else {
                    orders.put((String) r.get("production").getData(), new OrderDTO((String) r.get("production").getData(),
                            r.get("itemnumber").getData().toString(),
                            Integer.valueOf(r.get("quantity").getData().toString().split("\\.")[0]),
                            formatter.parse(r.get("delivery").toString())));
                }
            }
        }catch (ParseException e){
            e.printStackTrace();
        }
        return new ArrayList<>(orders.values());
    }
    
    public ArrayList getAllArticleNumber(){
        ArrayList<Integer> reslist= new ArrayList<>();
        List<Row> list;
        try {
            list =caller.find("itemnumber");
            for (Row r : list) {
                reslist.add((Integer) r.get(0).getData());
            }
        } catch (DoesNotExistsInModelException e) {
            e.printStackTrace();
        }
        return reslist;
        
    }
    
    public ArrayList getAllOrderNumber(){
      ArrayList<String> reslist= new ArrayList<>();
      List<Row> list;
      try {
          list =caller.find("production");
          for (Row r : list) {
              reslist.add((String) r.get(0).getData());
          }
      } catch (DoesNotExistsInModelException e) {
          e.printStackTrace();
      }
      return reslist;
        
    }
      
    public ArrayList getAllQuantity(){
        ArrayList<Integer> reslist= new ArrayList<>();
        List<Row> list;
        try {
            list =caller.find("quantity");
            for (Row r : list) {
                reslist.add((Integer) r.get(0).getData());
            }
        } catch (DoesNotExistsInModelException e) {
            e.printStackTrace();
        }
        return reslist;

    }
    
}
