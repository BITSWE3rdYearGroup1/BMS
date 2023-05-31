package com.example.bms;

import com.example.bms.AdminView;
import com.example.bms.BMS;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class AdminController {
    public static Parent createTellerAcc(){
        AdminView.teller.setOnAction(e->{

//            BMS.main.getChildren().removeAll();
            BMS.show( AdminController.seeTellerInfo(),BMS.stage);
        });
        AdminView.customer.setOnAction(e->{

            BMS.show( AdminController.seeUserInfo(),BMS.stage);
        });
        File file;
        AdminView.btnInsPhoto.setOnAction(e-> {
                    FileChooser fileChooser = new FileChooser();
                    File photo = fileChooser.showOpenDialog(BMS.stage);
                    System.out.println(photo.getAbsolutePath());
//            if(photo != null){
//                ImageView imgVwPhoto = new ImageView(new Image(photo.toURI().toString()));
//                imgVwPhoto.fitHeightProperty().bind(recPhoto.heightProperty());
//                imgVwPhoto.fitWidthProperty().bind(recPhoto.widthProperty());
//                gridPane.add(imgVwPhoto,3,1,1,2);
//            }

                });

        // Handling imgBack
        AdminView.imgBack.setOnMouseClicked(en->{
            BMS.show(new BMS().adminPage(BMS.stage),BMS.stage);

        });
        AdminView.btnCreateAcc.setOnAction(event->{
            try {
                BMS.initializeDatabase();
                String sql = "insert into tbl_teller values(?,?,?,?,?,?,?,?,?,?,'1')";
                PreparedStatement preparedStatement = BMS.connection.prepareStatement(sql);
                preparedStatement.setInt(1,Integer.parseInt(AdminView.txFldTellerId.getText()));
                preparedStatement.setString(2,AdminView.txFldFName.getText());
                preparedStatement.setString(3,AdminView.txFldLName.getText());
                preparedStatement.setString(4,AdminView.txFldGender.getText());
                preparedStatement.setString(5,AdminView.txFldUName.getText());
                preparedStatement.setString(6,AdminView.txFldPass.getText());
                preparedStatement.setInt(7,Integer.parseInt(AdminView.txFldPhone.getText()));
                preparedStatement.setString(8,AdminView.txFldEmail.getText());
                LocalDate currentDate = LocalDate.now();
                preparedStatement.setDate(9, Date.valueOf(currentDate.now()));
//                File file = new File("C:\\Users\\ezrat\\Desktop\\ap\\BMS\\BMS\\src\\main\\resources\\com\\example\\bms\\34-modified.png");
//                BufferedImage bufferedImage = ImageIO.read(file);
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                ImageIO.write(bufferedImage, "jpg", baos);
//                byte[] imageBytes = baos.toByteArray();
//                FileInputStream fis = new FileInputStream(photo);
//                byte[] imageBytes = new byte[(int) photo.length()];
//                fis.read(imageBytes);
//                fis.close();
//                preparedStatement.setBytes(10,imageBytes);
                preparedStatement.setString(10,"C:\\Users\\ezrat\\Desktop\\ap\\BMS\\BMS\\src\\main\\resources\\com\\example\\bms\\34-modified.png");
//                preparedStatement.setInt(11,Integer.parseInt(txFldTellerId.getText()));
                preparedStatement.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Alert Example");
                alert.setContentText("Inserted  successfully");
                // Display the alert
                alert.showAndWait();
            } catch (SQLException | ClassNotFoundException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information");
                alert.setHeaderText("Alert Example");
                alert.setContentText("Not Inserted  successfully");
                // Display the alert
                alert.showAndWait();
                throw new RuntimeException(ex);
            }
        });
        return   AdminView.getHome("create");
    }
    public static Parent seeTellerInfo(){
        new AdminView();
        AdminView.btnAccount.setOnAction(e->{

            BMS.show( AdminController.createTellerAcc(),BMS.stage);
        });
        AdminView.customer.setOnAction(e->{

            BMS.show( AdminController.seeUserInfo(),BMS.stage);
        });
        try {
            // Establish a connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BMS", "eziraa", "1234");
            // Create a statement
            PreparedStatement pstm = con.prepareStatement("select *from tbl_teller");
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                AdminView.adminView.tellerList.add(new Teller(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10),
                        resultSet.getInt(11)));
                System.out.println(resultSet.getString(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+ " "+resultSet.getString(4)+" "+resultSet.getString(5)+ " " +resultSet.getString(6)+ " "+ resultSet.getString(7)+ " "+resultSet.getString(8)+ " "+ resultSet.getString(9)+ " "+resultSet.getString(10)+ " " + resultSet.getString(11));
            }
            AdminView.adminView.tellerTableView.setItems(AdminView.adminView.tellerList);
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return AdminView.getHome("teller");
    }
    public static Parent seeUserInfo(){
        TellerView.EmployeeView();
       AdminView.btnAccount.setOnAction(e->{

           BMS.show( AdminController.createTellerAcc(),BMS.stage);
        });
        AdminView.teller.setOnAction(e->{
            BMS.show( AdminController.seeTellerInfo(),BMS.stage);
        });
        try {
            // Establish a connection]
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BMS", "eziraa", "1234");
            // Create a statement
            PreparedStatement pstm = con.prepareStatement("select *from tbl_customer");
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                TellerView.tellerView.userList.add(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getFloat(8),
                        resultSet.getString(9),
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(13)));
                System.out.println(resultSet.getString(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+ " "+resultSet.getString(4)+" "+resultSet.getString(5)+ " " +resultSet.getString(6)+ " "+ resultSet.getString(7)+ " "+resultSet.getString(8)+ " "+ resultSet.getString(9)+ " "+resultSet.getString(10)+ " " + resultSet.getString(11));
            }
            TellerView.tellerView.userTableView.setItems(TellerView.tellerView.userList);
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return AdminView.getHome("user") ;

    }
}
