///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
///**
// *
// * @author Maruf
// */
//
//
//
//import com.jfoenix.controls.JFXToggleButton;
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.util.ResourceBundle;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableColumn.CellDataFeatures;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.StackPane;
//import javafx.util.Callback;
//import util.DB;
//
///**
// * FXML Controller class
// *
// * @author Hridoy
// */
//public class AllMembersTableController implements Initializable {
//
//    
//    @FXML
//    private TableView memberTable;
//    private ObservableList data;
//
//    
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        con = DB.getConnection();
//        buildData();
//    }
//
//   
//
//    public void buildData() {
//        data = FXCollections.observableArrayList();
//        try {
//            //SQL FOR SELECTING ALL OF CUSTOMER
//            String SQL = "SELECT * from member";   						//change table name
//            //ResultSet
//            ResultSet rs = con.createStatement().executeQuery(SQL);
//
//            //* TABLE COLUMN ADDED DYNAMICALLY *
//            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
//                //We are using non property style for making dynamic table
//                final int j = i;
//                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
//                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
//                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
//                        return new SimpleStringProperty(param.getValue().get(j).toString());
//                    }
//                });
//
//                memberTable.getColumns().addAll(col);			//change table name
//                System.out.println("Column [" + i + "] ");
//            }
//            //* Data added to ObservableList *
//            while (rs.next()) {
//                //Iterate Row
//                ObservableList<String> row = FXCollections.observableArrayList();
//                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
//                    //Iterate Column
//                    row.add(rs.getString(i));
//                }
//                System.out.println("Row [1] added " + row);
//                data.add(row);
//
//            }
//
//            //FINALLY ADDED TO TableView
//            memberTable.setItems(data);			//change table name
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error on Building Data");
//        }
//    }
//}
