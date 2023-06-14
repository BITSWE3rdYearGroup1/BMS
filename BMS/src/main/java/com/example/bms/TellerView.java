package com.example.bms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import static com.example.bms.UserView.btnChangePass;


public class TellerView extends EmployeeView{
    public static final TextField txtFldBalance = new TextField();
    public static TableView<User> userTableView = new TableView<>();
    public static ObservableList<User> userList = FXCollections.observableArrayList();
    public static TableColumn<User,Integer> tellerID = new TableColumn<>("Customer ID");

    public static TableColumn<User, String> firstName =  new TableColumn<>("First Name");
    public static TableColumn<User, String> secondName = new TableColumn<>("Second Name");
    public static TableColumn<User, String> gender  = new TableColumn<>("Gender");
    public static TableColumn<User, String> username = new TableColumn<>("Username");
    public static TableColumn<User, String> password = new TableColumn<>("Password");
    public static TableColumn<User, String> accountNumber = new TableColumn<>("Account Number");
    public static TableColumn<User, Double> initialBalance = new TableColumn<>("Balance");
    public static TableColumn<User, String> phone = new TableColumn<>("Phone number");
    public static  TableColumn<User, String> email = new TableColumn<>("Email");
    public static TableColumn<User, String> registrationDate = new TableColumn<>("Registration Date");
    public static TableColumn<User, Image> photo = new TableColumn<>("Photo");
    public static Button btnDeposit = new Button("Deposit  ");
    public static Button btnTransfer = new Button("Transfer");
    public static Button btnWithDraw = new Button("WithDraw ");
    public static Button btnDelete = new Button("DELETE");
    public static Button btnUpdate = new Button("UPDATE");
    public static Button btnBack = new Button("Back");
    public static Button  btnSearchUser = new Button("Search");
    public static Button btnCustomer = new Button("Customer Info");
    public static Label emailS = new Label("email");
    public static Label phoneNumber = new Label("Phone Number");
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
    public  static ImageView searchedProfile = new ImageView(new Image(Objects.requireNonNull(TellerView.class.getResourceAsStream("new.jpg"))));
    public static TellerView tellerView;
    public static Label name = new Label("Name : ");
    public static Label searchedName = new Label("Name : ");
    public static Label Ugender = new Label("Gender : ");
    public static Label searchedGender = new Label("Gender : ");
    public static Label accNumber = new Label("Account Number : ");
    public static Label searchedAccNumber = new Label("Account Number : ");
    public static Label balance = new Label("Balance : ");
    public static Label searchedBalance = new Label("Balance : ");
    public static Label searchedEmail = new Label("Email : ");
    public static Label searchedPhoneNumber = new Label("Phone number : ");


    public static void EmployeeView(){
        tellerView = new TellerView();
    }
    public static Parent tellerHome(String type) {
        ImageView manageUser = new ImageView(new Image(Objects.requireNonNull(TellerView.class.getResourceAsStream("Image/transfer-modified.png"))));
        ImageView  history= new ImageView(new Image(Objects.requireNonNull(TellerView.class.getResourceAsStream("Image/customer-modified.png"))));
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
        leftMenu.getChildren().addAll(manageUser, btnmanage, history, btnCustomer, accountCreate, btnCreateAcc);
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
        ImageView imageView = new ImageView(Teller.teller.getImage());
        imageView.setFitWidth(200);
        imageView.setFitHeight(230);
        imageView.setTranslateX(30);
        name.setText("Name : "+ Teller.teller.getFirstName() + " " + Teller.teller.getSecondName() );
        Ugender.setText("Gender : " +Teller.teller.getGender());
        emailS.setText("Yours Email : " +Teller.teller.getEmail());
        phoneNumber.setText("Phone number : " + Teller.teller.getPhone());
        rightMenu.getChildren().addAll(imageView,name,Ugender,emailS,phoneNumber,btnChangePass);
        rightMenu.setMinWidth(250);
        body.setSpacing(30);
        if (type.equalsIgnoreCase("create")) {
            main.getChildren().add(createUserAccView());
            text.setText("Add new customer");
        }else if (type.equalsIgnoreCase("manage")) {
            main.getChildren().addAll(manageUserAccount());
            text.setText("Find customer ");
        }else if (type.equalsIgnoreCase("user")) {
            main.getChildren().addAll(loadUserTableView());
            text.setText("Customer information");
        } else if (type.contains("manage")) {
            main.getChildren().add(searchedUserInfo(type));
        } else {
            text.setText("Searched customer information");
            main.getChildren().addAll(displayUserInfo(type));

        }
        body.setMinWidth(1380);
        body.setMaxWidth(1380);
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
        hBox.setAlignment(Pos.TOP_CENTER);
        return manageUserAccountContainer;
}
public static Parent loadUserTableView(){
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
        TellerView.photo.getStyleClass().add(className);
        TellerView.initialBalance.getStyleClass().add(className);
        TellerView.accountNumber.getStyleClass().add(className);
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
        TellerView.tellerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        TellerView.firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TellerView.secondName.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        TellerView.gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        TellerView.username.setCellValueFactory(new PropertyValueFactory<>("username"));
        TellerView.password.setCellValueFactory(new PropertyValueFactory<>("password"));
        TellerView.initialBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        TellerView.accountNumber.setCellValueFactory(new PropertyValueFactory<>("accNumber"));
        TellerView.email.setCellValueFactory(new PropertyValueFactory<>("email"));
        TellerView.registrationDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        TellerView.phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        TellerView.photo.setCellValueFactory(cellData -> cellData.getValue().imageProperty());
        TellerView.photo.setCellFactory(new Callback<>() {
            @Override
            public TableCell<User, Image> call(TableColumn<User, Image> param) {
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
                            imageView.setOnMouseClicked(e -> {
                                ImageView clickedImage = (ImageView) e.getSource();
                                TableCell<?, ?> clickedCell = (TableCell<?, ?>) clickedImage.getParent();
                                TableRow<?> clickedRow = (TableRow<?>) clickedCell.getParent();
                                // Get the index of the clicked row
                                int rowIndex = clickedRow.getIndex();
                                // Retrieve the corresponding object from the data source
                                User objectToDelete = TellerView.tellerView.userTableView.getItems().get(rowIndex);
                                objectToDelete = userTableView.getSelectionModel().getSelectedItem();
                                // Create and show the confirmation alert
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation");
                                alert.setHeaderText("Delete Confirmation");
                                alert.setContentText("Are you sure you want to delete this item?");
                                // Customize the buttons
                                ButtonType deleteButton = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
                                ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                                alert.getButtonTypes().setAll(deleteButton, cancelButton);
                                // Show the alert and handle the result
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.isPresent() && result.get() == deleteButton) {
                                    // User clicked "Delete"
                                    getTableView().getItems().remove(objectToDelete);
                                }  // User clicked "Cancel" or closed the alert
                                // Handle any other actions or do nothing

                            });
                        }
                    }
                };
            }
        });
        userTableView.getColumns().addAll(TellerView.tellerID,TellerView.firstName,TellerView.secondName,TellerView.gender,TellerView.username,TellerView.password,TellerView.phone, TellerView.email,initialBalance,accountNumber,TellerView.registrationDate,TellerView.photo);
        TellerView.phone.setPrefWidth(AdminView.UPdate.resize(phone.getText()));
        TellerView.firstName.setPrefWidth(AdminView.UPdate.resize(firstName.getText()));
        TellerView.secondName.setPrefWidth(AdminView.UPdate.resize(secondName.getText()));
        TellerView.gender.setPrefWidth(AdminView.UPdate.resize(gender.getText()));
        TellerView.username.setPrefWidth(AdminView.UPdate.resize(phone.getText()));
        TellerView.password.setPrefWidth(AdminView.UPdate.resize(phone.getText()));
        TellerView.email.setPrefWidth(AdminView.UPdate.resize("email.getText()"));
        TellerView.registrationDate.setPrefWidth(AdminView.UPdate.resize("registration"));
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(userTableView);
        VBox centeral = new VBox();
        centeral.setSpacing(10);
        HBox searchContainer = new HBox();
        searchContainer.setAlignment(Pos.TOP_RIGHT);
        Label enterAcc = new Label("Enter account number");
        searchContainer.getChildren().addAll(enterAcc,txtFldAccNumber,btnSearchUser);
        searchContainer.setSpacing(15);
        centeral.getChildren().addAll(searchContainer,stackPane);
        centeral.setMaxWidth(800);
        return centeral;
    }
public static Parent displayUserInfo(String type){
        VBox leftContent = new VBox();
        searchedProfile.setFitWidth(80);
        searchedProfile.setFitHeight(100);
        searchedProfile.setTranslateX(20);
        leftContent.getChildren().addAll(searchedProfile,searchedName,searchedGender,searchedAccNumber,searchedBalance);
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
    public static Parent searchedUserInfo(String type){
        VBox leftContent = new VBox();
        searchedProfile.setFitWidth(80);
        searchedProfile.setFitHeight(100);
        searchedProfile.setTranslateX(20);
        leftContent.getChildren().addAll(searchedProfile,searchedName,searchedGender,searchedAccNumber,searchedBalance,searchedEmail,searchedPhoneNumber);
        VBox rightMenu = new VBox();
        btnDelete.setMinWidth(50);
        btnUpdate.setMinWidth(50);
        btnBack.setMinWidth(50);
        leftContent.setSpacing(30);
        rightMenu.setSpacing(5);
        rightMenu.getChildren().addAll(btnDelete,btnUpdate,btnBack);
        HBox hBox = new HBox();
        StackPane centerContent  =new StackPane();
        centerContent.prefWidthProperty().bind(BMS.scene.widthProperty().multiply(0.3));
        hBox.setSpacing(30);
        rightMenu.setAlignment(Pos.TOP_RIGHT);
        leftContent.setMinWidth(600);
        hBox.getChildren().addAll(leftContent,rightMenu);
        if (type.contains("DELETE"));
//            centerContent.getChildren().add(deleteUserView());
        else if (type.contains("UPDATE"))
            centerContent.getChildren().add(transferView());
        else if (type.contains("NOTIFY"))
            centerContent.getChildren().add(withdrawView());
        return hBox;
    }

}