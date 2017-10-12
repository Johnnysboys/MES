/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Date;

/**
 *
 * @author Troels
 */
public class OrderDTO {
    private String orderNumber;
    private int articleNumber;
    private int quantity;
    private Date orderedFor;
    private Date toBeDeliveredOn;
    private OrderStatus status;
    private Date datePlanted;
    private Date dateHarvested;
    private int amountDiscarded=0;
    private int toBePlanted;
   


public OrderDTO(String orderNumber, int articleNumber, int quantity, Date orderedFor){
    this.orderNumber = orderNumber;
    this.articleNumber = articleNumber;
    this.quantity = quantity;
    this.orderedFor = orderedFor;
    this.toBeDeliveredOn = this.orderedFor;
    this.status = OrderStatus.UNSCHEDULED;
}

    public OrderDTO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public int getArticleNumber() {
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

    public void setAmountDiscarded(int amountDiscarded) {
        this.amountDiscarded = amountDiscarded;
    }

    public int getToBePlanted() {
        return toBePlanted;
    }

    public void setToBePlanted(int toBePlanted) {
        this.toBePlanted = toBePlanted;
    }
}
