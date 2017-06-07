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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import util.DB;

/**
 * FXML Controller class
 *
 * @author Mohammad Arefin
 */
public class ShiftFormController implements Initializable {

    @FXML
    private JFXTextField shiftName;
    @FXML
    private JFXTextField shiftTime;
    @FXML
    private JFXButton addBtn;
    @FXML
    private JFXComboBox<String> packageName;

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<?> packageTable;
    @FXML
    private TableColumn<?, ?> serialColumn;
    @FXML
    private TableColumn<?, ?> packageNameColumn;
    @FXML
    private TableColumn<?, ?> packageTypeColumn;
    @FXML
    private TableColumn<?, ?> packageDurationColumn;
    @FXML
    private TableColumn<?, ?> packageFeeColumn;
    @FXML
    private TableColumn<?, ?> serialColumn1;
    @FXML
    private JFXButton closeBtn;
    Connection con = null;
    PreparedStatement ps;
    ResultSet rs;
    private String colorCode;
    @FXML
    private AnchorPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = new DB().getConnection();
        loadPackages();
        changeThemeColor();
    }

    public void loadPackages() {
        try {
            String sql = "SELECT * FROM package";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ObservableList<String> options
                        = FXCollections.observableArrayList(
                                rs.getString(2)
                        );

                packageName.getItems().addAll(options);

            }
        } catch (Exception e) {
        }

    }

    @FXML
    private void addshift(ActionEvent event) {
        try {
            String packName = packageName.getSelectionModel().getSelectedItem().toString();
            String sql1 = "SELECT package_id FROM package where package_name=?";
            ps = con.prepareStatement(sql1);
            ps.setString(1, packName);
            rs = ps.executeQuery();
            String packId = null;
            while (rs.next()) {
                packId = rs.getString(1);
            }

            String sql = "INSERT INTO shift(shift_name, start_time, package_id) VALUES(?,?,?) ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, shiftName.getText());
            ps.setString(2, shiftTime.getText());
            ps.setString(3, packId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {

                clearForm();
            }

        } catch (Exception e) {
            System.out.println("Can not added package");
        }
    }

    private void clearForm() {
        shiftName.setText("");
        shiftTime.setText("");
        packageName.getSelectionModel().clearSelection();

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
