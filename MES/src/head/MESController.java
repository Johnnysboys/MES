/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package head;

import dao_mes.OrderDAO;
import dto_mes.OrderDTO;
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
    
    public ObservableList getERPArticleNumber(){
        ArrayList ERPArrayList = (ArrayList) OrderDAO.get().getAllArticleNumber();
        ObservableList<OrderDTO> ERPArticleNumbersList = FXCollections.observableArrayList(ERPArrayList);
        
        return ERPArticleNumbersList;
        
        }
    public ObservableList getERPOrderNumber(){
        ArrayList ERPArrayList = (ArrayList) OrderDAO.get().getAllOrderNumnber();
        ObservableList<OrderDTO> ERPOrderNumnerList = FXCollections.observableArrayList(ERPArrayList);
        
        return ERPOrderNumnerList;
    }
    
    public ObservableList getERPQuantity(){
        ArrayList ERPArrayList = (ArrayList) OrderDAO.get().getAllQuantity();
        ObservableList<OrderDTO> ERPQuantityList = FXCollections.observableArrayList(ERPArrayList);
        
        return ERPQuantityList;
    }
    
    
    public void getInProgressOrderList(){
        
    }
    
    public void getDoneOrderList(){
        
    }
    
    
    
    
    
    
}
