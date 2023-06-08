package com.example.bms;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Date;

public class Admin {
    public static User user = new User();
    public static Teller teller = new Teller();
    private String firstName;
    private String secondName;
    private String gender;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Date registrationDate;
    private String photo;
    private int branchID;

    public Admin(String firstName, String secondName, String gender, String username, String password, String phone, String email, Date registrationDate, String photo, int branchID) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.gender = gender;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.registrationDate = registrationDate;
        this.photo = photo;
        this.branchID = branchID;
    }

    public Admin(String firstName, String secondName, String gender, String username, String password, String phone, String email, String registrationDate, String photo, String branchID) {

        this.firstName = firstName;
        this.secondName = secondName;
    }
    public Admin(){}

    public static void main(String[] args) {

    }
    public Parent createTellerAcc(Stage stage){
       return AdminView.createAccView();

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getBranchID() {
        return branchID;
    }

    public void setBranchID(int branchID) {
        this.branchID = branchID;
    }
}
