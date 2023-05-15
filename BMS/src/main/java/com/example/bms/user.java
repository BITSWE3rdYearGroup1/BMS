package com.example.bms;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import  javafx.scene.control.Alert.AlertType;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.HPos;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import static javafx.geometry.HPos.RIGHT;
import static javafx.geometry.Pos.CENTER;
import static javafx.scene.layout.GridPane.setHalignment;

public class user extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(user.class.getResource("user-view.fxml"));
        mainmenu();


    }
    public  void mainmenu() throws  IOException{
        Stage stage = new Stage();

        // border pane the whole scene
        BorderPane mother= new BorderPane();

        // for the top bar title + pesonla info
        GridPane top1=new GridPane();
        top1.setMinSize(30,40);


        //the title
        Text toptitle = new Text();
        toptitle.setText("Transaction");
        toptitle.setFont(Font.font("Times",FontWeight.BOLD,40));
        toptitle.setFill(Color.rgb(15,200,222));
        toptitle.setStroke(Color.DARKBLUE);
        top1.add(toptitle,10,0);

        //the pesonal information
        VBox infoContainer= new VBox();
        Font font = Font.font("name", FontWeight.EXTRA_BOLD, 15);
        Text  userName= new Text();
        userName.setFont(font);
        userName.setText("fikiremariyam b");
        userName.setFill(Color.WHITE);
        Text accountNumber = new Text() ;
        accountNumber.setFont(font);
        accountNumber.setText("1000183569729 ");
        accountNumber.setFill(Color.WHITE);
        infoContainer.getChildren().addAll(userName,accountNumber);
        infoContainer.setTranslateX(700);
        top1.add(infoContainer,10,0);
        top1.setStyle("-fx-background-color: #212F3F");
        top1.setHgap(15);
        mother.setTop(top1);

        // for the left bar
        //vbox to contain the buttons
        VBox sideBar = new VBox();
        sideBar.setPadding(new Insets(10, 50, 50, 50));
        sideBar.setSpacing(10);

        sideBar.setBackground(Background.fill(Color.web("#EDD3D3")));
        sideBar.setPrefSize(80,100);

        ImageView transferImage = new ImageView(new Image(new FileInputStream("BMS/src/main/resources/com/example/bms/Image/transfer-modified.png")));
        transferImage.setFitHeight(100);
        ImageView balanceImage = new ImageView(new Image(new FileInputStream("BMS/src/main/resources/com/example/bms/Image/balance-modified.png")));
        balanceImage.setFitHeight(100);
        ImageView transactionImage= new ImageView(new Image(new FileInputStream("BMS/src/main/resources/com/example/bms/Image/transfer-modified.png")));
        transactionImage.setFitHeight(100);
        Button balance = new Button("seebalance");
        balance.setTranslateX(100);
        balance.setOnAction( e ->{  stage.close();
            try {
                viewBalanace(12);
            }
            catch ( Exception exc){
                System.out.println("err with the balanace button");
            }
          });
        Button transfer = new Button("transferMoeny");
        transfer.setTranslateX(100);
        Button Transaction = new Button("seeTaransaction");
        Transaction.setTranslateX(100);
        Transaction.setOnAction(actionEvent ->{
            try{
                seeTransaction();
                stage.close();
            }
            catch (Exception transactin){
                System.out.println("err with see transaactin problem");
            }
        } );

        sideBar.getChildren().addAll(balanceImage,balance,transferImage,transfer,transactionImage,Transaction);

        mother.setLeft(sideBar);

        //for the center
        GridPane transferForm= new GridPane();
        transferForm.setBackground(Background.fill(Color.rgb(34,20,22)));

        Text amount= new Text(" amount :");
        Text recipantAcccount = new Text("recipant account");
        Text reason= new Text("reason :");
        amount.setFont(font);
        recipantAcccount.setFont(font);
        reason.setFont(font);
        amount.setFill(Color.WHITE);
        recipantAcccount.setFill(Color.WHITE);
        reason.setFill(Color.WHITE);

        TextField amountField= new TextField("set amount");
        TextField reasonFiled = new TextField("set a reason");
        TextField recipantAccountField = new TextField("account");
        Button next = new Button("next");
        Button proceed = new Button("procced");


        VBox detailOfRicipant= new VBox();
        detailOfRicipant.setMinSize(100,200);
        detailOfRicipant.setSpacing(10);
        detailOfRicipant.setBackground(Background.fill(Color.WHITE));
        Text recipantNameinfo = new Text("get name from database");
        String recipantacccinfo = recipantAcccount.getText();
        Text recipantacc= new Text(recipantacccinfo);
        recipantacc.setFont(Font.font("name", FontWeight.EXTRA_BOLD, 15));
        detailOfRicipant.getChildren().addAll(recipantNameinfo,recipantacc);



        transferForm.add(recipantAcccount ,0,0);
        transferForm.add(recipantAccountField,1,0);
        transferForm.add(amount,0,1);
        transferForm.add(amountField,1,1);
        transferForm.add(reason,0,2);
        transferForm.add(reasonFiled,1,2);
        transferForm.add(next,0,3);
        transferForm.addColumn(4,detailOfRicipant);
        transferForm.add(proceed,3,3);

        transferForm.setHgap(10);
        transferForm.setVgap(10);
        transferForm.setAlignment(CENTER);
        mother.setCenter(transferForm);




        Scene scene = new Scene(mother,1000,500);
        stage.setTitle("user!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
                                        //when the proceeed button is cliked

    public void confirmBox(String username,String reciveraccount,String amount,String reason ) throws IOException{
        Label confirm=new Label("cinfirm");

        Stage confirmTranasaction= new Stage();
        confirmTranasaction.setMinWidth(300);
        confirmTranasaction.setMinHeight(400);
        VBox results = new VBox();
        Label usernamrecivername= new Label(username);
        Label amount1= new Label(amount);
        Label reciveaccount= new Label(reciveraccount);
        Label reason1 = new Label(reason);
        Button ok = new Button("ok");
        results.getChildren().addAll(usernamrecivername,reciveaccount,amount1,reason1,ok);
        Scene scene=new Scene(results,200,200);
        confirmTranasaction.setScene(scene);
        confirmTranasaction.show();
    }
                        //the see balance box
    public void viewBalanace(double useraccount) throws IOException
    {
        Stage viewbalance = new Stage();
        viewbalance.initStyle(StageStyle.UNDECORATED);
        viewbalance.setTitle( "balance");
        Font font = Font.font("name", FontWeight.EXTRA_BOLD, 25);
        Label confirm =new Label("balance");
        confirm.setFont(font);
        VBox results = new VBox();
        results.setBackground(Background.fill(Color.web("#EDD3D3")));
        results.setAlignment(CENTER);
        Label usernamrecivername= new Label("username");
        usernamrecivername.setFont(font);
        Button back = new Button("back");
        back.setOnAction( actionEvent  -> {
            try {
                mainmenu();
                viewbalance.close();

            }
        catch (Exception e){
            System.out.println("error happend");
            }
        });
        Button exit= new Button("close");
        exit.setOnAction(actionEvent -> { System.exit(0);});
        exit.setTranslateX(50);
        exit.setTranslateY(-25);

        results.getChildren().addAll(usernamrecivername,confirm,back,exit);
        Scene scene=new Scene(results,400,200);
        viewbalance.setScene(scene);


        viewbalance.initModality(Modality.WINDOW_MODAL);

        viewbalance.showAndWait();
    }
    public void seeTransaction() throws  IOException{
        Stage stage = new Stage();
        BorderPane mother= new BorderPane();

        // for the top bar title + pesonla info

        GridPane top1=new GridPane();
        top1.setMinSize(30,40);


        //the title
        Text toptitle = new Text();
        toptitle.setText("Transaction");
        toptitle.setFont(Font.font("Times",FontWeight.BOLD,40));
        toptitle.setFill(Color.rgb(15,200,222));
        toptitle.setStroke(Color.DARKBLUE);
        top1.add(toptitle,10,0);

        //the pesonal information
        VBox infoContainer= new VBox();
        Font font = Font.font("name", FontWeight.EXTRA_BOLD, 15);
        Text  userName= new Text();
        userName.setFont(font);
        userName.setText("fikiremariyam b");
        userName.setFill(Color.WHITE);
        Text accountNumber = new Text() ;
        accountNumber.setFont(font);
        accountNumber.setText("1000183569729 ");
        accountNumber.setFill(Color.WHITE);
        infoContainer.getChildren().addAll(userName,accountNumber);
        infoContainer.setTranslateX(700);
        top1.add(infoContainer,10,0);
        top1.setStyle("-fx-background-color: #212F3F");
        top1.setHgap(15);
        mother.setTop(top1);

        // for the left bar
        //vbox to contain the buttons
        VBox sideBar = new VBox();
        sideBar.setPadding(new Insets(10, 50, 50, 50));
        sideBar.setSpacing(10);

        sideBar.setBackground(Background.fill(Color.web("#EDD3D3")));
        sideBar.setPrefSize(80,100);

        ImageView transferImage = new ImageView(new Image(new FileInputStream("BMS/src/main/resources/com/example/bms/Image/transfer-modified.png")));
        transferImage.setFitHeight(100);
        ImageView balanceImage = new ImageView(new Image(new FileInputStream("BMS/src/main/resources/com/example/bms/Image/balance-modified.png")));
        balanceImage.setFitHeight(100);
        ImageView transactionImage= new ImageView(new Image(new FileInputStream("BMS/src/main/resources/com/example/bms/Image/transfer-modified.png")));
        transactionImage.setFitHeight(100);
        Button balance = new Button("seebalance");
        balance.setTranslateX(100);
        balance.setOnAction( e ->{  stage.close();
            try {
                viewBalanace(12);
            }
            catch ( Exception exc){
                System.out.println("err with the balanace button");
            }
        });
        Button transfer = new Button("transferMoeny");
        transfer.setTranslateX(100);
        Button Transaction = new Button("seeTaransaction");
        Transaction.setTranslateX(100);
        Transaction.setOnAction(actionEvent -> {
                    try {
                        seeTransaction();
                        stage.close();
                    } catch (Exception transactin) {
                        System.out.println("err with see transaactin problem");
                    }
                });

        sideBar.getChildren().addAll(balanceImage,balance,transferImage,transfer,transactionImage,Transaction);

        mother.setLeft(sideBar);

        //for the center
        GridPane transferForm= new GridPane();
        transferForm.setBackground(Background.fill(Color.rgb(34,20,22)));

        Text amount= new Text(" amount :");
        Text recipantAcccount = new Text("recipant account");
        Text reason= new Text("reason :");
        amount.setFont(font);
        recipantAcccount.setFont(font);
        reason.setFont(font);
        amount.setFill(Color.WHITE);
        recipantAcccount.setFill(Color.WHITE);
        reason.setFill(Color.WHITE);

        TextField amountField= new TextField("set amount");
        TextField reasonFiled = new TextField("set a reason");
        TextField recipantAccountField = new TextField("account");
        Button next = new Button("next");
        Button proceed = new Button("procced");


        VBox detailOfRicipant= new VBox();
        detailOfRicipant.setMinSize(100,200);
        detailOfRicipant.setSpacing(10);
        detailOfRicipant.setBackground(Background.fill(Color.WHITE));
        Text recipantNameinfo = new Text("get name from database");
        String recipantacccinfo = recipantAcccount.getText();
        Text recipantacc= new Text(recipantacccinfo);
        recipantacc.setFont(Font.font("name", FontWeight.EXTRA_BOLD, 15));
        detailOfRicipant.getChildren().addAll(recipantNameinfo,recipantacc);

        transferForm.add(recipantAcccount ,0,0);
        transferForm.add(recipantAccountField,1,0);
        transferForm.add(amount,0,1);
        transferForm.add(amountField,1,1);
        transferForm.add(reason,0,2);
        transferForm.add(reasonFiled,1,2);
        transferForm.add(next,0,3);
        transferForm.addColumn(4,detailOfRicipant);
        transferForm.add(proceed,3,3);

        transferForm.setHgap(10);
        transferForm.setVgap(10);
        transferForm.setAlignment(CENTER);
        mother.setCenter(transferForm);


        Scene scene = new Scene(mother,1000,500);
        stage.setTitle("user!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}
