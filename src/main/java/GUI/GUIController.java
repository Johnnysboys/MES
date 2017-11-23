/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import dto_mes.OrderDTO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import head.MESController;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

/**
 *
 * @author Troels
 */
public class GUIController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TableColumn<String, String> OrderPane;
    @FXML
    private TableColumn<OrderDTO, String> OrdersInProgressPane;
    @FXML
    private TableColumn<OrderDTO, String> OrderDonePane;
    
    MESController MESController = new MESController();
    @FXML
    private TableView<OrderDTO> OrderTable;
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
        ERPTable.refresh();
        
          }
    
    private void handleExecute(ActionEvent event, RMIServer rmi, MESController mc){
        rmi.executeOrder((OrderDTO) mc.getERPOrderList());
        OrderTable.refresh();
        ERPTable.refresh();
        
    }
    
   
//    public void showAlert(AlertType alertType, String titleText, String headerText, String contentText){
//        Alert alert = new Alert(alertType);
//        alert.setTitle(titleText);
//        alert.setHeaderText(headerText);
//        alert.setContentText(contentText);
//    }
     


    @Override
    public void initialize(URL location, ResourceBundle resources) {
                
        TableColumn articleNumberCol = new TableColumn("Article Number");
        TableColumn quantityCol = new TableColumn("Quantity");
        TableColumn orderIDCol = new TableColumn("OrderID");
        TableColumn statusCol = new TableColumn("Status");
        
        articleNumberCol.setCellValueFactory(new PropertyValueFactory<>("Article Number"));
        articleNumberCol.setMinWidth(200);
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        quantityCol.setMinWidth(200);
        orderIDCol.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        orderIDCol.setMinWidth(200);
        statusCol.setCellValueFactory(new PropertyValueFactory<>("Status"));
        orderIDCol.setMinWidth(200);
        OrderTable.getColumns().addAll(articleNumberCol, quantityCol, orderIDCol);
        OrderTable.setItems(mc.getOrderList());
        
        TableColumn orderIDERP = new TableColumn("Order ID(ERP)");
        TableColumn articleNumberERP = new TableColumn("Articlenumber(ERP)");
        TableColumn QuantityERP = new TableColumn("Quantity(ERP)");
        
        orderIDERP.setCellFactory(new PropertyValueFactory<>("Order ID(ERP)"));
        orderIDERP.setMinWidth(200);
        articleNumberERP.setCellValueFactory(new PropertyValueFactory<>("Articlenumber(ERP)"));
        articleNumberERP.setMinWidth(200);
        QuantityERP.setCellValueFactory(new PropertyValueFactory<>("Quantity(ERP)"));
        QuantityERP.setMinWidth(200);
        ERPTable.getColumns().addAll(orderIDERP, articleNumberERP, QuantityERP);
        ERPTable.setItems(mc.getERPOrderList());
        
        
        
        
    }

   
    



}

