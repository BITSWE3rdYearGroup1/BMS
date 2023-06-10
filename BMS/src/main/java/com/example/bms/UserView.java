package com.example.bms;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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

import java.util.Objects;


public class UserView extends EmployeeView{
    static Button transferbtn = new Button("Transfer.");
    static Button balancebtn = new Button("See Balance");
    static Button transactionbtn = new Button("See transaction");
     public static ImageView profile = new ImageView();
     public static Label name = new Label();
     public static Label Ugender = new Label();
     public static Label accNumber = new Label();
     public static Label balance = new Label();
     public static Label emailS = new Label();
     public static Label phoneNumber = new Label();
    public static Label txtArea = new Label("");
   public static TextField txtFldReceiverAcc = new TextField();
   public static TextField txtFldAmount = new TextField();
   public static Button btnComplete = new Button("Complete");
    public static Parent userHome(String type) {
        ImageView transferImage = new ImageView(new Image(UserView.class.getResourceAsStream("Image/transfer-modified.png")));
        ImageView balanceImage = new ImageView(new Image(UserView.class.getResourceAsStream("Image/balance-modified.png")));
        ImageView transactionImage = new ImageView(new Image(UserView.class.getResourceAsStream("Image/createacc-modified.png")));
        balanceImage.setFitHeight(110);
        balanceImage.setFitWidth(110);
        transactionImage.setFitHeight(110);
        transactionImage.setFitWidth(110);
        transferImage.setFitHeight(110);
        transferImage.setFitWidth(110);
        Text text = new Text("Balance Page");
        text.setFill(Color.color(1,1,1));
        text.setFont(Font.font("verdana", FontWeight.BOLD,33));
        StackPane header = new StackPane();
        header.setMaxSize(BMS.scene.getWidth()*0.9,100);
        header.setMaxHeight(100);
        header.getStyleClass().add("header");
        header.setTranslateX(BMS.scene.getWidth()*0.05);
        header.getChildren().add(text);
        VBox leftMenu = new VBox();
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
        leftMenu.getStyleClass().add("left");
        leftMenu.getChildren().addAll(balanceImage, balancebtn, transferImage, transferbtn, transactionImage, transactionbtn);
        allPage.getChildren().addAll(header,body);
        body.setMaxSize(BMS.scene.getWidth()*0.9,100);
        body.setMaxHeight(100);
//        VBox leftContent = new VBox();
//        leftContent.getChildren().addAll();
//        VBox rightMenu = new VBox();
//        btnDelete.setMinWidth(50);
//        btnUpdate.setMinWidth(50);
//        btnNotify.setMinWidth(50);
//        rightMenu.getChildren().addAll(btnDelete,btnUpdate,btnNotify);
//        leftContent.setSpacing(30);
//        rightMenu.setSpacing(5);
//        HBox hBox = new HBox();
//        StackPane centerContent  =new StackPane();
//        centerContent.prefWidthProperty().bind(BMS.scene.widthProperty().multiply(0.3));
//        hBox.setSpacing(30);
//        rightMenu.setAlignment(Pos.TOP_RIGHT);
//        leftContent.setMinWidth(600);
//        hBox.getChildren().addAll(leftContent,rightMenu);
        VBox rightMenu = new VBox();
        rightMenu.getStyleClass().add("right");
        rightMenu.setMinWidth(300);
        ImageView imageView = new ImageView(User.user.getImage());
        imageView.setFitWidth(200);
        imageView.setFitHeight(230);
        imageView.setTranslateX(30);
        name.setText("Name : "+ User.user.getFirstName() + " " + User.user.getSecondName() );
        Ugender.setText("Gender : " +User.user.getGender());
        accNumber.setText("Account Number : "+User.user.getAccNumber());
        balance.setText("Current Balance : " +(User.user.getBalance()));
        emailS.setText("Yours Email : " +User.user.getEmail());
        phoneNumber.setText("Phone number : " +User.user.getPhone());
        rightMenu.getChildren().addAll(imageView,name,Ugender,accNumber,balance,emailS,phoneNumber);
        body.getStyleClass().add("header");
        body.setTranslateX(BMS.scene.getWidth()*0.05);
        StackPane main = new StackPane();
        main.setMinWidth(800);
        main.setMaxHeight(500);
        main.getStyleClass().add("body");
        body.getChildren().addAll(leftMenu,main,rightMenu);
        body.setSpacing(30);
        if (type.equalsIgnoreCase("balance"))
        main.getChildren().addAll(seeBalance());
        else if (type.equalsIgnoreCase("transfer")) {
            text.setText("Transfer fund page");
            main.getChildren().addAll(transferView());
        }
        else {
            text.setText("Transaction history page");
            main.getChildren().addAll(transactionView());
        }
        allPage.prefWidthProperty().bind(BMS.scene.widthProperty().multiply(1));
        return allPage;
    }
    public static Parent seeBalance(){
        StackPane balance = new StackPane();
        balance.getChildren().addAll(txtArea);
        txtArea.prefWidthProperty().bind(BMS.stage.widthProperty().divide(2));
        txtArea.prefHeightProperty().bind(BMS.stage.heightProperty().divide(2));
        txtArea.getStyleClass().add("balance");
        txtArea.setTextFill(Color.BLUEVIOLET);
        return balance;
    }
    public static GridPane transferView(){
        GridPane transferContainer = new GridPane();
        Label receiverAcc = new Label("Receiver Account");
        Label amount = new Label("Enter Amount");
        transferContainer.add(receiverAcc,0,0);
        transferContainer.add(txtFldReceiverAcc,1,0);
        transferContainer.add(amount,0,1);
        transferContainer.add(txtFldAmount,1,1);
        transferContainer.add(btnComplete,1,2);
        transferContainer.setPadding(new Insets(12));
        transferContainer.setVgap(21);
        transferContainer.setHgap(21);
        transferContainer.setStyle("-fx-background-color: #0a3260;");
        transferContainer.setMaxWidth(600);
        transferContainer.setMaxHeight(300);
        return transferContainer;
    }
    public static Parent transactionView(){
        return TransactionController.displayTransaction();
    }
}
