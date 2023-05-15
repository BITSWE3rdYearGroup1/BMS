package com.example.bms;

import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;

public class Teller {
    Color WHITE = Color.WHITE;
    public static void main(String[] args) {

    }
    public GridPane createUserAccountPage(Stage stage) {
        Text createUserAccount = new Text("Create Teller Account");
        Label fname = new Label("First Name:");
        fname.setTextFill(WHITE);
        Label mname = new Label("Middle Name:");
        Label lname = new Label("Last Name:");
        Label Gender = new Label("Gender:");
        Label bdate = new Label("Birth Date:");
        Label AId = new Label("Admin ID:");
        Label email = new Label("Email");
        Label phoneNum = new Label("Phone Number");
        Label photo = new Label("Photo");
        Label regDate = new Label("Registration Date");
        Label tellerId = new Label("Teller ID");
        Label userName = new Label("User Name");
        Label password = new Label("Password");
        TextField fnameTxt = new TextField();
        TextField mnameTxt = new TextField();
        TextField lnameTxt = new TextField();
        TextField genderTxt = new TextField();
        TextField bdateTxt = new TextField();
        TextField AIdTxt = new TextField();
        TextField emailTxt = new TextField();
        TextField phoneNumTxt = new TextField();
        Button browse = new Button("");
        ImageView image = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("Image/account-modified.png"))));
        browse.setGraphic(image);// made the upload image inside the brose button
        image.setFitWidth(85);
        image.setFitHeight(85);
        browse.setOnAction(e->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Photo");
            File initialDirectory = new File(System.getProperty("user.home"));
            fileChooser.setInitialDirectory(initialDirectory);
            FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter(
                    "Image Files", "*.jpg", "*.jpeg", "*.png");
            fileChooser.getExtensionFilters().add(imageFilter);
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                System.out.println("Selected photo: " + selectedFile.getAbsolutePath());
            }
        });

        DatePicker regdatePicker = new DatePicker();
        TextField tellerIdTxt = new TextField();
        TextField userNameTxt = new TextField();
        PasswordField passwordTxt = new PasswordField();
        Button create = new Button("Create");
        Button back = new Button("Back");

        GridPane createUserAccountPage = new GridPane();
        createUserAccountPage.add(createUserAccount,0,0);
        createUserAccountPage.add(fname, 0, 2);
        createUserAccountPage.add(fnameTxt, 1, 2);
        createUserAccountPage.add(lname, 0, 4);
        createUserAccountPage.add(lnameTxt, 1, 4);
        createUserAccountPage.add(mname, 0, 3);
        createUserAccountPage.add(mnameTxt, 1, 3);
        createUserAccountPage.add(genderTxt, 1, 5);
        createUserAccountPage.add(Gender, 0, 5);
        createUserAccountPage.add(bdate, 0, 6);
        createUserAccountPage.add(bdateTxt, 1, 6);
        createUserAccountPage.add(AId, 0, 7);
        createUserAccountPage.add(AIdTxt, 1, 7);
        createUserAccountPage.add(email, 0, 8);
        createUserAccountPage.add(emailTxt, 1, 8);
        createUserAccountPage.add(phoneNum, 0, 9);
        createUserAccountPage.add(phoneNumTxt, 1, 9);
        createUserAccountPage.add(photo, 0, 10);
        createUserAccountPage.add(browse,1,10);
        createUserAccountPage.add(regDate, 0, 11);
        createUserAccountPage.add(regdatePicker, 1, 11);
        createUserAccountPage.add(tellerId, 0, 12);
        createUserAccountPage.add(tellerIdTxt, 1, 12);
        createUserAccountPage.add(userName, 0, 13);
        createUserAccountPage.add(userNameTxt, 1, 13);
        createUserAccountPage.add(password, 0, 14);
        createUserAccountPage.add(passwordTxt, 1, 14);
        createUserAccountPage.add(create, 1, 15);
        createUserAccountPage.add(back, 2, 15);
        // here we set the space between the labels and TextFields
        ColumnConstraints column1 = new ColumnConstraints(100);// column1's width is set to 100
        ColumnConstraints column2 = new ColumnConstraints();// we let the 2nd column to fill the rest
        column2.setHgrow(Priority.ALWAYS);// let column2 grow horizontally
        createUserAccountPage.getColumnConstraints().addAll(column1, column2);//then column constraints got added
        //
        createUserAccountPage.setHgap(23);
        createUserAccountPage.setVgap(7);
        createUserAccount.setTranslateY(-30);
        createUserAccount.setTranslateX(100);
        createUserAccount.setFont(Font.font("verdana", FontWeight.BOLD, 33));
        createUserAccount.getStyleClass().add("styled");
        createUserAccount.setFill(WHITE);
        return createUserAccountPage;
    }
}
