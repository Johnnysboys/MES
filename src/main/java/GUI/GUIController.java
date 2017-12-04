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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import scadaConnection.AlreadyExecutedException;
import scadaConnection.ExceedsCapacityException;

import javax.swing.*;

/**
 *
 * @author Troels
 */
public class GUIController implements Initializable {
    
    @FXML
    private Label label;
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



    private MESController mc;


    
    @FXML
    private void update(ActionEvent event){
        OrderTable.refresh();
        
          }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mc = new MESController();
                
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
        if(OrderTable.getSelectionModel().getSelectedItem().equals(null)){
            System.out.println("The Order you selected is null");
        }else{
            try {
                mc.getRMI().executeOrder(OrderTable.getSelectionModel().getSelectedItem());
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (ExceedsCapacityException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.toString(), "There is not enough capacity at the moment",
                        JOptionPane.ERROR_MESSAGE);

            } catch (AlreadyExecutedException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.toString(), "This order is already executed",
                        JOptionPane.ERROR_MESSAGE);
            }
        }


    }

    }

