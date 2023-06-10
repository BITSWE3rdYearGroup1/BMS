package com.example.bms;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import java.io.ByteArrayInputStream;
import java.sql.*;
import static com.example.bms.BMS.connection;
import static com.example.bms.BMS.stage;

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
                if (TellerController.checkAccount(UserView.txtFldReceiverAcc.getText())){
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
                        preparedStatement1.setInt(2, Integer.parseInt(UserView.txtFldReceiverAcc.getText()));
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
                            UserView.txtFldReceiverAcc.clear();
                            alert.setTitle("Information");
                            alert.setHeaderText("Alert Example");
                            alert.setContentText("Transferred successfully");
                            // Display the alert
                            alert.showAndWait();
                            BMS.show(UserController.transferMoney(),stage);
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
    public static boolean check(String password ,String username) throws SQLException, ClassNotFoundException {
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
                User.user.setPassword(resultset.getString(6));
                User.user.setUsername(resultset.getString(7));
                User.user.setCustomerID(resultset.getInt(9));
                User.user.setBalance(resultset.getDouble(11));
                User.user.setPhoto(new Image(new ByteArrayInputStream(resultset.getBytes(10))));
                return true;
            }
        return false;
    }

}
