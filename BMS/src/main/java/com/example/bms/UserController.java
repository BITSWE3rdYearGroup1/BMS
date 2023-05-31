package com.example.bms;

import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.protocol.Resultset;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.sql.*;
import java.time.LocalDate;

import static com.example.bms.BMS.connection;

public class UserController {
    public static Parent seeBalance() throws SQLException, ClassNotFoundException {
        UserView.transferbtn.setOnAction(e->{
            try {

                BMS.show(UserController.transferMoney(),BMS.stage);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        });
        UserView.transactionbtn.setOnAction(e->{
            BMS.show(UserController.seeTransaction(),BMS.stage);

        });
        BMS.initializeDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement("select balance from tbl_customer where account_number = ?");
        preparedStatement.setLong(1,User.accountNumber);
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
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        });
        UserView.transactionbtn.setOnAction(e->{
            BMS.show(UserController.seeTransaction(),BMS.stage);

        });
        UserView.btnComplete.setOnAction(e-> {
            try {
                BMS.initializeDatabase();
            String sql = "update tbl_customer set balance = ? where account_number = ?";
             sql = "select * balance  tbl_customer set balance = ? where account_number = ?";
                CallableStatement preparedStatement1 = connection.prepareCall("{CALL spTransferFund(?,?,?,?,?)}");

                preparedStatement1.setDouble(1, Double.parseDouble(UserView.txtFldAmount.getText()));
                preparedStatement1.setInt(2, Integer.parseInt(UserView.txtFldReceiverAcc.getText()));
                preparedStatement1.setInt(3,User.id);
                preparedStatement1.setInt(4,User.id);
                preparedStatement1.registerOutParameter(5,Types.BOOLEAN);
                System.out.println("not executed");
                preparedStatement1.execute();
                System.out.println("executed");
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
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        return UserView.userHome("transfer");
    }
    public static Parent seeTransaction(){
        TransactionView.load();;
        UserView.balancebtn.setOnAction(e->{
            try {
                BMS.show(UserController.seeBalance(),BMS.stage);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        });
        UserView.transferbtn.setOnAction(e->{
            try {
                BMS.show(UserController.transferMoney(),BMS.stage);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        });
        return UserView.userHome("transaction");

    }
    public static boolean check(String password ,String username) throws SQLException, ClassNotFoundException {
        BMS.initializeDatabase();
        String sql = "select user_name_, password_ , account_number,customer_id from tbl_customer where user_name_=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        ResultSet resultset = preparedStatement.executeQuery();
        while (resultset.next())
            if (resultset.getString(1).equalsIgnoreCase(username)&&resultset.getString(2).equalsIgnoreCase(password)) {
                User.accountNumber = resultset.getLong(3);
                User.id = resultset.getInt(4);
                return true;
            }
        return false;
    }
}
