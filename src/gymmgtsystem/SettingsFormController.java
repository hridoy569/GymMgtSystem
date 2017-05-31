/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmgtsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author B3
 */
public class SettingsFormController implements Initializable {

    @FXML
    private JFXTextField bname;
    @FXML
    private JFXTextField bphone;
    @FXML
    private JFXTextField baddress;
    @FXML
    private JFXTextField bemail;
    @FXML
    private ImageView changeLogoView;
    @FXML
    private JFXButton updateBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void updateBtnAction(ActionEvent event) {
    }
    
}
