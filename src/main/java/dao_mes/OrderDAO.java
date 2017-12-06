package dao_mes;
import dto_mes.OrderDTO;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import models.ordersModel;
import wonton.Data;
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
        System.out.println("Returning OrderDAO");
        return instance;
    }

    private OrderDAO(){
        try {
            System.out.println("Created OrderDAO");
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


    public void updateOrder(OrderDTO order) {
        String tempStatus=null;
        switch(order.getStatus()){
            case SCHEDULED:
                tempStatus="Scheduled";
                break;
            case IN_PRODUCTION:
                tempStatus="Started";
                break;
            case DELIVERED:
                tempStatus="Ended";
            case UNSCHEDULED:
                tempStatus="Scheduled";
        }
        String finalStatus=tempStatus;
        List<Row> list = caller.find();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        for (Row r:list){
            if(((String) r.get("production").getData()).equals(order.getOrderNumber())){
                caller.update(
                        new ArrayList<Data>(){{
                            add(new Data<>("quantity",order.getQuantity()));
                            add(new Data<>("delivery",formatter.format(order.getOrderedFor())));
                            add(new Data<>("status",finalStatus));
                            //add(new Data<>("remainstatus",(order.getQuantity()-order.getAmountHarvested())));
                        }}
                ,(Integer) r.get("id").getData());
            }
        }
        getAllOrders();
    }

    public List<OrderDTO> getAllOrders() {
        System.out.println("Getting all orders");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        if(orders==null)
            orders=new HashMap<>();

        List<Row> list=caller.find();
        try {
            for (Row r : list) {
                if (orders.containsKey((String) r.get("production").getData()))
                    continue;
                else {
                    orders.put((String) r.get("production").getData(),
                            new OrderDTO((String) r.get("production").getData(),
                            r.get("itemnumber").getData().toString(),
                            Integer.valueOf(r.get("quantity").getData().toString().split("\\.")[0]),
                            formatter.parse(r.get("delivery").getData().toString()))
                    );
                    //{{
                  //  this.addHarvested(Integer.valueOf(r.get("remainstatus").getData().toString()));
                //}}
                }
            }
        }catch (ParseException e){
            e.printStackTrace();
        }
        System.out.println("Returning list of orders to Server");
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
    
    public  Wonton getWonton(){
        return this.wonton;
    }
    
}
