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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import util.DB;

/**
 * FXML Controller class
 *
 * @author Mohammad Arefin
 */
public class InstrumentsController implements Initializable {

    @FXML
    private StackPane memberStack;
    @FXML
    private AnchorPane formroot;
    @FXML
    private JFXTextField productName;
    @FXML
    private JFXTextField productCode;
    @FXML
    private JFXTextField productSize;
    @FXML
    private JFXTextField productQuantity;
    @FXML
    private JFXTextArea productDescription;
    @FXML
    private JFXTextField buyingPrice;
    @FXML
    private JFXTextField sellingPrice;
    @FXML
    private JFXDatePicker purchaseDate;
    @FXML
    private JFXTextField productType;
    @FXML
    private JFXButton addBtn;
    Connection con = null;
    PreparedStatement ps;
    ResultSet rs;
    private String colorCode;
    @FXML
    private VBox sidePane;
    @FXML
    private TabPane membersTabPane;
    @FXML
    private Tab addMemberTab;
    @FXML
    private Label mshipLabel;
    @FXML
    private Tab membershipTab;
    @FXML
    private TableView<?> productTable;
    @FXML
    private Label mshipLabel1;
    @FXML
    private TextField filterInput;
    @FXML
    private JFXTextField productSearch;
    @FXML
    private JFXButton getProductBtn;
    @FXML
    private Button refreshBtn;
    @FXML
    private Button deleteProductBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = DB.getConnection();
        changeThemeColor();
    }    

    @FXML
    private void addProduct(ActionEvent event) {
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

    @FXML
    private void searchProduct(ActionEvent event) {
    }

    @FXML
    private void memberTabAction(MouseEvent event) {
    }

    @FXML
    private void getProduct(ActionEvent event) {
    }

    @FXML
    private void resetMemberFormBtnAction(MouseEvent event) {
    }

    @FXML
    private void refreshBtnAction(ActionEvent event) {
    }

    @FXML
    private void deleteProduct(ActionEvent event) {
    }
    
}
