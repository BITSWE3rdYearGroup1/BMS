package com.example.bms;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

import static com.example.bms.AdminView.adminView;
import static com.example.bms.AdminView.txFldBranchID;
import static com.example.bms.TellerView.*;
public class AdminController {
    public  static String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    public  static String balanceRegex = "\\b\\d+(\\.\\d+)?\\b\n";
    public static Parent createTellerAcc(){
        AdminView.teller.setOnAction(e->{
            BMS.show( AdminController.seeTellerInfo(),BMS.stage);
            AdminView.btnCreateAcc.setText("UPDATE");
        });
        File[] selectedImage = new File[1];
        btnBrowse.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            selectedImage[0] = fileChooser.showOpenDialog(new Stage());
            ImageView imageView = new ImageView(new Image(selectedImage[0].getAbsolutePath()));
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            btnBrowse.setGraphic(imageView);
        });
            try {
                AdminView.btnCreateAcc.setOnAction(event -> {
                    try {
                        if (validateTeller())
                            if (isUsernameFound(AdminView.txFldUName.getText())  )
                            {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Information");
                                alert.setHeaderText("Insertion Alert ");
                                alert.setContentText("User name not available");
                                alert.showAndWait();
                            }
                        else if (selectedImage[0] == null) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Information");
                                alert.setHeaderText("Insertion alert");
                                alert.setContentText("Please Upload image");
                                alert.showAndWait();
                            } else
                                try {
                                    byte[] imageByte = Files.readAllBytes(selectedImage[0].toPath());
                                    BMS.initializeDatabase();
                                    String sql = "insert into tbl_teller(first_name ,last_name ,gender ,user_name ,password ,phone ,email ,registration_date,  branch_ID,photo,teller_id) values(?,?,?,?,?,?,?,?,'1',?,?)";
                                    PreparedStatement preparedStatement = BMS.connection.prepareStatement(sql);
                                    preparedStatement.setString(1, AdminView.txFldFName.getText());
                                    preparedStatement.setString(2, AdminView.txFldLName.getText());
                                    preparedStatement.setString(3, AdminView.txFldGender.getText());
                                    preparedStatement.setString(4, AdminView.txFldUName.getText());
                                    preparedStatement.setString(5, AdminView.txFldPass.getText());
                                    preparedStatement.setString(6, AdminView.txFldPhone.getText());
                                    preparedStatement.setString(7, AdminView.txFldEmail.getText());
                                    preparedStatement.setDate(8, Date.valueOf(LocalDate.now()));
                                    preparedStatement.setBytes(9, imageByte);
                                    preparedStatement.setInt(10,Integer.parseInt(txFldBranchID.getText()));
                                    preparedStatement.executeUpdate();
                                    AdminView.txFldFName.clear();
                                    txFldBranchID.clear();
                                    TellerView.txtFldConfirmPasswd.clear();
                                    AdminView.txFldLName.clear();
                                    AdminView.txFldGender.clear();
                                    AdminView.txFldUName.clear();
                                    AdminView.txFldPass.clear();
                                    AdminView.txFldEmail.clear();
                                    AdminView.txFldPhone.clear();
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Information");
                                    alert.setHeaderText("Insertion Alert ");
                                    alert.setContentText("The user Inserted  successfully");
                                    alert.showAndWait();
                                    btnBrowse.setGraphic(AdminView.image);
                                } catch (SQLException | ClassNotFoundException ex) {
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Information");
                                    alert.setHeaderText("Insertion Alert");
                                    alert.setContentText("Not Inserted  successfully");
                                    // Display the alert
                                    alert.showAndWait();
                                    throw new RuntimeException(ex);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        return   AdminView.getHome("create");
    }
    public static Parent seeTellerInfo(){
        new AdminView();
        tellerTableView = new TableView<>();
        AdminView.btnSearchTeller.setOnAction(e->{
            if (AdminView.txtFldTeller.getText().equalsIgnoreCase(""))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Notification Alert ");
                alert.setContentText("Please Teller ID");
                alert.showAndWait();
            }
            else
                try {
                    String tellerId = AdminView.txtFldTeller.getText();
                    if (findTeller(tellerId)) {
                        AdminView.name.setText("Name : " +Admin.teller.getFirstName()+ " " + Admin.teller.getSecondName());
                        AdminView.Ugender.setText("Gender : "  + Admin.teller.getGender());
                        AdminView.branchName.setText("Teller ID : "  + Admin.teller.getTeller_id());
                        AdminView.emailS.setText("Email : " +Admin.teller.getEmail());
                        AdminView.phoneNumber.setText("Phone Number : " +Admin.teller.getPhone());
                        AdminView.profile = new ImageView(Admin.teller.getImage());
                        BMS.show(displayTeller(), BMS.stage);
                        AdminView.txtFldTeller.clear();
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Information");
                        alert.setHeaderText("Alert Example");
                        alert.setContentText("Account does not found");
                        alert.showAndWait();
                    }

                } catch (SQLException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
        });
        AdminView.btnAccount.setOnAction(e->{
            BMS.show( AdminController.createTellerAcc(),BMS.stage);
        });

        try {
            // Establish a connection
            // Create a statement
            PreparedStatement pstm = BMS.connection.prepareStatement("select *from tbl_teller");
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getBytes(11) != null)
                AdminView.adminView.tellerList.add(new Teller(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                new Image(new ByteArrayInputStream( resultSet.getBytes(11))),
                resultSet.getInt(10)));
            }
            tellerTableView.setItems(AdminView.adminView.tellerList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return AdminView.getHome("tellers");
    }

    public static boolean checkAccount(String account ) throws SQLException {
        PreparedStatement preparedStatement = BMS.connection.prepareStatement("select * from tbl_customer where account_number = ?");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            return false;
        return true;
    }

    /**
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public static boolean findTeller(String tellerID) throws SQLException, ClassNotFoundException {
        BMS.initializeDatabase();
        PreparedStatement preparedStatement =BMS.connection.prepareStatement("select teller_id,first_name , last_name ,gender ,photo , email, phone ,branch_ID ,user_name ,password from  tbl_teller where teller_id= ?");
        preparedStatement.setString(1,tellerID);
        ResultSet resultset = preparedStatement.executeQuery();
        if (resultset.next()){
            Admin.teller.setTeller_id(resultset.getInt(1));
            Admin.teller.setFirstName(resultset.getString(2));
            Admin.teller.setSecondName(resultset.getString(3));
            Admin.teller.setGender(resultset.getString(4));
            Admin.teller.setPhone(resultset.getString(7));
            Admin.teller.setEmail(resultset.getString(6));
            Admin.teller.setBranchID(Integer.parseInt(resultset.getString(8)));
            Admin.teller.setUsername((resultset.getString(9)));
            Admin.teller.setPassword((resultset.getString(10)));
            Admin.teller.setPhoto(new Image(new ByteArrayInputStream(resultset.getBytes(5))));
            return true;
        }
        return false;
    }

    public static Parent  displayTeller(){
        AdminView.btnDelete.setOnAction(e->{
            try {
                PreparedStatement preparedStatement1 = BMS.connection.prepareStatement("delete from tbl_teller  where teller_id = ?");
                preparedStatement1.setInt(1,Admin.teller.getTeller_id());
                if (preparedStatement1.executeUpdate() > 0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information");
                    alert.setHeaderText("Delete Alert");
                    alert.setContentText("Teller successfully  deleted");
                    alert.showAndWait();
                    BMS.show(AdminController.seeTellerInfo(),BMS.stage);
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information");
                    alert.setHeaderText("Alert Example");
                    alert.setContentText("Teller not deleted");
                    // Display the alert
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        AdminView.btnNotify.setOnAction(e->{
            BMS.show(AdminController.seeTellerInfo(),BMS.stage);
        });
        AdminView.btnUpdate.setOnAction(e->{
            BMS.show(updateTeller(),BMS.stage);
            AdminView.txFldFName.setText(Admin.teller.getFirstName());
            AdminView.txFldLName.setText(Admin.teller.getSecondName());
            AdminView.txFldEmail.setText(Admin.teller.getEmail());
            AdminView.txFldPhone.setText(Admin.teller.getPhone());
            AdminView.txFldPass.setText(Admin.teller.getPassword());
            AdminView.txFldGender.setText(Admin.teller.getGender());
            AdminView.txFldUName.setText(Admin.teller.getUsername());
            AdminView.txtFldConfirmPasswd.setText(Admin.teller.getPassword());
            txFldBranchID.setText(Integer.toString(Admin.teller.getTeller_id()));
            ImageView photo = new ImageView(Admin.teller.getImage());
            photo.setFitWidth(50);
            photo.setFitHeight(50);
            btnBrowse.setGraphic(photo);
            AdminView.btnCreateAcc.setText("UPDATE");
        });
        return AdminView.getHome("update");
    }
    public static Parent updateTeller(){
        File[] selectedImage = new File[1];
        btnBrowse.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            selectedImage[0] = fileChooser.showOpenDialog(new Stage());
            ImageView imageView = new ImageView(new Image(selectedImage[0].getAbsolutePath()));
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            btnBrowse.setGraphic(imageView);
        });
        try {
            AdminView.btnCreateAcc.setOnAction(event -> {
                if (validateTeller())
                        try {
                            byte[] imageByte = new byte[60000];
                            if (selectedImage[0] != null)
                            imageByte= Files.readAllBytes(selectedImage[0].toPath());
                            BMS.initializeDatabase();
                            String sql = "UPDATE tbl_teller SET first_name = ?, last_name = ?, gender = ?, user_name = ?, password = ?, phone = ?, email = ?, registration_date = ?, teller_id = ? WHERE teller_id = ?";
                            PreparedStatement preparedStatement = BMS.connection.prepareStatement(sql);
                            preparedStatement.setString(1, AdminView.txFldFName.getText());
                            preparedStatement.setString(2, AdminView.txFldLName.getText());
                            preparedStatement.setString(3, AdminView.txFldGender.getText());
                            preparedStatement.setString(4, AdminView.txFldUName.getText());
                            preparedStatement.setString(5, AdminView.txFldPass.getText());
                            preparedStatement.setString(6, AdminView.txFldPhone.getText());
                            preparedStatement.setString(7, AdminView.txFldEmail.getText());
                            preparedStatement.setDate(8, Date.valueOf(LocalDate.now()));
                            if (selectedImage[0] !=null) {
                                PreparedStatement preparedStatement1 = BMS.connection.prepareStatement("update tbl_teller set photo = ? where teller_Id = ?");
                                preparedStatement1.setBytes(1,imageByte);
                                preparedStatement1.setInt(2,Admin.teller.getTeller_id());
                                preparedStatement1.executeUpdate();
                            }
                            preparedStatement.setInt(9,Integer.parseInt(txFldBranchID.getText()));
                            preparedStatement.setInt(10,Admin.teller.getTeller_id());
                            preparedStatement.executeUpdate();
                            AdminView.txFldFName.clear();
                            txFldBranchID.clear();
                            TellerView.txtFldConfirmPasswd.clear();
                            AdminView.txFldLName.clear();
                            AdminView.txFldGender.clear();
                            AdminView.txFldUName.clear();
                            AdminView.txFldPass.clear();
                            AdminView.txFldEmail.clear();
                            AdminView.txFldPhone.clear();
                            AdminView.btnCreateAcc.setText("Register");
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information");
                            alert.setHeaderText("Insertion Alert ");
                            alert.setContentText("teller updated  successfully");
                            alert.showAndWait();
                            btnBrowse.setGraphic(AdminView.image);
                        } catch (SQLException | ClassNotFoundException ex) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Information");
                            alert.setHeaderText("Insertion Alert");
                            alert.setContentText("Not Inserted  successfully");
                            // Display the alert
                            alert.showAndWait();
                            throw new RuntimeException(ex);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  AdminView.getHome("create");
    }
    public static boolean validateTeller(){
        if (AdminView.txFldFName.getText().isEmpty()) {
            txFldFName.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText( "First Name  is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional<?>) result).isPresent() && result.get() == ButtonType.OK) {
                txFldFName.setStyle("-fx-border-color: black;");
            } else {
                txFldFName.setStyle("-fx-border-color: black;");
            }
            return false;
        }
        else if (txFldLName.getText().isEmpty()) {
            txFldLName.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText( "Last Name  is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                txFldLName.setStyle("-fx-border-color: black;");
            } else {
                txFldLName.setStyle("-fx-border-color: black;");
            }
        }
        else if (txFldGender.getText().isEmpty()) {
            txFldGender.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText( "Gender  is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                txFldGender.setStyle("-fx-border-color: black;");
            } else {
                txFldGender.setStyle("-fx-border-color: black;");
            }
        }
        else if (txFldBranchID.getText().isEmpty()) {
            txFldBranchID.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText( "Branch id  is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional<?>) result).isPresent() && result.get() == ButtonType.OK) {
                txFldBranchID.setStyle("-fx-border-color: black;");
            } else {
                txFldBranchID.setStyle("-fx-border-color: black;");
            }
        }
        else if (txFldEmail.getText().isEmpty()) {
            txFldEmail.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText( "Email  is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional<?>) result).isPresent() && result.get() == ButtonType.OK) {
                txFldEmail.setStyle("-fx-border-color: black;");
            } else {
                txFldEmail.setStyle("-fx-border-color: black;");
            }
        }else if (txFldPhone.getText().isEmpty()) {
            txFldPhone.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText( "Phone number  is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional<?>) result).isPresent() && result.get() == ButtonType.OK) {
                txFldPhone.setStyle("-fx-border-color: black;");
            } else {
                txFldPhone.setStyle("-fx-border-color: black;");
            }
        }else if (txFldPhone.getText().length() != 10) {
            txFldPhone.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText( "Please check the correct phone number");
            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional<?>) result).isPresent() && result.get() == ButtonType.OK) {
                txFldPhone.setStyle("-fx-border-color: black;");
            } else {
                txFldPhone.setStyle("-fx-border-color: black;");
            }
        }else if (!txFldPhone.getText().matches(TellerController.phoneNumberRegex)) {
            txFldPhone.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText( "Please enter number only for phone number");
            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional<?>) result).isPresent() && result.get() == ButtonType.OK) {
                txFldPhone.setStyle("-fx-border-color: black;");
            } else {
                txFldPhone.setStyle("-fx-border-color: black;");
            }
        } else if (!txFldEmail.getText().matches(emailRegex)) {
            txFldEmail.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText( "Please enter correct email format");
            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional<?>) result).isPresent() && result.get() == ButtonType.OK) {
                txFldEmail.setStyle("-fx-border-color: black;");
            } else {
                txFldEmail.setStyle("-fx-border-color: black;");
            }

        } else if (txFldUName.getText().isEmpty()) {
            txFldUName.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText( "User name   is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional<?>) result).isPresent() && result.get() == ButtonType.OK) {
                txFldUName.setStyle("-fx-border-color: black;");
            } else {
                txFldUName.setStyle("-fx-border-color: black;");
            }

        } else if (!txFldUName.getText().startsWith("mbt")) {
            txFldUName.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText( "Please start with \'mbt\' for teller username");
            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional<?>) result).isPresent() && result.get() == ButtonType.OK) {
                txFldUName.setStyle("-fx-border-color: black;");
            } else {
                txFldUName.setStyle("-fx-border-color: black;");
            }
        } else if (txFldUName.getText().length()<8) {
            txFldUName.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText( "The username should be greater than or equal to 8");
            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional<?>) result).isPresent() && result.get() == ButtonType.OK) {
                txFldUName.setStyle("-fx-border-color: black;");
            } else {
                txFldUName.setStyle("-fx-border-color: black;");
            }
        } else if (txFldPass.getText().isEmpty()) {
            txFldPass.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText( "Password  is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional<?>) result).isPresent() && result.get() == ButtonType.OK) {
                txFldPass.setStyle("-fx-border-color: black;");
            } else {
                txFldPass.setStyle("-fx-border-color: black;");
            }
        }
        else if (txFldPass.getText().length()<4 || txFldPass.getText().length()> 8 ) {
            txFldPass.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText( "Password should be 4 up to 8");
            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional<?>) result).isPresent() && result.get() == ButtonType.OK) {
                txFldPass.setStyle("-fx-border-color: black;");
            } else {
                txFldPass.setStyle("-fx-border-color: black;");
            }
        }
        else if (txtFldConfirmPasswd.getText().isEmpty()) {
            txtFldConfirmPasswd.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText( " confirm password  is empty");
            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional<?>) result).isPresent() && result.get() == ButtonType.OK) {
                txtFldConfirmPasswd.setStyle("-fx-border-color: black;");
            } else {
                txtFldConfirmPasswd.setStyle("-fx-border-color: black;");
            }
        }
        else if (!txFldPass.getText().equals(txtFldConfirmPasswd.getText())) {
            txtFldConfirmPasswd.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText( "Please enter the same confirmation password");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                txtFldConfirmPasswd.setStyle("-fx-border-color: black;");
            } else {
                txtFldConfirmPasswd.setStyle("-fx-border-color: black;");
            }
        }
        else
            return true;
        return false;
    }
    public static boolean isUsernameFound(String username ) throws SQLException {
        PreparedStatement preparedStatement = BMS.connection.prepareStatement("select * from tbl_teller where user_name = ?");
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }
}
