package dto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Troels
 */
public enum OrderStatus {
    UNSCHEDULED("Unscheduled"), SCHEDULED("Scheduled"), IN_PRODUCTION("In production"), DELIVERED("Delivered");
    
    private final String status;

    OrderStatus(String status){
        this.status = status;
    }
    
    public String toString(){
        return status;
    }
}
