/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmgtsystem;

import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Hridoy
 */
public class AllMembersTableController implements Initializable {

    @FXML
    private TableView<?> movieTable;
    @FXML
    private TableColumn<?, ?> idColumn;
    @FXML
    private TableColumn<?, ?> titleColumn;
    @FXML
    private TableColumn<?, ?> descColumn;
    @FXML
    private TableColumn<?, ?> runTimeColumn;
    @FXML
    private TableColumn<?, ?> yearColumn;
    @FXML
    private TableColumn<?, ?> genreColumn;
    @FXML
    private TableColumn<?, ?> ratingColumn;
    @FXML
    private TableColumn<?, ?> watchColumn;
    @FXML
    private TableColumn<?, ?> favouriteColumn;
    @FXML
    private TableColumn<?, ?> dateColumn;
    @FXML
    private TableColumn<?, ?> posterColumn;
    @FXML
    private TextField filterInput;
    @FXML
    private ImageView img;
    @FXML
    private Button resetFormBtn;
    @FXML
    private Label foundItemLabel;
    @FXML
    private Button deleteBtn;
    @FXML
    private AnchorPane formroot;
    @FXML
    private StackPane viewStackPane;
    @FXML
    private JFXToggleButton toggleViewMembersBtn;
    @FXML
    private Button closeBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

        // TODO
    @FXML
    private void selectTableRowListener(MouseEvent event) {
    }


    @FXML
    private void resetFormBtnAction(MouseEvent event) {
    }

    @FXML
    private void deleteBtnAction(ActionEvent event) {
    }

    @FXML
    private void toggleViewMembersBtnAction(MouseEvent event) {
    }

    @FXML
    private void closeBtnAction(MouseEvent event) {
        formroot.toBack();
    }
    
}
