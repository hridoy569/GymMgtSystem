/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmgtsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import org.controlsfx.control.PropertySheet;
import util.DB;

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
    @FXML
    private JFXButton getProfileBtn;
    @FXML
    private HBox bgBlack;
    private boolean logoAdded;
    private File file;
    private BufferedImage bufferedImage;
    private WritableImage image;
    private String imagePath;
    private FileInputStream proPic;
    Connection con = null;
    PreparedStatement ps;
    ResultSet rs;
    private byte[] dbImage;
    private String profile_id;
    @FXML
    private StackPane settingsStackPane;
    int countcreatePkgBtnBtn = 0;
    private AnchorPane shiftForm;
    @FXML
    private JFXColorPicker colorPicker;
    @FXML
    private VBox sidePane;
    @FXML
    private JFXButton okBtn;
    String hex2;
    @FXML
    private AnchorPane rootPane;
    String colorCode;
    @FXML
    private HBox themeLabel;
    @FXML
    private Button addShiftBtn;
    @FXML
    private Button addShiftBtn1;
    @FXML
    private Label themeLabel1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        con = DB.getConnection();
//        colorPicker.setValue(Color.valueOf("#222"));
        changeThemeColor();
    }

    @FXML
    private void updateBtnAction(ActionEvent event) {

        try {

            String sql = "UPDATE business_profile SET businessName=?, businessAddress=?, businessPhone=?, businessEmail=?, businessLogo=? WHERE idbusinessProfile=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, bname.getText());
            ps.setString(2, baddress.getText());
            ps.setString(3, bphone.getText());
            ps.setString(4, bemail.getText());
            if (logoAdded == false) {
                ps.setBytes(5, dbImage);
            } else {
                ps.setBinaryStream(5, proPic, proPic.available());
                logoAdded = false;
            }
            ps.setString(6, profile_id);
            ps.executeUpdate();
            resetProfile();

            System.out.println("Profile updated");

        } catch (Exception e) {
            System.out.println("update error");
            e.printStackTrace();
        }
    }

    private void loadBusinessProfile() {
        try {

            String sql = "SELECT * FROM business_profile";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                profile_id = rs.getString("idbusinessProfile");
                bname.setText(rs.getString("businessName"));
                baddress.setText(rs.getString("businessAddress"));
                bphone.setText(rs.getString("businessPhone"));
                bemail.setText(rs.getString("businessEmail"));
                dbImage = rs.getBytes("businessLogo");
                Image convertToJavaFXImage = convertToJavaFXImage(dbImage, 250, 60);
                changeLogoView.setImage(convertToJavaFXImage);
                bgBlack.setStyle("-fx-background-color: "+colorCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Image convertToJavaFXImage(byte[] raw, final int width, final int height) {
        WritableImage image = new WritableImage(width, height);
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(raw);
            BufferedImage read = ImageIO.read(bis);
            image = SwingFXUtils.toFXImage(read, null);
        } catch (IOException ex) {
//            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }

    @FXML
    private void getProfileBtnAction(ActionEvent event) {
        loadBusinessProfile();
    }

    @FXML
    private void resetBtnAction(ActionEvent event) {
        resetProfile();

    }

    public void resetProfile() {
        bname.clear();
        baddress.clear();
        bphone.clear();
        bemail.clear();
        changeLogoView.setImage(null);
        bgBlack.setStyle("-fx-background-color: transparent");
    }

    @FXML
    private void browseBtnAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG (Joint Photographic Group)", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG (Joint Photographic Experts Group)", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG (Portable Network Graphics)", "*.png")
        );

        fileChooser.setTitle("Choise a Image File");

        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            System.out.println(file);
            bufferedImage = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
            changeLogoView.setImage(image);
            imagePath = file.getAbsolutePath();
            proPic = new FileInputStream(imagePath);
            System.out.println(imagePath);
            bgBlack.setStyle("-fx-background-color: #222");
        }
        logoAdded = true;
    }

    @FXML
    private void createPkgBtnAction(MouseEvent e) throws IOException {
        nextStage(GymMgtSystem.PackagesForm, "", true);
    }

    @FXML
    private void createShiftBtnAction(MouseEvent event) throws IOException {
        nextStage(GymMgtSystem.ShiftForm, "", true);
    }

    private void nextStage(String fxml, String title, boolean resizable) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(resizable);
        stage.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    @FXML
    private void colorPickerAction(ActionEvent event) {
        Color selectedColor = colorPicker.getValue();
//        sidePane.setBackground(new Background(new BackgroundFill(Paint.valueOf(selectedColor.toString()), CornerRadii.EMPTY, Insets.EMPTY)));
        themeLabel.setBackground(new Background(new BackgroundFill(Paint.valueOf(selectedColor.toString()), CornerRadii.EMPTY, Insets.EMPTY)));
        hex2 = "#" + Integer.toHexString(colorPicker.getValue().hashCode()).substring(0, 6); 
        
        
       
    }
    @FXML
    private void okBtnAction(ActionEvent event) {
        try {
            String sql = "UPDATE color SET color_code=? where id=1";
            ps = con.prepareStatement(sql);
            ps.setString(1, hex2);
            ps.executeUpdate();
//            System.out.println(hex2);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    private void defaultColorAction(ActionEvent event) {
        themeLabel.setBackground(new Background(new BackgroundFill(Paint.valueOf("#222"), CornerRadii.EMPTY, Insets.EMPTY)));
        colorPicker.setValue(Color.valueOf("#222"));
         try {
            String sql = "UPDATE color SET color_code=? where id=1";
            ps = con.prepareStatement(sql);
            ps.setString(1, "#222222");
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
