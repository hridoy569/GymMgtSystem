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
import java.sql.Date;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import util.DB;

/**
 * FXML Controller class
 *
 * @author Mohammad Arefin
 */
public class ProductsFormController implements Initializable {

    Connection con = null;

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
    @FXML
    private Button allMembersBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    LocalDate date;

    @FXML
    private void addProduct(ActionEvent event) {

     String sqlDate = purchaseDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        try {

            con = DB.getConnection();
            String sql = "INSERT INTO product(product_name, product_type, description, product_size, buying_price, selling_price, quantity, product_code, purchase_date) VALUES(?,?,?,?,?,?,?,?,?) ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, productName.getText());
            ps.setString(2, productType.getText());
            ps.setString(3, productDescription.getText());
            ps.setString(4, productSize.getText());
            ps.setString(5, buyingPrice.getText());
            ps.setString(6, sellingPrice.getText());
            ps.setString(7, productQuantity.getText());
            ps.setString(8, productCode.getText());

            ps.setString(9, sqlDate);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                clearForm();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearForm() {
        productName.setText("");
        productCode.setText("");
        productDescription.setText("");
        productSize.setText("");
        productQuantity.setText("");
        buyingPrice.setText("");
        sellingPrice.setText("");

    }

    @FXML
    private void allMembersBtn(MouseEvent event) {
    }

}
