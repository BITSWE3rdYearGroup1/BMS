package com.example.bms;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Employee {
    private final SimpleIntegerProperty teller_id;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty secondName;
    private final SimpleStringProperty gender;
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty email;
    private final SimpleStringProperty registrationDate;
    private final SimpleStringProperty photo;

    public Employee(int id, String firstName, String secondName, String gender, String username, String password, String phone, String email, String registrationDate, String photo) {
        this.teller_id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.secondName = new SimpleStringProperty(secondName);
        this.gender = new SimpleStringProperty(gender);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.registrationDate = new SimpleStringProperty( registrationDate);
        this.photo = new SimpleStringProperty(photo);
    }


    public int getTeller_id() {
        return teller_id.get();
    }

    public SimpleIntegerProperty teller_idProperty() {
        return teller_id;
    }

    public void setTeller_id(int teller_id) {
        this.teller_id.set(teller_id);
    }
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate.set(registrationDate);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getSecondName() {
        return secondName.get();
    }

    public SimpleStringProperty secondNameProperty() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName.set(secondName);
    }

    public String getGender() {
        return gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }





    public String getPhoto() {
        return photo.get();
    }

    public SimpleStringProperty photoProperty() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo.set(photo);
    }



    public String getRegistrationDate() {
        return registrationDate.get();
    }

    public SimpleStringProperty registrationDateProperty() {
        return registrationDate;
    }
    public static byte[] convertImage(String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int byteRead ;
        while ((byteRead=fileInputStream.read(buffer))!=-1){
            byteArrayOutputStream.write(buffer,0,byteRead);
        }
        byteArrayOutputStream.close();
        fileInputStream.close();
        return byteArrayOutputStream.toByteArray();

    }
}
