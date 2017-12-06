/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto_mes;

import com.sun.org.apache.xpath.internal.operations.Or;
import loggingConnector.OrderLogger;

import java.util.Date;

/**
 *
 * @author Troels
 */
public class OrderDTO {
    private String orderNumber;
    private String articleNumber;
    private int quantity;
    private Date orderedFor;
    private Date toBeDeliveredOn;
    private OrderStatus status;
    private Date datePlanted;
    private Date dateHarvested;
    private int toBePlanted;
    private int amountDiscarded=0;
    private int amountPlanted=0;
    private int amountHarvested=0;
   


public OrderDTO(String orderNumber, String articleNumber, int quantity, Date orderedFor){
    this.orderNumber = orderNumber;
    this.articleNumber = articleNumber;
    this.quantity = quantity;
    this.toBePlanted=quantity+(quantity/5);
    this.orderedFor = orderedFor;
    this.toBeDeliveredOn = this.orderedFor;
    this.status = OrderStatus.UNSCHEDULED;
}
public OrderDTO(String orderNumber, String articleNumber, int quantity, Date orderedFor, int remaining){
    this.orderNumber = orderNumber;
    this.articleNumber = articleNumber;
    this.quantity = quantity;
    this.toBePlanted=quantity+((int) Math.ceil((double)quantity/5));
    this.orderedFor = orderedFor;
    this.toBeDeliveredOn = this.orderedFor;
    this.status = OrderStatus.UNSCHEDULED;
    this.amountHarvested=quantity-remaining;
}

    public OrderDTO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getOrderedFor() {
        return orderedFor;
    }

    public void setOrderedFor(Date orderedFor) {
        this.orderedFor = orderedFor;
    }

    public Date getToBeDeliveredOn() {
        return toBeDeliveredOn;
    }

    public void setToBeDeliveredOn(Date toBeDeliveredOn) {
        this.toBeDeliveredOn = toBeDeliveredOn;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {

        this.status = status;
        OrderLogger.get().changeStatus(orderNumber,status);
    }

    public Date getDatePlanted() {
        return datePlanted;
    }

    public void setDatePlanted(Date datePlanted) {
        this.datePlanted = datePlanted;
    }

    public Date getDateHarvested() {
        return dateHarvested;
    }

    public void setDateHarvested(Date dateHarvested) {
        this.dateHarvested = dateHarvested;
    }

    public int getAmountDiscarded() {
        return amountDiscarded;
    }

    public void addDiscarded(int amountDiscarded) {
        this.amountDiscarded += amountDiscarded;
        OrderLogger.get().setDiscarded(orderNumber,this.amountDiscarded);
    }

    public int getAmountPlanted() {
        return amountPlanted;
    }

    public void addPlanted(int amountPlanted) {
        this.amountPlanted += amountPlanted;
        if(this.amountPlanted>=quantity){
            OrderLogger.get().setDatePlanted(this.orderNumber,new java.sql.Date(new Date().getTime()));
        }
        OrderLogger.get().setAmountPlanted(orderNumber,this.amountPlanted);
    }

    public int getAmountHarvested() {
        return amountHarvested;
    }

    public void addHarvested(int amountHarvested) {
        if(this.status== OrderStatus.DELIVERED)
            addDiscarded(amountHarvested);
        else
            this.amountHarvested += amountHarvested;
        if (amountHarvested>=quantity){
            this.status=OrderStatus.DELIVERED;
            OrderLogger.get().setDateHarvested(orderNumber,new java.sql.Date(new Date().getTime()));
            System.out.println("Order has been finished, status set to delivered.");
        }
    }

    public int getToBePlanted() {
        return toBePlanted;
    }

}
