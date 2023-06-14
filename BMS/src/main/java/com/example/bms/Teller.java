package com.example.bms;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

import java.util.Objects;

public class Teller extends Employee{
    public static User user = new User() ;
    public static Teller teller = new Teller() ;
  public  static  int id = 1;
    private final SimpleIntegerProperty BranchID;

    public Teller(int id, String firstName, String secondName, String gender, String username, String password, String phone, String email, String registrationDate, Image photo, int BranchID) {
        super(id,  firstName,  secondName,  gender,  username,  password,  phone,  email,  registrationDate,  photo);
        this.BranchID = new SimpleIntegerProperty(BranchID);
    }
    public Teller(){
        super(0,"firstName","secondName","gender","username","password","09349","kew","date",new Image(Objects.requireNonNull(User.class.getResourceAsStream("new.jpg"))) );
       this.BranchID = new SimpleIntegerProperty(1);
    }
    public int getBranchID() {
        return BranchID.get();
    }

    public SimpleIntegerProperty branchIDProperty() {
        return BranchID;
    }

    public void setBranchID(int branchID) {
        this.BranchID.set(branchID);
    }



}



