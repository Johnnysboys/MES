/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import dao_mes.OrderDAO;
import dto_mes.OrderDTO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import scadaConnection.RMIServer;

/**
 *
 * @author alsle
 */
public class MESController {

    private RMIServer rmi;

    public MESController() {
        try {
            rmi = new RMIServer();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public RMIServer getRMI(){
        return rmi;

    }


    
    public ObservableList getOrderList(){
        ArrayList ERPArrayList = (ArrayList) OrderDAO.get().getAllOrders();
        ObservableList<OrderDTO> allOrders = FXCollections.observableArrayList(ERPArrayList);
        
        return allOrders;
    }
    
    
    
    
    
    
}
