/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Date;
import dao.OrderStatus;

/**
 *
 * @author Troels
 */
public class OrderDTO {
    private int orderNumber;
    private int articleNumber;
    private int quantity;
    private Date orderedFor;
    private Date toBeDeliveredOn;
    private OrderStatus status;
   


public OrderDTO(int orderNumber, int articleNumber, int quantity, Date orderedFor){
    orderNumber = this.orderNumber;
    articleNumber = this.articleNumber;
    quantity = this.quantity;
    orderedFor = this.orderedFor;
    orderedFor = this.toBeDeliveredOn;
    this.status = OrderStatus.UNSCHEDULED;
    
}
}
