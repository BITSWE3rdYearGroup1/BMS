package com.example.bms;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.util.Objects;
import java.util.Optional;

import static com.example.bms.TellerView.*;

public class AdminView  extends EmployeeView{
    public static TextField txFldBranchID = new TextField();
    public static EmployeeView adminView;
    public static Button btnInsPhoto = new Button("Insert Photo");
    public static  ImageView imgBack  = new ImageView(new Image(Objects.requireNonNull(AdminView.class.getResourceAsStream("Image/backIcon.png"))));

    public static Button teller = new Button("Teller Info"); //This button is to enter to teller info page
    public static  Button btnAccount = new Button("Create Account"); //this button is to enter customer page
    public static Label recPhoto;
    public static Label name = new Label("name");
    public static Label Ugender = new Label("Gender");
    public static Label accNumber = new Label("Account number");
    public static Label balance = new Label("Balance");
    public static Label emailS = new Label("email");
    public static Label branchName = new Label("Branch Name");
    public  static ImageView profile = new ImageView(new Image(Objects.requireNonNull(TellerView.class.getResourceAsStream("new.jpg"))));
    public static Label phoneNumber = new Label("Balance");
    public static Button btnDelete = new Button("DELETE");
    public static Button btnUpdate = new Button("UPDATE");
    public static Button btnNotify = new Button("NOTIFY");
    public static Button  btnSearchTeller = new Button("Search");
    public static Button  btnCreateAcc = new Button("Register");
    public static TextField txtFldTeller = new TextField();
    static ImageView image = new ImageView(new Image(Objects.requireNonNull(Teller.class.getResourceAsStream("Image/upload_2_30x20.png"))));
    public AdminView(){
        adminView =  new EmployeeView();
    }
    public  static Parent getHome(String type)
    {
        VBox leftMenu = new VBox();
        ImageView tellerImage = new ImageView(new Image(Objects.requireNonNull(AdminView.class.getResourceAsStream("Image/teller-modified.png")))); // This is to teller image
        ImageView accountImage = new ImageView(new Image(Objects.requireNonNull(AdminView.class.getResourceAsStream("Image/account-modified.png"))));
        tellerImage.setFitHeight(110);
        tellerImage.setFitWidth(110);
        accountImage.setFitHeight(110);
        accountImage.setFitWidth(110);
        leftMenu.getChildren().addAll(tellerImage,teller,accountImage,btnAccount);
        leftMenu.getStyleClass().add("left");
        Text text = new Text("create Account");
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
        main.getStyleClass().add("body");
        main.setMaxHeight(500);
        body.getChildren().addAll(leftMenu,main);
        if (type.equalsIgnoreCase("create")) {
            text.setText("Create account ");
            body.setSpacing(30);
            main.getChildren().add(createAccView());
        }
        else if (type.equalsIgnoreCase("tellers")) {
            body.setSpacing(0);
            text.setText("Teller information");
            main.getChildren().add(loadTableView());
        }
        else {
            main.getChildren().add(searchedTellerInfo(type));
            body.setSpacing(0);
            text.setText("Searched Teller information");

        }
        VBox rightMenu = new VBox();
        rightMenu.getStyleClass().add("right");
        rightMenu.setMinWidth(300);
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(Teller.class.getResourceAsStream("new.jpg"))));
        imageView.setFitWidth(200);
        imageView.setFitHeight(230);
        imageView.setTranslateX(30);
        rightMenu.getChildren().add(imageView);
        body.getChildren().add(rightMenu);
        body.setSpacing(30);
        allPage.setPrefHeight(600);
        return allPage;

    }
    public static Parent createAccView(){
        GridPane gridPane = new GridPane();
        Label lblCreateTAcc = new Label("CREATE TELLER ACCOUNT");
        Label lblFName = new Label("First Name:");
        Label lblLName = new Label("Last Name:");
        Label lblGender = new Label("Gender:");
        Label lblUname = new Label("User Name:");
        Label lblPass = new Label("Password:");
        Label lblConfirm = new Label("Confirm:");
        Label lblPhone = new Label("Phone:");
        Label lblPhoto = new Label("Photo:");
        btnBrowse.setGraphic(image);// made the upload image inside the brose button
        Label lblEmail = new Label("Email");
        Label lblBranch = new Label("Teller ID");
        recPhoto = new Label();
        recPhoto.setMinSize(50,50);
        gridPane.setStyle("-fx-background-color: #212F3F;");
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        gridPane.add(lblCreateTAcc, 1,0, 3,1);
        gridPane.add(lblFName, 0, 1);
        gridPane.add(txFldFName, 1, 1);
        gridPane.add(lblLName,0,2);
        gridPane.add(txFldLName,1,2);
        gridPane.add(lblPhoto, 2, 4);
        gridPane.add(btnBrowse,3,4);
        gridPane.add(lblGender,0,3);
        gridPane.add(txFldGender,1,3);
        gridPane.add( lblUname,2,1);
        gridPane.add(txFldUName,3,1);
        gridPane.add(lblEmail,0,4);
        gridPane.add( txFldEmail,1,4);
        gridPane.add(lblPass,2,2);
        gridPane.add(txFldPass ,3,2);
        gridPane.add( lblBranch,0,5);
        gridPane.add(txFldBranchID,1,5);
        gridPane.add(lblPhone,0,6);
        gridPane.add(txFldPhone,1,6);
        gridPane.add(lblConfirm ,2,3);
        gridPane.add(txtFldConfirmPasswd ,3,3);
        gridPane.add(AdminView.btnCreateAcc,3,5);
        gridPane.setStyle("-fx-background-color:#0a3260");
        gridPane.setMinHeight(500);
        return gridPane;
    }
    public static Parent loadTableView(){
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
        tellerID.setCellValueFactory(new PropertyValueFactory<>("teller_id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        secondName.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        registrationDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        photo.setCellValueFactory(cellData -> cellData.getValue().imageProperty());
        photo.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Teller, Image> call(TableColumn<Teller, Image> param) {
                return new TableCell<>() {
                    private final ImageView imageView = new ImageView();

                    @Override
                    protected void updateItem(Image item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            imageView.setImage(item);
                            imageView.setFitHeight(50);
                            imageView.setFitWidth(50);
                            imageView.setTranslateX(20);
                            setGraphic(imageView);
                        }
                    }
                };
            }
        });
        branchID.setCellValueFactory(new PropertyValueFactory<Teller,Integer>("BranchID"));
        tellerTableView.getColumns().addAll(tellerID,firstName,secondName,gender,username,password,phone, email,registrationDate,photo,branchID);
        phone.setPrefWidth(UPdate.resize(phone.getText()));
        firstName.setPrefWidth(UPdate.resize(firstName.getText()));
        secondName.setPrefWidth(UPdate.resize(secondName.getText()));
        gender.setPrefWidth(UPdate.resize(gender.getText()));
        username.setPrefWidth(UPdate.resize(phone.getText()));
        password.setPrefWidth(UPdate.resize(phone.getText()));
        email.setPrefWidth(UPdate.resize("email.getText()"));
        registrationDate.setPrefWidth(UPdate.resize("registration"));
        StackPane stackPane = new StackPane();
        stackPane.setMaxSize(BMS.scene.getWidth()*0.7,500);
        stackPane.getChildren().add(tellerTableView);
        stackPane.setMaxSize(BMS.scene.getWidth()*0.52,500);
        tellerTableView.setStyle("-fx-foreground-color:red");
        stackPane.setStyle("-fx-background-color:red");
        VBox centeral = new VBox();
        centeral.setSpacing(10);
        HBox searchContainer = new HBox();
        searchContainer.setAlignment(Pos.TOP_RIGHT);
        Label enterAcc = new Label("Enter Teller ID ");
        searchContainer.getChildren().addAll(enterAcc,AdminView.txtFldTeller,btnSearchTeller);
        searchContainer.setSpacing(15);
        centeral.getChildren().addAll(searchContainer,stackPane);
        centeral.setMinHeight(500);
        return centeral;
    }
    static class UPdate {
        public static double AUTO ;
        public static double resize(String object){
            AUTO = object.length()*14;
            return AUTO;
        }

    }


    public static Parent searchedTellerInfo(String type){
        VBox leftContent = new VBox();
        profile.setFitWidth(80);
        profile.setFitHeight(100);
        profile.setTranslateX(20);
        leftContent.getChildren().addAll(profile,name,Ugender,branchName,emailS,phoneNumber);
        VBox rightMenu = new VBox();
        btnDelete.setMinWidth(50);
        btnUpdate.setMinWidth(50);
        btnNotify.setMinWidth(50);
        leftContent.setSpacing(30);
        rightMenu.setSpacing(5);
        rightMenu.getChildren().addAll(btnDelete,btnUpdate,btnNotify);
        HBox hBox = new HBox();
        StackPane centerContent  =new StackPane();
        centerContent.prefWidthProperty().bind(BMS.scene.widthProperty().multiply(0.3));
        hBox.setSpacing(30);
        rightMenu.setAlignment(Pos.TOP_RIGHT);
        leftContent.setMinWidth(600);
        hBox.setMinHeight(500);
        hBox.getChildren().addAll(leftContent,rightMenu);
        if (type.equalsIgnoreCase("DELETE"));
//            centerContent.getChildren().add(deleteUserView());
        else if (type.equalsIgnoreCase("UPDATE"))
            centerContent.getChildren().add(AdminController.updateTeller());
        else if (type.equalsIgnoreCase("back"))
            centerContent.getChildren().add(withdrawView());
        return hBox;
    }



}

