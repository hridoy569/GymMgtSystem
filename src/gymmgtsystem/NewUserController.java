/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmgtsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import util.DB;

/**
 * FXML Controller class
 *
 * @author B14
 */
public class NewUserController implements Initializable {

    @FXML
    private JFXTextField userName;
    @FXML
    private JFXButton createUserBtn;
    @FXML
    private JFXTextField fullName;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXTextField email;
    private JFXDatePicker datePick;
    @FXML
    private ImageView profilePhoto;
    @FXML
    private JFXButton closeBtn;
    @FXML
    private JFXComboBox<String> roleCmb;

    private File file;
    private BufferedImage bufferedImage;
    private WritableImage image;
    private String imagePath;
    FileInputStream proPic;

    Connection con = null;
    PreparedStatement ps;
    ResultSet rs;
    @FXML
    private JFXButton addPhotoBtn;
    @FXML
    private JFXButton getUserBtn;
    @FXML
    private Button resetFormBtn;
    @FXML
    private Button deleteMemberFormBtn;
    @FXML
    private JFXTextField userId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> memberTypeList = FXCollections.observableArrayList("Admin", "User");
        roleCmb.setItems(memberTypeList);
        con = DB.getConnection();
    }

    @FXML
    private void createUserBtnAction(ActionEvent event) {
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

        try {
            String sql = "INSERT into user(user_name, user_fullname, user_password, user_email, user_role, join_date, user_img) VALUES(?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, userName.getText());
            ps.setString(2, fullName.getText());
            ps.setString(3, password.getText());
            ps.setString(4, email.getText());
            ps.setString(5, roleCmb.getSelectionModel().getSelectedItem().toString());
            ps.setTimestamp(6, date);
            ps.setBinaryStream(7, proPic, proPic.available());
            ps.executeUpdate();

            resetUserDetails();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Save error");
        }
    }

    @FXML
    private void addPhotoBtnAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG (Portable Network Graphics)", "*.png"),
                new FileChooser.ExtensionFilter("JPG (Joint Photographic Group)", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG (Joint Photographic Experts Group)", "*.jpeg")
        );

        fileChooser.setTitle("Choise a Image File");

        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            System.out.println(file);
            bufferedImage = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
            profilePhoto.setImage(image);
            imagePath = file.getAbsolutePath();
            proPic = new FileInputStream(imagePath);
            System.out.println(imagePath);
        }
    }

    @FXML
    private void closeBtnAction(ActionEvent event) {
        Stage current = (Stage) closeBtn.getScene().getWindow();
        current.close();
    }

    private void resetUserDetails() {
        userId.clear();
        userName.clear();
        fullName.clear();
        password.clear();
        email.clear();
        roleCmb.getSelectionModel().clearSelection();
        profilePhoto.setImage(null);
    }

    @FXML
    private void getUserBtnAction(ActionEvent event) throws SQLException {
        ps = con.prepareStatement("Select * from user where user_name=?");
        ps.setString(1, userName.getText());
        rs = ps.executeQuery();
        while (rs.next()) {
            userId.setText(rs.getString(1));
            userName.setText(rs.getString(2));
            fullName.setText(rs.getString(3));
            password.setText(rs.getString(4));
            email.setText(rs.getString(5));
            roleCmb.setValue(rs.getString(6));
            Image convertToJavaFXImage = convertToJavaFXImage(rs.getBytes(8), 180, 180);
            profilePhoto.setImage(convertToJavaFXImage);
        }
    }

    private static Image convertToJavaFXImage(byte[] raw, final int width, final int height) {
        WritableImage image = new WritableImage(width, height);
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(raw);
            BufferedImage read = ImageIO.read(bis);
            image = SwingFXUtils.toFXImage(read, null);
        } catch (IOException ex) {
            Logger.getLogger(NewUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }

    @FXML
    private void resetBtnAction(MouseEvent event) {
        resetUserDetails();
    }

    @FXML
    private void deleteMemberFormBtnAction(ActionEvent event) {
    }

    @FXML
    private void updateUserAction(ActionEvent event) throws SQLException, IOException {
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

        try {
            ps = con.prepareStatement("update user set user_name=?, user_fullname=?, user_password=?, user_email=?, user_role=?, join_date=?, user_img=? where user_id = ?");
            ps.setString(1, userName.getText());
            ps.setString(2, fullName.getText());
            ps.setString(3, password.getText());
            ps.setString(4, email.getText());
            ps.setString(5, roleCmb.getSelectionModel().getSelectedItem().toString());
            ps.setTimestamp(6, date);
            ps.setBinaryStream(7, proPic, proPic.available());
            ps.setString(8, userId.getText());
            ps.executeUpdate();

            resetUserDetails();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Update error");
        }

    }

}
