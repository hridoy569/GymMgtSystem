/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmgtsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import util.DB;

/**
 * FXML Controller class
 *
 * @author Mohammad Arefin
 */
public class ProductsFormController implements Initializable {


    @FXML
    private AnchorPane formroot;
    @FXML
    private JFXTextField productName;
    @FXML
    private JFXTextField productCode;
    @FXML
    private JFXTextField productSize;
    @FXML
    private JFXTextField productQuantity;
    @FXML
    private JFXTextArea productDescription;
    @FXML
    private JFXTextField buyingPrice;
    @FXML
    private JFXTextField sellingPrice;
    @FXML
    private JFXDatePicker purchaseDate;
    @FXML
    private JFXTextField productType;
    @FXML
    private JFXButton addBtn;

    @FXML
    private TableView<ObservableList> productTable;

    @FXML
    private JFXTextField productSearch;
    
    @FXML
    private TextField filterInput;
    @FXML
    private Tab addMemberTab;
    @FXML
    private JFXButton getProductBtn;
    @FXML
    private Button refreshBtn;
    @FXML
    private Button deleteProductBtn;
    @FXML
    private VBox sidePane;
    Connection con = null;
    PreparedStatement ps;
    ResultSet rs;
    private String colorCode;
    @FXML
    private Label mshipLabel;
    @FXML
    private Label mshipLabel1;
    @FXML
    private Label foundItemLabel;
    @FXML
    private StackPane memberStack;
    @FXML
    private TabPane membersTabPane;
    @FXML
    private Tab membershipTab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
       con = new DB().getConnection();
          buildData();
        changeThemeColor();
        
        filterInput.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterProductList((String) oldValue, (String) newValue);
            }

        });
    }
    LocalDate date;
    String add = "add";
//    String update = "Update Product";
    
        public void filterProductList(String oldValue, String newValue) {
        ObservableList<ObservableList> filteredListIncrease = FXCollections.observableArrayList();
        ObservableList<ObservableList> filteredListDecrese = FXCollections.observableArrayList();
        if (filterInput.equals("") || newValue.equals("")) {
            productTable.setItems(data);
            //counts records
            int totalRecord = data.size();
            if (totalRecord <= 1) {
                foundItemLabel.setText("Found " + totalRecord + " product");
            } else {
                foundItemLabel.setText("Found " + totalRecord + " products");
            }

        } else if (newValue.length() < oldValue.length()) {
            newValue = newValue.toUpperCase();
            for (ObservableList product : data) {
                String filterId = product.get(0).toString();
                String filterName = product.get(1).toString();
                String filterCode = product.get(8).toString();
                String filterType = product.get(2).toString();
            
                if (filterId.toUpperCase().contains(newValue) || filterName.toUpperCase().contains(newValue) || filterCode.toUpperCase().contains(newValue) || filterType.toUpperCase().contains(newValue) ) {
                    filteredListDecrese.add(product);
                }
            }
            productTable.setItems(filteredListDecrese);
            //counts records
            int totalRecord = filteredListDecrese.size();
            if (totalRecord <= 1) {
                foundItemLabel.setText("Found " + totalRecord + " product");
            } else {
                foundItemLabel.setText("Found " + totalRecord + " products");
            }

        } else {
            newValue = newValue.toUpperCase();
            for (ObservableList product : data) {
                String filterId = product.get(0).toString();
                String filterName = product.get(1).toString();
                String filterCode = product.get(8).toString();
                String filterType = product.get(2).toString();
                if (filterId.toUpperCase().contains(newValue) || filterName.toUpperCase().contains(newValue) || filterCode.toUpperCase().contains(newValue) || filterType.toUpperCase().contains(newValue) ) {
                    filteredListIncrease.add(product);

                }
            }
            productTable.setItems(filteredListIncrease);
            //counts records
            int totalRecord = filteredListIncrease.size();
            if (totalRecord <= 1) {
                foundItemLabel.setText("Found " + totalRecord + " product");
            } else {
                foundItemLabel.setText("Found " + totalRecord + " products");
            }
        }
    }
    
    
 
    @FXML
    private void addProduct(ActionEvent event) {
        if (add.equals("add")) {
           
String sqlDate = purchaseDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            try {

                String sql = "INSERT INTO product(product_name, product_type, description, product_size, buying_price, selling_price, quantity, product_code, purchase_date) VALUES(?,?,?,?,?,?,?,?,?) ";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, productName.getText());
                ps.setString(2, productType.getText());
                ps.setString(3, productDescription.getText());
                ps.setString(4, productSize.getText());
                ps.setString(5, buyingPrice.getText());
                ps.setString(6, sellingPrice.getText());
                ps.setString(7, productQuantity.getText());
                ps.setString(8, productCode.getText());

                ps.setString(9, sqlDate);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    clearForm();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else  {

            try {
String sqlDate = purchaseDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String sql = "UPDATE product SET product_name = ?, product_type =?, description =?, product_size =?, buying_price =?, selling_price =?, quantity=?, product_code =?, purchase_date = ? where product_id=? ";
                PreparedStatement ps = con.prepareStatement(sql);
                
                ps.setString(1, productName.getText());
                ps.setString(2, productType.getText());
                ps.setString(3, productDescription.getText());
                ps.setString(4, productSize.getText());
                ps.setString(5, buyingPrice.getText());
                ps.setString(6, sellingPrice.getText());
                ps.setString(7, productQuantity.getText());
                ps.setString(8, productCode.getText());
                ps.setString(9, sqlDate);
                ps.setString(10, productSearch.getText());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    clearForm();
                    addBtn.setText("Add Product");
                    productSearch.setText("");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    


    private void clearForm() {
        productName.setText("");
        productType.setText("");
        productCode.setText("");
        productDescription.setText("");
        productSize.setText("");
        productQuantity.setText("");
        buyingPrice.setText("");
        sellingPrice.setText("");
        purchaseDate.setValue(null);

    }

    
  

    String getProductId;

    @FXML
    private void getProduct(ActionEvent event) {
         try {
            String sql = "Select * from product where product_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            getProductId = productSearch.getText();
            ps.setString(1, getProductId);
       
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                productName.setText(rs.getString(2));
                productType.setText(rs.getString(3));
                productDescription.setText(rs.getString(4));
                productSize.setText(rs.getString(4));
               
                buyingPrice.setText(rs.getString(5));
                sellingPrice.setText(rs.getString(6));
                productQuantity.setText(rs.getString(7));
                productCode.setText(rs.getString(8));
                 java.sql.Date d = rs.getDate("purchase_date");
                purchaseDate.setValue(d.toLocalDate());
                
                addBtn.setText("Update Product");
                add = "update";
                
            } else {

            }
        } catch (Exception e) {
        }
    }


    @FXML
    private void deleteProduct(ActionEvent event) {
        System.out.println("enter");
        try {
           
            String sql = "DELETE FROM product where product_id= '"+productSearch.getText()+"'";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    ObservableList<ObservableList> data;

    private void buildData() {
        data = FXCollections.observableArrayList();
        try {
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * from product";   						//change table name
            //ResultSet
            ResultSet rs = con.createStatement().executeQuery(SQL);

            //* TABLE COLUMN ADDED DYNAMICALLY *
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                productTable.getColumns().addAll(col);			//change table name
                System.out.println("Column [" + i + "] ");
            }
            //* Data added to ObservableList *
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            productTable.setItems(data);			//change table name
            filterInput.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

    }

    @FXML
    private void refreshBtnAction(ActionEvent event) {
        reloadData();
        
    }



    @FXML
    private void resetMemberFormBtnAction(MouseEvent event) {
    }
    
    private void changeThemeColor() {
        try {
            String sql = "SELECT color_code FROM color where id=1";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                colorCode = rs.getString("color_code");
                sidePane.setStyle("-fx-background-color:" + colorCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void reloadData() {
        for (int i = 0; i < productTable.getColumns().size(); i++) {
            productTable.getColumns().get(i).setVisible(false);
        }

        filterInput.clear();
        buildData();
    }

    @FXML
    private void memberTabAction(MouseEvent event) {
    }
}
