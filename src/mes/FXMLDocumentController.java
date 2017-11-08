/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

/**
 * FXML Controller class
 *
 * @author Anders
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TableColumn<?, ?> orderIDCol;
    @FXML
    private TableColumn<?, ?> articleNumberCol;
    @FXML
    private TableColumn<?, ?> quantityCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    
    
}
