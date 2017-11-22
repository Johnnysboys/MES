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
import javafx.event.Event;
import javafx.scene.control.Button;

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
    
    private void handleButtonAction(ActionEvent event) {
     
    }

    @FXML
    private void update(ActionEvent event){
        OrderTable.refresh();
        
        
    }
    
   
//    public void showAlert(AlertType alertType, String titleText, String headerText, String contentText){
//        Alert alert = new Alert(alertType);
//        alert.setTitle(titleText);
//        alert.setHeaderText(headerText);
//        alert.setContentText(contentText);
//    }
     
    
    public void initialize(URL url, ResourceBundle rb, MESController mc) { 
        TableColumn articleNumberCol = new TableColumn("Article Number");
        TableColumn quantityCol = new TableColumn("Quantity");
        TableColumn orderIDCol = new TableColumn("OrderID");
        
        
        articleNumberCol.setCellValueFactory(new PropertyValueFactory<>("Article Number"));
        articleNumberCol.setMinWidth(200);
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        quantityCol.setMinWidth(200);
        orderIDCol.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        orderIDCol.setMinWidth(200);
        OrderTable.getColumns().addAll(articleNumberCol, quantityCol, orderIDCol);
        
        OrderTable.setItems(mc.getERPOrderList());
        

        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }


   
    
}

