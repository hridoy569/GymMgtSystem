/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmgtsystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author B3
 */
public class MembershipTableController implements Initializable {

    @FXML
    private AnchorPane formAnchorPane;
    @FXML
    private Button resetBillBtn;
    @FXML
    private Button deleteBillBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void resetMemberFormBtnAction(MouseEvent event) {
    }

    @FXML
    private void resetBillBtn(ActionEvent event) {
    }

    @FXML
    private void deleteBillBtnAction(ActionEvent event) {
    }
    
}
