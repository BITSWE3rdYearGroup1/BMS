package com.example.bms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Objects;


public class TellerView extends EmployeeView{
    public static final TextField txtFldBalance = new TextField();
    public  TableView<User> userTableView = new TableView<>();
    public  ObservableList<User> userList = FXCollections.observableArrayList();
    public static TableColumn<User,Integer> tellerID = new TableColumn<>("Customer ID");

    public static TableColumn<User, String> firstName =  new TableColumn<>("First Name");
    public static TableColumn<User, String> secondName = new TableColumn<>("Second Name");
    public static TableColumn<User, String> gender  = new TableColumn<>("Gender");
    public static TableColumn<User, String> username = new TableColumn<>("Username");
    public static TableColumn<User, String> password = new TableColumn<>("Password");
    public static TableColumn<User, String> phone = new TableColumn<>("Phone number");
    public static  TableColumn<User, String> email = new TableColumn<>("Email");
    public static TableColumn<User, String> registrationDate = new TableColumn<>("Registration Date");
    public static TableColumn<User, Image> photo = new TableColumn<>("Photo");
    public static Button btnDeposit = new Button("Deposit  ");
    public static Button btnTransfer = new Button("Transfer  ");
    public static Button btnWithDraw = new Button("WithDraw  ");
    public static final TextField txtFldAccNumber = new TextField() ;
    public static final Button btnBrowse = new Button("");
    public static final Button btnSearch = new Button("Search");

    public static final Button create = new Button("Create");
    public static final Button back = new Button("Back");
    public static Button btnCompleteDeposit = new Button("Deposit");
    public static Button btnCompleteTransfer = new Button("Transfer");
    public static Button btnCompleteWithdraw = new Button("Withdraw");

    public static  final TextField txtFldAmount = new TextField();
    public static  Button  btnmanage = new Button("manage user Acc"); //This button is to manage user account that means to search user by account number and manage their account
    public static Button btnSeeHistory = new Button(" See History "); //This button is to the history of ones teller
    public static Button btnCreateAcc  = new Button("Create user Acc"); // This button is to create user account
    public  static ImageView profile = new ImageView(new Image(Objects.requireNonNull(TellerView.class.getResourceAsStream("new.jpg"))));
    public static TellerView tellerView;
    public static Label name = new Label("Name : ");
    public static Label Ugender = new Label("Gender : ");
    public static Label accNumber = new Label("Account Number : ");
    public static Label balance = new Label("Balance : ");

    public static void EmployeeView(){
        tellerView = new TellerView();
    }
    public static Parent tellerHome(String type) {
        ImageView manageUser = new ImageView(new Image(Objects.requireNonNull(TellerView.class.getResourceAsStream("Image/transfer-modified.png"))));
        ImageView  history= new ImageView(new Image(Objects.requireNonNull(TellerView.class.getResourceAsStream("Image/transfer-modified.png"))));
        ImageView accountCreate = new ImageView(new Image(Objects.requireNonNull(TellerView.class.getResourceAsStream("Image/createacc-modified.png"))));
        manageUser.setFitHeight(110);
        manageUser.setFitWidth(110);
        history.setFitHeight(110);
        history.setFitWidth(110);
        accountCreate.setFitHeight(110);
        accountCreate.setFitWidth(110);
        Text text = new Text("Balance Page");
        text.setFill(Color.color(1,1,1));
        text.setFont(Font.font("verdana", FontWeight.BOLD,33));
        StackPane header = new StackPane();
        header.setMaxSize(BMS.scene.getWidth()*0.9,100);
        header.setMaxHeight(100);
        header.getStyleClass().add("header");
        header.setTranslateX(BMS.scene.getWidth()*0.05);
        header.getChildren().add(text);
        btnmanage.setTranslateX(0);
        btnmanage.setTranslateY(0);
        btnSeeHistory.setTranslateY(0);
        btnSeeHistory.setTranslateX(0);
        VBox leftMenu = new VBox();
        VBox allPage = new VBox();
        HBox body = new HBox();
        VBox rightMenu = new VBox();
        rightMenu.getStyleClass().add("right");
        allPage.setStyle("""
                -fx-text-fill:green;
                -fx-font-weight:bold;
                -fx-font-size:15;
                -fx-font-weight:bold;
                -fx-font-family:verdana;
                -fx-background-color: #0a3260;
                -fx-padding:12;
                -fx-spacing:12;""");
        leftMenu.getStyleClass().add("left");
        leftMenu.getChildren().addAll(manageUser, btnmanage, history, btnSeeHistory, accountCreate, btnCreateAcc);
        allPage.getChildren().addAll(header,body);
        body.setMaxHeight(100);
        body.getStyleClass().add("header");
        body.setTranslateX(BMS.scene.getWidth()*0.05);
        StackPane main = new StackPane();
        main.setMinWidth(800);
        main.setMaxHeight(500);
        main.getStyleClass().add("body");
        body.getChildren().addAll(leftMenu,main,rightMenu);
        rightMenu.setMinWidth(300);
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(Teller.class.getResourceAsStream("new.jpg"))));
        imageView.setFitWidth(200);
        imageView.setFitHeight(230);
        imageView.setTranslateX(30);
        rightMenu.getChildren().add(imageView);
        body.setSpacing(30);
        if (type.equalsIgnoreCase("create"))
            main.getChildren().add(createUserAccView());
        else if (type.equalsIgnoreCase("manage")) {
            main.getChildren().addAll(manageUserAccount());
            text.setText("Transfer fund page");
        } else if (type.equalsIgnoreCase("")) {
            main.getChildren().add(displayUserInfo("type"));
        } else {
            text.setText("Transaction history page");
            main.getChildren().addAll(displayUserInfo(type));
        }
        return allPage;
    }
    public static GridPane createUserAccView(){
    Label fname = new Label("First Name:");
    Label lblAccNumber = new Label("Account Number:");
    Label lname = new Label("Last Name:");
    Label Gender = new Label("Gender:");
    Label email = new Label("Email");
    Label phoneNum = new Label("Phone Number");
    Label photo = new Label("Photo");
    Label balance = new Label("Initial Balance");
    Label userName = new Label("User Name");
    Label password = new Label("Password");
    Label conPassword = new Label("Confirm Password");
    ImageView image = new ImageView(new Image(Objects.requireNonNull(Teller.class.getResourceAsStream("Image/upload_2_30x20.png"))));
    btnBrowse.setGraphic(image);// made the upload image inside the brose button
    GridPane createUserAccountPage = new GridPane();
    createUserAccountPage.add(fname, 0, 1);
    createUserAccountPage.add(txFldFName, 1, 1);
    createUserAccountPage.add(lname, 0, 2);
    createUserAccountPage.add(txFldLName, 1, 2);
    createUserAccountPage.add(Gender, 0, 3);
    createUserAccountPage.add(txFldGender, 1, 3);
    createUserAccountPage.add(lblAccNumber, 0, 4);
    createUserAccountPage.add(txtFldAccNumber, 1, 4);
    createUserAccountPage.add(email, 0, 5);
    createUserAccountPage.add(txFldEmail, 1, 5);
    createUserAccountPage.add(phoneNum, 0, 6);
    createUserAccountPage.add(txFldPhone, 1, 6);
    createUserAccountPage.add(photo, 0, 7);
    createUserAccountPage.add(btnBrowse, 1, 7);
    createUserAccountPage.add(balance, 3, 1);
    createUserAccountPage.add(txtFldBalance, 4, 1);
    createUserAccountPage.add(userName, 3, 2);
    createUserAccountPage.add(txFldUName, 4, 2);
    createUserAccountPage.add(password, 3, 3);
    createUserAccountPage.add(txFldPass, 4, 3);
    createUserAccountPage.add(conPassword, 3, 4);
    createUserAccountPage.add(txtFldConfirmPasswd, 4, 4);
    createUserAccountPage.add(create, 4, 6);
    createUserAccountPage.add(back, 4, 16);
    createUserAccountPage.setHgap(23);
    createUserAccountPage.setVgap(7);
    createUserAccountPage.setAlignment(Pos.TOP_CENTER);
    return createUserAccountPage;
}
public static Parent manageUserAccount(){
        StackPane manageUserAccountContainer = new StackPane();
        Label searchAccounNo = new Label("Enter the account Number ");
        HBox hBox = new HBox();
        hBox.getChildren().addAll(searchAccounNo,txtFldAccNumber,btnSearch);
        hBox.setSpacing(30);
        hBox.setTranslateY(200);
        manageUserAccountContainer.getChildren().add(hBox);
        manageUserAccountContainer.setMinHeight(600);
        return manageUserAccountContainer;
}
public static Parent displayUserInfo(String type){
        VBox leftContent = new VBox();
        profile.setFitWidth(80);
        profile.setFitHeight(100);
        profile.setTranslateX(20);
        leftContent.getChildren().addAll(profile,name,Ugender,accNumber,balance);
        VBox rightMenu = new VBox();
        btnWithDraw.setMinWidth(50);
        btnTransfer.setMinWidth(50);
        btnDeposit.setMinWidth(50);
        leftContent.setSpacing(30);
        rightMenu.setSpacing(5);
        rightMenu.getChildren().addAll(btnWithDraw,btnTransfer,btnDeposit);
        HBox hBox = new HBox();
        StackPane centerContent  =new StackPane();
        centerContent.setMinWidth(280);
        centerContent.prefWidthProperty().bind(BMS.scene.widthProperty().multiply(0.3));
        hBox.setSpacing(30);
        hBox.getChildren().addAll(leftContent,centerContent,rightMenu);
        if (type.equalsIgnoreCase("deposit"))
            centerContent.getChildren().add(depositView());
        else if (type.equalsIgnoreCase("transfer"))
            centerContent.getChildren().add(transferView());
        else if (type.equalsIgnoreCase("withDraw"))
            centerContent.getChildren().add(withdrawView());
        return hBox;
}
public static Parent depositView(){
        Label lblAmount = new Label("Enter amount");
        GridPane depositContainer = new GridPane();
        depositContainer.add(lblAmount,0,0);
        depositContainer.add(txtFldAmount,1,0);
        depositContainer.add(btnCompleteDeposit,1,1);
        depositContainer.setTranslateY(200);
        depositContainer.setVgap(20);
        depositContainer.setHgap(10);
        return depositContainer;
}
public static Parent withdrawView(){
    Label lblAmount = new Label("Enter amount");
    GridPane withdrawContainer = new GridPane();
    withdrawContainer.add(lblAmount,0,0);
    withdrawContainer.add(txtFldAmount,1,0);
    withdrawContainer.add(btnCompleteWithdraw,1,1);
    withdrawContainer.setTranslateY(200);
    withdrawContainer.setVgap(20);
    withdrawContainer.setHgap(10);
    return withdrawContainer;
}
    public static Parent transferView(){
        Label lblAmount = new Label("Enter amount ");
        Label lblAccNumber = new Label("Enter Acc No:");
        GridPane transferContainer = new GridPane();
        transferContainer.add(lblAmount,0,0);
        transferContainer.add(txtFldAmount,1,0);
        transferContainer.add(lblAccNumber,0,1);
        transferContainer.add(txtFldAccNumber,1,1);
        transferContainer.add(btnCompleteTransfer,1,2);
        transferContainer.setTranslateY(200);
        transferContainer.setVgap(20);
        transferContainer.setHgap(10);
        return transferContainer;
    }


}