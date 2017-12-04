/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import dto_mes.OrderDTO;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import head.MESController;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import scadaConnection.ExceedsCapacityException;
import scadaConnection.RMIServer;

/**
 *
 * @author Troels
 */
public class GUIController implements Initializable {
    
    @FXML
    private Label label;
    MESController MESController = new MESController();
    @FXML
    private static TableView<OrderDTO> OrderTable;
    @FXML
    private Button updatebtn;
    @FXML
    private TableColumn<?, ?> OrderPane1;
    @FXML
    private Button ExecuteOrder66;
    @FXML
    private TableColumn<?, ?> OrdersInProgressPane1;
    @FXML
    private TableColumn<?, ?> OrderDonePane1;
    @FXML
    private TableView<?> ERPTable;
    
    @FXML
    private ScrollPane LoggingText;
    

    
    private MESController mc = new MESController();
    
    @FXML
    private void update(ActionEvent event){
        OrderTable.refresh();
        
          }
    
    private void handleExecute(ActionEvent event, RMIServer rmi, MESController mc){
        
        while(mc.getERPOrderList().iterator().hasNext()){
            try {
                mc.getOrderList().add(mc.getERPOrderList().size()-1);
                
                int orderPlace = mc.getERPOrderList().size()-1;
                int orderObject = mc.getERPOrderList().indexOf(orderPlace);
                
                try {
                    rmi.executeOrder((OrderDTO) mc.getERPOrderList().get(orderObject));
                } catch (ExceedsCapacityException ex) {
                    Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
       
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
                
        TableColumn articleNumberCol = new TableColumn("Article Number");
        TableColumn quantityCol = new TableColumn("Quantity");
        TableColumn orderIDCol = new TableColumn("OrderID");
        TableColumn statusCol = new TableColumn("Status");

        orderIDCol.setCellValueFactory(new PropertyValueFactory<OrderDTO, String>("orderNumber"));
        orderIDCol.setMinWidth(200);
        
        articleNumberCol.setCellValueFactory(new PropertyValueFactory<OrderDTO, String>("articleNumber"));
        articleNumberCol.setMinWidth(200);
        quantityCol.setCellValueFactory(new PropertyValueFactory<OrderDTO, String>("quantity"));
        quantityCol.setMinWidth(200);
        statusCol.setCellValueFactory(new PropertyValueFactory<OrderDTO, String>("status"));
        orderIDCol.setMinWidth(200);
        OrderTable.getColumns().addAll(orderIDCol, articleNumberCol, quantityCol, statusCol );
        OrderTable.setItems(mc.getOrderList());

        
        
        

    }


    public void handleExecute(ActionEvent actionEvent) {
    }
}

