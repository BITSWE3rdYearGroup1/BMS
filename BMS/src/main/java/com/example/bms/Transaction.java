package com.example.bms;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Transaction {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty accountNumber;
    private final SimpleStringProperty destination;
    private final SimpleStringProperty transactionType;
    private final SimpleDoubleProperty amount;
    private final SimpleStringProperty transactionDate;
    private final SimpleStringProperty CustomerName;
    private final SimpleStringProperty TellerName;

    public Transaction(int id,String transactionType, String accountNumber,  double amount, String destination,  String transactionDate, String customerName, String tellerName) {
        this.id =new SimpleIntegerProperty(id);
        this.destination = new SimpleStringProperty(destination);
        this.accountNumber = new SimpleStringProperty(accountNumber);
        this.transactionType = new SimpleStringProperty( transactionType);
        this.amount = new SimpleDoubleProperty( amount);
        this.transactionDate = new SimpleStringProperty( transactionDate);
        CustomerName = new SimpleStringProperty(customerName);
        TellerName = new SimpleStringProperty(tellerName);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }


    public String getTransactionType() {
        return transactionType.get();
    }

    public SimpleStringProperty transactionTypeProperty() {
        return transactionType;
    }

    public String getAccountNumber() {
        return accountNumber.get();
    }

    public SimpleStringProperty accountNumberProperty() {
        return accountNumber;
    }

    public double getAmount() {
        return amount.get();
    }

    public SimpleDoubleProperty amountProperty() {
        return amount;
    }



    public String getCustomerName() {
        return CustomerName.get();
    }

    public SimpleStringProperty customerNameProperty() {
        return CustomerName;
    }

    public String getTellerName() {
        return TellerName.get();
    }

    public SimpleStringProperty tellerNameProperty() {
        return TellerName;
    }

    public String getTransactionDate() {
        return transactionDate.get();
    }

    public SimpleStringProperty transactionDateProperty() {
        return transactionDate;
    }

    public String getDestination() {
        return destination.get();
    }

    public SimpleStringProperty destinationProperty() {
        return destination;
    }
}
