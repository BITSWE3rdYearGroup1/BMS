package com.example.bms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.Image;

import javax.swing.text.html.ImageView;

public class EmployeeView {
    public static TextField txFldTellerID;
    public  static TableView<Teller> tellerTableView;
    public  ObservableList<Teller> tellerList = FXCollections.observableArrayList();
    public static TableColumn<Teller,Integer> tellerID = new TableColumn<Teller, Integer>("Teller ID");
    public  static String  className = "table-cell";

    public static TableColumn<Teller, String> firstName =  new TableColumn<Teller,String>("First Name");
    public static TableColumn<Teller, String> secondName = new TableColumn<Teller,String>("Second Name");
    public static TableColumn<Teller, String> gender  = new TableColumn<Teller,String>("Gender");
    public static TableColumn<Teller, String> username = new TableColumn<Teller,String>("Username");
    public static TableColumn<Teller, String> password =  new TableColumn<Teller,String>("Password");
    public static TableColumn<Teller, String> phone =  new TableColumn<Teller,String>("Phone number");
    public static  TableColumn<Teller, String> email =  new TableColumn<Teller,String>("Email");
    public static TableColumn<Teller, String> registrationDate =  new TableColumn<Teller,String>("Registration Date");
    public  static TableColumn<Teller, Image> photo =  new TableColumn<Teller,Image>("Photo");
    public static TableColumn<Teller, Integer> branchID =  new TableColumn<Teller,Integer>("Branch ID");


    static TextField txFldTellerId = new TextField();

    public static TextField txFldFName = new TextField();

    public static TextField txFldLName = new TextField();

    public static TextField txFldGender = new TextField();

    public static TextField txFldUName = new  TextField();

    public static PasswordField txFldPass = new PasswordField();

    public static TextField txFldPhone = new TextField();

    public static TextField txFldEmail = new TextField();
    public static PasswordField txtFldConfirmPasswd = new PasswordField();

    public static Button btnCreateAcc = new Button("Register");

}
