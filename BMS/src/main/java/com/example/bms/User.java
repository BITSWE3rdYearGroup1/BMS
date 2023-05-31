package com.example.bms;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class User {
    public static long accountNumber;
    public static int id;
    private final SimpleIntegerProperty customerID;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty secondName;
    private final SimpleStringProperty gender;
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;
    private final SimpleIntegerProperty accNumber ;
    private final SimpleDoubleProperty balance;
    private final SimpleStringProperty email;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty registrationDate;
    private final SimpleStringProperty photo;

    public User(int customerID, String firstName, String secondName, String gender, String username, String password, int accNumber, double balance, String email, String phone, String registrationDate, String photo) {
        this.customerID =new SimpleIntegerProperty(customerID);
        this.firstName = new SimpleStringProperty(firstName);
        this.secondName = new SimpleStringProperty(secondName);
        this.gender =new SimpleStringProperty( gender);
        this.username =new SimpleStringProperty( username);
        this.password = new SimpleStringProperty(password);
        this.accNumber = new SimpleIntegerProperty(accNumber);
        this.balance =new SimpleDoubleProperty( balance);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.registrationDate =new SimpleStringProperty( registrationDate);
        this.photo = new SimpleStringProperty(photo);
    }


    public  int getCustomerID() {
        return customerID.get();
    }

    public  SimpleIntegerProperty customerIDProperty() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID.set(customerID);
    }

    public  String getFirstName() {
        return firstName.get();
    }

    public  SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public  String getSecondName() {
        return secondName.get();
    }

    public  SimpleStringProperty secondNameProperty() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName.set(secondName);
    }

    public  String getGender() {
        return gender.get();
    }

    public  SimpleStringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public  String getUsername() {
        return username.get();
    }

    public  SimpleStringProperty usernameProperty() {
        return username;
    }

    public  void setUsername(String username) {
        this.username.set(username);
    }

    public  String getPassword() {
        return password.get();
    }

    public  SimpleStringProperty passwordProperty() {
        return password;
    }

    public  void setPassword(String password) {
        this.password.set(password);
    }

    public  int getAccNumber() {
        return accNumber.get();
    }

    public  SimpleIntegerProperty accNumberProperty() {
        return accNumber;
    }

    public  void setAccNumber(int accNumber) {
        this.accNumber.set(accNumber);
    }

    public  double getBalance() {
        return balance.get();
    }

    public  SimpleDoubleProperty balanceProperty() {
        return balance;
    }

    public  void setBalance(double balance) {
        this.balance.set(balance);
    }

    public  String getEmail() {
        return email.get();
    }

    public  SimpleStringProperty emailProperty() {
        return email;
    }

    public  void setEmail(String email) {
        this.email.set(email);
    }

    public  String getPhone() {
        return phone.get();
    }

    public  SimpleStringProperty phoneProperty() {
        return phone;
    }

    public  void setPhone(String phone) {
        this.phone.set(phone);
    }

    public  String getRegistrationDate() {
        return registrationDate.get();
    }

    public  SimpleStringProperty registrationDateProperty() {
        return registrationDate;
    }

    public  void setRegistrationDate(String registrationDate) {
        this.registrationDate.set(registrationDate);
    }

    public  String getPhoto() {
        return photo.get();
    }

    public  SimpleStringProperty photoProperty() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo.set(photo);
    }
}


























//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(User.class.getResource("user-view.fxml"));
//        mainmenu();
//
//
//    }
//    public  void mainmenu() throws  IOException{
//        Stage stage = new Stage();
//
//        // border pane the whole scene
//        BorderPane mother= new BorderPane();
//
//        // for the top bar title + pesonla info
//        GridPane top1=new GridPane();
//        top1.setMinSize(30,40);
//
//
//        //the title
//        Text toptitle = new Text();
//        toptitle.setText("Transaction");
//        toptitle.setFont(Font.font("Times",FontWeight.BOLD,40));
//        toptitle.setFill(Color.rgb(15,200,222));
//        toptitle.setStroke(Color.DARKBLUE);
//        top1.add(toptitle,10,0);
//
//        //the pesonal information
//        VBox infoContainer= new VBox();
//        Font font = Font.font("name", FontWeight.EXTRA_BOLD, 15);
//        Text  userName= new Text();
//        userName.setFont(font);
//        userName.setText("fikiremariyam b");
//        userName.setFill(Color.WHITE);
//        Text accountNumber = new Text() ;
//        accountNumber.setFont(font);
//        accountNumber.setText("1000183569729 ");
//        accountNumber.setFill(Color.WHITE);
//        infoContainer.getChildren().addAll(userName,accountNumber);
//        infoContainer.setTranslateX(700);
//        top1.add(infoContainer,10,0);
//        top1.setStyle("-fx-background-color: #212F3F");
//        top1.setHgap(15);
//        mother.setTop(top1);
//
//        // for the left bar
//        //vbox to contain the buttons
//        VBox sideBar = new VBox();
//        sideBar.setPadding(new Insets(10, 50, 50, 50));
//        sideBar.setSpacing(10);
//
//        sideBar.setBackground(Background.fill(Color.web("#EDD3D3")));
//        sideBar.setPrefSize(80,100);
//
//        ImageView transferImage = new ImageView(new Image(new FileInputStream("C:/Users/ezrat/Desktop/ap/BMS/BMS/src/main/resources/com/example/bms/Image/transfer-modified.png")));
//        transferImage.setFitHeight(100);
//        ImageView balanceImage = new ImageView(new Image(new FileInputStream("C:/Users/ezrat/Desktop/ap/BMS/BMS/src/main/resources/com/example/bms/Image/balance-modified.png")));
//        balanceImage.setFitHeight(100);
//        ImageView transactionImage= new ImageView(new Image(new FileInputStream("C:/Users/ezrat/Desktop/ap/BMS/BMS/src/main/resources/com/example/bms/Image/transfer-modified.png")));
//        transactionImage.setFitHeight(100);
//        Button balance = new Button("seebalance");
//        balance.setTranslateX(100);
//        balance.setOnAction( e ->{  stage.close();
//            try {
//                viewBalanace(12);
//            }
//            catch ( Exception exc){
//                System.out.println("err with the balanace button");
//            }
//          });
//        Button transfer = new Button("transferMoeny");
//        transfer.setTranslateX(100);
//        Button Transaction = new Button("seeTaransaction");
//        Transaction.setTranslateX(100);
//        Transaction.setOnAction(actionEvent ->{
//            try{
//                seeTransaction();
//                stage.close();
//            }
//            catch (Exception transactin){
//                System.out.println("err with see transaactin problem");
//            }
//        } );
//
//        sideBar.getChildren().addAll(balanceImage,balance,transferImage,transfer,transactionImage,Transaction);
//
//        mother.setLeft(sideBar);
//
//        //for the center
//        GridPane transferForm= new GridPane();
//        transferForm.setBackground(Background.fill(Color.rgb(34,20,22)));
//
//        Text amount= new Text(" amount :");
//        Text recipantAcccount = new Text("recipant account");
//        Text reason= new Text("reason :");
//        amount.setFont(font);
//        recipantAcccount.setFont(font);
//        reason.setFont(font);
//        amount.setFill(Color.WHITE);
//        recipantAcccount.setFill(Color.WHITE);
//        reason.setFill(Color.WHITE);
//
//        TextField amountField= new TextField("set amount");
//        TextField reasonFiled = new TextField("set a reason");
//        TextField recipantAccountField = new TextField("account");
//        Button next = new Button("next");
//        Button proceed = new Button("procced");
//
//
//        VBox detailOfRicipant= new VBox();
//        detailOfRicipant.setMinSize(100,200);
//        detailOfRicipant.setSpacing(10);
//        detailOfRicipant.setBackground(Background.fill(Color.WHITE));
//        Text recipantNameinfo = new Text("get name from database");
//        String recipantacccinfo = recipantAcccount.getText();
//        Text recipantacc= new Text(recipantacccinfo);
//        recipantacc.setFont(Font.font("name", FontWeight.EXTRA_BOLD, 15));
//        detailOfRicipant.getChildren().addAll(recipantNameinfo,recipantacc);
//
//
//
//        transferForm.add(recipantAcccount ,0,0);
//        transferForm.add(recipantAccountField,1,0);
//        transferForm.add(amount,0,1);
//        transferForm.add(amountField,1,1);
//        transferForm.add(reason,0,2);
//        transferForm.add(reasonFiled,1,2);
//        transferForm.add(next,0,3);
//        transferForm.addColumn(4,detailOfRicipant);
//        transferForm.add(proceed,3,3);
//
//        transferForm.setHgap(10);
//        transferForm.setVgap(10);
//        transferForm.setAlignment(CENTER);
//        mother.setCenter(transferForm);
//
//
//
//
//        Scene scene = new Scene(mother,1000,500);
//        stage.setTitle("user!");
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.show();
//
//    }
//                                        //when the proceeed button is cliked
//
//    public void confirmBox(String username,String reciveraccount,String amount,String reason ) throws IOException{
//        Label confirm=new Label("cinfirm");
//
//        Stage confirmTranasaction= new Stage();
//        confirmTranasaction.setMinWidth(300);
//        confirmTranasaction.setMinHeight(400);
//        VBox results = new VBox();
//        Label usernamrecivername= new Label(username);
//        Label amount1= new Label(amount);
//        Label reciveaccount= new Label(reciveraccount);
//        Label reason1 = new Label(reason);
//        Button ok = new Button("ok");
//        results.getChildren().addAll(usernamrecivername,reciveaccount,amount1,reason1,ok);
//        Scene scene=new Scene(results,200,200);
//        confirmTranasaction.setScene(scene);
//        confirmTranasaction.show();
//    }
//                        //the see balance box
//    public void viewBalanace(double useraccount) throws IOException
//    {
//        Stage viewbalance = new Stage();
//        viewbalance.initStyle(StageStyle.UNDECORATED);
//        viewbalance.setTitle( "balance");
//        Font font = Font.font("name", FontWeight.EXTRA_BOLD, 25);
//        Label confirm =new Label("balance");
//        confirm.setFont(font);
//        VBox results = new VBox();
//        results.setBackground(Background.fill(Color.web("#EDD3D3")));
//        results.setAlignment(CENTER);
//        Label usernamrecivername= new Label("username");
//        usernamrecivername.setFont(font);
//        Button back = new Button("back");
//        back.setOnAction( actionEvent  -> {
//            try {
//                mainmenu();
//                viewbalance.close();
//
//            }
//        catch (Exception e){
//            System.out.println("error happend");
//            }
//        });
//        Button exit= new Button("close");
//        exit.setOnAction(actionEvent -> { System.exit(0);});
//        exit.setTranslateX(50);
//        exit.setTranslateY(-25);
//
//        results.getChildren().addAll(usernamrecivername,confirm,back,exit);
//        Scene scene=new Scene(results,400,200);
//        viewbalance.setScene(scene);
//
//
//        viewbalance.initModality(Modality.WINDOW_MODAL);
//
//        viewbalance.showAndWait();
//    }
//    public void seeTransaction() throws  IOException{
//        Stage stage = new Stage();
//        BorderPane mother= new BorderPane();
//
//        // for the top bar title + pesonla info
//
//        GridPane top1=new GridPane();
//        top1.setMinSize(30,40);
//
//
//        //the title
//        Text toptitle = new Text();
//        toptitle.setText("Transaction");
//        toptitle.setFont(Font.font("Times",FontWeight.BOLD,40));
//        toptitle.setFill(Color.rgb(15,200,222));
//        toptitle.setStroke(Color.DARKBLUE);
//        top1.add(toptitle,10,0);
//
//        //the pesonal information
//        VBox infoContainer= new VBox();
//        Font font = Font.font("name", FontWeight.EXTRA_BOLD, 15);
//        Text  userName= new Text();
//        userName.setFont(font);
//        userName.setText("fikiremariyam b");
//        userName.setFill(Color.WHITE);
//        Text accountNumber = new Text() ;
//        accountNumber.setFont(font);
//        accountNumber.setText("1000183569729 ");
//        accountNumber.setFill(Color.WHITE);
//        infoContainer.getChildren().addAll(userName,accountNumber);
//        infoContainer.setTranslateX(700);
//        top1.add(infoContainer,10,0);
//        top1.setStyle("-fx-background-color: #212F3F");
//        top1.setHgap(15);
//        mother.setTop(top1);
//
//        // for the left bar
//        //vbox to contain the buttons
//        VBox sideBar = new VBox();
//        sideBar.setPadding(new Insets(10, 50, 50, 50));
//        sideBar.setSpacing(10);
//
//        sideBar.setBackground(Background.fill(Color.web("#EDD3D3")));
//        sideBar.setPrefSize(80,100);
//
//        ImageView transferImage = new ImageView(new Image(new FileInputStream("C:/Users/ezrat/Desktop/ap/BMS/BMS/src/main/resources/com/example/bms/Image/transfer-modified.png")));
//        transferImage.setFitHeight(100);
//        ImageView balanceImage = new ImageView(new Image(new FileInputStream("C:/Users/ezrat/Desktop/ap/BMS/BMS/src/main/resources/com/example/bms/Image/balance-modified.png")));
//        balanceImage.setFitHeight(100);
//        ImageView transactionImage= new ImageView(new Image(new FileInputStream("C:/Users/ezrat/Desktop/ap/BMS/BMS/src/main/resources/com/example/bms/Image/transfer-modified.png")));
//        transactionImage.setFitHeight(100);
//        Button balance = new Button("seebalance");
//        balance.setTranslateX(100);
//        balance.setOnAction( e ->{  stage.close();
//            try {
//                viewBalanace(12);
//            }
//            catch ( Exception exc){
//                System.out.println("err with the balanace button");
//            }
//        });
//        Button transfer = new Button("transferMoeny");
//        transfer.setTranslateX(100);
//        Button Transaction = new Button("seeTaransaction");
//        Transaction.setTranslateX(100);
//        Transaction.setOnAction(actionEvent -> {
//                    try {
//                        seeTransaction();
//                        stage.close();
//                    } catch (Exception transactin) {
//                        System.out.println("err with see transaactin problem");
//                    }
//                });
//
//        sideBar.getChildren().addAll(balanceImage,balance,transferImage,transfer,transactionImage,Transaction);
//
//        mother.setLeft(sideBar);
//
//        //for the center
//        GridPane transferForm= new GridPane();
//        transferForm.setBackground(Background.fill(Color.rgb(34,20,22)));
//
//        Text amount= new Text(" amount :");
//        Text recipantAcccount = new Text("recipant account");
//        Text reason= new Text("reason :");
//        amount.setFont(font);
//        recipantAcccount.setFont(font);
//        reason.setFont(font);
//        amount.setFill(Color.WHITE);
//        recipantAcccount.setFill(Color.WHITE);
//        reason.setFill(Color.WHITE);
//
//        TextField amountField= new TextField("set amount");
//        TextField reasonFiled = new TextField("set a reason");
//        TextField recipantAccountField = new TextField("account");
//        Button next = new Button("next");
//        Button proceed = new Button("procced");
//
//
//        VBox detailOfRicipant= new VBox();
//        detailOfRicipant.setMinSize(100,200);
//        detailOfRicipant.setSpacing(10);
//        detailOfRicipant.setBackground(Background.fill(Color.WHITE));
//        Text recipantNameinfo = new Text("get name from database");
//        String recipantacccinfo = recipantAcccount.getText();
//        Text recipantacc= new Text(recipantacccinfo);
//        recipantacc.setFont(Font.font("name", FontWeight.EXTRA_BOLD, 15));
//        detailOfRicipant.getChildren().addAll(recipantNameinfo,recipantacc);
//
//        transferForm.add(recipantAcccount ,0,0);
//        transferForm.add(recipantAccountField,1,0);
//        transferForm.add(amount,0,1);
//        transferForm.add(amountField,1,1);
//        transferForm.add(reason,0,2);
//        transferForm.add(reasonFiled,1,2);
//        transferForm.add(next,0,3);
//        transferForm.addColumn(4,detailOfRicipant);
//        transferForm.add(proceed,3,3);
//
//        transferForm.setHgap(10);
//        transferForm.setVgap(10);
//        transferForm.setAlignment(CENTER);
//        mother.setCenter(transferForm);
//
//
//        Scene scene = new Scene(mother,1000,500);
//        stage.setTitle("user!");
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.show();
//    }
//
//
//
//    public  void main(String[] args) {
//        launch();
//    }
//}
