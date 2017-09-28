package dto;

import java.util.Date;//skift evt. til java.sql.Date;

public class OrderDTO {
    private String productID;
    private int itemNumber;
    private int quantity;
    private Date delivery;
    private String status;

    public OrderDTO(String productID, int itemNumber, int quantity, Date delivery, String status){
        this.productID=productID;
        this.itemNumber=itemNumber;
        this.quantity=quantity;
        this.delivery=delivery;
        this.status=status;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDelivery() {
        return delivery;
    }

    public void setDelivery(Date delivery) {
        this.delivery = delivery;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
