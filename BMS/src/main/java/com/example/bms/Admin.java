package com.example.bms;
import java.awt.image.BufferedImage;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.time.LocalDate;

import javax.imageio.ImageIO;
import java.io.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Admin {
    public static void main(String[] args) {

    }
    public GridPane createTellerAcc(Stage stage){

        GridPane gridPane = new GridPane();

        // Back icon image
        ImageView imgBack = new ImageView(new Image(getClass().getResourceAsStream("Image/backIcon.png")));

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

        TextField txFldTellerId = new TextField();

        TextField txFldFName = new TextField();

        TextField txFldLName = new TextField();

        TextField txFldGender = new TextField();

        TextField txFldUName = new TextField();

        TextField txFldPass = new TextField();

        TextField txFldPhone = new TextField();

        TextField txFldEmail = new TextField();

        Button btnCreateAcc = new Button("Create Account");

        Button btnInsPhoto = new Button("Insert Photo");

        Rectangle recPhoto = new Rectangle();

        gridPane.setStyle("-fx-background-color: #212F3F;");
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        lblCreateTAcc.setFont(Font.font("verdana", FontWeight.BOLD, 24));
        lblCreateTAcc.setTextFill(Color.WHITE);
        lblTellerId.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        lblTellerId.setTextFill(Color.WHITE);
        lblFName.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        lblFName.setTextFill(Color.WHITE);
        lblLName.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        lblLName.setTextFill(Color.WHITE);
        lblGender.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        lblGender.setTextFill(Color.WHITE);
        lblUname.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        lblUname.setTextFill(Color.WHITE);
        lblPass.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        lblPass.setTextFill(Color.WHITE);
        lblPhone.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        lblPhone.setTextFill(Color.WHITE);
        lblPhoto.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        lblPhoto.setTextFill(Color.WHITE);
        lblEmail.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        lblEmail.setTextFill(Color.WHITE);
        btnCreateAcc.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        btnCreateAcc.setStyle("fx-background-color: #fff; -fx-text-fill: #212F3F;");
        btnInsPhoto.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        btnInsPhoto.setStyle("fx-background-color: #fff; -fx-text-fill: #212F3F;");
        recPhoto.setWidth(85);
        recPhoto.setHeight(85);
        recPhoto.setStroke(Color.WHITE);
        recPhoto.setFill(Color.rgb(33, 47, 63));

        // Adding nodes to gridPane

        gridPane.add(imgBack, 0,0);
        gridPane.add(lblCreateTAcc, 1,0, 3,1);
        gridPane.add(lblTellerId, 0,1);
        gridPane.add(txFldTellerId, 1,1);
        gridPane.add(lblPhoto, 2, 1);
        gridPane.add(recPhoto,3,1,1,2);
        gridPane.add(lblFName, 0, 2);
        gridPane.add(txFldFName, 1, 2);
        gridPane.add(lblLName,0,3);
        gridPane.add(txFldLName,1,3);
        gridPane.add(btnInsPhoto,3,3);
        gridPane.add(lblGender,0,4);
        gridPane.add(txFldGender,1,4);
        gridPane.add(lblUname,0,5);
        gridPane.add(txFldUName,1,5);
        gridPane.add(lblEmail,2,5);
        gridPane.add(txFldEmail,3,5);
        gridPane.add(lblPass,0,6);
        gridPane.add(txFldPass,1,6);
        gridPane.add(lblPhone,0,7);
        gridPane.add(txFldPhone,1,7);
        gridPane.add(btnCreateAcc,3,7);
        gridPane.translateYProperty().bind(new BMS().scene.heightProperty().divide(-4));
        // Handling btnInsPhoto
        btnInsPhoto.setOnAction(e->{
            FileChooser fileChooser = new FileChooser();
            File photo = fileChooser.showOpenDialog(stage);
            if(photo != null){
                ImageView imgVwPhoto = new ImageView(new Image(photo.toURI().toString()));
                imgVwPhoto.fitHeightProperty().bind(recPhoto.heightProperty());
                imgVwPhoto.fitWidthProperty().bind(recPhoto.widthProperty());
                gridPane.add(imgVwPhoto,3,1,1,2);
            }

        });

        // Handling imgBack
        imgBack.setOnMouseClicked(e->{
            new BMS().show(new BMS().adminPage(stage),stage);

        });
        btnCreateAcc.setOnAction(e->{
            try {
                BMS.initializeDatabase();
//                String sql = "insert into tbl_teller values(?,?,?,?,?,?,?,?,?,?,'1')";
//                PreparedStatement preparedStatement = BMS.connection.prepareStatement(sql);
//                preparedStatement.setInt(1,Integer.parseInt(txFldTellerId.getText()));
//                preparedStatement.setString(2,txFldFName.getText());
//                preparedStatement.setString(3,txFldLName.getText());
//                preparedStatement.setString(4,txFldGender.getText());
//                preparedStatement.setString(5,txFldUName.getText());
//                preparedStatement.setString(6,txFldPass.getText());
//                preparedStatement.setInt(7,Integer.parseInt(txFldPhone.getText()));
//                preparedStatement.setString(8,txFldEmail.getText());
//                LocalDate currentDate = LocalDate.now();
//                preparedStatement.setDate(9, Date.valueOf(currentDate.now()));
////                File file = new File("C:\\Users\\ezrat\\Desktop\\ap\\BMS\\BMS\\src\\main\\resources\\com\\example\\bms\\34-modified.png");
////                BufferedImage bufferedImage = ImageIO.read(file);
////                ByteArrayOutputStream baos = new ByteArrayOutputStream();
////                ImageIO.write(bufferedImage, "jpg", baos);
////                byte[] imageBytes = baos.toByteArray();
//                preparedStatement.setString(10,"C:\\Users\\ezrat\\Desktop\\ap\\BMS\\BMS\\src\\main\\resources\\com\\example\\bms\\34-modified.png");
////                preparedStatement.setInt(11,Integer.parseInt(txFldTellerId.getText()));
//                preparedStatement.executeUpdate();
                BMS.statement =BMS.connection.createStatement();
               ResultSet resultSet =  BMS.statement.executeQuery("select * from tbl_teller");
               while (resultSet.next())
                System.out.println(resultSet.getInt(1)+"     "+resultSet.getString(2)+ "  "+resultSet.getString(3));

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        return gridPane;
    }
}
