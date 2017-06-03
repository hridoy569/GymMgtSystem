/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmgtsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import util.DB;

/**
 * FXML Controller class
 *
 * @author Hridoy
 */
public class LoginController implements Initializable {

    Connection con = null;
    Stage stage;
    Scene scene;
    @FXML
    private JFXButton loginBtn;
    private Pane pane;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXButton closeBtn;
    @FXML
    private JFXTextField uname;
    @FXML
    private JFXPasswordField upass;
    @FXML
    private JFXComboBox<String> urole;
    @FXML
    private Label businessName;
    @FXML
    private ImageView gif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = DB.getConnection();
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.4), rootPane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);
        fadeIn.play();
  
          ObservableList<String> genreList = FXCollections.observableArrayList("Admin", "User");
        urole.setItems(genreList);
        urole.getSelectionModel().selectFirst();
        loadBusinessProfile();
    }

    private void nextStage(String fxml, String title, boolean resizable) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        stage = new Stage();
        scene = new Scene(root);
//        scene.getStylesheets().add(getClass().getResource("style/winDec.css").toExternalForm());
        stage.setScene(scene);
//        makeResizable(true);
        stage.setTitle(title);
        stage.setResizable(resizable);
        stage.setMaximized(true);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

    }

    @FXML
    public void loginAction(ActionEvent event) throws SQLException {
        gif.setVisible(true);
        PauseTransition pauseTransition = new PauseTransition();
        pauseTransition.setDuration(Duration.seconds(3));
        pauseTransition.setOnFinished(ev -> {
            doLogin(event);

        });
        pauseTransition.play();
        

    }

    public void doLogin(ActionEvent event) {
        try {
            String roleValue = urole.getSelectionModel().getSelectedItem().toString();
            String sql = "SELECT * FROM user WHERE user_name= ? AND user_password= ?  AND user_role = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, uname.getText());
            ps.setString(2, upass.getText());
            ps.setString(3, roleValue);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString(2);
                String fullname = rs.getString(3);
                String email = rs.getString(5);
                String role = rs.getString(6);
                
                ((Node) event.getSource()).getScene().getWindow().hide();
                Stage st = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gymmgtsystem/MainFrame.fxml"));
                Parent root = fxmlLoader.load();
                MainFrameController main = (MainFrameController) fxmlLoader.getController();
                main.setUsrNameMedia(name);
                main.setFullnameMedia(fullname);
                main.setEmailMedia(email);
                main.setRoleMedia(role);
                Scene scene = new Scene(root);
                st.setScene(scene);
                st.setMaximized(true);
                st.setTitle("Gym Management System");
//                st.getIcons().add(new Image("/image/icon.png"));
st.initStyle(StageStyle.UNDECORATED);
st.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Login error");
        }
    }
    
    private void loadBusinessProfile() {
        try {
            
            String sql = "SELECT businessName FROM business_profile";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                businessName.setText(rs.getString("businessName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void closeAction(ActionEvent event) {
        Stage current = (Stage) closeBtn.getScene().getWindow();
        current.close();
    }

}
