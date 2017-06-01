/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmgtsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.DB;

/**
 * FXML Controller class
 *
 * @author B3
 */
public class InstructorInfoController implements Initializable {

    @FXML
    private JFXTextField fname;
    @FXML
    private JFXDatePicker dob;
    @FXML
    private JFXTextField weight1;
    @FXML
    private JFXTextArea address;
    @FXML
    private JFXTextArea address1;
    @FXML
    private JFXTextArea address2;
    @FXML
    private JFXButton closeBtn;
    @FXML
    private AnchorPane rootPane;
    Connection con = null;
    PreparedStatement ps;
    ResultSet rs;
    private String colorCode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 
        con = DB.getConnection();
        changeThemeColor();
    }    

    @FXML
    private void closeBtnAction(ActionEvent event) {
        Stage current = (Stage) closeBtn.getScene().getWindow();
        current.close();
        
    }
    
    private void changeThemeColor() {
        try {
            String sql = "SELECT color_code FROM color where id=1";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                colorCode = rs.getString("color_code");
                rootPane.setStyle("-fx-background-color:" + colorCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
