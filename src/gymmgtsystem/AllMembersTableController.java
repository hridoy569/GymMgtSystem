/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmgtsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import util.DB;

/**
 * FXML Controller class
 *
 * @author Hridoy
 */
public class AllMembersTableController implements Initializable {

    @FXML
    private TextField filterInput;
    @FXML
    private ImageView img;
    @FXML
    private Label foundItemLabel;
    @FXML
    private Button deleteBtn;
    @FXML
    private AnchorPane formroot;
    @FXML
    private StackPane viewStackPane;
    @FXML
    private Button closeBtn;
    private ObservableList<ObservableList> data;
    ObservableList<String> row;
    @FXML
    private TableView <ObservableList> memberTable;
    @FXML
    private Button reloadTableBtn;
    private String id;
    Connection con = null;
    PreparedStatement ps;
    ResultSet rs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = DB.getConnection();
        buildData();
        
        filterInput.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterMovieList((String) oldValue, (String) newValue);
            }

        });
    }
    
    public void filterMovieList(String oldValue, String newValue) {
        ObservableList<ObservableList> filteredListIncrease = FXCollections.observableArrayList();
        ObservableList<ObservableList> filteredListDecrese = FXCollections.observableArrayList();
        if (filterInput.equals("") || newValue.equals("")) {
            memberTable.setItems(data);
            //counts records
            int totalRecord = data.size();
            if (totalRecord <= 1) {
                foundItemLabel.setText("Found " + totalRecord + " member");
            } else {
                foundItemLabel.setText("Found " + totalRecord + " members");
            }

        } else if (newValue.length() < oldValue.length()) {
            newValue = newValue.toUpperCase();
            for (ObservableList member : data) {
                String filterId = member.get(0).toString();
                String filterFname = member.get(1).toString();
                String filterLname = member.get(2).toString();
                String filterEmail = member.get(5).toString();
                String filterCell = member.get(6).toString();
                if (filterId.toUpperCase().contains(newValue) || filterFname.toUpperCase().contains(newValue) || filterLname.toUpperCase().contains(newValue)|| filterEmail.toUpperCase().contains(newValue)|| filterCell.toUpperCase().contains(newValue) ) {
                    filteredListDecrese.add(member);
                }
            }
            memberTable.setItems(filteredListDecrese);
            //counts records
            int totalRecord = filteredListDecrese.size();
            if (totalRecord <= 1) {
                foundItemLabel.setText("Found " + totalRecord + " member");
            } else {
                foundItemLabel.setText("Found " + totalRecord + " members");
            }

        } else {
            newValue = newValue.toUpperCase();
            for (ObservableList member : data) {
                String filterId = member.get(0).toString();
                String filterFname = member.get(1).toString();
                String filterLname = member.get(2).toString();
                String filterEmail = member.get(5).toString();
                String filterCell = member.get(6).toString();
                if (filterId.toUpperCase().contains(newValue) || filterFname.toUpperCase().contains(newValue) || filterLname.toUpperCase().contains(newValue)|| filterEmail.toUpperCase().contains(newValue)|| filterCell.toUpperCase().contains(newValue) ) {
                    filteredListIncrease.add(member);

                }
            }
            memberTable.setItems(filteredListIncrease);
            //counts records
            int totalRecord = filteredListIncrease.size();
            if (totalRecord <= 1) {
                foundItemLabel.setText("Found " + totalRecord + " member");
            } else {
                foundItemLabel.setText("Found " + totalRecord + " members");
            }
        }
    }

    @FXML
    private void selectTableRowListener(MouseEvent event) {
        id = memberTable.getItems().get(memberTable.getSelectionModel().getSelectedIndex()).get(0).toString();
        System.out.println(id);
    }


    @FXML
    private void deleteBtnAction(ActionEvent event) {
        System.out.println("delete");
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Confirmation to " + "delete"));
        content.setBody(new Text("Do you want to delete Member ID "+id
                + " ? If your answer is yes press Okay button "
                + "else click outside of this box."));
        JFXDialog dialog = new JFXDialog(viewStackPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("Okay");
        button.setStyle("-fx-background-color: #094AAB; -fx-text-fill: #fff;");
        final Glow glow = new Glow();
        glow.setLevel(0.69);
        button.setEffect(glow);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ps = con.prepareStatement("delete from member where member_id=?");
                    ps.setString(1, id);
                    ps.executeUpdate();
                    dialog.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MembersFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

           });
        content.setActions(button);
        dialog.show();
    }

    @FXML
    private void closeBtnAction(MouseEvent event) {
        formroot.toBack();
    }

    public void buildData() {
        data = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * from member";
            ResultSet rs = con.createStatement().executeQuery(SQL);

            for (int i = 0; i < 13; i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                memberTable.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            while (rs.next()) {
                //Iterate Row
                row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                data.add(row);
            }

            //FINALLY ADDED TO TableView
            memberTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @FXML
    private void reloadTableBtnAction(MouseEvent event) {
        memberTable.refresh();
        data.clear();
        row.clear();
        filterInput.clear();
        buildData();
        
    }

    
    

}
