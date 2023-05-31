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

import java.sql.SQLException;

import static com.example.bms.UserController.seeBalance;
import static com.example.bms.UserView.transferView;

public class TellerView extends EmployeeView{
    public  TableView<User> userTableView = new TableView<User>();
    public  ObservableList<User> userList = FXCollections.observableArrayList();
    public static TableColumn<User,Integer> tellerID = new TableColumn<User, Integer>("Customer ID");
    String className = "header";

    public static TableColumn<User, String> firstName =  new TableColumn<User,String>("First Name");
    public static TableColumn<User, String> secondName = new TableColumn<User,String>("Second Name");
    public static TableColumn<User, String> gender  = new TableColumn<User,String>("Gender");
    public static TableColumn<User, String> username = new TableColumn<User,String>("Username");
    public static TableColumn<User, String> password =  new TableColumn<User,String>("Password");
    public static TableColumn<User, String> phone =  new TableColumn<User,String>("Phone number");
    public static  TableColumn<User, String> email =  new TableColumn<User,String>("Email");
    public static TableColumn<User, String> registrationDate =  new TableColumn<User,String>("Registration Date");
    public static TableColumn<User, String> photo =  new TableColumn<User,String>("Photo");
//    public static TableColumn<User, Integer> branchID =  new TableColumn<User,Integer>("Branch ID");


    public static final TextField txtFldBirthDAte = new TextField() ;
    public static final TextField txtFldAccNumber = new TextField() ;
    public static final Button btnBrowse = new Button("");

    public static final Button create = new Button("Create");
    public static final Button back = new Button("Back");
    public static  final TextField txtFldID = new TextField();
    public static final TextField tellerIdTxt  = new TextField();
    public static TellerView tellerView;

//    public TellerView(int id, String firstName, String secondName, String gender, String username, String password, String phone, String email, String registrationDate, String photo) {
//        super(id, firstName, secondName, gender, username, password, phone, email, registrationDate, photo);
//    }
//    public TellerView(){
//        this();
//
//    }
    public static void EmployeeView(){
        tellerView = new TellerView();
    }
    public static Parent tellerHome(String type) throws SQLException, ClassNotFoundException {
        ImageView manageUser = new ImageView(new Image(TellerView.class.getResourceAsStream("Image/transfer-modified.png")));
        ImageView  history= new ImageView(new Image(TellerView.class.getResourceAsStream("Image/transfer-modified.png")));
        ImageView accountCreate = new ImageView(new Image(TellerView.class.getResourceAsStream("Image/createacc-modified.png")));
        manageUser.setFitHeight(110);
        manageUser.setFitWidth(110);
        history.setFitHeight(110);
        history.setFitWidth(110);
        accountCreate.setFitHeight(110);
        accountCreate.setFitWidth(110);
        Button  manage = new Button("manage user Acc."); //This button is to manage user account that means to search user by account number and manage their account
        Button seeHistory = new Button("See History"); //This button is to the history of ones teller
        Button account  = new Button("Create user Acc"); // This button is to create user account

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
        leftMenu.getChildren().addAll(manageUser, manage, history, seeHistory, accountCreate, account);
        allPage.getChildren().addAll(header,body);
        body.setMaxSize(BMS.scene.getWidth()*0.9,100);
        body.setMaxHeight(100);

        body.getStyleClass().add("header");
        body.setTranslateX(BMS.scene.getWidth()*0.05);
        StackPane main = new StackPane();
        main.setMinWidth(800);
        main.setMaxHeight(500);
        main.getStyleClass().add("body");
        body.getChildren().addAll(leftMenu,main);
        body.setSpacing(30);
        main.setTranslateX(100);
        if (type.equalsIgnoreCase("create"))
            main.getChildren().add(createUserAccView());
//        else if (type.equalsIgnoreCase("transfer")) {
//            text.setText("Transfer fund page");
//            main.getChildren().addAll(transferView());
//        }
//        else {
//            text.setText("Transaction history page");
//            main.getChildren().addAll(transactionView());
//        }
        return allPage;
    }
    public static GridPane createUserAccView(){
    Text createUserAccount = new Text("Create Teller Account");
    Label fname = new Label("First Name:");
    Label lblAccNumber = new Label("Account Number:");
    Label lname = new Label("Last Name:");
    Label Gender = new Label("Gender:");
    Label bdate = new Label("Birth Date:");
    Label AId = new Label("Admin ID:");
    Label email = new Label("Email");
    Label phoneNum = new Label("Phone Number");
    Label photo = new Label("Photo");
    Label regDate = new Label("Registration Date");
    Label tellerId = new Label("Teller ID");
    Label customerID = new Label("Customer ID");
    Label userName = new Label("User Name");
    Label password = new Label("Password");
    Label conPassword = new Label("Confirm Password");
    ImageView image = new ImageView(new Image(Teller.class.getResourceAsStream("Image/upload_2_30x20.png")));
    btnBrowse.setGraphic(image);// made the upload image inside the brose button
    DatePicker regdatePicker = new DatePicker();
    GridPane createUserAccountPage = new GridPane();
    createUserAccountPage.add(createUserAccount, 0, 0,2,3);
    createUserAccountPage.add(customerID, 0, 1);
    createUserAccountPage.add(txtFldID, 1, 1);
    createUserAccountPage.add(fname, 0, 2);
    createUserAccountPage.add(txFldFName, 1, 2);
    createUserAccountPage.add(lname, 0, 4);
    createUserAccountPage.add(txFldLName, 1, 4);
    createUserAccountPage.add(Gender, 0, 5);
    createUserAccountPage.add(txFldGender, 1, 5);
    createUserAccountPage.add(bdate, 0, 6);
    createUserAccountPage.add(txtFldBirthDAte, 1, 6);
    createUserAccountPage.add(lblAccNumber, 0, 7);
    createUserAccountPage.add(txtFldAccNumber, 1, 7);
    createUserAccountPage.add(email, 0, 8);
    createUserAccountPage.add(txFldEmail, 1, 8);
    createUserAccountPage.add(phoneNum, 0, 9);
    createUserAccountPage.add(txFldPhone, 1, 9);
    createUserAccountPage.add(photo, 0, 10);
    createUserAccountPage.add(btnBrowse, 1, 10);
    createUserAccountPage.add(regDate, 0, 11);
    createUserAccountPage.add(regdatePicker, 1, 11);
    createUserAccountPage.add(tellerId, 0, 12);
    createUserAccountPage.add(tellerIdTxt, 1, 12);
    createUserAccountPage.add(userName, 0, 13);
    createUserAccountPage.add(txFldUName, 1, 13);
    createUserAccountPage.add(password, 0, 14);
    createUserAccountPage.add(txFldPass, 1, 14);
    createUserAccountPage.add(conPassword, 0, 15);
    createUserAccountPage.add(txtFldConfirmPasswd, 1, 15);
    createUserAccountPage.add(create, 2, 16);
    createUserAccountPage.add(back, 1, 16);
    // here we set the space between the labels and TextFields
    ColumnConstraints column1 = new ColumnConstraints(100);// column1's width is set to 100
    ColumnConstraints column2 = new ColumnConstraints();// we let the 2nd column to fill the rest
    column2.setHgrow(Priority.ALWAYS);// let column2 grow horizontally
    createUserAccountPage.getColumnConstraints().addAll(column1, column2);//then column constraints got added
    //
    createUserAccountPage.setHgap(23);
    createUserAccountPage.setVgap(7);
        createUserAccount.setTranslateY(-55);
//    createUserAccount.setTranslateX(-100);
    createUserAccount.setFont(Font.font("verdana", FontWeight.BOLD, 33));
    //createUserAccount.getStyleClass().add("styled");
    //createUserAccount.setFill(WHITE);
    createUserAccountPage.setAlignment(Pos.TOP_CENTER);

    return createUserAccountPage;
}

}