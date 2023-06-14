package com.example.bms;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import static com.example.bms.AdminView.*;
import static com.example.bms.TellerView.*;
import static com.example.bms.UserView.*;

public class BMS extends Application {
    public static Statement statement ;
    public static Stage stage  ;
    public static  Connection connection;
    public static boolean isLogin = true;
    public static Scene scene = new Scene(new Group() , 1400, 650);
     public static ImageView logoutImage = new ImageView(new Image(Objects.requireNonNull(BMS.class.getResourceAsStream("Image/logout.png"))));
    public  static ImageView homePageImage = new ImageView(new Image(Objects.requireNonNull(BMS.class.getResourceAsStream("Image/home.png"))));

    @Override
    public void start(Stage stage) {
        BMS.stage = stage;
        scene.getStylesheets().add("css/boostrap.css");
        if (isLogin) {
            BMS.stage = new Stage();
        }
            show(loginPage(),stage);
            //This method is to call it when  we need to navigate from one page to other page
        //rather than call start method this means initiate the app again if we call start

    }
    //This method is to call it when  we need to navigate from one page to other page
    //rather than call start method this means initiate the app again if we call start
    public static void show(Parent parent,Stage stage){
        GridPane main = new GridPane();
        Text text = new Text("Bank Management System"); //main
        text.setFont(Font.font("verdana", FontWeight.BOLD,34));
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setTextAlignment(TextAlignment.JUSTIFY);
        main.getStyleClass().add("bg-primary");
        Button logoutButton = new Button("Logout");
        ImageView logo = new ImageView(new Image(Objects.requireNonNull(BMS.class.getResourceAsStream("Image/banking.png"))));
        HBox header = new HBox();
        logoutImage.setFitWidth(50);
        logoutImage.setFitHeight(50);
        header.getChildren().add(logo);
        header.getChildren().add(text);
        VBox all = new VBox();
        all.setTranslateY(40);
        all.setStyle("-fx-background-color: #000000");
        all.getChildren().addAll(header,parent);
        scene = new Scene(all, scene.getWidth(), scene.getHeight());
        header.spacingProperty().bind(scene.widthProperty().divide(5));
        stage.setScene(scene);
        scene.getStylesheets().add(String.valueOf(BMS.class.getResource("styles.css")));
        if (isLogin){
            header.getChildren().add(logoutImage);
        }
        stage.show();

    }
    public static void main(String[] args) {
        launch();
    }
    public Parent loginPage(){
        isLogin = false;
        GridPane loginContainer = new GridPane(); // this gridpane is Login information container
        Text welcometext = new Text("Welcome"); // This text to show welcome message
        ImageView username = new ImageView(new Image(Objects.requireNonNull(BMS.class.getResourceAsStream("Image/user.png"))));
        ImageView password = new ImageView(new Image(Objects.requireNonNull(BMS.class.getResourceAsStream("Image/lock.png"))));
        Label errorTxt = new Label("Wrong Username or Password");
        Button login = new Button("Login"); // This Button is to login
        Hyperlink forgotPassword = new Hyperlink("Forgot password?"); // this hyper link is for forgot password
        username.setTranslateX(15);
        username.setTranslateY(-5);
        password.setTranslateX(15);
        password.setTranslateY(-5);
        TextField usernametextfield = new TextField(); // This text field is to username
        TextField passwordtextfield = new TextField(); // This text field is to password
        usernametextfield.setPromptText("User Name");
        passwordtextfield.setPromptText("Password");
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
        loginContainer.setHgap(23);
        loginContainer.setVgap(7);
        loginContainer.setMaxWidth(400);
        loginContainer.setMaxHeight(400);
        loginContainer.setTranslateX(scene.getWidth()*0.2);
        loginContainer.setTranslateY(scene.getWidth()*0.1);
        loginContainer.setAlignment(Pos.CENTER);
        VBox mainContainer = new VBox();
        mainContainer.getChildren().addAll(loginContainer);
        mainContainer.setPrefSize(190,650);
        mainContainer.getStyleClass().add("vBox");
        mainContainer.setStyle("-fx-background-color:white");
        login.setOnAction(e->{
            try {
                initializeDatabase();
                String str = usernametextfield.getText();
                if (str.contains("teller")) {
                    show(tellerPage(stage), stage);
                } else if (str.contains("admin")) {
                    show(adminPage(stage), stage);
                } else if (UserController.checkUser(passwordtextfield.getText(), usernametextfield.getText())) {
                    show(customerPage(stage), stage);
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Wrong login");
                    alert.setTitle("Login alert");
                    alert.setContentText("Wrong password or username");
                    alert.showAndWait();
                }
                } catch (SQLException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
        });
        return mainContainer;
    }
    public  Parent adminPage(Stage stage){
        Text welcometext = new Text("Admin Page");
        ImageView tellerImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("Image/teller-modified.png")))); // This is to teller image
        ImageView accountImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("Image/account-modified.png"))));
        homePageImage.setFitHeight(60);
        homePageImage.setFitWidth(60);
        tellerImage.setFitWidth(110);
        accountImage.setFitWidth(110);
        tellerImage.setFitHeight(110);
        accountImage.setFitHeight(110);
        VBox leftMenu = new VBox();
        leftMenu.getChildren().addAll(tellerImage,teller,accountImage,btnAccount);
        leftMenu.getStyleClass().add("left");
        Button account  = new Button("Create Teller Acc"); //This button is to create teller account
        welcometext.setFill(Color.color(1,1,1));
        welcometext.setFont(Font.font("verdana",FontWeight.BOLD,33));
        teller.setFont(Font.font("verdana",FontWeight.BOLD,14));
        account.setFont(Font.font("verdana",FontWeight.BOLD,14));
        StackPane header = new StackPane();
        header.setMaxSize(scene.getWidth()*0.9,300);
        header.setMinHeight(100);
        header.setTranslateX(scene.getWidth()*0.05);
        header.getStyleClass().add("header");
        header.getChildren().add(welcometext);
        HBox mainContainer = new HBox();
        mainContainer.setMaxWidth(scene.getWidth()*0.9);
        mainContainer.setTranslateX(scene.getWidth()*0.05);
        System.out.println(mainContainer.getPrefWidth());
        mainContainer.getStyleClass().add("home-page");
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setTickLabelFill(Color.WHITE); // Set x-axis number text color to white
        yAxis.setTickLabelFill(Color.WHITE);
        xAxis.setLabel("Date");
        yAxis.setLabel("Cumulative Amount");
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Bank Transaction History");
        lineChart.setPrefSize(800, 600);
        XYChart.Series<Number, Number> depositSeries = new XYChart.Series<>();
        depositSeries.setName("Deposits");
        XYChart.Series<Number, Number> transferSeries = new XYChart.Series<>();
        transferSeries.setName("Transfers");
        XYChart.Series<Number, Number> withdrawalSeries = new XYChart.Series<>();
        withdrawalSeries.setName("Withdrawals");
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT transaction_type, amount, transaction_date FROM tbl_transaction");
            double depositTotal = 0.0;
            double transferTotal = 0.0;
            double withdrawalTotal = 0.0;
            while (rs.next()) {
                String type = rs.getString("transaction_type");
                double amount = rs.getDouble("amount");
                LocalDate transactionDate = rs.getDate("transaction_date").toLocalDate();
                if (type.equalsIgnoreCase("deposit")) {
                    depositTotal += amount;
                } else if (type.equalsIgnoreCase("transfer")) {
                    transferTotal += amount;
                } else if (type.equalsIgnoreCase("withdraw")) {
                    withdrawalTotal += amount;
                }
                long epochDay = transactionDate.toEpochDay();
                depositSeries.getData().add(new XYChart.Data<>(epochDay, depositTotal));
                transferSeries.getData().add(new XYChart.Data<>(epochDay, transferTotal));
                withdrawalSeries.getData().add(new XYChart.Data<>(epochDay, withdrawalTotal));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<XYChart.Series<Number, Number>> data = FXCollections.observableArrayList();
        data.addAll(depositSeries, transferSeries, withdrawalSeries);
        lineChart.setAnimated(true); // Set the legend text color to black
        lineChart.getData().addAll(data);
        mainContainer.getChildren().addAll(leftMenu,lineChart);
        mainContainer.setSpacing(20);
        mainContainer.setMinWidth(550);
        VBox adminContiner = new VBox();
        adminContiner.getChildren().addAll(header,mainContainer);
        adminContiner.getStyleClass().add("vBox");
        btnAccount.setOnAction(e->{
            show(AdminController.createTellerAcc(),BMS.stage);
                }
        );
        teller.setOnAction(e->{
            show( AdminController.seeTellerInfo(),BMS.stage);
        });
        return adminContiner;
    }

    public  Parent tellerPage(Stage stage){
        Text welcometext = new Text("Teller Page");
        ImageView manageUserImage = new ImageView(new Image(getClass().getResourceAsStream("Image/transfer-modified.png")));
        ImageView  historyImage= new ImageView(new Image(getClass().getResourceAsStream("Image/transfer-modified.png")));
        ImageView accountCreateImage = new ImageView(new Image(getClass().getResourceAsStream("Image/createacc-modified.png")));
        manageUserImage.setFitHeight(110);
        historyImage.setFitHeight(110);
        accountCreateImage.setFitHeight(110);
        accountCreateImage.setFitWidth(110);
        historyImage.setFitWidth(110);
        manageUserImage.setFitWidth(110);
        homePageImage.setFitHeight(60);
        homePageImage.setFitWidth(60);
        VBox leftMenu = new VBox();
        leftMenu.getStyleClass().add("left");
        leftMenu.getChildren().addAll(manageUserImage, btnmanage, historyImage, btnCustomer, accountCreateImage, TellerView.btnCreateAcc);
        GridPane customerPage = new GridPane(); //This grid pane to contain the costumer page element
        welcometext.setFill(Color.color(1,1,1));
        welcometext.setFont(Font.font("verdana",FontWeight.BOLD,33));
        StackPane header = new StackPane();
        header.setMaxSize(scene.getWidth()*0.9,300);
        header.setMinHeight(100);
        header.setTranslateX(scene.getWidth()*0.05);
        customerPage.setMaxWidth(400);
        header.setStyle("-fx-background-color: #1b324c");
        header.getChildren().addAll(homePageImage,welcometext);
//        CategoryAxis xAxis = new CategoryAxis();
//        NumberAxis yAxis = new NumberAxis();
//        xAxis.setTickLabelFill(Color.WHITE); // Set x-axis number text color to white
//        yAxis.setTickLabelFill(Color.WHITE);
//        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
//        barChart.setTitle("Customer Transaction Summary");
//        XYChart.Series<String, Number> depositSeries = new XYChart.Series<>();
//        depositSeries.setName("Deposits");
//
//        XYChart.Series<String, Number> transferSeries = new XYChart.Series<>();
//        transferSeries.setName("Transfers");
//
//        XYChart.Series<String, Number> withdrawalSeries = new XYChart.Series<>();
//        withdrawalSeries.setName("Withdrawals");
//        try {
//            String query = "SELECT transaction_type, SUM(amount) FROM tbl_transaction GROUP BY transaction_type";
//            PreparedStatement stmt = connection.prepareStatement(query);
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                String transaction_type = rs.getString("transaction_type");
//                double amount = rs.getDouble("SUM(amount)");
//
//                if (transaction_type.equalsIgnoreCase("deposit")) {
//                    depositSeries.getData().add(new XYChart.Data<>(transaction_type, amount));
//                } else if (transaction_type.equalsIgnoreCase("transfer")) {
//                    transferSeries.getData().add(new XYChart.Data<>(transaction_type, amount));
//                } else if (transaction_type.equalsIgnoreCase("withdraw")) {
//                    withdrawalSeries.getData().add(new XYChart.Data<>(transaction_type, amount));
//                }
//            }
//
//            rs.close();
//            stmt.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList();
//        data.addAll( withdrawalSeries, transferSeries,depositSeries );
//
//        barChart.getData().addAll(data);
        HBox mainContainer = new HBox();
        mainContainer.setMaxWidth(scene.getWidth()*0.9);
        mainContainer.setTranslateX(scene.getWidth()*0.05);
        mainContainer.getChildren().addAll(leftMenu);
        mainContainer.setSpacing(20);
        mainContainer.setMinHeight(400);
        mainContainer.getStyleClass().add("home-page");
        VBox stack = new VBox();
        stack.getChildren().addAll(header,mainContainer);
        VBox vBox = new VBox();
        homePageImage.translateXProperty().bind(header.widthProperty().divide(-2.2));
        vBox.getChildren().addAll(stack);
        vBox.getStyleClass().add("vBox");
        UserView.btnChangePass.setOnAction(e->{
            show(UserController.changeYourPassword(),stage);
        });
        logoutImage.setOnMouseClicked(e->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout");
            alert.setHeaderText("Confirmation");
            alert.setContentText("Are you sure you want to logout?");
            alert.getDialogPane().getStyleClass().add("my-dialog");
            alert.getDialogPane().setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, null)));
            ButtonType buttonTypeOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
            alert.setOnCloseRequest(event -> {
                if (alert.getResult() == buttonTypeOK) {
                    // Perform logout actions here
                    show(loginPage(),stage);
                } else {
                    // User clicked Cancel or closed the dialog
                    event.consume(); // Prevent closing the application or any other unwanted behavior
                }
            });
        });
        homePageImage.setOnMouseClicked(e->{
            show(customerPage(stage),stage);
        });
        btnCustomer.setOnAction(e->{
            show(TellerController.seeUserInfo(),stage);
        });
        stack.setMaxWidth(850);
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
        balanceImage.setFitHeight(110);
        transferImage.setFitHeight(110);
        transactionImage.setFitHeight(110);
        transactionImage.setFitWidth(110);
        balanceImage.setFitWidth(110);
        transferImage.setFitWidth(110);
        homePageImage.setFitHeight(60);
        homePageImage.setFitWidth(60);
        VBox leftMenu = new VBox();
        leftMenu.getStyleClass().add("left");
        leftMenu.getChildren().addAll(balanceImage, balancebtn, transferImage, transferbtn, transactionImage, transactionbtn);
        GridPane customerPage = new GridPane(); //This grid pane to contain the costumer page element
        welcometext.setFill(Color.color(1,1,1));
        welcometext.setFont(Font.font("verdana",FontWeight.BOLD,33));
        StackPane header = new StackPane();
        header.setMaxSize(scene.getWidth()*0.9,300);
        header.setMinHeight(100);
        header.setTranslateX(scene.getWidth()*0.05);
        customerPage.setMaxWidth(400);
        header.setStyle("-fx-background-color: #1b324c");
        header.getChildren().addAll(homePageImage,welcometext);
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setTickLabelFill(Color.WHITE); // Set x-axis number text color to white
        yAxis.setTickLabelFill(Color.WHITE);
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Customer Transaction Summary");

        XYChart.Series<String, Number> depositSeries = new XYChart.Series<>();
        depositSeries.setName("Deposits");

        XYChart.Series<String, Number> transferSeries = new XYChart.Series<>();
        transferSeries.setName("Transfers");

        XYChart.Series<String, Number> withdrawalSeries = new XYChart.Series<>();
        withdrawalSeries.setName("Withdrawals");
        try {
            String query = "SELECT transaction_type, SUM(amount) FROM tbl_transaction WHERE customer_id = ? GROUP BY transaction_type";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, User.user.getCustomerID());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String transaction_type = rs.getString("transaction_type");
                double amount = rs.getDouble("SUM(amount)");

                if (transaction_type.equalsIgnoreCase("deposit")) {
                    depositSeries.getData().add(new XYChart.Data<>(transaction_type, amount));
                } else if (transaction_type.equalsIgnoreCase("transfer")) {
                    transferSeries.getData().add(new XYChart.Data<>(transaction_type, amount));
                } else if (transaction_type.equalsIgnoreCase("withdraw")) {
                    withdrawalSeries.getData().add(new XYChart.Data<>(transaction_type, amount));
                }
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList();
        data.addAll( withdrawalSeries, transferSeries,depositSeries );

        barChart.getData().addAll(data);

        HBox mainContainer = new HBox();
        mainContainer.setMaxWidth(scene.getWidth()*0.9);
        mainContainer.setTranslateX(scene.getWidth()*0.05);
        mainContainer.setStyle("-fx-background-color:#082c54");
        mainContainer.getChildren().addAll(leftMenu,barChart);
        mainContainer.setSpacing(20);
        mainContainer.setMinHeight(400);
        VBox stack = new VBox();
        stack.getChildren().addAll(header,mainContainer);
        VBox vBox = new VBox();
        homePageImage.translateXProperty().bind(header.widthProperty().divide(-2.2));
        vBox.getChildren().addAll(stack);
        vBox.getStyleClass().add("vBox");
        UserView.btnChangePass.setOnAction(e->{
            show(UserController.changeYourPassword(),stage);
        });
        logoutImage.setOnMouseClicked(e->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Logout alert");
            alert.setContentText( "Are you sure you want to logut");
            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional<?>) result).isPresent() && result.get() == ButtonType.OK) {
                show(loginPage(),stage);
            }
        });
        homePageImage.setOnMouseClicked(e->{
            show(customerPage(stage),stage);
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
            show(UserController.seeTransaction(),stage);
        });
        stack.setMaxWidth(850);
        return vBox;
    }

    public  static  void initializeDatabase() throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
         connection = DriverManager.getConnection("jdbc:mysql://localhost/bms","eziraa","1234");
         statement = connection.createStatement();
//
    }
}