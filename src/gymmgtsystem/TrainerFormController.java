/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmgtsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import util.DB;

/**
 * FXML Controller class
 *
 * @author Hridoy
 */
public class TrainerFormController implements Initializable {

    @FXML
    private StackPane memberStack;
    @FXML
    private AnchorPane formroot;
    @FXML
    private TabPane membersTabPane;
    @FXML
    private Tab addMemberTab;
    @FXML
    private JFXTextField lname11;
    @FXML
    private Tab membershipTab;
    @FXML
    private JFXTextField fname1;
    @FXML
    private JFXTextField lname111;
    @FXML
    private JFXComboBox<?> bodyshapCombo12;
    @FXML
    private JFXTextField lname112;
    @FXML
    private JFXTextField lname1111;
    @FXML
    private JFXComboBox<?> bodyshapCombo121;
    @FXML
    private JFXTextField lname1121;
    @FXML
    private JFXTextField lname11111;
    @FXML
    private JFXTextField lname11112;
    @FXML
    private JFXComboBox<?> bodyshapCombo1;
    @FXML
    private JFXButton getBtn12;
    @FXML
    private JFXButton getBtn121;
    @FXML
    private JFXComboBox<?> bodyshapCombo13;
    @FXML
    private JFXButton getBtn123;
    @FXML
    private JFXButton getBtn1212;
    @FXML
    private Button resetMemberFormBtn;
    @FXML
    private Button deleteMemberFormBtn;
    @FXML
    private Button allMembersBtn;
    @FXML
    private Button addShiftBtn;
    @FXML
    private Button addShiftBtn22;
    @FXML
    private JFXTextField mealName;
    @FXML
    private JFXTextField foodName;
    @FXML
    private JFXTextField quantity;
    @FXML
    private JFXTextField measure;
    @FXML
    private JFXComboBox<?> mealPlanCmb;
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
        // TODOsdfgszdfsdfsdfsdf
        con = new DB().getConnection();
        changeThemeColor();
    }    

    @FXML
    private void getBtnAction(ActionEvent event) {
    }

    @FXML
    private void memberTabAction(MouseEvent event) {
    }

    @FXML
    private void resetMemberFormBtnAction(MouseEvent event) {
    }

    @FXML
    private void deleteMemberFormBtnAction(ActionEvent event) {
    }

    @FXML
    private void allMembersBtn(MouseEvent event) {
    }

    @FXML
    private void addShiftBtnAction(ActionEvent event) {
    }

    @FXML
    private void addMealAction(ActionEvent event) {
    }

    @FXML
    private void addMealTypeAction(ActionEvent event) {
    }

    @FXML
    private void editMealAction(ActionEvent event) {
    }

    @FXML
    private void deleteMealAction(ActionEvent event) {
    }

    @FXML
    private void bodyShapeAction(ActionEvent event) {
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
