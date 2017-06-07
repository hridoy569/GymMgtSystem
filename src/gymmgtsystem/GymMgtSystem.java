/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmgtsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.DB;

/**
 *
 * @author Hridoy
 */
public class GymMgtSystem extends Application {
    public static final String MainFrame = "/gymmgtsystem/MainFrame.fxml";
    public static final String Login = "/gymmgtsystem/Login.fxml";
    public static final String InsInfo = "/gymmgtsystem/InstructorInfo.fxml";
    public static final String ShiftForm = "/gymmgtsystem/ShiftForm.fxml";
    public static final String PackagesForm = "/gymmgtsystem/PackagesForm.fxml";
    public static final String NewUser = "/gymmgtsystem/NewUser.fxml";
    public static final String DBconnect = "/util/DBconnect.fxml";
    Parent root;
    
    @Override
    public void start(Stage stage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
      
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
//        stage.setMaximized(true);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2); 
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);  
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
