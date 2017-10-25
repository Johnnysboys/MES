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

    public MESController() {
        
    }
    
    public ObservableList getERPOrderList(){
        ArrayList ERPArrayList = (ArrayList) OrderDAO.get().getAllOrders();
        ObservableList<OrderDTO> ERPOrderList = FXCollections.observableArrayList(ERPArrayList);
        
        
        return ERPOrderList;
    }
    
    public ObservableList getERPArtivleNumbers(){
        ArrayList ERPArrayList = (ArrayList) OrderDAO.get().getAllArticleNumber();
        ObservableList<OrderDTO> ERPArticleNumbersList = FXCollections.observableArrayList(ERPArrayList);
        
        return ERPArticleNumbersList;
        
        }
    
    
    public void getInProgressOrderList(){
        
    }
    
    public void getDoneOrderList(){
        
    }
    
    
    
    
    
    
}
