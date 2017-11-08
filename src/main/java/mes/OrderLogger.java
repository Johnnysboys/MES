/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes;

import java.sql.SQLException;
import models.LogModel;
import wonton.Wonton;
import wonton.connections.PGConnection;
import wonton.service.Service;
import wonton.types.Operators;
import wonton.Parameter;
import wonton.exceptions.DoesNotExistsInModelException;



/**
 *
 * @author Troels
 */
public class OrderLogger {
    
    private Service service;

public OrderLogger(){

    try{
    Wonton wonton = new Wonton(new PGConnection("admin_dhk", "admin_dhk", "code1234", "51.254.143.91"));
    service = wonton.createService(new LogModel());
    
    }
    catch (SQLException e){
        e.printStackTrace();
}
    }

    public void changeDiscardedCount(String orderId, int numberDiscarded) throws DoesNotExistsInModelException{
        service.find(new Parameter("order_number", Operators.EQ, orderId) );
        
    }
}
    