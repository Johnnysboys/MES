/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes;

import dao.OrderDAO;
import dto.OrderDTO;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author alsle
 */
public class MESController {
    OrderDAO orderDAO = new OrderDAO();

    public MESController() {
        
    }
    
    public ObservableList getERPOrderList(){
        
        ArrayList ERPArrayList = orderDAO.getAllOrders();
        ObservableList<OrderDTO> ERPOrderList = FXCollections.observableArrayList(ERPArrayList);
        
        return ERPOrderList;
    }
    
    public void getInProgressOrderList(){
        
    }
    
    public void getDoneOrderList(){
        
    }
    
    
    
    
    
    
}
