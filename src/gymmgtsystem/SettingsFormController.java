/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmgtsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javax.imageio.ImageIO;
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
    
    private void loadLogo() {
        try {
            Connection con = DB.getConnection();
            String sql = "SELECT * FROM business_profile";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bname.setText(rs.getString("businessName"));
                baddress.setText(rs.getString("businessAddress"));
                bphone.setText("Phone: "+rs.getString("businessPhone"));
                bemail.setText("Email: "+rs.getString("businessEmail"));
                Image convertToJavaFXImage = convertToJavaFXImage(rs.getBytes("businessLogo"), 250, 60);
                changeLogoView.setImage(convertToJavaFXImage);
                bgBlack.setStyle("-fx-background-color: #222");
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
        loadLogo();
    }

    @FXML
    private void resetBtnAction(ActionEvent event) {
        bname.clear();
                baddress.clear();
                bphone.clear();
                bemail.clear();
                changeLogoView.setImage(null);
                bgBlack.setStyle("-fx-background-color: transparent");
        
    }

    @FXML
    private void browseBtnAction(ActionEvent event) {
    }
}
