package com.example.bms;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;
import java.time.LocalDate;


import static com.example.bms.TellerView.*;

public class AdminController {
    public static Parent createTellerAcc(){
        AdminView.teller.setOnAction(e->{

            BMS.show( AdminController.seeTellerInfo(),BMS.stage);
            AdminView.btnCreateAcc.setText("UPDATE");
        });
        AdminView.customer.setOnAction(e->{

            BMS.show( AdminController.seeUserInfo(),BMS.stage);
        });

        TellerView.btnBrowse.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File selectedImage = fileChooser.showOpenDialog(new Stage());
            ImageView imageView = new ImageView(new Image(selectedImage.getAbsolutePath()));
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            TellerView.btnBrowse.setGraphic(imageView);
            try {
                byte[] imagebyte = Files.readAllBytes(selectedImage.toPath());
        AdminView.btnCreateAcc.setOnAction(event->{
            try {
                BMS.initializeDatabase();
                String sql = "insert into tbl_teller(first_name ,last_namae ,gender ,user_name ,password ,phone ,email ,registeration_date,  branch_ID,photo) values(?,?,?,?,?,?,?,?,'1',?)";
                PreparedStatement preparedStatement = BMS.connection.prepareStatement(sql);
                preparedStatement.setString(1,AdminView.txFldFName.getText());
                preparedStatement.setString(2,AdminView.txFldLName.getText());
                preparedStatement.setString(3,AdminView.txFldGender.getText());
                preparedStatement.setString(4,AdminView.txFldUName.getText());
                preparedStatement.setString(5,AdminView.txFldPass.getText());
                preparedStatement.setInt(6,Integer.parseInt(AdminView.txFldPhone.getText()));
                preparedStatement.setString(7,AdminView.txFldEmail.getText());
                LocalDate currentDate = LocalDate.now();
                preparedStatement.setDate(8, Date.valueOf(LocalDate.now()));
                preparedStatement.setBytes(9,imagebyte);
                preparedStatement.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Alert Example");
                alert.setContentText("Inserted  successfully");
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
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        AdminView.imgBack.setOnMouseClicked(en->{
            BMS.show(new BMS().adminPage(BMS.stage),BMS.stage);

        });
        return   AdminView.getHome("create");
    }
    public static Parent seeTellerInfo(){
        new AdminView();
        AdminView.btnSearchTeller.setOnAction(e->{
            if (AdminView.txtFldTeller.getText().equalsIgnoreCase(""))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Notification Alert ");
                alert.setContentText("Please Account number");
                alert.showAndWait();
            }
            else
                try {
                    String accNumber = AdminView.txtFldTeller.getText();
                    if (findTeller(accNumber)) {
                        AdminView.name.setText("Name : " +Admin.teller.getFirstName()+ " " + Admin.teller.getSecondName());
                        AdminView.Ugender.setText("Gender : "  + Admin.teller.getGender());
                        AdminView.branchName.setText("Branch ID : "  + Admin.teller.getBranchID());
                        AdminView.emailS.setText("Email : " +Admin.teller.getEmail());
                        AdminView.phoneNumber.setText("Phone Number : " +Admin.teller.getPhone());
                        AdminView.profile = new ImageView(Admin.teller.getImage());
                        BMS.show(displayTeller(), BMS.stage);
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Information");
                        alert.setHeaderText("Alert Example");
                        alert.setContentText("Account does not found");
                        // Display the alert
                        alert.showAndWait();
                    }

                } catch (SQLException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
        });

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
                new Image(new ByteArrayInputStream( resultSet.getBytes(11))),
                resultSet.getInt(10)));
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
        AdminView.btnSearchUser.setOnAction(e->{
            if (AdminView.txtFldAccNumber.getText().equalsIgnoreCase(""))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Notification Alert ");
                alert.setContentText("Please Account number");
                alert.showAndWait();
            }
            else
                try {
                    String accNumber = AdminView.txtFldAccNumber.getText();
                    if (findUser(accNumber)) {
                        AdminView.name.setText("Name : " +Admin.user.getFirstName()+ " " + Admin.user.getSecondName());
                        AdminView.Ugender.setText("Gender : "  + Admin.user.getGender());
                        AdminView.accNumber.setText("Account Number : "  + Admin.user.getAccNumber());
                        AdminView.balance.setText("Current Balance : " +Admin.user.getBalance());
                        AdminView.emailS.setText("Email : " +Admin.user.getEmail());
                        System.out.println(Admin.user.getEmail());
                        System.out.println(Admin.user.getPhone());
                        AdminView.phoneNumber.setText("Phone Number : " +Admin.user.getPhone());
                        AdminView.profile = new ImageView(Admin.user.getImage());
                        BMS.show(displayUser(), BMS.stage);
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Information");
                        alert.setHeaderText("Alert Example");
                        alert.setContentText("Account does not found");
                        // Display the alert
                        alert.showAndWait();
                    }

                } catch (SQLException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
        });
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
                        resultSet.getString(12),
                        new Image(new ByteArrayInputStream(resultSet.getBytes(11)))));
            }
            TellerView.tellerView.userTableView.setItems(TellerView.tellerView.userList);
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return AdminView.getHome("user") ;

    }
    public static boolean checkAccount(String account ) throws SQLException {
        PreparedStatement preparedStatement = BMS.connection.prepareStatement("select * from tbl_customer where account_number = ?");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            return false;
        return true;
    }
    public static  Parent handleDelete(){
        AdminView.btnDelete.setOnAction(e->{

        });
        return AdminView.getHome("delete");
    }
    public static boolean findUser(String accNumber) throws SQLException, ClassNotFoundException {
        BMS.initializeDatabase();
        PreparedStatement preparedStatement =BMS.connection.prepareStatement("\tselect customer_id,first_name , last_namae ,gender, account_number,balance ,photo , email, phone from  tbl_customer where account_number = ?");
        preparedStatement.setString(1,accNumber);
        ResultSet resultset = preparedStatement.executeQuery();
        if (resultset.next()){
            Admin.user.setCustomerID(resultset.getInt(1));
            Admin.user.setFirstName(resultset.getString(2));
            Admin.user.setSecondName(resultset.getString(3));
            Admin.user.setGender(resultset.getString(4));
            Admin.user.setAccNumber(Integer.parseInt(resultset.getString(5)));
            Admin.user.setBalance(Double.parseDouble(resultset.getString(6)));
            Admin.user.setPhone(resultset.getString(9));
            Admin.user.setEmail(resultset.getString(8));
            Admin.user.setPhoto(new Image(new ByteArrayInputStream(resultset.getBytes(7))));
            return true;
        }
        return false;
    }
    public static boolean findTeller(String firstName) throws SQLException, ClassNotFoundException {
        BMS.initializeDatabase();
        PreparedStatement preparedStatement =BMS.connection.prepareStatement("\tselect teller_id,first_name , last_namae ,gender ,photo , email, phone ,branch_ID from  tbl_teller where first_name = ?");
        preparedStatement.setString(1,firstName);
        ResultSet resultset = preparedStatement.executeQuery();
        if (resultset.next()){
            Admin.teller.setTeller_id(resultset.getInt(1));
            Admin.teller.setFirstName(resultset.getString(2));
            Admin.teller.setSecondName(resultset.getString(3));
            Admin.teller.setGender(resultset.getString(4));
            Admin.teller.setPhone(resultset.getString(7));
            Admin.teller.setEmail(resultset.getString(6));
            Admin.teller.setBranchID(Integer.parseInt(resultset.getString(8)));
            Admin.teller.setPhoto(new Image(new ByteArrayInputStream(resultset.getBytes(5))));
            return true;
        }
        return false;
    }
    public static Parent  displayUser(){
        AdminView.btnDelete.setOnAction(e->{
            try {
                PreparedStatement preparedStatement = BMS.connection.prepareStatement("delete from tbl_customer where customer_id = ?");
                PreparedStatement preparedStatement1 = BMS.connection.prepareStatement("delete from tbl_transaction where customer_id = ?");
                preparedStatement.setInt(1,Admin.user.getCustomerID());
                preparedStatement1.setInt(1,Admin.user.getCustomerID());
                preparedStatement1.executeUpdate();
                if (preparedStatement.executeUpdate() > 0){
                    BMS.show(AdminController.seeUserInfo(),BMS.stage);
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information");
                    alert.setHeaderText("Alert Example");
                    alert.setContentText("Account not deleted");
                    // Display the alert
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        return AdminView.getHome(" ");
    }
    public static Parent  displayTeller(){
        AdminView.btnDelete.setOnAction(e->{
            try {
                PreparedStatement preparedStatement = BMS.connection.prepareStatement("delete from tbl_teller where teller_id = ?");
                PreparedStatement preparedStatement1 = BMS.connection.prepareStatement("delete from tbl_transaction where teller_id = ?");
                preparedStatement.setInt(1,Admin.teller.getTeller_id());
                preparedStatement1.setInt(1,Admin.teller.getTeller_id());
                preparedStatement1.executeUpdate();
                if (preparedStatement.executeUpdate() > 0){
                    BMS.show(AdminController.seeTellerInfo(),BMS.stage);
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information");
                    alert.setHeaderText("Alert Example");
                    alert.setContentText("Teller not deleted");
                    // Display the alert
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        AdminView.btnUpdate.setOnAction(e->{
            BMS.show(updateTeller(),BMS.stage);
            AdminView.txFldFName.setText(Admin.teller.getFirstName());
            AdminView.txFldLName.setText(Admin.teller.getSecondName());
            AdminView.txFldEmail.setText(Admin.teller.getEmail());
            AdminView.txFldPhone.setText(Admin.teller.getPhone());
            AdminView.btnCreateAcc.setText("UPDATE");

        });
        return AdminView.getHome("teller s");
    }
    public static Parent updateTeller(){

        return  AdminView.getHome("create");
    }


}
