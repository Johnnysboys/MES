/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import dto.OrderDTO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mes.MESController;

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
    
    private void handleButtonAction(ActionEvent event) {
     
    }
    
    private void setOrderTable(ActionEvent event, MESController mc){
        TableColumn articleNumber = new TableColumn("Article Number");
        TableColumn quantity = new TableColumn("Quantity");
        TableColumn orderID = new TableColumn("OrderID");
        
        articleNumber.setCellValueFactory(new PropertyValueFactory<>("Article Number"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        orderID.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        
        OrderTable.getColumns().addAll(articleNumber, quantity, orderID);
        
        OrderTable.setItems(mc.getERPArtivleNumbers());
  
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

   
    
}
