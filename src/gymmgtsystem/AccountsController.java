/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmgtsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Mohammad Arefin
 */
public class AccountsController implements Initializable {

    @FXML
    private StackPane accountsStackPane;
    private int countRevenueBtn;
    private AnchorPane revenueForm;
    private AnchorPane expenseForm;
    private int countExpenseBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void revenueAction(MouseEvent e) {
        if (e.getClickCount() >= 1) {
            countRevenueBtn += 1;
        }

        if (countRevenueBtn<= 1) {

            try {
                revenueForm = FXMLLoader.load(getClass().getResource("RevenueForm.fxml"));
                accountsStackPane.getChildren().add(revenueForm);
            } catch (IOException ex) {
                Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("revenueForm added");
        } else {
            revenueForm.toFront();
            System.out.println("revenueForm to Front");
        }
    }

    @FXML
    private void expenseAction(MouseEvent e) {
        if (e.getClickCount() >= 1) {
            countExpenseBtn += 1;
        }

        if (countExpenseBtn<= 1) {

            try {
                expenseForm = FXMLLoader.load(getClass().getResource("ExpenseForm.fxml"));
                accountsStackPane.getChildren().add(expenseForm);
            } catch (IOException ex) {
                Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("expenseForm added");
        } else {
            expenseForm.toFront();
            System.out.println("expenseForm to Front");
        }
    }



}
