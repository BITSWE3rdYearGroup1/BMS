package com.example.bms;

import javafx.scene.Parent;

import java.sql.*;

public class TransactionController {
    public static Parent displayTransaction(){
        try {
            // Establish a connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BMS", "eziraa", "1234");
            // Create a statement
            PreparedStatement pstm = con.prepareStatement("select * from tbl_transaction where customer_id = ?");
            pstm.setInt(1,User.user.getCustomerID());
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                TransactionView.transactionView.transactionList.add(new Transaction(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8)));
                System.out.println(resultSet.getString(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+ " "+resultSet.getString(4)+" "+resultSet.getString(5)+ " " +resultSet.getString(6)+ " "+ resultSet.getString(7)+ " "+resultSet.getString(8));
            }
            TransactionView.transactionView.transactionTableView.setItems(TransactionView.transactionView.transactionList);
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return TransactionView.loadTransactionTableView();
    }
}
