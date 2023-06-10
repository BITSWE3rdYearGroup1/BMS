package com.example.bms;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;

import java.util.Objects;


public class User {
    public static User user = new User();
    public    long accountNumber;
    public  int id;
    private final SimpleIntegerProperty customerID;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty secondName;
    private final SimpleStringProperty gender;
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;
    private final SimpleStringProperty accNumber ;
    private final SimpleDoubleProperty balance;
    private final SimpleStringProperty email;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty registrationDate;
    private Image photo;

    public User(int customerID, String firstName, String secondName, String gender, String username, String password, String accNumber, double balance, String email, String phone, String registrationDate, Image photo) {
        this.customerID =new SimpleIntegerProperty(customerID);
        this.firstName = new SimpleStringProperty(firstName);
        this.secondName = new SimpleStringProperty(secondName);
        this.gender =new SimpleStringProperty( gender);
        this.username =new SimpleStringProperty( username);
        this.password = new SimpleStringProperty(password);
        this.accNumber = new SimpleStringProperty(accNumber);
        this.balance =new SimpleDoubleProperty( balance);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.registrationDate =new SimpleStringProperty( registrationDate);
        this.photo = (photo);
    }
public User(){

    this.customerID =new SimpleIntegerProperty(0);
    this.firstName = new SimpleStringProperty("firstName");
    this.secondName = new SimpleStringProperty("secondName");
    this.gender =new SimpleStringProperty( "gender");
    this.username =new SimpleStringProperty( "username");
    this.password = new SimpleStringProperty("password");
    this.accNumber = new SimpleStringProperty("0");
    this.balance =new SimpleDoubleProperty( 0);
    this.email = new SimpleStringProperty("email");
    this.phone = new SimpleStringProperty("phone");
    this.registrationDate =new SimpleStringProperty( "registrationDate");
    this.photo = (new Image(Objects.requireNonNull(User.class.getResourceAsStream("new.jpg"))));
}

    public  int getCustomerID() {
        return customerID.get();
    }

    public  SimpleIntegerProperty customerIDProperty() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID.set(customerID);
    }

    public  String getFirstName() {
        return firstName.get();
    }

    public  SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public  String getSecondName() {
        return secondName.get();
    }

    public  SimpleStringProperty secondNameProperty() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName.set(secondName);
    }

    public  String getGender() {
        return gender.get();
    }

    public  SimpleStringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public  String getUsername() {
        return username.get();
    }

    public  SimpleStringProperty usernameProperty() {
        return username;
    }

    public  void setUsername(String username) {
        this.username.set(username);
    }

    public  String getPassword() {
        return password.get();
    }

    public  SimpleStringProperty passwordProperty() {
        return password;
    }

    public  void setPassword(String password) {
        this.password.set(password);
    }

    public  String getAccNumber() {
        return accNumber.get();
    }

    public  SimpleStringProperty accNumberProperty() {
        return accNumber;
    }

    public  void setAccNumber(String accNumber) {
        this.accNumber.set(accNumber);
    }

    public  double getBalance() {
        return balance.get();
    }

    public  SimpleDoubleProperty balanceProperty() {
        return balance;
    }

    public  void setBalance(double balance) {
        this.balance.set(balance);
    }

    public  String getEmail() {
        return email.get();
    }

    public  SimpleStringProperty emailProperty() {
        return email;
    }

    public  void setEmail(String email) {
        this.email.set(email);
    }

    public  String getPhone() {
        return phone.get();
    }

    public  SimpleStringProperty phoneProperty() {
        return phone;
    }

    public  void setPhone(String phone) {
        this.phone.set(phone);
    }

    public  String getRegistrationDate() {
        return registrationDate.get();
    }

    public  SimpleStringProperty registrationDateProperty() {
        return registrationDate;
    }

    public  void setRegistrationDate(String registrationDate) {
        this.registrationDate.set(registrationDate);
    }



    public Image getImage() {
        return photo;
    }

    public ObservableValue<Image> imageProperty() {
        return new ObservableValue<Image>() {
            @Override
            public void addListener(ChangeListener<? super Image> changeListener) {

            }

            @Override
            public void removeListener(ChangeListener<? super Image> changeListener) {

            }

            @Override
            public Image getValue() {
                return photo;
            }

            @Override
            public void addListener(InvalidationListener invalidationListener) {

            }

            @Override
            public void removeListener(InvalidationListener invalidationListener) {

            }
        };
    }

    public void setPhoto(Image image) {
        this.photo = image;
    }
}























