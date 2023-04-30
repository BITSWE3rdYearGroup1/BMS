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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class HelloApplication extends Application {
    Scene scene = new Scene(new Group() , 1100, 800);
    @Override
    public void start(Stage stage) throws IOException {
        show(loginpage(stage),stage); //This method is to call it when  we need to navigate from one page to other page
        //rather than call start method this means initiate the app again if we call start

    }
    //This method is to call it when  we need to navigate from one page to other page
    //rather than call start method this means initiate the app again if we call start
    public void show(Parent parent,Stage stage){
        Text text = new Text("Bank Management System"); //main
        text.setFont(Font.font("verdana", FontWeight.BOLD,34));
        text.setFill(Color.WHITE);
        text.setTranslateX(-50);
        text.setTranslateY(-50);
        ImageView logo = new ImageView(new Image("C:\\Users\\ezrat\\Downloads\\34-modified.png"));
        logo.setScaleX(0.2);
        logo.setScaleY(0.2);
        logo.setTranslateX(-50);
        logo.setTranslateY(-50);
        GridPane gridPane = new GridPane(); //this gridpane is the main container
        gridPane.setStyle("-fx-background-color: #212F3F");
        gridPane.addColumn(1,text);
        gridPane.addColumn(0,logo);
        gridPane.addColumn(1,parent);
        scene = new Scene(gridPane, 1100, 800);
        scene.setFill(Color.BLUEVIOLET);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
    public Parent loginpage(Stage stage){

        GridPane loginContainer = new GridPane(); //this gridpane is Login information container
        Text welcometext = new Text("Welcome"); //This text to show welcome message
        Label username =  new Label("Username"); // this label is to username
        Label password = new Label("Password"); //This label is To Password
        Button login = new Button("Login"); //This Button is to login
        Hyperlink forgotPassword = new Hyperlink("Forgot password?"); //this hyper link is for forgot password
        welcometext.setTranslateY(-60);
        TextField usernametextfield = new TextField(); //This text field is to username
        TextField passwordtextfield = new TextField(); //This text field is to password
        username.setTextFill(Color.color(1,1,1));
        password.setTextFill(Color.color(1,1,1));
        login.setStyle("-fx-background-color: #0f0");
        welcometext.setFill(Color.color(1,1,1));
        welcometext.setFont(Font.font("verdana",FontWeight.BOLD,33));
        username.setFont(Font.font("verdana",FontWeight.BOLD,20));
        password.setFont(Font.font("verdana",FontWeight.BOLD,20));
        login.setFont(Font.font("verdana",FontWeight.BOLD,20));
        forgotPassword.setFont(Font.font("verdana",FontWeight.BOLD,16));
        forgotPassword.setTextFill(Color.BLUE);
        loginContainer.addColumn(1,welcometext);
        loginContainer.addRow(2,username);
        loginContainer.addRow(2,usernametextfield);
        loginContainer.addRow(3,password);
        loginContainer.addRow(3,passwordtextfield);
        loginContainer.addColumn(1,login);
        loginContainer.addColumn(1,forgotPassword);
        loginContainer.setHgap(23);
        loginContainer.setVgap(7);
        loginContainer.setTranslateX(-50);
        loginContainer.setTranslateY(-50);
        login.setOnAction(e->{
            String str = new String();
            str = usernametextfield.getText();
            if(str.contains("teller"))
                show(tellerPage(stage),stage);
            else if (str.contains("user")) {
                show(customerPage(stage),stage);
            } else if (str.contains("admin")) {
                show(adminPage(stage),stage);
            }
            else
            {
                usernametextfield.setText("Wrong input");
            }
        });
        return loginContainer;
    }
    public  GridPane adminPage(Stage stage){
        Text welcometext = new Text("Admin Page");
        ImageView tellerImage = new ImageView(new Image("C:\\Users\\ezrat\\Downloads\\teller-modified.png")); // This is to teller image
        ImageView customerImage = new ImageView(new Image("C:\\Users\\ezrat\\Downloads\\customer-modified.png"));
        ImageView accountImage = new ImageView(new Image("C:\\Users\\ezrat\\Downloads\\account-modified.png"));
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
        logout.setStyle("-fx-background-color:blue");
        GridPane adminContainer = new GridPane(); // This ggridpane to cellct admin page main element
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
        welcometext.setTranslateY(-30);
        welcometext.setTranslateX(100);
        welcometext.setFill(Color.color(1,1,1));
        welcometext.setFont(Font.font("verdana",FontWeight.BOLD,33));
        teller.setFont(Font.font("verdana",FontWeight.BOLD,14));
        customer.setFont(Font.font("verdana",FontWeight.BOLD,14));
        account.setFont(Font.font("verdana",FontWeight.BOLD,14));
        adminContainer.setHgap(23);
        adminContainer.setVgap(7);
        adminContainer.setTranslateY(-90);
        adminContainer.setTranslateX(-50);
        adminContainer.setTranslateY(-90);
        logout.setOnAction(e->{
            Platform.exit();
        });
        return adminContainer;
    }

    public  GridPane tellerPage(Stage stage){
        Text welcometext = new Text("Teller Page");
        ImageView manageUser = new ImageView(new Image("C:\\Users\\ezrat\\Downloads\\transfer-modified.png"));
        ImageView  history= new ImageView(new Image("C:\\Users\\ezrat\\Downloads\\transfer-modified.png"));
        ImageView accountCreate = new ImageView(new Image("C:\\Users\\ezrat\\Downloads\\createacc-modified.png"));
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
        tellerContainer.addColumn(0,welcometext);
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
        welcometext.setTranslateY(-30);
        welcometext.setTranslateX(100);
        TextField usernametextfield = new TextField();
        usernametextfield.setMinWidth(100);
        welcometext.setFill(Color.color(1,1,1));
        welcometext.setFont(Font.font("verdana",FontWeight.BOLD,33));
        seeHistory.setFont(Font.font("verdana",FontWeight.BOLD,14));
        manage.setFont(Font.font("verdana",FontWeight.BOLD,14));
        account.setFont(Font.font("verdana",FontWeight.BOLD,14));
        tellerContainer.setHgap(23);
        tellerContainer.setVgap(7);
        tellerContainer.setTranslateX(-70);
        tellerContainer.setTranslateY(-90);
        logout.setOnAction(e->{
            Platform.exit();
        });
        return tellerContainer;
    }
    public  GridPane customerPage(Stage stage){
        Text welcometext = new Text("Customer Page");
        ImageView transferImage = new ImageView(new Image("C:\\Users\\ezrat\\Downloads\\transfer-modified.png"));
        ImageView  balanceImage = new ImageView(new Image("C:\\Users\\ezrat\\Downloads\\balance-modified.png"));
        ImageView transactionImage = new ImageView(new Image("C:\\Users\\ezrat\\Downloads\\createacc-modified.png"));
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
        System.out.println((balancebtn.getLayoutY() - customerPage.getLayoutY()) );
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
        welcometext.setTranslateY(-30);
        welcometext.setTranslateX(100);
        TextField usernametextfield = new TextField();
        usernametextfield.setMinWidth(100);
        welcometext.setFill(Color.color(1,1,1));
        welcometext.setFont(Font.font("verdana",FontWeight.BOLD,33));
        balancebtn.setFont(Font.font("verdana",FontWeight.BOLD,14));
        transferbtn.setFont(Font.font("verdana",FontWeight.BOLD,14));
        transactionbtn.setFont(Font.font("verdana",FontWeight.BOLD,14));
        customerPage.setHgap(23);
        customerPage.setVgap(7);
        customerPage.setTranslateX(-70);
        customerPage.setTranslateY(-90);
        logout.setOnAction(e->{
            Platform.exit();
        });
        return customerPage;
    }
}