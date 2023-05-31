package com.example.bms;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.*;

import java.awt.*;
import java.awt.Rectangle;
import java.sql.*;

public class BMS extends Application {
    public static Statement statement ;
    public static Stage stage  ;
    public static  Connection connection;
    public static Scene scene = new Scene(new Group() , 1400, 800);
    @Override
    public void start(Stage stage) {
        BMS.stage = stage;
        show(loginpage(),stage); //This method is to call it when  we need to navigate from one page to other page
        //rather than call start method this means initiate the app again if we call start

    }
    //This method is to call it when  we need to navigate from one page to other page
    //rather than call start method this means initiate the app again if we call start
    public static void show(Parent parent,Stage stage){
        GridPane main = new GridPane();
        Text text = new Text("Bank Management System"); //main
        text.setFont(Font.font("verdana", FontWeight.BOLD,34));
        text.setFill(Color.WHITE);
        text.setTranslateX(50);
        ImageView logo = new ImageView(new Image(BMS.class.getResourceAsStream("Image/img.png")));
        logo.setScaleX(0.5);
        logo.setScaleY(0.7);
        main.setStyle("-fx-background-color: #212F3F");
        main.addColumn(1,text);
        main.addColumn(0,logo);
        main.setPrefSize(scene.getWidth(),150);
        VBox vBox = new VBox();
        vBox.setPrefSize(scene.getWidth(),scene.getHeight());
        vBox.getChildren().addAll(main,parent);
        scene = new Scene(vBox, scene.getWidth(), scene.getHeight());
        scene.setFill(Color.BLUEVIOLET);
        stage.setScene(scene);
        scene.getStylesheets().add(String.valueOf(BMS.class.getResource("styles.css")));
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
    public Parent loginpage(){

        GridPane loginContainer = new GridPane(); //this gridpane is Login information container
        Text welcometext = new Text("Welcome"); //This text to show welcome message
        Label username =  new Label("Username"); // this label is to username
        Label password = new Label("Password"); //This label is To Password
        Button login = new Button("Login"); //This Button is to login
        Hyperlink forgotPassword = new Hyperlink("Forgot password?"); //this hyper link is for forgot password
        TextField usernametextfield = new TextField(); //This text field is to username
        TextField passwordtextfield = new TextField(); //This text field is to password
        username.setTextFill(Color.color(1,1,1));
        password.setTextFill(Color.color(1,1,1));
        login.setStyle("-fx-background-color: #0f0");
        welcometext.setFill(Color.color(1,1,1));
        welcometext.setFont(Font.font("verdana",FontWeight.BOLD,33));
        forgotPassword.setFont(Font.font("verdana",FontWeight.BOLD,16));
        forgotPassword.setTextFill(Color.BLUE);
        loginContainer.addRow(2,username);
        loginContainer.addRow(2,usernametextfield);
        loginContainer.addRow(3,password);
        loginContainer.addRow(3,passwordtextfield);
        loginContainer.addColumn(1,login);
        loginContainer.addColumn(1,forgotPassword);
        loginContainer.setHgap(23);
        loginContainer.setVgap(7);
        StackPane hBox = new StackPane();
        hBox.setMaxSize(scene.getWidth()*0.9,300);
        hBox.setMinHeight(100);
        hBox.setTranslateX(scene.getWidth()*0.05);
        loginContainer.setMaxWidth(400);
        loginContainer.setMaxHeight(400);
        hBox.setStyle("-fx-background-color: #1b324c");
        hBox.getChildren().add(welcometext);
        StackPane hBox1 = new StackPane();
        hBox1.setMaxWidth(scene.getWidth()*0.9);
        hBox1.setMinHeight(600);
        hBox1.setTranslateX(scene.getWidth()*0.05);
        System.out.println(hBox1.getPrefWidth());
        hBox1.setStyle("-fx-background-color: #1b324c");
        hBox1.getChildren().add(loginContainer);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(hBox,hBox1);
        vBox.setPrefSize(200,800);
        vBox.getStyleClass().add("vBox");
        login.setOnAction(e->{
            try {
                initializeDatabase();
                String str = new String();
                str = usernametextfield.getText();
                if (str.contains("teller")) {
                    show(tellerPage(stage),stage);
                } else if (str.contains("admin")) {
                    show(adminPage(stage),stage);
                }
                else if(UserController.check(passwordtextfield.getText(),usernametextfield.getText()))
                    show(customerPage(stage),stage);
                else
                {
                    usernametextfield.setText("Wrong input");
                }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
//        loginContainer.setTranslateY(30);
//        loginContainer.setTranslateX(30);

        return vBox;
    }
    public  Parent adminPage(Stage stage){
        Text welcometext = new Text("Admin Page");
        ImageView tellerImage = new ImageView(new Image(getClass().getResourceAsStream("Image/teller-modified.png"))); // This is to teller image
        ImageView customerImage = new ImageView(new Image(getClass().getResourceAsStream("Image/customer-modified.png")));
        ImageView accountImage = new ImageView(new Image(getClass().getResourceAsStream("Image/account-modified.png")));
        tellerImage.setScaleX(0.5);
        tellerImage.setScaleY(0.5);
        customerImage.setScaleX(0.4);
        customerImage.setScaleY(0.4);
        accountImage.setScaleX(0.3);
        accountImage.setScaleY(0.3);
        customerImage.setTranslateY(-50);
        customerImage.setTranslateX(-100);
        tellerImage.setTranslateX(-130);
        tellerImage.setTranslateY(-50);
        Button teller = new Button("Teller Info"); //This button is to enter to teller info page
        Button customer = new Button("Customer Info"); //this button is to enter customer page
        customer.setLayoutX(customerImage.getLayoutX()-23);
        customer.setLayoutY(customerImage.getLayoutY()-23);
        teller.setTranslateX((customer.getLayoutX() - customerImage.getLayoutX()) * 3);
        customer.setTranslateX((customer.getLayoutX() - customerImage.getLayoutX()) * 1);
        teller.setTranslateY((customer.getLayoutY() - customerImage.getLayoutY()) * 5);
        customer.setTranslateY((customer.getLayoutY() - customerImage.getLayoutY()) * 5);
        Button account  = new Button("Create Teller Acc"); //This button is to create teller account
        Button logout = new Button("Logout");
        logout.setFont(Font.font("verdana",FontWeight.BOLD,20));
        logout.setTranslateX(-260);
        logout.setTranslateY(-150);
        logout.getStyleClass().add("logout");
//        logout.setStyle("-fx-background-color:blue");
        GridPane adminContainer = new GridPane(); // This gridpane to collect admin page main element
        adminContainer.addColumn(0,welcometext);
        adminContainer.addRow(2,customerImage);
        adminContainer.addRow(3,customer);
        adminContainer.addRow(2,tellerImage);
        adminContainer.addRow(3,teller);
        adminContainer.addRow(4,accountImage);
        adminContainer.addRow(5,account);
        adminContainer.addColumn(1,logout);
        accountImage.setTranslateY(-300);
        accountImage.setTranslateX(20);
        account.setTranslateY(-430);
        account.setTranslateX(130);
//        welcometext.setTranslateY(-30);
//        welcometext.setTranslateX(100);
        welcometext.setFill(Color.color(1,1,1));
        welcometext.setFont(Font.font("verdana",FontWeight.BOLD,33));
        teller.setFont(Font.font("verdana",FontWeight.BOLD,14));
        customer.setFont(Font.font("verdana",FontWeight.BOLD,14));
        account.setFont(Font.font("verdana",FontWeight.BOLD,14));
        adminContainer.setHgap(23);
        adminContainer.setVgap(7);
        logout.setOnAction(e->{
            Platform.exit();
        });
        StackPane hBox = new StackPane();
        hBox.setMaxSize(scene.getWidth()*0.9,300);
        hBox.setMinHeight(100);
        hBox.setTranslateX(scene.getWidth()*0.05);
        adminContainer.setMaxWidth(400);
        adminContainer.setMaxHeight(400);
        hBox.setStyle("-fx-background-color: #1b324c");
        hBox.getChildren().add(welcometext);
        StackPane hBox1 = new StackPane();
        hBox1.setMaxWidth(scene.getWidth()*0.9);
        hBox1.setMinHeight(600);
        hBox1.setTranslateX(scene.getWidth()*0.05);
        System.out.println(hBox1.getPrefWidth());
        hBox1.setStyle("-fx-background-color: #1b324c");
        adminContainer.setTranslateX(80);
        adminContainer.setTranslateY(80);
        hBox1.getChildren().add(adminContainer);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(hBox,hBox1);
        vBox.setPrefSize(200,800);
        vBox.getStyleClass().add("vBox");
        account.setOnAction(e->{
            new BMS().show(AdminController.createTellerAcc(),BMS.stage);
                }
        );
        teller.setOnAction(e->{
            new BMS().show( AdminController.seeTellerInfo(),BMS.stage);
        });
        customer.setOnAction(e->{
            new BMS().show( AdminController.seeUserInfo(),BMS.stage);
        });
//        adminContainer.setTranslateX(-130);
        return vBox;
    }

    public  Parent tellerPage(Stage stage){
        Text welcometext = new Text("Teller Page");
        ImageView manageUser = new ImageView(new Image(getClass().getResourceAsStream("Image/transfer-modified.png")));
        ImageView  history= new ImageView(new Image(getClass().getResourceAsStream("Image/transfer-modified.png")));
        ImageView accountCreate = new ImageView(new Image(getClass().getResourceAsStream("Image/createacc-modified.png")));
        history.setScaleX(0.5);
        history.setScaleY(0.5);
        manageUser.setScaleX(0.5);
        manageUser.setScaleY(0.5);
        accountCreate.setScaleX(0.3);
        accountCreate.setScaleY(0.3);
        manageUser.setTranslateY(-50);
        manageUser.setTranslateX(-70);
        history.setTranslateX(-130);
        history.setTranslateY(-55);
        Button  manage = new Button("manage user Acc."); //This button is to manage user account that means to search user by account number and manage their account
        Button seeHistory = new Button("See History"); //This button is to the history of ones teller
        manage.setLayoutX(manageUser.getLayoutX()-23);
        manage.setLayoutY(manageUser.getLayoutY()-23);
        seeHistory.setTranslateX((manage.getLayoutX() - manageUser.getLayoutX()) * 3);
        manage.setTranslateX((manage.getLayoutX() - manageUser.getLayoutX()) * 1);
        seeHistory.setTranslateY((manage.getLayoutY() - manageUser.getLayoutY()) * 4.7);
        manage.setTranslateY((manage.getLayoutY() - manageUser.getLayoutY()) * 4.5);
        Button account  = new Button("Create user Acc"); // This button is to create user account
        Button logout = new Button("Logout");
        logout.setFont(Font.font("verdana",FontWeight.BOLD,20));
        logout.setTranslateX(-240);
        logout.setTranslateY(-150);
        logout.setStyle("-fx-background-color:blue");
        GridPane tellerContainer = new GridPane();
        tellerContainer.addRow(2,manageUser);
        tellerContainer.addRow(3,manage);
        tellerContainer.addRow(2,history);
        tellerContainer.addRow(3,seeHistory);
        tellerContainer.addRow(4,accountCreate);
        tellerContainer.addRow(5,account);
        tellerContainer.addColumn(1,logout);
        accountCreate.setTranslateY(-300);
        accountCreate.setTranslateX(40);
        account.setTranslateY(-430);
        account.setTranslateX(160);
        TextField usernametextfield = new TextField();
        usernametextfield.setMinWidth(100);
        welcometext.setFill(Color.color(1,1,1));
        welcometext.setFont(Font.font("verdana",FontWeight.BOLD,33));
        seeHistory.setFont(Font.font("verdana",FontWeight.BOLD,14));
        manage.setFont(Font.font("verdana",FontWeight.BOLD,14));
        account.setFont(Font.font("verdana",FontWeight.BOLD,14));
        tellerContainer.setHgap(23);
        tellerContainer.setVgap(7);
        StackPane hBox = new StackPane();
        hBox.setMaxSize(scene.getWidth()*0.9,300);
        hBox.setMinHeight(100);
        hBox.setTranslateX(scene.getWidth()*0.05);
        tellerContainer.setMaxWidth(400);
        tellerContainer.setMaxHeight(400);
        hBox.setStyle("-fx-background-color: #1b324c");
        hBox.getChildren().add(welcometext);
        StackPane hBox1 = new StackPane();
        hBox1.setMaxWidth(scene.getWidth()*0.9);
        hBox1.setMinHeight(600);
        hBox1.setTranslateX(scene.getWidth()*0.05);
        System.out.println(hBox1.getPrefWidth());
        hBox1.setStyle("-fx-background-color: #1b324c");
        tellerContainer.setTranslateX(80);
        tellerContainer.setTranslateY(80);
        hBox1.getChildren().add(tellerContainer);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(hBox,hBox1);
        vBox.setPrefSize(200,800);
        vBox.getStyleClass().add("vBox");
        logout.setOnAction(e->{
            Platform.exit();
        });
        account.setOnAction(e->{
            try {
                show( TellerController.createAcc(),stage);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        return vBox;
    }
    public  Parent customerPage(Stage stage){
        new AdminView();
        Text welcometext = new Text("Customer Page");
        ImageView transferImage = new ImageView(new Image(getClass().getResourceAsStream("Image/transfer-modified.png")));
        ImageView  balanceImage = new ImageView(new Image(getClass().getResourceAsStream("Image/balance-modified.png")));
        ImageView transactionImage = new ImageView(new Image(getClass().getResourceAsStream("Image/createacc-modified.png")));
        balanceImage.setScaleX(0.43);
        balanceImage.setScaleY(0.43);
        transferImage.setScaleX(0.5);
        transferImage.setScaleY(0.5);
        transactionImage.setScaleX(0.3);
        transactionImage.setScaleY(0.3);
        transferImage.setTranslateY(-50);
        transferImage.setTranslateX(-70);
        balanceImage.setTranslateX(-130);
        balanceImage.setTranslateY(-55);
        Button  transferbtn = new Button("Transfer.");
        Button balancebtn = new Button("See Balance");
        balancebtn.setTranslateX(-50);
        balancebtn.setTranslateY(-130);
        transferbtn.setTranslateY(-130);
        Button transactionbtn  = new Button("Transaction");
        Button logout = new Button("Logout");
        logout.setFont(Font.font("verdana",FontWeight.BOLD,20));
        logout.setTranslateX(-220);
        logout.setTranslateY(-150);
        logout.setStyle("-fx-background-color:blue");
        GridPane customerPage = new GridPane(); //This gridpane to contain the costumer page element
        customerPage.addColumn(0,welcometext);
        customerPage.addRow(2,transferImage);
        customerPage.addRow(3,transferbtn);
        customerPage.addRow(2,balanceImage);
        customerPage.addRow(3,balancebtn);
        customerPage.addRow(4,transactionImage);
        customerPage.addRow(5,transactionbtn);
        customerPage.addColumn(1,logout);
        transactionImage.setTranslateY(-300);
        transactionImage.setTranslateX(40);
        transactionbtn.setTranslateY(-430);
        transactionbtn.setTranslateX(175);
        welcometext.setFill(Color.color(1,1,1));
        welcometext.setFont(Font.font("verdana",FontWeight.BOLD,33));
        customerPage.setHgap(23);
        customerPage.setVgap(7);
        StackPane hBox = new StackPane();
        hBox.setMaxSize(scene.getWidth()*0.9,300);
        hBox.setMinHeight(100);
        hBox.setTranslateX(scene.getWidth()*0.05);
        customerPage.setMaxWidth(400);
        customerPage.setMaxHeight(400);
        hBox.setStyle("-fx-background-color: #1b324c");
        hBox.getChildren().add(welcometext);
        StackPane hBox1 = new StackPane();
        hBox1.setMaxWidth(scene.getWidth()*0.9);
        hBox1.setMinHeight(600);
        hBox1.setTranslateX(scene.getWidth()*0.05);
        System.out.println(hBox1.getPrefWidth());
        hBox1.setStyle("-fx-background-color: #1b324c");
        customerPage.setTranslateX(80);
        customerPage.setTranslateY(80);
        hBox1.getChildren().add(customerPage);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(hBox,hBox1);
        vBox.setPrefSize(200,800);
        vBox.getStyleClass().add("vBox");
        logout.setOnAction(e->{
            Platform.exit();
        });
        balancebtn.setOnAction(e->{
            try {
                show(UserController.seeBalance(),stage);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        transferbtn.setOnAction(e->{
            try {
                show(UserController.transferMoney(),stage);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        transactionbtn.setOnAction(e->{
            TransactionView.load();
            show(UserController.seeTransaction(),stage);
        });
        return vBox;
    }
//    public GridPane createTellerAcc(Stage stage){
//
//        GridPane gridPane = new GridPane();
//
//        // Back icon image
//        ImageView imgBack = new ImageView(new Image(getClass().getResourceAsStream("Image/backIcon.png")));
//
//        Label lblCreateTAcc = new Label("Create Teller Account");
//
//        Label lblTellerId = new Label("Teller ID:");
//
//        Label lblFName = new Label("First Name:");
//
//        Label lblLName = new Label("Last Name:");
//
//        Label lblGender = new Label("Gender:");
//
//        Label lblUname = new Label("User Name:");
//
//        Label lblPass = new Label("Password:");
//
//        Label lblPhone = new Label("Phone:");
//
//        Label lblPhoto = new Label("Photo:");
//
//        Label lblEmail = new Label("Email");
//
//        TextField txFldTellerId = new TextField();
//
//        TextField txFldFName = new TextField();
//
//        TextField txFldLName = new TextField();
//
//        TextField txFldGender = new TextField();
//
//        TextField txFldUName = new TextField();
//
//        TextField txFldPass = new TextField();
//
//        TextField txFldPhone = new TextField();
//
//        TextField txFldEmail = new TextField();
//
//        Button btnCreateAcc = new Button("Create Account");
//
//        Button btnInsPhoto = new Button("Insert Photo");
//
//        Rectangle recPhoto = new Rectangle();
//
//        // Styling nodes
//        gridPane.setStyle("-fx-background-color: #212F3F;");
//        gridPane.setPadding(new Insets(10,10,10,10));
//        gridPane.setVgap(20);
//        gridPane.setHgap(20);
//        lblCreateTAcc.setFont(Font.font("verdana", FontWeight.BOLD, 24));
//        lblCreateTAcc.setTextFill(Color.WHITE);
//        lblTellerId.setFont(Font.font("verdana", FontWeight.BOLD, 14));
//        lblTellerId.setTextFill(Color.WHITE);
//        lblFName.setFont(Font.font("verdana", FontWeight.BOLD, 14));
//        lblFName.setTextFill(Color.WHITE);
//        lblLName.setFont(Font.font("verdana", FontWeight.BOLD, 14));
//        lblLName.setTextFill(Color.WHITE);
//        lblGender.setFont(Font.font("verdana", FontWeight.BOLD, 14));
//        lblGender.setTextFill(Color.WHITE);
//        lblUname.setFont(Font.font("verdana", FontWeight.BOLD, 14));
//        lblUname.setTextFill(Color.WHITE);
//        lblPass.setFont(Font.font("verdana", FontWeight.BOLD, 14));
//        lblPass.setTextFill(Color.WHITE);
//        lblPhone.setFont(Font.font("verdana", FontWeight.BOLD, 14));
//        lblPhone.setTextFill(Color.WHITE);
//        lblPhoto.setFont(Font.font("verdana", FontWeight.BOLD, 14));
//        lblPhoto.setTextFill(Color.WHITE);
//        lblEmail.setFont(Font.font("verdana", FontWeight.BOLD, 14));
//        lblEmail.setTextFill(Color.WHITE);
//        btnCreateAcc.setFont(Font.font("verdana", FontWeight.BOLD, 14));
//        btnCreateAcc.setStyle("fx-background-color: #fff; -fx-text-fill: #212F3F;");
//        btnInsPhoto.setFont(Font.font("verdana", FontWeight.BOLD, 14));
//        btnInsPhoto.setStyle("fx-background-color: #fff; -fx-text-fill: #212F3F;");
//        recPhoto.setWidth(85);
//        recPhoto.setHeight(85);
//        recPhoto.setStroke(Color.WHITE);
//        recPhoto.setFill(Color.rgb(33, 47, 63));
//
//        // Adding nodes to gridPane
//
//        gridPane.add(imgBack, 0,0);
//        gridPane.add(lblCreateTAcc, 1,0, 3,1);
//        gridPane.add(lblTellerId, 0,1);
//        gridPane.add(txFldTellerId, 1,1);
//        gridPane.add(lblPhoto, 2, 1);
//        gridPane.add(recPhoto,3,1,1,2);
//        gridPane.add(lblFName, 0, 2);
//        gridPane.add(txFldFName, 1, 2);
//        gridPane.add(lblLName,0,3);
//        gridPane.add(txFldLName,1,3);
//        gridPane.add(btnInsPhoto,3,3);
//        gridPane.add(lblGender,0,4);
//        gridPane.add(txFldGender,1,4);
//        gridPane.add(lblUname,0,5);
//        gridPane.add(txFldUName,1,5);
//        gridPane.add(lblEmail,2,5);
//        gridPane.add(txFldEmail,3,5);
//        gridPane.add(lblPass,0,6);
//        gridPane.add(txFldPass,1,6);
//        gridPane.add(lblPhone,0,7);
//        gridPane.add(txFldPhone,1,7);
//        gridPane.add(btnCreateAcc,3,7);
//        gridPane.translateYProperty().bind(scene.heightProperty().divide(-4));
//        // Handling btnInsPhoto
//        btnInsPhoto.setOnAction(e->{
//            FileChooser fileChooser = new FileChooser();
//            File photo = fileChooser.showOpenDialog(stage);
//            if(photo != null){
//                ImageView imgVwPhoto = new ImageView(new Image(photo.toURI().toString()));
//                imgVwPhoto.fitHeightProperty().bind(recPhoto.heightProperty());
//                imgVwPhoto.fitWidthProperty().bind(recPhoto.widthProperty());
//                gridPane.add(imgVwPhoto,3,1,1,2);
//            }
//
//        });
//
//        // Handling imgBack
//        imgBack.setOnMouseClicked(e->{
//            show(adminPage(stage),stage);
//
//        });
//        return gridPane;
//    }
    public  static  void initializeDatabase() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
         connection = DriverManager.getConnection("jdbc:mysql://localhost/bms","eziraa","1234");
         statement = connection.createStatement();
    }
}