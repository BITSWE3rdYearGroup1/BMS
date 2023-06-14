package com.example.bms;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.Optional;

import static com.example.bms.BMS.connection;
import static com.example.bms.BMS.stage;
import static com.example.bms.TellerView.txtFldAccNumber;
import static com.example.bms.TellerView.txtFldAmount;
import static com.example.bms.UserView.txtFldReceiverAcc;

public class UserController {
    public static Parent seeBalance() throws SQLException, ClassNotFoundException {
        UserView.transferbtn.setOnAction(e->{
            try {
                BMS.show(UserController.transferMoney(),BMS.stage);
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        UserView.transactionbtn.setOnAction(e->{
            BMS.show(UserController.seeTransaction(),BMS.stage);
        });
        BMS.initializeDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement("select balance from tbl_customer where account_number = ?");
        preparedStatement.setString(1,User.user.getAccNumber());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println(resultSet.getDouble(1));
           UserView.txtArea.setText("Dear customer your current balance is  "+resultSet.getDouble(1));
    }
        else
            UserView.txtArea.setText("Dear customer your current balance is \nin sufficient ");
        return UserView.userHome("balance");
    }
    public static Parent transferMoney() throws SQLException, ClassNotFoundException {
        UserView.balancebtn.setOnAction(e->{
            try {
                BMS.show(UserController.seeBalance(),BMS.stage);
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        });
        UserView.transactionbtn.setOnAction(e->{
            BMS.show(UserController.seeTransaction(),BMS.stage);

        });
        UserView.btnComplete.setOnAction(e-> {
            try {
                if (validateAccountNumber())
                if (validateBalance())
                    if ((txtFldReceiverAcc.getText()).equalsIgnoreCase(User.user.getAccNumber())) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText("Fund transfer Alert ");
                        alert.setContentText("Dear teller you are trying to transfer money to the customer himself ");
                        alert.showAndWait();
                    }
                else
                if (TellerController.checkAccount(txtFldReceiverAcc.getText())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information");
                    alert.setHeaderText("Transfer fund Alert");
                    alert.setContentText("Account does not exist");
                    // Display the alert
                    alert.showAndWait();
                }
                else
                        try {
                            BMS.initializeDatabase();
                            CallableStatement preparedStatement1 = connection.prepareCall("{CALL spTransferFund(?,?,?,?,?)}");
                            preparedStatement1.setDouble(1, Double.parseDouble(UserView.txtFldAmount.getText()));
                            preparedStatement1.setInt(2, Integer.parseInt(txtFldReceiverAcc.getText()));
                            preparedStatement1.setInt(3,1);
                            preparedStatement1.setInt(4,User.user.getCustomerID());
                            preparedStatement1.registerOutParameter(5,Types.BOOLEAN);
                            preparedStatement1.executeUpdate();
                            boolean bool = preparedStatement1.getBoolean("@make");
                            System.out.println(bool);
                            if (bool) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                User.user.setBalance(User.user.getBalance()- Double.parseDouble(UserView.txtFldAmount.getText()));
                                UserView.txtFldAmount.clear();
                                txtFldReceiverAcc.clear();
                                alert.setTitle("Information");
                                alert.setHeaderText("Alert ");
                                alert.setContentText("Transferred successfully");
                                // Display the alert
                                alert.showAndWait();
                                BMS.show(UserController.transferMoney(),stage);
                            }
                            else
                            {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Information");
                                alert.setHeaderText("Alert ");
                                alert.setContentText("Your balance is low");
                                // Display the alert
                                alert.showAndWait();
                            }
                        } catch (SQLException ex) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Information");
                            alert.setHeaderText("Alert ");
                            alert.setContentText("NO successfully Updated");
                            // Display the alert
                            alert.showAndWait();
                            throw new RuntimeException(ex);
                        } catch (ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        return UserView.userHome("transfer");
    }
    public static Parent seeTransaction(){
        TransactionView.load();
        UserView.balancebtn.setOnAction(e->{
            try {
                BMS.show(UserController.seeBalance(),BMS.stage);
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        });
        UserView.transferbtn.setOnAction(e->{
            try {
                BMS.show(UserController.transferMoney(),BMS.stage);
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        });
        return UserView.userHome("transaction");

    }
    public static boolean checkUser(String password ,String username) throws SQLException, ClassNotFoundException {
        BMS.initializeDatabase();
        String sql = "select first_name, last_name,gender,email,phone, user_name, password , account_number,customer_id ,photo,balance from tbl_customer where user_name =?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        ResultSet resultset = preparedStatement.executeQuery();
        while (resultset.next())
            if (resultset.getString(6).equalsIgnoreCase(username)&&resultset.getString(7).equalsIgnoreCase(password)) {
                User.user.setPhone(resultset.getString(5));
                User.user.setAccNumber(resultset.getString(8));
                User.user.setEmail(resultset.getString(4));
                User.user.setFirstName(resultset.getString(1));
                User.user.setSecondName(resultset.getString(2));
                User.user.setGender(resultset.getString(3));
                User.user.setPassword(resultset.getString(7));
                User.user.setUsername(resultset.getString(6));
                User.user.setCustomerID(resultset.getInt(9));
                User.user.setBalance(resultset.getDouble(11));
                User.user.setPhoto(new Image(new ByteArrayInputStream(resultset.getBytes(10))));
                return true;
            }
        return false;
    }
    public static Parent changeYourPassword(){
        UserView.btnChange.setOnAction(e->{
            if (UserView.txtOldPass.getText().equals(User.user.getPassword())){
                if (UserView.txtNewPass.getText().equals(UserView.txtConfirmPass.getText())){
                    if(UserView.txtNewPass.getText().equals(User.user.getPassword()))
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Information");
                        alert.setContentText("Your are  entering the same password as before please change");
                        alert.setHeaderText("Change Password Alert");
                        alert.showAndWait();

                    }
                    else
                        try {
                            PreparedStatement preparedStatement = connection.prepareStatement("update  tbl_customer set password = ? where user_name = ?");
                            preparedStatement.setString(2,User.user.getUsername());
                            preparedStatement.setString(1,UserView.txtNewPass.getText());
                            if (preparedStatement.executeUpdate() > 0){
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Information");
                                alert.setContentText("Your password is successfully changed");
                                alert.setHeaderText("Change Password Alert");
                                alert.showAndWait();
                            }
                            else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Information");
                                alert.setContentText("Your password is not  successfully Changed");
                                alert.setHeaderText("Change Password Alert");
                                alert.showAndWait();
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information");
                    alert.setContentText("Please enter correct password confirmation");
                    alert.setHeaderText("Change Password Alert");
                    alert.showAndWait();

                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information");
                alert.setContentText("Please enter correct your old password");
                alert.setHeaderText("Change Password Alert");
                alert.showAndWait();
            }
        });
        return UserView.userHome("change");
    }
    public static boolean validateAccountNumber(){
        if (txtFldReceiverAcc.getText().isEmpty()) {
            txtFldReceiverAcc.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText( "Account Number is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if (( result).isPresent() && result.get() == ButtonType.OK) {
                txtFldReceiverAcc.setStyle("-fx-border-color: black;");
            } else {
                txtFldReceiverAcc.setStyle("-fx-border-color: black;");
            }
        }
        else if (!txtFldReceiverAcc.getText().trim().matches(TellerController.accountNumberRegex)) {
            txtFldReceiverAcc.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText( "Please enter number only Account Number");
            Optional<ButtonType> result = alert.showAndWait();
            if (( result).isPresent() && result.get() == ButtonType.OK) {
                txtFldReceiverAcc.setStyle("-fx-border-color: black;");
            } else {
                txtFldReceiverAcc.setStyle("-fx-border-color: black;");
            }
        }
        else if (txtFldReceiverAcc.getText().length() != 5) {
            txtFldReceiverAcc.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText( "Account Number length should be 5");
            Optional<ButtonType> result = alert.showAndWait();
            if (( result).isPresent() && result.get() == ButtonType.OK) {
                txtFldReceiverAcc.setStyle("-fx-border-color: black;");
            } else {
                txtFldReceiverAcc.setStyle("-fx-border-color: black;");
            }
        }
        else
            return true;
        return false;
    }
    public static boolean validateBalance(){
        if (UserView.txtFldAmount.getText().isEmpty()) {
            UserView.txtFldAmount.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText( "Balance is  is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if (( result).isPresent() && result.get() == ButtonType.OK) {
                UserView.txtFldAmount.setStyle("-fx-border-color: black;");
            } else {
                UserView.txtFldAmount.setStyle("-fx-border-color: black;");
            }
        }
        else if (!UserView.txtFldAmount.getText().matches(TellerController.balanceRegex)) {
            UserView.txtFldAmount.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText( "Please enter positive number only don\'t enter string or character for amount");
            Optional<ButtonType> result = alert.showAndWait();
            if (( result).isPresent() && result.get() == ButtonType.OK) {
                UserView.txtFldAmount.setStyle("-fx-border-color: black;");
            } else {
                UserView.txtFldAmount.setStyle("-fx-border-color: black;");
            }
        }
        else
            return true;
        return false;
    }

}
