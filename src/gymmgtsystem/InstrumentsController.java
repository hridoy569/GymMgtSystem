///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package gymmgtsystem;
//
//import com.jfoenix.controls.JFXButton;
//import com.jfoenix.controls.JFXDatePicker;
//import com.jfoenix.controls.JFXTextArea;
//import com.jfoenix.controls.JFXTextField;
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Observable;
//import java.util.ResourceBundle;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.Tab;
//import javafx.scene.control.TabPane;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
//import javafx.util.Callback;
//import util.DB;
//
///**
// * FXML Controller class
// *
// * @author Mohammad Arefin
// */
//public class InstrumentsController implements Initializable {
//
//    @FXML
//    private StackPane memberStack;
//    @FXML
//    private AnchorPane formroot;
//    @FXML
//    private JFXTextField buyingPrice;
//    @FXML
//    private JFXDatePicker purchaseDate;
//    @FXML
//    private JFXButton addBtn;
//    Connection con = null;
//    PreparedStatement ps;
//    ResultSet rs;
//    private String colorCode;
//    @FXML
//    private VBox sidePane;
//    @FXML
//    private TabPane membersTabPane;
//    @FXML
//    private Label mshipLabel;
//    @FXML
//    private Label mshipLabel1;
//    @FXML
//    private TextField filterInput;
//    @FXML
//    private JFXTextField equipmentSearch;
//    @FXML
//    private Button refreshBtn;
//    @FXML
//    private Label foundItemLabel;
//    @FXML
//    private Tab addEquipmentTab;
//    @FXML
//    private JFXTextField equipmentName;
//    @FXML
//    private JFXTextField equipmentType;
//    @FXML
//    private JFXTextField equipmentSize;
//    @FXML
//    private JFXTextField equipmentQuantity;
//    @FXML
//    private JFXTextArea equipmentDescription;
//    @FXML
//    private JFXTextField equipmentWeight;
//    @FXML
//    private Tab equipmentTab;
//    private TableView<ObservableList> equipmentTable;
//    @FXML
//    private Button deleteEquipmentBtn;
//
//    @FXML
//    private JFXButton getEquipmentBtn;
//    ObservableList<ObservableList> data;
//    @FXML
//    private TableView<?> instrumentTable;
//
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        con = DB.getConnection();
//        buildData();
//        changeThemeColor();
//
//        filterInput.textProperty().addListener(new ChangeListener() {
//            @Override
//            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
//                filterInstrumentList((String) oldValue, (String) newValue);
//            }
//
//        });
//    }
//
//    public void filterInstrumentList(String oldValue, String newValue) {
//        ObservableList<ObservableList> filteredListIncrease = FXCollections.observableArrayList();
//        ObservableList<ObservableList> filteredListDecrese = FXCollections.observableArrayList();
//        if (filterInput.equals("") || newValue.equals("")) {
//            equipmentTable.setItems(data);
//            //counts records
//            int totalRecord = data.size();
//            if (totalRecord <= 1) {
//                foundItemLabel.setText("Found " + totalRecord + " equipment");
//            } else {
//                foundItemLabel.setText("Found " + totalRecord + " equipments");
//            }
//
//        } else if (newValue.length() < oldValue.length()) {
//            newValue = newValue.toUpperCase();
//            for (ObservableList equipment : data) {
//                String filterId = equipment.get(0).toString();
//                String filterName = equipment.get(1).toString();
//                String filterCode = equipment.get(8).toString();
//                String filterType = equipment.get(2).toString();
//
//                if (filterId.toUpperCase().contains(newValue) || filterName.toUpperCase().contains(newValue) || filterCode.toUpperCase().contains(newValue) || filterType.toUpperCase().contains(newValue)) {
//                    filteredListDecrese.add(equipment);
//                }
//            }
//            equipmentTable.setItems(filteredListDecrese);
//            //counts records
//            int totalRecord = filteredListDecrese.size();
//            if (totalRecord <= 1) {
//                foundItemLabel.setText("Found " + totalRecord + " equipment");
//            } else {
//                foundItemLabel.setText("Found " + totalRecord + " equipments");
//            }
//
//        } else {
//            newValue = newValue.toUpperCase();
//            for (ObservableList equipment : data) {
//                String filterId = equipment.get(0).toString();
//                String filterName = equipment.get(1).toString();
//                String filterCode = equipment.get(8).toString();
//                String filterType = equipment.get(2).toString();
//                if (filterId.toUpperCase().contains(newValue) || filterName.toUpperCase().contains(newValue) || filterCode.toUpperCase().contains(newValue) || filterType.toUpperCase().contains(newValue)) {
//                    filteredListIncrease.add(equipment);
//
//                }
//            }
//            equipmentTable.setItems(filteredListIncrease);
//            //counts records
//            int totalRecord = filteredListIncrease.size();
//            if (totalRecord <= 1) {
//                foundItemLabel.setText("Found " + totalRecord + " equipment");
//            } else {
//                foundItemLabel.setText("Found " + totalRecord + " equipments");
//            }
//        }
//    }
//    LocalDate date;
//    String add = "add";
//
//    private void changeThemeColor() {
//        try {
//            String sql = "SELECT color_code FROM color where id=1";
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                colorCode = rs.getString("color_code");
//                sidePane.setStyle("-fx-background-color:" + colorCode);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    private void memberTabAction(MouseEvent event) {
//    }
//
//    @FXML
//    private void resetMemberFormBtnAction(MouseEvent event) {
//    }
//
//    @FXML
//    private void refreshBtnAction(ActionEvent event) {
//        reloadData();
//    }
//
//    @FXML
//    private void addEquipment(ActionEvent event) {
//        if (add.equals("add")) {
//
//            String sqlDate = purchaseDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//            try {
//
//                con = DB.getConnection();
//                String sql = "INSERT INTO equipment(equipment_name, equipment_type, description, equipment_size, buying_price, selling_price, quantity, equipment_code, purchase_date) VALUES(?,?,?,?,?,?,?,?,?) ";
//                PreparedStatement ps = con.prepareStatement(sql);
//                ps.setString(1, equipmentName.getText());
//                ps.setString(2, equipmentType.getText());
//                ps.setString(3, equipmentDescription.getText());
//                ps.setString(4, equipmentSize.getText());
//                ps.setString(5, buyingPrice.getText());
//                ps.setString(6, equipmentWeight.getText());
//                ps.setString(7, equipmentQuantity.getText());
//
//                ps.setString(9, sqlDate);
//                int rowsAffected = ps.executeUpdate();
//                if (rowsAffected > 0) {
//                    //clearForm();
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//
//            try {
//                String sqlDate = purchaseDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//                con = DB.getConnection();
//                String sql = "UPDATE equipment SET equipment_name = ?, equipment_type =?, description =?, equipment_size =?, buying_price =?, selling_price =?, quantity=?, equipment_code =?, purchase_date = ? where equipment_id=? ";
//                PreparedStatement ps = con.prepareStatement(sql);
//
//                ps.setString(1, equipmentName.getText());
//                ps.setString(2, equipmentType.getText());
//                ps.setString(3, equipmentDescription.getText());
//                ps.setString(4, equipmentSize.getText());
//                ps.setString(5, buyingPrice.getText());
//
//                ps.setString(7, equipmentQuantity.getText());
//
//                ps.setString(9, sqlDate);
//                ps.setString(10, equipmentSearch.getText());
//                int rowsAffected = ps.executeUpdate();
//                if (rowsAffected > 0) {
//                    //   clearForm();
//                    addBtn.setText("Add Instrument");
//                    equipmentSearch.setText("");
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//
//    }
//
////        private void clearForm() {
////        equipmentName.setText("");
////        equipmentType.setText("");
////        equipmentCode.setText("");
////        equipmentDescription.setText("");
////        equipmentSize.setText("");
////        equipmentQuantity.setText("");
////        buyingPrice.setText("");
////        sellingPrice.setText("");
////        purchaseDate.setValue(null);
////
////    }
//    private void buildData() {
//        data = FXCollections.observableArrayList();
//        try {
//            //SQL FOR SELECTING ALL OF CUSTOMER
//            String SQL = "SELECT * from equipment";   						//change table name
//
//
//            ResultSet rs = con.createStatement().executeQuery(SQL);
//
//            //* TABLE COLUMN ADDED DYNAMICALLY *
//            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
//                //We are using non property style for making dynamic table
//                final int j = i;
//                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
//                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
//                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
//                        return new SimpleStringProperty(param.getValue().get(j).toString());
//                    }
//                });
//
//                equipmentTable.getColumns().addAll(col);			//change table name
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
//            equipmentTable.setItems(data);			//change table name
//            filterInput.setText("");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error on Building Data");
//        }
//
//    }
//
//    public void reloadData() {
//        for (int i = 0; i < equipmentTable.getColumns().size(); i++) {
//            equipmentTable.getColumns().get(i).setVisible(false);
//        }
//
//        filterInput.clear();
//        buildData();
//    }
//
//    @FXML
//    private void deleteEquipment(ActionEvent event) {
//        System.out.println("enter");
//        try {
//
//            con = DB.getConnection();
//            String sql = "DELETE FROM equipment where equipment_id= '" + equipmentSearch.getText() + "'";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//    String getInstrumentId;
//
//    @FXML
//    private void getEquipment(ActionEvent event) {
//        try {
//            con = DB.getConnection();
//            String sql = "Select * from equipment where equipment_id=? OR equipment_name=?";
//            PreparedStatement ps = con.prepareStatement(sql);
//            getInstrumentId = equipmentSearch.getText();
//            ps.setString(1, getInstrumentId);
//            ps.setString(2, getInstrumentId);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                equipmentName.setText(rs.getString(2));
//                equipmentType.setText(rs.getString(3));
//                equipmentDescription.setText(rs.getString(4));
//                equipmentSize.setText(rs.getString(4));
//
//                buyingPrice.setText(rs.getString(5));
//
//                equipmentQuantity.setText(rs.getString(7));
//
//                java.sql.Date d = rs.getDate("purchase_date");
//                purchaseDate.setValue(d.toLocalDate());
//
//                addBtn.setText("Update Instrument");
//                add = "update";
//
//            } else {
//
//            }
//        } catch (Exception e) {
//        }
//    }
//}
