package com.example.bms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ProgressBarTreeTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class TransactionView {
    public static TransactionView transactionView ;
    public  TableView<Transaction> transactionTableView = new TableView<Transaction>();
    public ObservableList<Transaction> transactionList  = FXCollections.observableArrayList();
    public static TableColumn<Transaction, Integer> id = new TableColumn<Transaction,Integer>("Transaction ID");
    public  static TableColumn<Transaction,String> accountNumber = new TableColumn<Transaction,String>("Source");
    public  static TableColumn<Transaction,String> destination = new TableColumn<Transaction,String>("Destination");
    public  static TableColumn<Transaction,String> transactionType = new TableColumn<Transaction,String>("Transaction Type");
    public  static TableColumn<Transaction,Double> amount = new TableColumn<Transaction,Double>("Amount");
    public  static TableColumn<Transaction,String> transactionDate = new TableColumn<Transaction,String>("Date");
//    public  static TableColumn<Transaction,String> customerName = new TableColumn<Transaction,String>("Customer Name");
//    public  static TableColumn<Transaction,String> tellerName = new TableColumn<Transaction,String>("Teller Name");
    public static void load(){
        transactionView = new TransactionView();
    }
    public static StackPane loadTransactionTableView(){
        StackPane transactionTable = new StackPane();
        id.getStyleClass().add(EmployeeView.className);
        accountNumber.getStyleClass().add(EmployeeView.className);
        destination.getStyleClass().add(EmployeeView.className);
        transactionType.getStyleClass().add(EmployeeView.className);
        amount.getStyleClass().add(EmployeeView.className);
        transactionDate.getStyleClass().add(EmployeeView.className);
//        customerName.getStyleClass().add(EmployeeView.className);
//        tellerName.getStyleClass().add(EmployeeView.className);
        id.setMinWidth(100);
        accountNumber.setMinWidth(130);
        destination.setMinWidth(100);
        transactionDate.setMinWidth(100);
        transactionType.setMinWidth(100);
//        customerName.setMinWidth(100);
//        tellerName.setMinWidth(100);
        amount.setMinWidth(100);
        id.setPrefWidth(AdminView.UPdate.resize(id.getText()));
        accountNumber.setPrefWidth(AdminView.UPdate.resize("accountNumber.get"));
        destination.setPrefWidth(AdminView.UPdate.resize(destination.getText()));
        transactionType.setPrefWidth(AdminView.UPdate.resize(transactionType.getText()));
        transactionDate.setPrefWidth(AdminView.UPdate.resize("transactionDate"));
//        customerName.setPrefWidth(AdminView.UPdate.resize(customerName.getText()));
//        tellerName.setPrefWidth(AdminView.UPdate.resize(tellerName.getText()));
        amount.setPrefWidth(AdminView.UPdate.resize(amount.getText()));

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        accountNumber.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        transactionType.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        transactionDate.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
//        customerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
//        tellerName.setCellValueFactory(new PropertyValueFactory<>("TellerName"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        TransactionView.transactionView.transactionTableView.getColumns().addAll(id,accountNumber,amount,destination,transactionType,transactionDate);
        transactionTable.getChildren().add(TransactionView.transactionView.transactionTableView);
        transactionTable.setMaxSize(BMS.scene.getWidth()*0.52,500);
        return transactionTable;
    }
}
