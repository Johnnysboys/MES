/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes;

import connector.IERPConnector;
import dao.OrderDAO;
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
    
    public void getERPOrderList(){
         orderDAO.getAllOrders(connection)
        ObservableList<> ERPOrderList = FXCollections.observableArrayList();
    }
    
    public void getInProgressOrderList(){
        
    }
    
    public void getDoneOrderList(){
        
    }
    
    
    
    
    
    
}
