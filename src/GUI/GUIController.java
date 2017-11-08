/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Anders
 */
public class GUIController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TableView<?> OrderTable;
    @FXML
    private TableColumn<?, ?> OrderPane;
    @FXML
    private TableColumn<?, ?> OrdersInProgressPane;
    @FXML
    private TableColumn<?, ?> OrderDonePane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
