package util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import gymmgtsystem.GymMgtSystem;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DB {
    
    @FXML
    private Label businessName;
    @FXML
    private JFXTextField dbuname;
    @FXML
    private JFXPasswordField dbupass;
    @FXML
    private JFXButton dbConnectBtn;
    @FXML
    private AnchorPane dbPane;
    String user ;
    String pass ;

    Connection con = null;

    public Connection getConnection() {
        try {
            System.out.println("con method "+user+" "+pass);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gymmgtsystem", user, pass);
            System.out.println("connection done");
            return con;
        } catch (Exception e) {
            System.out.println("connection error");
            return null;
        }

    }
    

    @FXML
    private void dbConnectBtnAction(ActionEvent event) {
        user = dbuname.getText();
        pass = dbupass.getText();
        if (getConnection()==null) {
            dbuname.setText("Insert Correct info!");
        } else {
            dbPane.toBack();
            System.out.println(user+" "+pass);
        }
//        try {
//            getConnection();
//            dbPane.toBack();
//        } catch (Exception e) {
//            
//        }
//        
    }
    
}
