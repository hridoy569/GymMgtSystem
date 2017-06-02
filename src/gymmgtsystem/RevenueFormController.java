/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmgtsystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.DB;

/**
 * FXML Controller class
 *
 * @author Hridoy
 */
public class RevenueFormController implements Initializable {

    @FXML
    private AnchorPane formroot;
    @FXML
    private Button reloadTableBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private VBox sidePane;
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
    private void reloadTableBtnAction(MouseEvent event) {
    }

    @FXML
    private void deleteBtnAction(ActionEvent event) {
    }

    @FXML
    private void closeBtnAction(MouseEvent event) {
        formroot.toBack();
    }
    private void changeThemeColor(){
        try {
            String sql = "SELECT color_code FROM color where id=1";
            ps = con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                colorCode = rs.getString("color_code");
                sidePane.setStyle("-fx-background-color:"+colorCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
