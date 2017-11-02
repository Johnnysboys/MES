/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes;

import java.util.HashMap;

/**
 *
 * @author Troels
 */
public class OrderStatusOverview {
    private HashMap<String,OrderStatus> statusMap;
    
    public OrderStatusOverview(){
        statusMap = new HashMap<String, OrderStatus>();
        
        for (OrderStatus status : OrderStatus.values()){
            statusMap.put(status.toString(), status);
        }
    }
    
    public OrderStatus getOrderStatus(String statusString){
        return statusMap.get(statusString);
}
    
}
