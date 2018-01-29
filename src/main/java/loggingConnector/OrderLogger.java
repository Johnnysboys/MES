package loggingConnector;

import dao_mes.OrderDAO;
import java.util.ArrayList;
import java.sql.Date;
import java.util.concurrent.TimeUnit;
import models.LogModel;
import wonton.Data;
import wonton.Model;
import wonton.Parameter;
import wonton.Row;
import wonton.exceptions.DoesNotExistsInModelException;
import wonton.service.Service;
import wonton.types.Operators;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Troels
 */
public class OrderLogger {

    private static OrderLogger instance=null;
    private Service logService;
    private Model logModel;

    public static synchronized OrderLogger get(){
        if(instance==null)
            instance= new OrderLogger();
        return instance;
    }
    public OrderLogger(){
        this.logModel = new LogModel();
        logService = OrderDAO.get().getWonton().createService(logModel);
    }
    
    public Service getService(){
        return logService;
    }
    
    public void addOrder(String orderId, String articleNumber, Enum status, int quantity, Date orderedFor, Date toBeDeliveredOn){
        logService.create(new ArrayList<Data>(){{ 
            add(new Data<>("order_id", orderId));
            add(new Data<>("article_number", articleNumber));
            add(new Data<>("status", status.toString()));
            add(new Data<>("quantity", quantity));
            add(new Data<>("ordered_for", orderedFor));
            add(new Data<>("to_be_delivered_on", toBeDeliveredOn));
            add(new Data<>("amount_planted", 0));  
        }});
    }

    //Use this for GUI
    //Returns how much there has been discarded
    //Gange med 100 hvis jeg vil have den som %
    public double getDiscardedRatio(Date from, Date to, String article)  {
        try{
            int totalPlanted = 0;
            int totalDiscarded = 0;
            Parameter articleParameter = new Parameter("article_number", Operators.EQ, article);
            Parameter fromParameter = new Parameter("ordered_for", Operators.GT, from);
            Parameter toParameter = new Parameter("ordered_for", Operators.LT, to);
            ArrayList<Row> rowList = (ArrayList) logService.find(articleParameter, fromParameter, toParameter);

            for (Row r : rowList) {
                if( r.get(11).getData() != null) {
                    totalPlanted    += (int) r.get(11).getData();
                }
                if(r.get(12).getData() != null) {
                    totalDiscarded  += (int) r.get(12).getData();
                }
            }

            if(totalDiscarded <= 0 || totalPlanted <= 0) return -1;

            return (double) totalDiscarded / (double) totalPlanted;
        }
     
        
        catch (DoesNotExistsInModelException e){
          e.printStackTrace();
        }
        catch (ArithmeticException e){
            System.out.println("Nothing is planted");
        }
        return -1;
    }

    
    
    //Returner et gennemsnit for hvem meget vi returnere ved siden af kundens ønske
    //No article in this method
    public double getAverageOrderDelay(Date from, Date to){
        try{
            int delayed = 0;
            int numberOfOrders = 0;


            Parameter fromParameter = new Parameter("ordered_for", Operators.GTE, from);
            Parameter toParameter = new Parameter("ordered_for", Operators.LTE, to);
            ArrayList<Row> rowList = (ArrayList<Row>) logService.find(fromParameter, toParameter);

            for (Row r: rowList){
                // IF there is both dates in the database.
                if(r.get(6).getData() != null && r.get(7).getData() != null) {
                    Date orderedFor = (Date)r.get(6).getData();
                    Date deliveredOn = (Date)r.get(7).getData();

                    long difference = Math.abs(deliveredOn.getTime() - orderedFor.getTime());
                    delayed += (int)TimeUnit.MILLISECONDS.toDays(difference);
                    numberOfOrders ++;
                }
            }

            if (delayed <= 0 || numberOfOrders <= 0) return -1;

            return  (((double) delayed / (double) numberOfOrders) * 100);
        }
        catch(DoesNotExistsInModelException e){
                e.printStackTrace();
                }
        catch(ArithmeticException e){
                e.printStackTrace();
                System.out.println("No orders in the selected date range");
                }
        return -1;
        
    }

    //Gennemsnit af dyrkning af en specifik artikel
    public double getAverageGrowTime(Date from, Date to, String article) {
        Parameter fromParameter = new Parameter("ordered_for", Operators.GTE, from);
        Parameter toParameter = new Parameter("ordered_for", Operators.LTE, to);
        Parameter articleParameter = new Parameter("article_number", Operators.EQ, article);
        int numberOfOrders = 0;
        int daysOfGrowth = 0;

        try {
            ArrayList<Row> rowList = new ArrayList(logService.find(fromParameter, toParameter, articleParameter));
            for (Row r : rowList) {
                Date datePlanted = (Date) r.get("date_planted").getData();
                Date dateHarvested = (Date) r.get("date_harvested").getData();
                if (datePlanted != null && dateHarvested != null) {
                    long difference = dateHarvested.getTime() - datePlanted.getTime();
                    daysOfGrowth += (int) TimeUnit.MILLISECONDS.toDays(difference);
                }
                numberOfOrders++;                                
            }
            if(daysOfGrowth <= 0 || numberOfOrders <= 0) return -1;
            return daysOfGrowth / numberOfOrders; //skulle have været dividere for at få det rigtige tal
        }
        
        catch(DoesNotExistsInModelException e){
            e.printStackTrace();
        }
        
        catch(ArithmeticException e){
            System.out.println("There are no orders of that article in the selected timeframe");
        }
        
        
        return -1;
    }
    
    
    public void setDatePlanted(String orderId, Date plantedDate){
        Parameter param = new Parameter("order_id", Operators.EQ, orderId);
        ArrayList<Data> dataList = new ArrayList();
        Data data = new Data<>("date_planted", plantedDate);
        dataList.add(data);
        logService.update(dataList, param);
    }
    
    public void setDateHarvested(String orderId, Date dateHarvested){
        Parameter param = new Parameter("order_id", Operators.EQ, orderId);
        ArrayList<Data> dataList = new ArrayList();
        Data data = new Data<>("date_harvested", dateHarvested);
        dataList.add(data);
        logService.update(dataList, param);
    }
    
    
    public void setDiscarded(String orderId, int discardedAmount){
        Parameter param = new Parameter("order_id", Operators.EQ, orderId);
        ArrayList<Data> dataList = new ArrayList();
        Data data = new Data<>("amount_discarded", discardedAmount);
        dataList.add(data);
        logService.update(dataList, param);  
        
        
    }
    
    public void changeStatus(String orderId, Enum newStatus){
        Parameter param = new Parameter("order_id", Operators.EQ, orderId);
        ArrayList<Data> dataList = new ArrayList();
        Data data = new Data<> ("status", newStatus.toString());
        dataList.add(data);
        logService.update(dataList, param);     
    }
    
    public void setAmountPlanted(String orderId, int newAmount){
        Parameter param = new Parameter("order_id", Operators.EQ, orderId);
        ArrayList<Data> dataList = new ArrayList();
        Data data = new Data<> ("amount_planted", newAmount);
        dataList.add(data);
        logService.update(dataList,param);
    }
}