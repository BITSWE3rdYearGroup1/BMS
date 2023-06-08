package com.example.bms;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
import java.util.Optional;

import static com.example.bms.BMS.connection;
import static com.example.bms.TellerView.*;

public class TellerController {
    public static Parent createAcc() throws SQLException, ClassNotFoundException {
        TellerView.btnBrowse.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File selectedImage = fileChooser.showOpenDialog(new Stage());
            ImageView imageView = new ImageView(new Image(selectedImage.getAbsolutePath()));
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            TellerView.btnBrowse.setGraphic(imageView);
        TellerView.create.setOnAction(event->{
            if (validateInput()) {
                try {
                    if (checkAccount(txtFldAccNumber.getText()))
                        try {
                        BMS.initializeDatabase();
                        String sql = "insert into tbl_customer(first_name ,last_namae ,gender ,user_name ,password ,account_number ,balance ,phone,email ,registeration_date ,photo ,teller_id ) values(?,?,?,?,?,?,?,?,?,?,?,?)";
                        PreparedStatement preparedStatement = BMS.connection.prepareStatement(sql);
                        preparedStatement.setString(1,EmployeeView.txFldFName.getText());
                        preparedStatement.setString(2,EmployeeView.txFldLName.getText());
                        preparedStatement.setString(3,EmployeeView.txFldGender.getText());
                        preparedStatement.setString(4,EmployeeView.txFldUName.getText());
                        preparedStatement.setString(5,EmployeeView.txFldPass.getText());
                        preparedStatement.setInt(6,Integer.parseInt(txtFldAccNumber.getText()));
                        preparedStatement.setFloat(7, Float.parseFloat(txtFldBalance.getText()));
                        preparedStatement.setString(8,TellerView.txFldEmail.getText());
                        preparedStatement.setString(9,EmployeeView.txFldPhone.getText());
                        LocalDate currentDate = LocalDate.now();
                        preparedStatement.setDate(10, Date.valueOf(LocalDate.now()));
                        if (selectedImage != null){
                            byte[] imageByte = Files.readAllBytes(selectedImage.toPath());
                            preparedStatement.setBytes(11, imageByte);
                            preparedStatement.setInt(12,Teller.id);
                            if (preparedStatement.executeUpdate()!=0){
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Information");
                                alert.setHeaderText("Alert Example");
                                alert.setContentText( "INSERTED successfully Updated");
                                // Display the alert
                                alert.showAndWait();
                            }
                            else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Information");
                                alert.setHeaderText("Alert Example");
                                alert.setContentText("NOT INSERTED successfully Updated");
                                // Display the alert
                                alert.showAndWait();
                            }
                        }
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Information");
                        alert.setHeaderText("Alert Example");
                        alert.setContentText("NOT INSERTED successfully Updated");
                        // Display the alert
                        alert.showAndWait();
                    }
                    BMS.statement =BMS.connection.createStatement();
                    ResultSet resultSet =  BMS.statement.executeQuery("select * from tbl_customer");
                    while (resultSet.next())
                        System.out.println(resultSet.getInt(1)+"     "+resultSet.getString(2)+ "  "+resultSet.getString(3));

                } catch (SQLException | ClassNotFoundException | IOException ex) {
                    throw new RuntimeException(ex);
                }
                    else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Information");
                        alert.setHeaderText("Info alert");
                        alert.setContentText("Account number is already exist");
                        // Display the alert
                        alert.showAndWait();
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        });
        TellerView.back.setOnAction(e -> {
            BMS.show(new BMS().tellerPage(BMS.stage), BMS.stage);
        });
        return TellerView.tellerHome("create");
    }
    public static Parent manageUserAcc() throws SQLException, ClassNotFoundException {

        TellerView.btnSearch.setOnAction(e->{
            if (txtFldAccNumber.getText().equalsIgnoreCase(""))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Notification Alert ");
                alert.setContentText("Please Account number");
                // Display the alert
                alert.showAndWait();
            }
            else
                try {
                    String accNumber = txtFldAccNumber.getText();
                    PreparedStatement preparedStatement = BMS.connection.prepareStatement("select customer_id, account_number from  tbl_customer where account_number = ?");
                    preparedStatement.setString(1, accNumber);
                    if (searchUser(accNumber)) {
                        name.setText("Name : " +Teller.user.getFirstName()+ " " + Teller.user.getSecondName());
                        Ugender.setText("Gender : "  + Teller.user.getGender());
                        TellerView.accNumber.setText("Account Number : "  + Teller.user.getAccNumber());
                        balance.setText("Current Balance : " +Teller.user.getBalance());
                        profile = new ImageView(Teller.user.getImage());
                        BMS.show(TellerController.display(), BMS.stage);
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
        return TellerView.tellerHome("manage");
    }
    private static boolean validateInput() {
   if (txFldFName.getText().isEmpty()) {
            txFldFName.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Waring Alert");
            alert.setContentText( "First Name text field is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional<?>) result).isPresent() && result.get() == ButtonType.OK) {
                txFldFName.setStyle("-fx-border-color: black;");
            } else {
                txFldFName.setStyle("-fx-border-color: black;");
            }
        }
        else if (txFldLName.getText().isEmpty()) {
            txFldLName.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Waring Alert");
            alert.setContentText( "Last Name text field is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional<?>) result).isPresent() && result.get() == ButtonType.OK) {
                txFldLName.setStyle("-fx-border-color: black;");
            } else {
                txFldLName.setStyle("-fx-border-color: black;");
            }
        }
        else if (txFldGender.getText().isEmpty()) {
            txFldGender.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Waring Alert");
            alert.setContentText( "Gender text field is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional<?>) result).isPresent() && result.get() == ButtonType.OK) {
                txFldGender.setStyle("-fx-border-color: black;");
            } else {
                txFldGender.setStyle("-fx-border-color: black;");
            }
        }
        else if (txtFldAccNumber.getText().isEmpty()) {
            txtFldAccNumber.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Waring Alert");
            alert.setContentText( "Account Number text field is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional<?>) result).isPresent() && result.get() == ButtonType.OK) {
                txtFldAccNumber.setStyle("-fx-border-color: black;");
            } else {
                txtFldAccNumber.setStyle("-fx-border-color: black;");
            }
        }
        else if (txFldEmail.getText().isEmpty()) {
            txFldEmail.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Waring Alert");
            alert.setContentText( "Email text field is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional<?>) result).isPresent() && result.get() == ButtonType.OK) {
                txFldEmail.setStyle("-fx-border-color: black;");
            } else {
                txFldEmail.setStyle("-fx-border-color: black;");
            }
        }
        else if (txFldPass.getText().isEmpty()) {
            txFldPass.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Waring Alert");
            alert.setContentText( "Password text field is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional<?>) result).isPresent() && result.get() == ButtonType.OK) {
                txFldPass.setStyle("-fx-border-color: black;");
            } else {
                txFldPass.setStyle("-fx-border-color: black;");
            }
        }
       else if (txtFldBalance.getText().isEmpty()) {
            txtFldBalance.setStyle("-fx-border-color: red;");
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Information");
           alert.setHeaderText("Waring Alert");
           alert.setContentText( "Password text field is Empty");
           Optional<ButtonType> result = alert.showAndWait();
           if (((Optional<?>) result).isPresent() && result.get() == ButtonType.OK) {
               txFldPass.setStyle("-fx-border-color: black;");
           } else {
               txFldPass.setStyle("-fx-border-color: black;");
           }
       }
        else if (txtFldConfirmPasswd.getText().isEmpty()) {
            txtFldConfirmPasswd.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Waring Alert");
            alert.setContentText( "Please confirm password text field is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional<?>) result).isPresent() && result.get() == ButtonType.OK) {
                txtFldConfirmPasswd.setStyle("-fx-border-color: black;");
            } else {
                txtFldConfirmPasswd.setStyle("-fx-border-color: black;");
            }
        }
   else if (!txFldPass.getText().equals(txtFldConfirmPasswd.getText())) {
       txtFldConfirmPasswd.setStyle("-fx-border-color: red;");
       Alert alert = new Alert(Alert.AlertType.WARNING);
       alert.setTitle("Information");
       alert.setHeaderText("Waring Alert");
       alert.setContentText( "Please enter the same confirmation password");
       Optional<ButtonType> result = alert.showAndWait();
       if (result.isPresent() && result.get() == ButtonType.OK) {
           txtFldConfirmPasswd.setStyle("-fx-border-color: black;");
       } else {
           txtFldConfirmPasswd.setStyle("-fx-border-color: black;");
       }
   }
        else
            return true;
        return false;
    }
    public static Parent display() throws SQLException, ClassNotFoundException {
        TellerView.btnDeposit.setOnAction(e->{
            BMS.show(TellerController.depositMoney(),BMS.stage);
        });
        TellerView.btnTransfer.setOnAction(e->{
            BMS.show(TellerController.transferFund(),BMS.stage);
        });
        TellerView.btnWithDraw.setOnAction(e->{
            BMS.show(TellerController.widthDrawMoney(),BMS.stage);
        });
        return TellerView.tellerHome("");
    }
    public static Parent depositMoney(){
        btnCompleteDeposit.setOnAction(e->{
            try {
                CallableStatement preparedStatement1 = connection.prepareCall("{CALL spDeposit(?,?,?,?)}");
                preparedStatement1.setDouble(1, Double.parseDouble(TellerView.txtFldAmount.getText()));
                Teller.id = 1;
                preparedStatement1.setInt(2,Teller.id);
                preparedStatement1.setInt(3, Teller.user.getCustomerID());
                preparedStatement1.registerOutParameter(4,Types.BOOLEAN);
                preparedStatement1.execute();
                boolean bool = preparedStatement1.getBoolean("@make");
                if (bool) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText("Update alert ");
                    alert.setContentText(txtFldAmount.getText() + " deposited successfully✅✅✅" );
                    alert.showAndWait();
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText("Update alert ");
                    alert.setContentText(" Not deposited successfully🌋🌋" );
                    alert.showAndWait();
                }
            } catch (SQLException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information");
                alert.setHeaderText("Alert Example");
                alert.setContentText("NO successfully deposited");
                // Display the alert
                alert.showAndWait();
                throw new RuntimeException(ex);
            }

        });
        return TellerView.tellerHome("deposit");
    }
public static Parent transferFund(){
        btnCompleteTransfer.setOnAction(e->{
            try {
                if (!checkAccount(txtFldAccNumber.getText()))
                try{
                CallableStatement preparedStatement1 = connection.prepareCall("{CALL spTransferFund(?,?,?,?,?)}");
                preparedStatement1.setDouble(1, Double.parseDouble(TellerView.txtFldAmount.getText()));
                preparedStatement1.setInt(2, Integer.parseInt(txtFldAccNumber.getText()));
                Teller.id = 1;
                preparedStatement1.setInt(3,Teller.id);
                preparedStatement1.setInt(4,Teller.user.getCustomerID());
                preparedStatement1.registerOutParameter(5,Types.BOOLEAN);
                preparedStatement1.execute();
                boolean bool = preparedStatement1.getBoolean("@make");
                if (bool) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText("Alert Example");
                    alert.setContentText("Transferred successfully");
                    // Display the alert
                    alert.showAndWait();
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText("Alert Example");
                    alert.setContentText("Your balance is low");
                    // Display the alert
                    alert.showAndWait();
                }
            } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information");
            alert.setHeaderText("Alert Example");
            alert.setContentText("NO successfully Updated");
            // Display the alert
            alert.showAndWait();
            throw new RuntimeException(ex);
        }
                else  {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information");
                    alert.setHeaderText("Alert Example");
                    alert.setContentText("Account Does not Exist");
                    // Display the alert
                    alert.showAndWait();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        return TellerView.tellerHome("transfer");
    }
public static Parent widthDrawMoney(){
         btnCompleteWithdraw.setOnAction(e->{
             try {
                 CallableStatement preparedStatement1 = connection.prepareCall("{CALL spWithDraw(?,?,?,?)}");
                 preparedStatement1.setDouble(1, Double.parseDouble(TellerView.txtFldAmount.getText()));
                 Teller.id = 1;
                 preparedStatement1.setInt(2,Teller.id);
                 preparedStatement1.setInt(3, Teller.user.getCustomerID());
                 preparedStatement1.registerOutParameter(4,Types.BOOLEAN);
                 preparedStatement1.execute();
                 boolean bool = preparedStatement1.getBoolean("@make");
                 if (bool) {
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("Information");
                     alert.setHeaderText("Update alert ");
                     alert.setContentText(txtFldAmount.getText() + " Withdrawn successfully✅✅✅" );
                     alert.showAndWait();
                 }
                 else
                 {
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                     alert.setTitle("Information");
                     alert.setHeaderText("Update alert ");
                     alert.setContentText(" Not Withdrawn successfully🌋🌋" );
                     alert.showAndWait();
                 }
             } catch (SQLException ex) {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Information");
                 alert.setHeaderText("Alert Example");
                 alert.setContentText("NO successfully Withdrawn");
                 alert.showAndWait();
                 throw new RuntimeException(ex);
             }
         });
        return TellerView.tellerHome("withDraw");
    }
    public static boolean searchUser(String accNumber) throws SQLException, ClassNotFoundException {
        BMS.initializeDatabase();
        PreparedStatement preparedStatement =BMS.connection.prepareStatement("\tselect customer_id,first_name , last_namae ,gender, account_number,balance ,photo from  tbl_customer where account_number = ?");
        preparedStatement.setString(1,accNumber);
        ResultSet resultset = preparedStatement.executeQuery();
        if (resultset.next()){
                Teller.user.setCustomerID(resultset.getInt(1));
                Teller.user.setFirstName(resultset.getString(2));
                Teller.user.setSecondName(resultset.getString(3));
                Teller.user.setGender(resultset.getString(4));
                Teller.user.setAccNumber(Integer.parseInt(resultset.getString(5)));
                Teller.user.setBalance(Double.parseDouble(resultset.getString(6)));
                Teller.user.setPhoto(new Image(new ByteArrayInputStream(resultset.getBytes(7))));
                return true;
            }
        return false;
    }
    public static boolean checkAccount(String account ) throws SQLException {
        PreparedStatement preparedStatement = BMS.connection.prepareStatement("select * from tbl_customer where account_number = ?");
        preparedStatement.setString(1,account);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next())
            return false;
        return true;
    }
}
