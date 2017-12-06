/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loggingConnector;

import dto_mes.OrderStatus;
import java.sql.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Troels
 */
public class LogTester {
    
    
    
    public static void main (String[] args){
        
//        Date d = Date.valueOf("2016-05-12");
//        Date d2 = Date.valueOf("2016-05-17");
//        Date d3 = Date.valueOf("2016-05-16");
//        
        OrderLogger oggeLog = new OrderLogger();
//        oggeLog.addOrder("5", "a", OrderStatus.UNSCHEDULED, 4, d, d2 );
//        oggeLog.addOrder("6", "a", OrderStatus.UNSCHEDULED, 3, d, d2 );
//        oggeLog.addOrder("7", "b", OrderStatus.UNSCHEDULED, 4, d, d2 );
//        oggeLog.addOrder("8", "b", OrderStatus.UNSCHEDULED, 3, d, d2 );
//        
//        oggeLog.setAmountPlanted("5", 6);
//        oggeLog.setAmountPlanted("6", 6);
//        oggeLog.setAmountPlanted("7", 6);
//        oggeLog.setAmountPlanted("8", 6);
//        
//        oggeLog.setDiscarded("5", 2);
//        oggeLog.setDiscarded("6", 2);
//        oggeLog.setDiscarded("7", 2);
//        oggeLog.setDiscarded("8", 2);
//        
//        oggeLog.setDatePlanted("5", d);
//        oggeLog.setDatePlanted("6", d);
//        oggeLog.setDatePlanted("7", d);
//        oggeLog.setDatePlanted("8", d);
//        
//        oggeLog.setDateHarvested("5", d3);
//        oggeLog.setDateHarvested("6", d3);
//        oggeLog.setDateHarvested("7", d3);
//        oggeLog.setDateHarvested("8", d3);
        
        
        System.out.println("nu " + oggeLog.getAverageOrderDelay(Date.valueOf("2016-05-10"), Date.valueOf("2016-05-20") ));
        System.out.println(oggeLog.getDiscardedRatio(Date.valueOf("2016-05-10"), Date.valueOf("2016-05-20"), "a"));
        System.out.println(oggeLog.getAverageGrowTime(Date.valueOf("2016-05-10"), Date.valueOf("2016-05-20"), "a"));
    
    }
    
    
    
    
}
     