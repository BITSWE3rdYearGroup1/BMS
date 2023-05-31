package com.example.bms;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.sql.*;
import java.util.Date;
import java.util.Objects;



public class AdminView  extends EmployeeView{
    public static TextField txFldBranchID = new TextField();
    public static EmployeeView adminView;
    public static Button btnInsPhoto = new Button("Insert Photo");
    public static  ImageView imgBack  = new ImageView(new Image(Objects.requireNonNull(AdminView.class.getResourceAsStream("Image/backIcon.png"))));

    public static Button teller = new Button("Teller Info"); //This button is to enter to teller info page
    public static Button customer = new Button("Customer Info"); //this button is to enter customer page
    public static  Button btnAccount = new Button("Create Account"); //this button is to enter customer page
    public AdminView(){
        adminView =  new EmployeeView();
    }
    public  static Parent getHome(String type)
    {
        GridPane gridPane = new GridPane();
        VBox leftMenu = new VBox();

        ImageView tellerImage = new ImageView(new Image(Objects.requireNonNull(AdminView.class.getResourceAsStream("Image/teller-modified.png")))); // This is to teller image
        ImageView customerImage = new ImageView(new Image(Objects.requireNonNull(AdminView.class.getResourceAsStream("Image/customer-modified.png"))));
        ImageView accountImage = new ImageView(new Image(Objects.requireNonNull(AdminView.class.getResourceAsStream("Image/account-modified.png"))));
//        tellerImage.setScaleX(0.5);
//        tellerImage.setScaleY(0.5);
//        customerImage.setScaleX(0.4);
//        customerImage.setScaleY(0.4);
//        accountImage.setScaleX(0.3);
//        accountImage.setScaleY(0.3);
//        customerImage.setTranslateY(-125);
//        customerImage.setTranslateX(-115);
//        tellerImage.setTranslateX(-90);
//        tellerImage.setTranslateY(-40);
//        accountImage.setTranslateX(-170);
//        accountImage.setTranslateY(-300);
        tellerImage.setFitHeight(110);
        tellerImage.setFitWidth(110);
        accountImage.setFitHeight(110);
        accountImage.setFitWidth(110);
        customerImage.setFitHeight(110);
        customerImage.setFitWidth(110);
        leftMenu.getChildren().addAll(tellerImage,teller,customerImage,customer,accountImage,btnAccount);
        leftMenu.getStyleClass().add("left");
        Text text = new Text("create Account Page");
        text.setFill(Color.color(1,1,1));
        text.setFont(Font.font("verdana", FontWeight.BOLD,33));
        StackPane header = new StackPane();
        header.setMaxSize(BMS.scene.getWidth()*0.9,100);
        header.setMaxHeight(100);
        header.getStyleClass().add("header");
        header.setTranslateX(BMS.scene.getWidth()*0.05);
        header.getChildren().add(text);
        VBox allPage = new VBox();
        HBox body = new HBox();
//        allPage.getStyleClass().add("vBox");
        allPage.setStyle("-fx-text-fill:green;\n" +
                "-fx-font-weight:bold;\n" +
                "-fx-font-size:15;\n" +
                "-fx-font-weight:bold;\n" +
                "-fx-font-family:verdana;\n" +
                "-fx-background-color: #0a3260;\n" +
                "-fx-padding:12;\n" +
                "-fx-spacing:12;");
        allPage.getChildren().addAll(header,body);
        body.setMaxSize(BMS.scene.getWidth()*0.9,100);
        body.setMaxHeight(100);
        body.getStyleClass().add("header");
        body.setTranslateX(BMS.scene.getWidth()*0.05);
        StackPane main = new StackPane();
        main.setMinWidth(800);
        main.setMaxHeight(500);
//        main.getStyleClass().add("body");
        body.getChildren().addAll(leftMenu,main);
        main.setTranslateX(100);
        if (type.equalsIgnoreCase("create")) {
            text.setText("Create account Page");
            body.setSpacing(30);
            main.getChildren().add(createAccView());
        }
        else if (type.equalsIgnoreCase("teller")) {
            body.setSpacing(0);
            text.setText("Teller information Page");
            main.getChildren().add(loadTableView());
        }
        else {
            main.getChildren().add(loadUserTableView());
            body.setSpacing(0);
            text.setText("Customer information Page");
        }
//        AdminView.btnAccount.setOnAction(e->{
//
//            new BMS().show(createAccView() ,BMS.stage);
//        });
        return allPage;

    }
    public static GridPane createAccView(){
        GridPane gridPane = new GridPane();

        Label lblCreateTAcc = new Label("Create Teller Account");

        Label lblTellerId = new Label("Teller ID:");

        Label lblFName = new Label("First Name:");

        Label lblLName = new Label("Last Name:");

        Label lblGender = new Label("Gender:");

        Label lblUname = new Label("User Name:");

        Label lblPass = new Label("Password:");

        Label lblPhone = new Label("Phone:");

        Label lblPhoto = new Label("Photo:");

        Label lblEmail = new Label("Email");
        Label lblBranch = new Label("Branch ID");
        Rectangle recPhoto = new Rectangle();
        gridPane.setStyle("-fx-background-color: #212F3F;");
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        recPhoto.setWidth(85);
        recPhoto.setHeight(85);
        recPhoto.setStroke(Color.WHITE);
        recPhoto.setFill(Color.rgb(33, 47, 63));

        // Adding nodes to gridPane

        gridPane.add(imgBack, 0,0);
        gridPane.add(lblCreateTAcc, 1,0, 3,1);
        gridPane.add(lblTellerId, 0,1);
        gridPane.add(txFldTellerId, 1,1);
        gridPane.add(lblFName, 0, 2);
        gridPane.add(txFldFName, 1, 2);
        gridPane.add(lblLName,0,3);
        gridPane.add(txFldLName,1,3);
        gridPane.add(lblPhoto, 2, 3);
        gridPane.add(recPhoto,3,3,1,2);
        gridPane.add(btnInsPhoto,3,5);
        gridPane.add(lblGender,0,4);
        gridPane.add(txFldGender,1,4);
        gridPane.add(lblEmail,2,1);
        gridPane.add(txFldEmail,3,1);
        gridPane.add(lblUname,0,5);
        gridPane.add(txFldUName,1,5);
        gridPane.add(lblBranch,2,2);
        gridPane.add(txFldBranchID,3,2);
        gridPane.add(lblPass,0,6);
        gridPane.add(txFldPass,1,6);
        gridPane.add(lblPhone,0,7);
        gridPane.add(txFldPhone,1,7);
        gridPane.add(btnCreateAcc,3,7);
        gridPane.setStyle("-fx-background-color:#0a3260");
        return gridPane;
    }
    public static StackPane loadTableView(){
        Region region = new Region();
        region.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(region, Priority.ALWAYS);

        // Create an HBox to hold the label and the region
        tellerID.getStyleClass().add(className);
        firstName.getStyleClass().add(className);
        secondName.getStyleClass().add(className);
        gender.getStyleClass().add(className);
        username.getStyleClass().add(className);
        password.getStyleClass().add(className);
        email.getStyleClass().add(className);
        registrationDate.getStyleClass().add(className);
        branchID.getStyleClass().add(className);
        phone.getStyleClass().add(className);
        photo.getStyleClass().add(className);
        tellerID.setMinWidth(100);
        firstName.setMinWidth(100);
        secondName.setMinWidth(100);
        gender.setMinWidth(100);
        username.setMinWidth(100);
        password.setMinWidth(100);
        email.setMinWidth(100);
        registrationDate.setMinWidth(100);
        photo.setMinWidth(100);
        branchID.setMinWidth(100);
        tellerID.setCellValueFactory(new PropertyValueFactory<Teller,Integer>("teller_id"));
        firstName.setCellValueFactory(new PropertyValueFactory<Teller,String>("firstName"));
        secondName.setCellValueFactory(new PropertyValueFactory<Teller,String>("secondName"));
        gender.setCellValueFactory(new PropertyValueFactory<Teller,String>("gender"));
        username.setCellValueFactory(new PropertyValueFactory<Teller,String>("username"));
        password.setCellValueFactory(new PropertyValueFactory<Teller,String>("password"));
        email.setCellValueFactory(new PropertyValueFactory<Teller,String>("email"));
        registrationDate.setCellValueFactory(new PropertyValueFactory<Teller,String>("registrationDate"));
        phone.setCellValueFactory(new PropertyValueFactory<Teller,String>("phone"));
        photo.setCellValueFactory(new PropertyValueFactory<Teller,String>("photo"));
        branchID.setCellValueFactory(new PropertyValueFactory<Teller,Integer>("BranchID"));
        AdminView.adminView.tellerTableView.getColumns().addAll(tellerID,firstName,secondName,gender,username,password,phone, email,registrationDate,photo,branchID);
        phone.setPrefWidth(UPdate.resize(phone.getText()));
        firstName.setPrefWidth(UPdate.resize(firstName.getText()));
        secondName.setPrefWidth(UPdate.resize(secondName.getText()));
        gender.setPrefWidth(UPdate.resize(gender.getText()));
        username.setPrefWidth(UPdate.resize(phone.getText()));
        password.setPrefWidth(UPdate.resize(phone.getText()));
        email.setPrefWidth(UPdate.resize("email.getText()"));
        photo.setPrefWidth(UPdate.resize(photo.getText()));
        registrationDate.setPrefWidth(UPdate.resize("registration"));
        StackPane stackPane = new StackPane();
        stackPane.setMaxSize(BMS.scene.getWidth()*0.7,500);
        stackPane.getChildren().add(AdminView.adminView.tellerTableView);
        HBox hbox = new HBox();
        stackPane.setMaxSize(BMS.scene.getWidth()*0.7,500);
        AdminView.adminView.tellerTableView.setStyle("-fx-foreground-color:red");
        stackPane.setStyle("-fx-background-color:red");
        return stackPane;
    }
    class UPdate {
        public static double AUTO ;
        public static double resize(String object){
            AUTO = object.length()*14;
            return AUTO;
        }

    }
    public static StackPane loadUserTableView(){
        Region region = new Region();
        region.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(region, Priority.ALWAYS);
        // Create an HBox to hold the label and the region
        TellerView.tellerID.getStyleClass().add(className);
        TellerView.firstName.getStyleClass().add(className);
        TellerView.secondName.getStyleClass().add(className);
        TellerView.gender.getStyleClass().add(className);
        TellerView.username.getStyleClass().add(className);
        TellerView.password.getStyleClass().add(className);
        TellerView.email.getStyleClass().add(className);
        TellerView.registrationDate.getStyleClass().add(className);
        TellerView.branchID.getStyleClass().add(className);
        TellerView.phone.getStyleClass().add(className);
        TellerView.tellerID.setMinWidth(100);
        TellerView.firstName.setMinWidth(100);
        TellerView.secondName.setMinWidth(100);
        TellerView.gender.setMinWidth(100);
        TellerView.username.setMinWidth(100);
        TellerView.password.setMinWidth(100);
        TellerView.email.setMinWidth(100);
        TellerView.registrationDate.setMinWidth(100);
        TellerView.photo.setMinWidth(100);
        TellerView.branchID.setMinWidth(100);
        TellerView.tellerID.setCellValueFactory(new PropertyValueFactory<User,Integer>("customerID"));
        TellerView.firstName.setCellValueFactory(new PropertyValueFactory<User,String>("firstName"));
        TellerView.secondName.setCellValueFactory(new PropertyValueFactory<User,String>("secondName"));
        TellerView.gender.setCellValueFactory(new PropertyValueFactory<User,String>("gender"));
        TellerView.username.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        TellerView.password.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
        TellerView.email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        TellerView.registrationDate.setCellValueFactory(new PropertyValueFactory<User,String>("registrationDate"));
        TellerView.phone.setCellValueFactory(new PropertyValueFactory<User,String>("phone"));
        TellerView.photo.setCellValueFactory(new PropertyValueFactory<User,String>("photo"));
        TellerView.tellerView.userTableView.getColumns().addAll(TellerView.tellerID,TellerView.firstName,TellerView.secondName,TellerView.gender,TellerView.username,TellerView.password,TellerView.phone, TellerView.email,TellerView.registrationDate,TellerView.photo);
        TellerView.phone.setPrefWidth(AdminView.UPdate.resize(phone.getText()));
        TellerView.firstName.setPrefWidth(AdminView.UPdate.resize(firstName.getText()));
        TellerView.secondName.setPrefWidth(AdminView.UPdate.resize(secondName.getText()));
        TellerView.gender.setPrefWidth(AdminView.UPdate.resize(gender.getText()));
        TellerView.username.setPrefWidth(AdminView.UPdate.resize(phone.getText()));
        TellerView.password.setPrefWidth(AdminView.UPdate.resize(phone.getText()));
        TellerView.email.setPrefWidth(AdminView.UPdate.resize("email.getText()"));
        TellerView.photo.setPrefWidth(AdminView.UPdate.resize(photo.getText()));
        TellerView.registrationDate.setPrefWidth(AdminView.UPdate.resize("registration"));
        StackPane stackPane = new StackPane();
        TellerView.tellerView.userTableView.setMinSize(700,500);
//        TellerView.tellerView.userTableView.setTranslateX(-200);
        stackPane.getChildren().add(TellerView.tellerView.userTableView);
        HBox hbox = new HBox();
        stackPane.setMaxSize(BMS.scene.getWidth()*0.7,500);
        return stackPane;
    }



}

