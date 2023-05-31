package com.example.bms;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

//package com.example.bms;
//
//import javafx.beans.property.IntegerProperty;
//import javafx.beans.property.SimpleIntegerProperty;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.scene.control.*;
//import javafx.scene.layout.ColumnConstraints;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.Priority;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.stage.FileChooser;
//
//import java.io.File;
//import java.util.Date;
//
//public
public class Teller extends Employee{

    private final SimpleIntegerProperty BranchID;

    public Teller(int id, String firstName, String secondName, String gender, String username, String password, String phone, String email, String registrationDate, String photo,int BranchID) {
        super(id,  firstName,  secondName,  gender,  username,  password,  phone,  email,  registrationDate,  photo);
        this.BranchID = new SimpleIntegerProperty(BranchID);
    }

    public int getBranchID() {
        return BranchID.get();
    }

    public SimpleIntegerProperty branchIDProperty() {
        return BranchID;
    }

    public void setBranchID(int branchID) {
        this.BranchID.set(branchID);
    }



}

//    public SimpleIntegerProperty id;
//    public SimpleStringProperty firstName;
//    public SimpleStringProperty secondName;
//    public SimpleStringProperty gender;
//    public SimpleStringProperty username;
//    public SimpleStringProperty password;
//    public SimpleStringProperty phone;
//    public SimpleStringProperty email;
//    public Date registrationDate;
//    public SimpleStringProperty photo;
//    public SimpleStringProperty BranchID;
//    Color WHITE = Color.WHITE;
//    BMS bms = new BMS();
//
//    public Teller(int id, String firstName, String secondName, String gender, String username, String password, String phone, String email, String registrationDate, String photo) {
//        this.id = new SimpleIntegerProperty(id);
//        this.firstName = new SimpleStringProperty( firstName);
//        this.secondName =new SimpleStringProperty(  secondName);
//        this.gender = new SimpleStringProperty( gender);
//        this.username =new SimpleStringProperty(  username);
//        this.password = new SimpleStringProperty( password);
//        this.phone = new SimpleStringProperty( phone);
//        this.email = new SimpleStringProperty(  email);
////        this.registrationDate = registrationDate;
//        this.photo =new SimpleStringProperty(  photo);
//    }
//
//    public Teller() {
//        //
//    }
//
//    public  void add() {
//          TableView<Teller> tellerTableView = new TableView<Teller>();
//         TableColumn<Teller, Integer> tellerID = new TableColumn<>("id");
//        TableColumn<Teller, String> firstName =  new TableColumn<>("firstName");
//         TableColumn<Teller, String> secondName = new TableColumn<>("teller ID");
//        TableColumn<Teller, String> gender  = new TableColumn<>("teller ID");
//        TableColumn<Teller, String> username = new TableColumn<>("teller ID");
//         TableColumn<Teller, String> password =  new TableColumn<>("teller ID");
//       TableColumn<Teller, String> phone =  new TableColumn<>("teller ID");
//         TableColumn<Teller, String> email =  new TableColumn<>("teller ID");
//         TableColumn<Teller, Date> registrationDate =  new TableColumn<>("teller ID");
//         TableColumn<Teller, String> photo =  new TableColumn<>("teller ID");
//         TableColumn<Teller, Integer> branchID =  new TableColumn<>("teller ID");
//
//        ObservableList<Teller> tellerList = FXCollections.observableArrayList();
//
//    }
////    public GridPane createUserAccountPage(Stage stage) {
////        Text createUserAccount = new Text("Create Teller Account");
////        Label fname = new Label("First Name:");
////        fname.setTextFill(WHITE);
////        Label mname = new Label("Middle Name:");
////        Label lname = new Label("Last Name:");
////        Label Gender = new Label("Gender:");
////        Label bdate = new Label("Birth Date:");
////        Label AId = new Label("Admin ID:");
////        Label email = new Label("Email");
////        Label phoneNum = new Label("Phone Number");
////        Label photo = new Label("Photo");
////        Label regDate = new Label("Registration Date");
////        Label tellerId = new Label("Teller ID");
////        Label userName = new Label("User Name");
////        Label password = new Label("Password");
////        Label conPassword = new Label("Confirm Password");
////        TextField fnameTxt = new TextField();
////        TextField mnameTxt = new TextField();
////        TextField lnameTxt = new TextField();
////        TextField genderTxt = new TextField();
////        TextField bdateTxt = new TextField();
////        TextField AIdTxt = new TextField();
////        TextField emailTxt = new TextField();
////        TextField phoneNumTxt = new TextField();
////        Button browse = new Button("");
////        ImageView image = new ImageView(new Image(getClass().getResourceAsStream("Image/img.png")));
////        browse.setGraphic(image);// made the upload image inside the brose button
////
////        browse.setOnAction(e -> {
////            FileChooser fileChooser = new FileChooser();
////            fileChooser.setTitle("Choose Photo");
////            File initialDirectory = new File(System.getProperty("user.home"));
////            fileChooser.setInitialDirectory(initialDirectory);
////            FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter(
////                    "Image Files", "*.jpg", "*.jpeg", "*.png");
////            fileChooser.getExtensionFilters().add(imageFilter);
////            File selectedFile = fileChooser.showOpenDialog(stage);
////            if (selectedFile != null) {
////                System.out.println("Selected photo: " + selectedFile.getAbsolutePath());
////            }
////        });
////
////        DatePicker regdatePicker = new DatePicker();
////        TextField tellerIdTxt = new TextField();
////        TextField userNameTxt = new TextField();
////        PasswordField passwordTxt = new PasswordField();
////        PasswordField conPasswordTxt = new PasswordField();
////        Button create = new Button("Create");
////        Button back = new Button("Back");
////        back.setOnAction(e -> {
////            bms.show(bms.tellerPage(stage), stage);
////        });
////        GridPane createUserAccountPage = new GridPane();
////        createUserAccountPage.add(createUserAccount, 0, 0);
////        createUserAccountPage.add(fname, 0, 2);
////        createUserAccountPage.add(fnameTxt, 1, 2);
////        createUserAccountPage.add(lname, 0, 4);
////        createUserAccountPage.add(lnameTxt, 1, 4);
////        createUserAccountPage.add(mname, 0, 3);
////        createUserAccountPage.add(mnameTxt, 1, 3);
////        createUserAccountPage.add(genderTxt, 1, 5);
////        createUserAccountPage.add(Gender, 0, 5);
////        createUserAccountPage.add(bdate, 0, 6);
////        createUserAccountPage.add(bdateTxt, 1, 6);
////        createUserAccountPage.add(AId, 0, 7);
////        createUserAccountPage.add(AIdTxt, 1, 7);
////        createUserAccountPage.add(email, 0, 8);
////        createUserAccountPage.add(emailTxt, 1, 8);
////        createUserAccountPage.add(phoneNum, 0, 9);
////        createUserAccountPage.add(phoneNumTxt, 1, 9);
////        createUserAccountPage.add(photo, 0, 10);
////        createUserAccountPage.add(browse, 1, 10);
////        createUserAccountPage.add(regDate, 0, 11);
////        createUserAccountPage.add(regdatePicker, 1, 11);
////        createUserAccountPage.add(tellerId, 0, 12);
////        createUserAccountPage.add(tellerIdTxt, 1, 12);
////        createUserAccountPage.add(userName, 0, 13);
////        createUserAccountPage.add(userNameTxt, 1, 13);
////        createUserAccountPage.add(password, 0, 14);
////        createUserAccountPage.add(passwordTxt, 1, 14);
////        createUserAccountPage.add(conPassword, 0, 15);
////        createUserAccountPage.add(conPasswordTxt, 1, 15);
////        createUserAccountPage.add(create, 2, 16);
////        createUserAccountPage.add(back, 1, 16);
////        // here we set the space between the labels and TextFields
////        ColumnConstraints column1 = new ColumnConstraints(100);// column1's width is set to 100
////        ColumnConstraints column2 = new ColumnConstraints();// we let the 2nd column to fill the rest
////        column2.setHgrow(Priority.ALWAYS);// let column2 grow horizontally
////        createUserAccountPage.getColumnConstraints().addAll(column1, column2);//then column constraints got added
////        //
////        createUserAccountPage.setHgap(23);
////        createUserAccountPage.setVgap(7);
////        createUserAccount.setTranslateY(-30);
////        createUserAccount.setTranslateX(100);
////        createUserAccount.setFont(Font.font("verdana", FontWeight.BOLD, 33));
////        createUserAccount.getStyleClass().add("styled");
////        createUserAccount.setFill(WHITE);
////        return createUserAccountPage;
////
////    }
////
////    public GridPane findCustomersAccount(Stage stage) {
////        Text findAccount = new Text("Find Customer's Account");
////        Label accNum = new Label("Account number: ");
////        TextField accnumTxt = new TextField();
////        Button browse = new Button("Browse");
////        Button back = new Button("Back");
////        Label errorTxt = new Label("NO Bank Account Found!");
////        errorTxt.setTextFill(Color.color(1,0,0));
////        back.setOnAction(e -> {
////            bms.show(bms.tellerPage(stage), stage);
////        });
////        GridPane findAccPage = new GridPane();
////        findAccPage.add(findAccount, 0, 0);
////        findAccPage.add(accNum, 0, 2);
////        findAccPage.add(accnumTxt, 1, 2);
////        findAccPage.add(browse, 2, 2);
////        browse.setOnAction(e -> {
////            String str = new String();
////            str = accnumTxt.getText();
////            if (str.contains("1000"))
////                bms.show(manageCustomersAccount(stage), stage);
////            else
////                findAccPage.add(errorTxt,1,7);
////        });
////        findAccPage.add(back, 0, 6);
////
////        findAccPage.setHgap(23);
////        findAccPage.setVgap(7);
////        findAccount.setTranslateY(-30);
////        findAccount.setTranslateX(100);
////        findAccount.setFont(Font.font("verdana", FontWeight.BOLD, 33));
////        findAccount.getStyleClass().add("styled");
////        findAccount.setFill(WHITE);
////
////
////        ColumnConstraints column1 = new ColumnConstraints(100);
////        ColumnConstraints column2 = new ColumnConstraints();
////        column2.setHgrow(Priority.ALWAYS);
////        findAccPage.getColumnConstraints().addAll(column1, column2);
////
////        return findAccPage;
////
////    }
////    public GridPane manageCustomersAccount(Stage stage) {
////        Text manageAccount = new Text("Manage customer's Account");
////        Button withdraw = new Button("Withdraw");
////        Button transfer = new Button("Trasfer");
////        Button deposite = new Button("Deposite");;
////        TextArea accDetailTxt = new TextArea();
////        Button back = new Button("Back");
////        back.setOnAction(e -> {
////            bms.show(findCustomersAccount(stage), stage);
////        });
////        GridPane manageAccountPage = new GridPane();
////        manageAccountPage.add(manageAccount, 0, 0);
////        manageAccountPage.add(withdraw, 0, 2);
////        manageAccountPage.add(deposite, 1, 2);
////        manageAccountPage.add(transfer, 2, 2);
////        manageAccountPage.add(accDetailTxt, 0, 4);
////        manageAccountPage.add(back, 0, 6);
////
////        manageAccountPage.setHgap(23);
////        manageAccountPage.setVgap(7);
////        manageAccount.setTranslateY(-30);
////        manageAccount.setTranslateX(80);
////        manageAccount.setFont(Font.font("verdana", FontWeight.BOLD, 33));
////        manageAccount.getStyleClass().add("styled");
////        manageAccount.setFill(WHITE);
////
////
////        ColumnConstraints column1 = new ColumnConstraints(70);
////        ColumnConstraints column2 = new ColumnConstraints(70);
////        ColumnConstraints column3 = new ColumnConstraints();
////        column3.setHgrow(Priority.ALWAYS);
////        manageAccountPage.getColumnConstraints().addAll(column1, column2,column3);
////
////        return manageAccountPage;
////
////    }
//
//}

