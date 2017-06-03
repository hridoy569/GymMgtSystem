/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmgtsystem;

import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXToggleButton;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import util.DB;

/**
 *
 * @author Hridoy
 */
public class MainFrameController implements Initializable {

    @FXML
    public HBox hbox;
    @FXML
    private BorderPane root;
    private ImageView closeIcon;
    @FXML
    private Button fullscreen;
    @FXML
    private Button minimize;
    @FXML
    private Button maximize;
    @FXML
    private Button close;

    Stage stage;
    Rectangle2D rec2;
    Double w, h;

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private Label label;
    @FXML
    private HBox hbox2;
    @FXML
    public AnchorPane mainAnchor;
    @FXML
    private StackPane stackPane;
    int countToggleView = 0;
    int countMemberBtn = 0;
    int countShiftBtn = 0;
    int countPackagesBtn = 0;
    int countTrainerBtn = 0;
    int countProductBtn = 0;
    int countInstrumentsBtn = 0;
    int countAccountsBtn = 0;
    int countReportsBtn = 0;
    int countSettingsBtn = 0;
    int countUserBtn = 0;
    @FXML
    private StackPane stack4DialogLayout;
    HBox hbox1;
    AnchorPane form;
    @FXML
    AnchorPane attandenceTable;
    AnchorPane shiftForm;
    AnchorPane packagesForm;
    AnchorPane trainerForm;
    AnchorPane productForm;
    AnchorPane instrumentsForm;
    AnchorPane accountsForm;
    AnchorPane reportsForm;
    AnchorPane settingsForm;
    AnchorPane newUserForm;
    @FXML
    private AnchorPane homePane;
    @FXML
    private Button homeBtn;
    @FXML
    private Button memberBtn;
    @FXML
    private TextField attandSearch;
    @FXML
    private JFXToggleButton toggleAttStatus;
    private Label viewLabel;
    private Label loggedLabel;
    String viewChange = "tile";
    String loogedStatusChange = "in";
    @FXML
    private Label time;
    @FXML
    private Label date;
    private int minute;
    private int hour;
    private int second;
    private int pam;

    private int wday;
    private int month;
    private int dday;
    private int year;
    private Button shiftBtn;
    private Button packagesBtn;
    @FXML
    private Button tainerBtn;
    @FXML
    private Button productBtn;
    @FXML
    private Button instrumentBtn;
    @FXML
    private Button accountsBtn;
    @FXML
    private Button reportsBtn;
    @FXML
    private AnchorPane attandenceTable1;
    @FXML
    private Label loggedInUser;
    @FXML
    private MenuButton mbtnUsrInfoBox;
    @FXML
    private MenuItem miPopOver;
    @FXML
    private Label lblUsrNamePopOver;
    @FXML
    private Label lblRoleAs;
    @FXML
    private Button btnLogOut;
    @FXML
    private Hyperlink createNewUser;
    @FXML
    private Label lblEmail;
    @FXML
    private Button settingsBtn;
    @FXML
    private ImageView logoFrame;
    @FXML
    private Label bnameLabel;
    @FXML
    private Label baddressLabel;
    @FXML
    private Label bphoneLabel;
    @FXML
    private Label bemailLabel;
    private JFXColorPicker colorPicker;
    @FXML
    private VBox vbox2;
    Connection con = null;
    PreparedStatement ps;
    ResultSet rs;
    private String colorCode;

    public void setUsrNameMedia(String name) {
        loggedInUser.setText(name);
    }

    public void setFullnameMedia(String name) {
        lblUsrNamePopOver.setText(name);
    }

    public void setEmailMedia(String name) {
        lblEmail.setText(name);
    }

    public void setRoleMedia(String name) {
        lblRoleAs.setText(name);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                homeBtn.requestFocus();
            }
        });
        homeBtn.setStyle("-fx-background-color: null; -fx-background-image:url(img/movies.png); -fx-background-repeat:no-repeat;");
        doubleClick();
        rec2 = Screen.getPrimary().getVisualBounds();
        w = 0.1;
        h = 0.1;
        Platform.runLater(() -> {
            stage = (Stage) maximize.getScene().getWindow();
            stage.setMaximized(true);
            stage.setHeight(rec2.getHeight());
            maximize.getStyleClass().add("decoration-button-restore");

        });
        makeDragable(true);
        escEvent();
        clock();
        loadBusinessProfile();
        con = DB.getConnection();
        changeThemeColor();
    }

    @FXML
    private void aksifullscreen(ActionEvent event) {
        stage = (Stage) fullscreen.getScene().getWindow();
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
            maximize.setDisable(false);
            makeDragable(true);
        } else {
            stage.setFullScreen(true);
            maximize.setDisable(true);
            makeDragable(false);

        }
    }

    @FXML
    private void aksiminimize(ActionEvent event) {
        stage = (Stage) minimize.getScene().getWindow();
        if (stage.isMaximized()) {
            w = rec2.getWidth();
            h = rec2.getHeight();
            stage.setMaximized(false);
            stage.setHeight(h);
            stage.setWidth(w);
            stage.centerOnScreen();
            Platform.runLater(() -> {
                stage.setIconified(true);
            });
        } else {
            stage.setIconified(true);
        }
    }

    @FXML
    private void aksiMaximized(ActionEvent event) {
        stage = (Stage) maximize.getScene().getWindow();
        if (stage.isMaximized()) {
            if (w == rec2.getWidth() && h == rec2.getHeight()) {
                stage.setMaximized(false);
                stage.setHeight(600);
                stage.setWidth(900);
                stage.centerOnScreen();
                maximize.getStyleClass().remove("decoration-button-restore");
//                resize.setVisible(true);
            } else {
                stage.setMaximized(false);
                maximize.getStyleClass().remove("decoration-button-restore");
//                resize.setVisible(true);
            }

        } else {
            stage.setMaximized(true);
            stage.setHeight(rec2.getHeight());
            maximize.getStyleClass().add("decoration-button-restore");
//            resize.setVisible(false);
        }
    }

    @FXML
    private void aksiClose(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    private void doubleClick() {
        hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
                    stage = (Stage) maximize.getScene().getWindow();
                    if (stage.isMaximized()) {
                        if (w == rec2.getWidth() && h == rec2.getHeight()) {
                            stage.setMaximized(false);
                            stage.setHeight(600);
                            stage.setWidth(900);
                            stage.centerOnScreen();
                            maximize.getStyleClass().remove("decoration-button-restore");
                        } else {
                            stage.setMaximized(false);
                            maximize.getStyleClass().remove("decoration-button-restore");
                        }

                    } else {
                        stage.setMaximized(true);
                        stage.setHeight(rec2.getHeight());
                        maximize.getStyleClass().add("decoration-button-restore");
                    }

                }
            }
        });
    }

    private boolean makeDragable(boolean b) {
        if (b) {
            label.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });
            label.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                }
            });
        } else {
            label.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                }
            });
            label.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                }
            });
        }
        return b;
    }

    private void escEvent() {
        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ESCAPE) {
//                    System.out.println("Key Pressed: " + ke.getCode());
                    makeDragable(true);
                    maximize.setDisable(false);
                }
            }
        });
    }

    @FXML
    private void membersBtnAction(MouseEvent e) {
        if (e.getClickCount() >= 1) {
            countMemberBtn += 1;
        } else {
        }

        if (countMemberBtn <= 1) {

            try {
                form = FXMLLoader.load(getClass().getResource("MembersForm.fxml"));
                stack4DialogLayout.getChildren().add(form);
                stage.setMaximized(true);
            } catch (IOException ex) {
                Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("form added");
        } else {
            form.toFront();
            stage.setMaximized(true);
            System.out.println("form to Front");
        }
        homeBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        memberBtn.setStyle("-fx-background-color: null; -fx-background-image:url(img/movies.png); -fx-background-repeat:no-repeat;");
//        shiftBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        tainerBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
//        packagesBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        productBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        instrumentBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        accountsBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        reportsBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
    }

    @FXML
    private void homeBtnAction(ActionEvent event) {
        loadBusinessProfile();
        homePane.toFront();
        homeBtn.setStyle("-fx-background-color: null; -fx-background-image:url(img/movies.png); -fx-background-repeat:no-repeat;");
        memberBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
//        shiftBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        tainerBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
//        packagesBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        productBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        instrumentBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        accountsBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        reportsBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
    }

    @FXML
    private void toggleAttStatusAction(MouseEvent event) {

        if (loogedStatusChange.equals("in")) {
            toggleAttStatus.setText("Logged Out");
            loogedStatusChange = "out";
            System.out.println("from main out");
        } else {
            toggleAttStatus.setText("Logged In");
            loogedStatusChange = "in";
            System.out.println("from main in");
        }
    }

    void clock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            Calendar cal = Calendar.getInstance();
            second = cal.get(Calendar.SECOND);
            minute = cal.get(Calendar.MINUTE);
            hour = cal.get(Calendar.HOUR);
            if (hour == 0) {
                hour = 12;
            };

            pam = cal.get(Calendar.AM_PM);
            String pmam;
            if (pam == 0) {
                pmam = "AM";
            } else {
                pmam = "PM";
            }

            wday = cal.get(Calendar.DAY_OF_WEEK);
            String weekDay = "";
            month = cal.get(Calendar.MONTH);
            String monthString = "";
            dday = cal.get(Calendar.DAY_OF_MONTH);
            year = cal.get(Calendar.YEAR);

            if (wday == 7) {
                weekDay = "Saturday";
            } else if (wday == 1) {
                weekDay = "Sunday";
            } else if (wday == 2) {
                weekDay = "Monday";
            } else if (wday == 3) {
                weekDay = "Tuesday";
            } else if (wday == 4) {
                weekDay = "Wednesday";
            } else if (wday == 5) {
                weekDay = "Thursday";
            } else if (wday == 6) {
                weekDay = "Friday";
            };
            if (month == 0) {
                monthString = "Jan";
            } else if (month == 1) {
                monthString = "Feb";
            } else if (month == 2) {
                monthString = "Mar";
            } else if (month == 3) {
                monthString = "App";
            } else if (month == 4) {
                monthString = "May";
            } else if (month == 5) {
                monthString = "Jun";
            } else if (month == 6) {
                monthString = "Jul";
            } else if (month == 7) {
                monthString = "Aug";
            } else if (month == 8) {
                monthString = "Sep";
            } else if (month == 9) {
                monthString = "Oct";
            } else if (month == 10) {
                monthString = "Nov";
            } else if (month == 11) {
                monthString = "Dec";
            }

            if (Integer.toString(hour).length() == 1 && Integer.toString(minute).length() == 2 && Integer.toString(second).length() == 2) {
                time.setText("0" + hour + ":" + minute + ":" + second + " " + pmam);
            } else if (Integer.toString(hour).length() == 2 && Integer.toString(minute).length() == 1 && Integer.toString(second).length() == 2) {
                time.setText(hour + ":" + "0" + minute + ":" + second + " " + pmam);

            } else if (Integer.toString(hour).length() == 2 && Integer.toString(minute).length() == 2 && Integer.toString(second).length() == 1) {
                time.setText(hour + ":" + minute + ":" + "0" + second + " " + pmam);

            } else if (Integer.toString(hour).length() == 1 && Integer.toString(minute).length() == 1 && Integer.toString(second).length() == 2) {
                time.setText("0" + hour + ":" + "0" + minute + ":" + second + " " + pmam);

            } else if (Integer.toString(hour).length() == 1 && Integer.toString(minute).length() == 2 && Integer.toString(second).length() == 1) {
                time.setText("0" + hour + ":" + minute + ":" + "0" + second + " " + pmam);

            } else if (Integer.toString(hour).length() == 2 && Integer.toString(minute).length() == 1 && Integer.toString(second).length() == 1) {
                time.setText(hour + ":" + "0" + minute + ":" + "0" + second + " " + pmam);

            } else if (Integer.toString(hour).length() == 1 && Integer.toString(minute).length() == 1 && Integer.toString(second).length() == 1) {
                time.setText("0" + hour + ":" + "0" + minute + ":" + "0" + second + " " + pmam);

            } else {
                time.setText(hour + ":" + minute + ":" + second + " " + pmam);
            }

            date.setText(weekDay + ", " + monthString + " " + dday + ", " + year);

        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

    }
    
    @FXML
    private void trainerBtnAction(MouseEvent e) {
        if (e.getClickCount() >= 1) {
            countTrainerBtn += 1;
        } else {
        }

        if (countTrainerBtn <= 1) {
        try {
            trainerForm = FXMLLoader.load(getClass().getResource("TrainerForm.fxml"));
            stack4DialogLayout.getChildren().add(trainerForm);
            stage.setMaximized(true);
        } catch (IOException ex) {
            Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("trainerForm added");
        } else {
            trainerForm.toFront();
            stage.setMaximized(true);
            System.out.println("trainerForm to Front");
        }
        homeBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        memberBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
//        shiftBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
//        packagesBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        tainerBtn.setStyle("-fx-background-color: null; -fx-background-image:url(img/movies.png); -fx-background-repeat:no-repeat;");
        productBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        instrumentBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        accountsBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        reportsBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
    }

    @FXML
    private void productBtnAction(MouseEvent e) {
        if (e.getClickCount() >= 1) {
            countProductBtn += 1;
        } else {
        }

        if (countProductBtn <= 1) {

            try {
                productForm = FXMLLoader.load(getClass().getResource("ProductsForm.fxml"));
                stack4DialogLayout.getChildren().add(productForm);
                stage.setMaximized(true);
            } catch (IOException ex) {
                Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("productForm added");
        } else {
            productForm.toFront();
            stage.setMaximized(true);
            System.out.println("productForm to Front");
        }
        homeBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        memberBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
//        shiftBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
//        packagesBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        tainerBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        productBtn.setStyle("-fx-background-color: null; -fx-background-image:url(img/movies.png); -fx-background-repeat:no-repeat;");
        instrumentBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        accountsBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        reportsBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
    }

    @FXML
    private void instrumentBtnAction(MouseEvent e) {
        if (e.getClickCount() >= 1) {
            countInstrumentsBtn += 1;
        } else {
        }

        if (countInstrumentsBtn <= 1) {

            try {
                instrumentsForm = FXMLLoader.load(getClass().getResource("Instruments.fxml"));
                stack4DialogLayout.getChildren().add(instrumentsForm);
                stage.setMaximized(true);
            } catch (IOException ex) {
                Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("instrumentsForm added");
        } else {
            instrumentsForm.toFront();
            stage.setMaximized(true);
            System.out.println("instrumentsForm to Front");
        }
        homeBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        memberBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
//        shiftBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
//        packagesBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        tainerBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        productBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        instrumentBtn.setStyle("-fx-background-color: null; -fx-background-image:url(img/movies.png); -fx-background-repeat:no-repeat;");
        accountsBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        reportsBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
    }

    @FXML
    private void accountBtnAction(MouseEvent e) {
        if (e.getClickCount() >= 1) {
            countAccountsBtn += 1;
        } else {
        }

        if (countAccountsBtn <= 1) {

            try {
                accountsForm = FXMLLoader.load(getClass().getResource("Accounts.fxml"));
                stack4DialogLayout.getChildren().add(accountsForm);
                stage.setMaximized(true);
            } catch (IOException ex) {
                Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("accountsForm added");
        } else {
            accountsForm.toFront();
            stage.setMaximized(true);
            System.out.println("accountsForm to Front");
        }
        homeBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        memberBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
//        shiftBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
//        packagesBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        tainerBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        productBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        instrumentBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        accountsBtn.setStyle("-fx-background-color: null; -fx-background-image:url(img/movies.png); -fx-background-repeat:no-repeat;");
        reportsBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
    }

    @FXML
    private void reportsBtnAction(MouseEvent e) {
        if (e.getClickCount() >= 1) {
            countReportsBtn += 1;
        } else {
        }

        if (countReportsBtn <= 1) {

            try {
                reportsForm = FXMLLoader.load(getClass().getResource("Reports.fxml"));
                stack4DialogLayout.getChildren().add(reportsForm);
                stage.setMaximized(true);
            } catch (IOException ex) {
                Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("reportsForm added");
        } else {
            reportsForm.toFront();
            stage.setMaximized(true);
            System.out.println("reportsForm to Front");
        }
        homeBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        memberBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
//        shiftBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
//        packagesBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        tainerBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        productBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        instrumentBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        accountsBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        reportsBtn.setStyle("-fx-background-color: null; -fx-background-image:url(img/movies.png); -fx-background-repeat:no-repeat;");
    }

    @FXML
    private void btnLogOut(ActionEvent event) throws IOException {
        Stage current = (Stage) close.getScene().getWindow();
        nextStage(GymMgtSystem.Login, "", true);
        current.hide();
    }

    @FXML
    private void mbtnOnClick(ActionEvent event) {
    }

    @FXML
    private void userDetailsAction(ActionEvent e) throws IOException {
        nextStage(GymMgtSystem.NewUser, "", true);
    }

    private void nextStage(String fxml, String title, boolean resizable) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(resizable);
        stage.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);

    }

    @FXML
    private void settingsBtnAction(MouseEvent e) {
        if (e.getClickCount() >= 1) {
            countSettingsBtn += 1;
        } else {
        }

        if (countSettingsBtn <= 1) {

            try {
                settingsForm = FXMLLoader.load(getClass().getResource("SettingsForm.fxml"));
                stack4DialogLayout.getChildren().add(settingsForm);
                stage.setMaximized(true);
            } catch (IOException ex) {
                Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("settingsForm added");
        } else {
            settingsForm.toFront();
            stage.setMaximized(true);
            System.out.println("settingsForm to Front");
        }
        homeBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        memberBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
//        shiftBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
//        packagesBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        tainerBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        productBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        instrumentBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        accountsBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
        reportsBtn.setStyle("-fx-background-color: null; -fx-background-repeat:no-repeat;");
//        settingsBtn.setStyle("-fx-background-color: null; -fx-background-image:url(img/movies.png); -fx-background-repeat:no-repeat;");
    }

    private void loadBusinessProfile() {
        try {
            Connection con = DB.getConnection();
            String sql = "SELECT * FROM business_profile";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bnameLabel.setText(rs.getString("businessName"));
                baddressLabel.setText(rs.getString("businessAddress"));
                bphoneLabel.setText("Phone: " + rs.getString("businessPhone"));
                bemailLabel.setText("Email: " + rs.getString("businessEmail"));
                Image convertToJavaFXImage = convertToJavaFXImage(rs.getBytes("businessLogo"), 250, 60);
                logoFrame.setImage(convertToJavaFXImage);
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
    private void changeThemeColor(){
        try {
            String sql = "SELECT color_code FROM color where id=1";
            ps = con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                colorCode = rs.getString("color_code");
                hbox2.setStyle("-fx-background-color:"+colorCode);
                vbox2.setStyle("-fx-background-color:"+colorCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
