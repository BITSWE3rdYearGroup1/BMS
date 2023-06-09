package com.example.bms;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Objects;

import static com.example.bms.TellerView.btnSeeHistory;
import static com.example.bms.TellerView.btnmanage;
public class BMS extends Application {
    public static Statement statement ;
    public static Stage stage  ;
    public static  Connection connection;
    public static Scene scene = new Scene(new Group() , 1000, 650);
    @Override
    public void start(Stage stage) {
        BMS.stage = stage;
        scene.getStylesheets().add("css/boostrap.css");

        show(loginPage(),stage); //This method is to call it when  we need to navigate from one page to other page
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
        main.getStyleClass().add("bg-primary");

        ImageView logo = new ImageView(new Image(Objects.requireNonNull(BMS.class.getResourceAsStream("Image/banking.png"))));
        logo.setTranslateX(80);
        text.setTranslateX(100);
        logo.setScaleX(0.7);
        logo.setScaleY(0.7);
        main.addColumn(1,text);
        main.addColumn(0,logo);
        VBox vBox = new VBox();
        vBox.setTranslateY(40);
        vBox.setStyle("-fx-background-color: #000000");
        vBox.setPrefSize(scene.getWidth(),scene.getHeight());
        vBox.getChildren().addAll(main,parent);
        scene = new Scene(vBox, scene.getWidth(), scene.getHeight());
        stage.setScene(scene);
        scene.getStylesheets().add(String.valueOf(BMS.class.getResource("styles.css")));;
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
    public Parent loginPage(){
        GridPane loginContainer = new GridPane(); // this gridpane is Login information container
        Text welcometext = new Text("Welcome"); // This text to show welcome message
        ImageView username = new ImageView(new Image(BMS.class.getResourceAsStream("Image/user.png")));
        ImageView password = new ImageView(new Image(BMS.class.getResourceAsStream("Image/lock.png")));
        Label errorTxt = new Label("Wrong Username or Password");
        Button login = new Button("Login"); // This Button is to login
        Hyperlink forgotPassword = new Hyperlink("Forgot password?"); // this hyper link is for forgot password
        username.setTranslateX(15);
        username.setTranslateY(-5);
        password.setTranslateX(15);
        password.setTranslateY(-5);
        TextField usernametextfield = new TextField("User Name"); // This text field is to username
        TextField passwordtextfield = new TextField("Password"); // This text field is to password
        username.setScaleX(0.5);
        username.setScaleY(0.5);
        password.setScaleX(0.5);
        password.setScaleY(0.5);
        welcometext.setFont(Font.font("verdana", FontWeight.BOLD, 33));
        forgotPassword.setFont(Font.font("verdana", FontWeight.BOLD, 16));

        loginContainer.addColumn(1, welcometext);
        loginContainer.addRow(2, username);
        loginContainer.addRow(2, usernametextfield);
        loginContainer.addRow(3, password);
        loginContainer.addRow(3, passwordtextfield);
        loginContainer.addColumn(1, login);
        loginContainer.addColumn(1, forgotPassword);
        loginContainer.setHgap(23);
        loginContainer.setVgap(7);
        loginContainer.setMaxWidth(400);
        loginContainer.setMaxHeight(400);
        StackPane hBox1 = new StackPane();
        loginContainer.setTranslateX(scene.getWidth()*0.1);
        loginContainer.setTranslateY(scene.getWidth()*0.1);
        loginContainer.setAlignment(Pos.CENTER);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(loginContainer,hBox1);
        vBox.setPrefSize(200,800);
        vBox.getStyleClass().add("vBox");
        login.setOnAction(e->{
            try {
                initializeDatabase();
                String str = usernametextfield.getText();
                if (str.contains("teller")) {
                    show(tellerPage(stage), stage);
                } else if (str.contains("admin")) {
                    show(adminPage(stage), stage);
                } else if (UserController.check(passwordtextfield.getText(), usernametextfield.getText())) {
                    show(customerPage(stage), stage);
                }
                else
                {
                    loginContainer.add(errorTxt, 1, 7);
                }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

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
            show(AdminController.createTellerAcc(),BMS.stage);
                }
        );
        teller.setOnAction(e->{
            show( AdminController.seeTellerInfo(),BMS.stage);
        });
        customer.setOnAction(e->{
            show( AdminController.seeUserInfo(),BMS.stage);
        });
        return vBox;
    }

    public  Parent tellerPage(Stage stage){
        Text welcometext = new Text("Teller Page");
        ImageView btnmanageUser = new ImageView(new Image(getClass().getResourceAsStream("Image/transfer-modified.png")));
        ImageView  history= new ImageView(new Image(getClass().getResourceAsStream("Image/transfer-modified.png")));
        ImageView accountCreate = new ImageView(new Image(getClass().getResourceAsStream("Image/createacc-modified.png")));
        history.setScaleX(0.5);
        history.setScaleY(0.5);
        btnmanageUser.setScaleX(0.5);
        btnmanageUser.setScaleY(0.5);
        accountCreate.setScaleX(0.3);
        accountCreate.setScaleY(0.3);
        btnmanageUser.setTranslateY(-50);
        btnmanageUser.setTranslateX(-70);
        history.setTranslateX(-130);
        history.setTranslateY(-55);
        btnmanage.setLayoutX(btnmanageUser.getLayoutX()-23);
        btnmanage.setLayoutY(btnmanageUser.getLayoutY()-23);
        btnSeeHistory.setTranslateX((btnmanage.getLayoutX() - btnmanageUser.getLayoutX()) * 3);
        btnmanage.setTranslateX((btnmanage.getLayoutX() - btnmanageUser.getLayoutX()) * 1);
        btnSeeHistory.setTranslateY((btnmanage.getLayoutY() - btnmanageUser.getLayoutY()) * 4.7);
        btnmanage.setTranslateY((btnmanage.getLayoutY() - btnmanageUser.getLayoutY()) * 4.5);
        Button account  = new Button("Create user Acc"); // This button is to create user account
        Button logout = new Button("Logout");
        logout.setFont(Font.font("verdana",FontWeight.BOLD,20));
        logout.setTranslateX(-240);
        logout.setTranslateY(-150);
        logout.setStyle("-fx-background-color:blue");
        GridPane tellerContainer = new GridPane();
        tellerContainer.addRow(2,btnmanageUser);
        tellerContainer.addRow(3,btnmanage);
        tellerContainer.addRow(2,history);
        tellerContainer.addRow(3,btnSeeHistory);
        tellerContainer.addRow(4,accountCreate);
        tellerContainer.addRow(5,account);
        tellerContainer.addColumn(1,logout);
        tellerContainer.setStyle("-fx-background-color:transparent");
        accountCreate.setTranslateY(-300);
        accountCreate.setTranslateX(40);
        account.setTranslateY(-430);
        account.setTranslateX(160);
        TextField usernametextfield = new TextField();
        usernametextfield.setMinWidth(100);
        welcometext.setFill(Color.color(1,1,1));
        welcometext.setFont(Font.font("verdana",FontWeight.BOLD,33));
        btnSeeHistory.setFont(Font.font("verdana",FontWeight.BOLD,14));
        btnmanage.setFont(Font.font("verdana",FontWeight.BOLD,14));
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
        TellerView.btnCreateAcc.setOnAction(e->{
            try {
                show( TellerController.createAcc(),stage);
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        TellerView.btnmanage.setOnAction(e->{
            try {
                show(TellerController.manageUserAcc(),stage);
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        return vBox;
    }
    public  Parent customerPage(Stage stage){
        new AdminView();
        Text welcometext = new Text("Customer Page");
        ImageView transferImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("Image/transfer-modified.png"))));
        ImageView  balanceImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("Image/balance-modified.png"))));
        ImageView transactionImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("Image/createacc-modified.png"))));
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
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        transactionbtn.setOnAction(e->{
            TransactionView.load();
            show(UserController.seeTransaction(),stage);
        });
        return vBox;
    }

    public  static  void initializeDatabase() throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
         connection = DriverManager.getConnection("jdbc:mysql://localhost/bms","eziraa","1234");
         statement = connection.createStatement();
//         connection = DriverManager.getConnection("jdbc:mysql://localhost/bms","eziraa","1234");
//         statement = connection.createStatement();
    }
}