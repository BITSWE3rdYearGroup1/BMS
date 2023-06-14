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

import static com.example.bms.AdminView.txFldBranchID;
import static com.example.bms.BMS.connection;
import static com.example.bms.BMS.stage;
import static com.example.bms.TellerView.*;
public class TellerController {
    public static String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    public static String balanceRegex = "^\\d+(\\.\\d+)?$";
    public static String accountNumberRegex = "\\d+";
    public static String phoneNumberRegex = "\\d+";

    public static Parent createAcc() throws SQLException, ClassNotFoundException {
        File[] selectedImage = new File[1];
        TellerView.btnBrowse.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            selectedImage[0] = fileChooser.showOpenDialog(new Stage());
            ImageView imageView = new ImageView(new Image(selectedImage[0].getAbsolutePath()));
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            TellerView.btnBrowse.setGraphic(imageView);
        });
        TellerView.create.setOnAction(event -> {
            if (validateInput()) {
                try {
                    if (checkAccount(txtFldAccNumber.getText()))
                        if (isUsernameFound(EmployeeView.txFldUName.getText())) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information");
                            alert.setHeaderText("Insertion Alert ");
                            alert.setContentText("User name not available");
                            alert.showAndWait();
                        } else if (selectedImage[0] == null) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Information");
                            alert.setHeaderText("Insertion alert");
                            alert.setContentText("Please Upload image");
                            alert.showAndWait();
                        } else
                            try {
                                byte[] imageByte = Files.readAllBytes(selectedImage[0].toPath());
                                BMS.initializeDatabase();
                                String sql = "insert into tbl_customer(first_name ,last_name ,gender ,user_name ,password ,account_number ,balance ,phone,email ,registration_date ,photo ,teller_id ) values(?,?,?,?,?,?,?,?,?,?,?,?)";
                                PreparedStatement preparedStatement = BMS.connection.prepareStatement(sql);
                                preparedStatement.setString(1, EmployeeView.txFldFName.getText());
                                preparedStatement.setString(2, EmployeeView.txFldLName.getText());
                                preparedStatement.setString(3, EmployeeView.txFldGender.getText());
                                preparedStatement.setString(4, EmployeeView.txFldUName.getText());
                                preparedStatement.setString(5, EmployeeView.txFldPass.getText());
                                preparedStatement.setString(6, TellerView.txtFldAccNumber.getText());
                                preparedStatement.setFloat(7, Float.parseFloat(txtFldBalance.getText()));
                                preparedStatement.setString(9, TellerView.txFldEmail.getText());
                                preparedStatement.setString(8, EmployeeView.txFldPhone.getText());
                                preparedStatement.setDate(10, Date.valueOf(LocalDate.now()));
                                preparedStatement.setBytes(11, imageByte);
                                preparedStatement.setInt(12, Teller.id);
                                if (preparedStatement.executeUpdate() != 0) {
                                    AdminView.txFldFName.clear();
                                    TellerView.txtFldBalance.clear();
                                    txtFldAccNumber.clear();
                                    TellerView.txtFldConfirmPasswd.clear();
                                    AdminView.txFldLName.clear();
                                    AdminView.txFldGender.clear();
                                    AdminView.txFldUName.clear();
                                    AdminView.txFldPass.clear();
                                    AdminView.txFldEmail.clear();
                                    AdminView.txFldPhone.clear();
                                    btnBrowse.setGraphic(new ImageView());
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Information");
                                    alert.setHeaderText("Insertion alert");
                                    alert.setContentText("INSERTED successfully Updated");
                                    // Display the alert
                                    alert.showAndWait();

                                } else {
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Information");
                                    alert.setHeaderText("Insertion alert");
                                    alert.setContentText("NOT INSERTED successfully Updated");
                                    // Display the alert
                                    alert.showAndWait();
                                }

                                BMS.statement = BMS.connection.createStatement();
                                ResultSet resultSet = BMS.statement.executeQuery("select * from tbl_customer");
                            } catch (SQLException | ClassNotFoundException | IOException ex) {
                                throw new RuntimeException(ex);
                            }
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Information");
                        alert.setHeaderText("Info alert");
                        alert.setContentText("Account number is already exist");
                        alert.showAndWait();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        TellerView.back.setOnAction(e -> {
            BMS.show(new BMS().tellerPage(BMS.stage), BMS.stage);
        });
        return TellerView.tellerHome("create");
    }

    public static Parent manageUserAcc() throws SQLException, ClassNotFoundException {

        TellerView.btnSearch.setOnAction(e -> {
            if (txtFldAccNumber.getText().equalsIgnoreCase("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Notification Alert ");
                alert.setContentText("Please Account number");
                // Display the alert
                alert.showAndWait();
            } else
                try {
                    String accNumber = txtFldAccNumber.getText();
                    if (searchUser(accNumber)) {
                        searchedName.setText("Name : " + Teller.user.getFirstName() + " " + Teller.user.getSecondName());
                        searchedGender.setText("Gender : " + Teller.user.getGender());
                        searchedAccNumber.setText("Account Number : " + Teller.user.getAccNumber());
                        searchedBalance.setText("Balance : " + Teller.user.getBalance());
                        searchedProfile = new ImageView(Teller.user.getImage());
                        txtFldAccNumber.clear();
                        BMS.show(TellerController.display(), BMS.stage);
                    } else {
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
        return TellerView.tellerHome("manage");
    }

    public static boolean validateInput() {
        if (txFldFName.getText().isEmpty()) {
            txFldFName.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("First Name is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result).isPresent() && result.get() == ButtonType.OK) {
                txFldFName.setStyle("-fx-border-color: black;");
            } else {
                txFldFName.setStyle("-fx-border-color: black;");
            }
        } else if (txFldLName.getText().isEmpty()) {
            txFldLName.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("Last Name is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result).isPresent() && result.get() == ButtonType.OK) {
                txFldLName.setStyle("-fx-border-color: black;");
            } else {
                txFldLName.setStyle("-fx-border-color: black;");
            }
        } else if (txFldGender.getText().isEmpty()) {
            txFldGender.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("Gender is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result).isPresent() && result.get() == ButtonType.OK) {
                txFldGender.setStyle("-fx-border-color: black;");
            } else {
                txFldGender.setStyle("-fx-border-color: black;");
            }
        }
//        }else if (!txFldGender.getText().equalsIgnoreCase("m") || (!txFldGender.getText().equalsIgnoreCase("f")  )) {
//            txFldGender.setStyle("-fx-border-color: red;");
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Information");
//            alert.setHeaderText("Warning Alert");
//            alert.setContentText( "Please enter gender \'m\' or \'f\' only one character");
//            Optional<ButtonType> result = alert.showAndWait();
//            if (( result).isPresent() && result.get() == ButtonType.OK) {
//                txFldGender.setStyle("-fx-border-color: black;");
//            } else {
//                txFldGender.setStyle("-fx-border-color: black;");
//            }
//        }
        else if (txtFldAccNumber.getText().isEmpty()) {
            txtFldAccNumber.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("Account Number is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result).isPresent() && result.get() == ButtonType.OK) {
                txtFldAccNumber.setStyle("-fx-border-color: black;");
            } else {
                txtFldAccNumber.setStyle("-fx-border-color: black;");
            }
        } else if (!txtFldAccNumber.getText().trim().matches(accountNumberRegex)) {
            txtFldAccNumber.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("Please enter number only Account Number");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result).isPresent() && result.get() == ButtonType.OK) {
                txtFldAccNumber.setStyle("-fx-border-color: black;");
            } else {
                txtFldAccNumber.setStyle("-fx-border-color: black;");
            }
        } else if (txtFldAccNumber.getText().length() != 5) {
            txtFldAccNumber.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("Account Number length should be 5");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result).isPresent() && result.get() == ButtonType.OK) {
                txtFldAccNumber.setStyle("-fx-border-color: black;");
            } else {
                txtFldAccNumber.setStyle("-fx-border-color: black;");
            }
        } else if (txFldEmail.getText().isEmpty()) {
            txFldEmail.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("Email is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result).isPresent() && result.get() == ButtonType.OK) {
                txFldEmail.setStyle("-fx-border-color: black;");
            } else {
                txFldEmail.setStyle("-fx-border-color: black;");
            }
        } else if (!txFldEmail.getText().matches(emailRegex)) {
            txFldEmail.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("Please enter correct email format");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result).isPresent() && result.get() == ButtonType.OK) {
                txFldEmail.setStyle("-fx-border-color: black;");
            } else {
                txFldEmail.setStyle("-fx-border-color: black;");
            }

        } else if (txtFldBalance.getText().isEmpty()) {
            txtFldBalance.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("Balance is  is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result).isPresent() && result.get() == ButtonType.OK) {
                txFldPass.setStyle("-fx-border-color: black;");
            } else {
                txFldPass.setStyle("-fx-border-color: black;");
            }
        } else if (Double.parseDouble(txtFldBalance.getText()) < 0) {
            txtFldBalance.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("Please enter positive number only for balance ");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result).isPresent() && result.get() == ButtonType.OK) {
                txFldPass.setStyle("-fx-border-color: black;");
            } else {
                txFldPass.setStyle("-fx-border-color: black;");
            }
        } else if (!txtFldBalance.getText().matches(balanceRegex)) {
            txtFldBalance.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("Please enter number only don\'t enter string ");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result).isPresent() && result.get() == ButtonType.OK) {
                txFldPass.setStyle("-fx-border-color: black;");
            } else {
                txFldPass.setStyle("-fx-border-color: black;");
            }
        } else if (txFldPhone.getText().isEmpty()) {
            txFldPhone.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("Phone number  is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result).isPresent() && result.get() == ButtonType.OK) {
                txFldPhone.setStyle("-fx-border-color: black;");
            } else {
                txFldPhone.setStyle("-fx-border-color: black;");
            }
        } else if (!txFldPhone.getText().matches(TellerController.phoneNumberRegex)) {
            txFldPhone.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("Please enter number only for phone number");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result).isPresent() && result.get() == ButtonType.OK) {
                txFldPhone.setStyle("-fx-border-color: black;");
            } else {
                txFldPhone.setStyle("-fx-border-color: black;");
            }
        } else if (txFldPhone.getText().length() != 10) {
            txFldPhone.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("Please enter correct phone number it should be 10 in length");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result).isPresent() && result.get() == ButtonType.OK) {
                txFldPhone.setStyle("-fx-border-color: black;");
            } else {
                txFldPhone.setStyle("-fx-border-color: black;");
            }
        } else if (txFldUName.getText().isEmpty()) {
            txFldUName.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("User name   is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result).isPresent() && result.get() == ButtonType.OK) {
                txFldUName.setStyle("-fx-border-color: black;");
            } else {
                txFldUName.setStyle("-fx-border-color: black;");
            }

        } else if (!txFldUName.getText().startsWith("mbu")) {
            txFldUName.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("Please start with \'mbu\'  for user username");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result).isPresent() && result.get() == ButtonType.OK) {
                txFldUName.setStyle("-fx-border-color: black;");
            } else {
                txFldUName.setStyle("-fx-border-color: black;");
            }
        } else if (txFldPass.getText().isEmpty()) {
            txFldPass.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("Password is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result).isPresent() && result.get() == ButtonType.OK) {
                txFldPass.setStyle("-fx-border-color: black;");
            } else {
                txFldPass.setStyle("-fx-border-color: black;");
            }
        } else if (txFldPass.getText().length() < 4 || txFldPass.getText().length() > 8) {
            txFldPass.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("Password should be 4 up to 8");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result).isPresent() && result.get() == ButtonType.OK) {
                txFldPass.setStyle("-fx-border-color: black;");
            } else {
                txFldPass.setStyle("-fx-border-color: black;");
            }
        } else if (txtFldConfirmPasswd.getText().isEmpty()) {
            txtFldConfirmPasswd.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("Please confirm password");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result).isPresent() && result.get() == ButtonType.OK) {
                txtFldConfirmPasswd.setStyle("-fx-border-color: black;");
            } else {
                txtFldConfirmPasswd.setStyle("-fx-border-color: black;");
            }
        } else if (!txFldPass.getText().equals(txtFldConfirmPasswd.getText())) {
            txtFldConfirmPasswd.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("Please enter the same confirmation password");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                txtFldConfirmPasswd.setStyle("-fx-border-color: black;");
            } else {
                txtFldConfirmPasswd.setStyle("-fx-border-color: black;");
            }
        } else
            return true;
        return false;
    }

    public static Parent displayUser() {
        btnDelete.setOnAction(e -> {
            try {
                PreparedStatement preparedStatement = BMS.connection.prepareStatement("delete from tbl_customer where customer_id = ?");
                PreparedStatement preparedStatement1 = BMS.connection.prepareStatement("delete from tbl_transaction where customer_id = ?");
                preparedStatement.setInt(1, Teller.user.getCustomerID());
                preparedStatement1.setInt(1, Teller.user.getCustomerID());
                preparedStatement1.executeUpdate();
                if (preparedStatement.executeUpdate() > 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information");
                    alert.setHeaderText("Delete Alert");
                    alert.setContentText("Account successfully  deleted");
                    BMS.show(seeUserInfo(), BMS.stage);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information");
                    alert.setHeaderText("Alert Example");
                    alert.setContentText("Account not deleted");
                    // Display the alert
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        TellerView.btnUpdate.setOnAction(e -> {
            BMS.show(updateUser(), BMS.stage);
            EmployeeView.txFldFName.setText(Teller.user.getFirstName());
            EmployeeView.txFldLName.setText(Teller.user.getSecondName());
            EmployeeView.txFldEmail.setText(Teller.user.getEmail());
            EmployeeView.txFldPhone.setText(Teller.user.getPhone());
            EmployeeView.txFldPass.setText(Teller.user.getPassword());
            EmployeeView.txFldGender.setText(Teller.user.getGender());
            EmployeeView.txFldUName.setText(Teller.user.getUsername());
            TellerView.txtFldAccNumber.setText(Teller.user.getAccNumber());
            txtFldBalance.setText(Double.toString(Teller.user.getBalance()));
            EmployeeView.txtFldConfirmPasswd.setText(Teller.user.getPassword());
            ImageView photo = new ImageView(Teller.user.getImage());
            photo.setFitWidth(50);
            photo.setFitHeight(50);
            btnBrowse.setGraphic(photo);
            create.setText("UPDATE");
        });
        return TellerView.tellerHome("manage s");
    }

    public static Parent display() throws SQLException, ClassNotFoundException {
        TellerView.btnDeposit.setOnAction(e -> {
            BMS.show(TellerController.depositMoney(), BMS.stage);
        });
        TellerView.btnTransfer.setOnAction(e -> {
            BMS.show(TellerController.transferFund(), BMS.stage);
        });
        TellerView.btnWithDraw.setOnAction(e -> {
            BMS.show(TellerController.widthDrawMoney(), BMS.stage);
        });
        return TellerView.tellerHome("");
    }

    public static Parent depositMoney() {
        btnCompleteDeposit.setOnAction(e -> {
            if (validateBalance())
                try {
                    CallableStatement preparedStatement1 = connection.prepareCall("{CALL spDeposit(?,?,?,?)}");
                    preparedStatement1.setDouble(1, Double.parseDouble(TellerView.txtFldAmount.getText()));
                    Teller.id = 1;
                    preparedStatement1.setInt(2, Teller.id);
                    preparedStatement1.setInt(3, Teller.user.getCustomerID());
                    preparedStatement1.registerOutParameter(4, Types.BOOLEAN);
                    preparedStatement1.execute();
                    boolean bool = preparedStatement1.getBoolean("@make");
                    if (bool) {
                        Teller.user.setBalance(Teller.user.getBalance() + Double.parseDouble(txtFldAmount.getText()));
                        searchedBalance.setText("Balance : " + Teller.user.getBalance());
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText("Update alert ");
                        alert.setContentText(txtFldAmount.getText() + " deposited successfully✅✅✅");
                        alert.showAndWait();
                        txtFldAmount.clear();
                        BMS.show(TellerController.depositMoney(), stage);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText("Update alert ");
                        alert.setContentText(" Not deposited successfully🌋🌋");
                        alert.showAndWait();
                    }
                } catch (SQLException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information");
                    alert.setHeaderText("Alert Example");
                    alert.setContentText("NO successfully deposited");
                    // Display the alert
                    alert.showAndWait();
                    throw new RuntimeException(ex);
                }

        });
        return TellerView.tellerHome("deposit");
    }

    public static Parent transferFund() {
        btnCompleteTransfer.setOnAction(e -> {
            try {
                if (validateAccountNumber())
                    if (validateBalance())
                        if ((txtFldAccNumber.getText()).equalsIgnoreCase(Teller.user.getAccNumber())) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information");
                            alert.setHeaderText("Fund transfer Alert ");
                            alert.setContentText("Dear teller you are trying to transfer money to the customer himself ");
                            alert.showAndWait();
                        } else if (!checkAccount(txtFldAccNumber.getText()))
                            try {
                                CallableStatement preparedStatement1 = connection.prepareCall("{CALL spTransferFund(?,?,?,?,?)}");
                                preparedStatement1.setDouble(1, Double.parseDouble(txtFldAmount.getText()));
                                preparedStatement1.setInt(2, Integer.parseInt(txtFldAccNumber.getText()));
                                Teller.id = 1;
                                preparedStatement1.setInt(3, Teller.id);
                                preparedStatement1.setInt(4, Teller.user.getCustomerID());
                                preparedStatement1.registerOutParameter(5, Types.BOOLEAN);
                                preparedStatement1.executeUpdate();
                                boolean bool = preparedStatement1.getBoolean("@make");
                                if (bool) {
                                    Teller.user.setBalance(Teller.user.getBalance() - Double.parseDouble(txtFldAmount.getText()));
                                    searchedBalance.setText("Balance : " + Teller.user.getBalance());
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Information");
                                    alert.setHeaderText("Fund transfer Alert ");
                                    alert.setContentText(txtFldAmount.getText() + "Transferred successfully");
                                    alert.showAndWait();
                                    txtFldAmount.clear();
                                    txtFldAccNumber.clear();
                                    BMS.show(TellerController.transferFund(), stage);
                                } else {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Information");
                                    alert.setHeaderText("Alert Example");
                                    alert.setContentText("Your balance is low");
                                    alert.showAndWait();
                                }
                            } catch (SQLException ex) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Information");
                                alert.setHeaderText("Alert Example");
                                alert.setContentText("NO successfully Updated");
                                // Display the alert
                                alert.showAndWait();
                                throw new RuntimeException(ex);
                            }
                        else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Information");
                            alert.setHeaderText("Alert Example");
                            alert.setContentText("Account Does not Exist");
                            // Display the alert
                            alert.showAndWait();
                        }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        return TellerView.tellerHome("transfer");
    }

    public static Parent widthDrawMoney() {
        btnCompleteWithdraw.setOnAction(e -> {
            if (validateBalance())
                if (Double.parseDouble(TellerView.txtFldAmount.getText()) < Teller.user.getBalance())
                    try {
                        CallableStatement preparedStatement1 = connection.prepareCall("{CALL spWithDraw(?,?,?,?)}");
                        preparedStatement1.setDouble(1, Double.parseDouble(TellerView.txtFldAmount.getText()));
                        Teller.id = 1;
                        preparedStatement1.setInt(2, Teller.id);
                        preparedStatement1.setInt(3, Teller.user.getCustomerID());
                        preparedStatement1.registerOutParameter(4, Types.BOOLEAN);
                        preparedStatement1.execute();
                        boolean bool = preparedStatement1.getBoolean("@make");
                        if (bool) {
                            Teller.user.setBalance(Teller.user.getBalance() - Double.parseDouble(txtFldAmount.getText()));
                            searchedBalance.setText("Balance : " + Teller.user.getBalance());
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information");
                            alert.setHeaderText("Update alert ");
                            alert.setContentText(txtFldAmount.getText() + " Withdrawn successfully✅✅✅");
                            alert.showAndWait();
                            txtFldAmount.clear();
                            BMS.show(TellerController.widthDrawMoney(), BMS.stage);
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Information");
                            alert.setHeaderText("Update alert ");
                            alert.setContentText(" Not Withdrawn successfully🌋🌋");
                            alert.showAndWait();
                        }
                    } catch (SQLException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Information");
                        alert.setHeaderText("Alert Example");
                        alert.setContentText("NO successfully Withdrawn");
                        alert.showAndWait();
                        throw new RuntimeException(ex);
                    }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information");
                    alert.setHeaderText("Insufficient Alert");
                    alert.setContentText("Dear teller this account does not hav enough money to withdraw");
                    alert.showAndWait();
                }
        });
        return TellerView.tellerHome("withDraw");
    }

    public static Parent seeUserInfo() {
        TellerView.EmployeeView();
        userTableView = new TableView<>();
        btnSearchUser.setOnAction(e -> {
            if (txtFldAccNumber.getText().equalsIgnoreCase("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Notification Alert ");
                alert.setContentText("Please Account number");
                alert.showAndWait();
            } else
                try {
                    String accNumber = txtFldAccNumber.getText();
                    if (searchUser(accNumber)) {
                        searchedName.setText("Name : " + Teller.user.getFirstName() + " " + Teller.user.getSecondName());
                        searchedGender.setText("Gender : " + Teller.user.getGender());
                        searchedAccNumber.setText("Account Number : " + Teller.user.getAccNumber());
                        searchedBalance.setText("Balance : " + Teller.user.getBalance());
                        searchedEmail.setText("Email : " + Teller.user.getEmail());
                        searchedPhoneNumber.setText("Phone Number : " + Teller.user.getPhone());
                        searchedProfile = new ImageView(Teller.user.getImage());
                        BMS.show(displayUser(), BMS.stage);
                        txtFldAccNumber.clear();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Information");
                        alert.setHeaderText("Alert Example");
                        alert.setContentText("Account does not found");
                        // Display the alert
                        alert.showAndWait();
                    }

                } catch (SQLException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
        });
        btnmanage.setOnAction(e -> {
            try {
                BMS.show(TellerController.manageUserAcc(), BMS.stage);
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnCreateAcc.setOnAction(e -> {
            try {
                BMS.show(TellerController.createAcc(), BMS.stage);
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        try {
            // Establish a connection]
            // Create a statement
            PreparedStatement pstm = connection.prepareStatement("select *from tbl_customer");
            ResultSet resultSet = pstm.executeQuery();
            userList.clear();
            while (resultSet.next()) {
                if (resultSet.getBytes(11) != null)
                    TellerView.userList.add(new User(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getFloat(8),
                            resultSet.getString(10),
                            resultSet.getString(9),
                            resultSet.getString(12),
                            new Image(new ByteArrayInputStream(resultSet.getBytes(11)))));
            }
            TellerView.userTableView.getItems().clear();
            TellerView.userTableView.setItems(TellerView.userList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return TellerView.tellerHome("user");

    }

    public static boolean findUser(String accNumber) throws SQLException, ClassNotFoundException {
        BMS.initializeDatabase();
        PreparedStatement preparedStatement = BMS.connection.prepareStatement("\tselect customer_id,first_name , last_name ,gender, account_number,balance ,photo , email, phone from  tbl_customer where account_number = ?");
        preparedStatement.setString(1, accNumber);
        ResultSet resultset = preparedStatement.executeQuery();
        if (resultset.next()) {
            Teller.user.setCustomerID(resultset.getInt(1));
            Teller.user.setFirstName(resultset.getString(2));
            Teller.user.setSecondName(resultset.getString(3));
            Teller.user.setGender(resultset.getString(4));
            Teller.user.setAccNumber((resultset.getString(5)));
            Teller.user.setBalance(Double.parseDouble(resultset.getString(6)));
            Teller.user.setPhone(resultset.getString(9));
            Teller.user.setEmail(resultset.getString(8));
            Teller.user.setPhoto(new Image(new ByteArrayInputStream(resultset.getBytes(7))));
            return true;
        }
        return false;
    }

    public static boolean searchUser(String accNumber) throws SQLException, ClassNotFoundException {
        BMS.initializeDatabase();
        PreparedStatement preparedStatement = BMS.connection.prepareStatement("select customer_id,first_name , last_name ,gender, account_number,balance ,photo,user_name,password, phone , email from  tbl_customer where account_number = ?");
        preparedStatement.setString(1, accNumber);
        ResultSet resultset = preparedStatement.executeQuery();
        if (resultset.next()) {
            Teller.user.setCustomerID(resultset.getInt(1));
            Teller.user.setFirstName(resultset.getString(2));
            Teller.user.setSecondName(resultset.getString(3));
            Teller.user.setGender(resultset.getString(4));
            Teller.user.setAccNumber((resultset.getString(5)));
            Teller.user.setUsername((resultset.getString(8)));
            Teller.user.setPassword((resultset.getString(9)));
            Teller.user.setPhone((resultset.getString(10)));
            Teller.user.setEmail((resultset.getString(11)));
            Teller.user.setBalance(Double.parseDouble(resultset.getString(6)));
            Teller.user.setPhoto(new Image(new ByteArrayInputStream(resultset.getBytes(7))));
            System.out.println(resultset.getString(8));
            System.out.println(resultset.getString(11));
            return true;
        }
        return false;
    }

    public static boolean checkAccount(String account) throws SQLException {
        PreparedStatement preparedStatement = BMS.connection.prepareStatement("select * from tbl_customer where account_number = ?");
        preparedStatement.setString(1, account);
        ResultSet resultSet = preparedStatement.executeQuery();
        return !resultSet.next();
    }

    public static Parent updateUser() {
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
            TellerView.create.setOnAction(event -> {
                if (validateInput())
                    try {
                        byte[] imageByte = new byte[60000];
                        if (selectedImage[0] != null)
                            imageByte = Files.readAllBytes(selectedImage[0].toPath());
                        BMS.initializeDatabase();
                        String sql = "UPDATE tbl_customer SET first_name = ?, last_name = ?, gender = ?, user_name = ?, password = ?, phone = ?, email = ? ,balance = ? , account_number = ?  WHERE customer_id = ?";
                        PreparedStatement preparedStatement = BMS.connection.prepareStatement(sql);
                        preparedStatement.setString(1, EmployeeView.txFldFName.getText());
                        preparedStatement.setString(2, EmployeeView.txFldLName.getText());
                        preparedStatement.setString(3, EmployeeView.txFldGender.getText());
                        preparedStatement.setString(4, EmployeeView.txFldUName.getText());
                        preparedStatement.setString(5, EmployeeView.txFldPass.getText());
                        preparedStatement.setString(6, EmployeeView.txFldPhone.getText());
                        preparedStatement.setString(7, EmployeeView.txFldEmail.getText());
                        preparedStatement.setString(8, txtFldBalance.getText());
                        preparedStatement.setString(9, txtFldAccNumber.getText());
                        preparedStatement.setString(10, Integer.toString(Teller.user.getCustomerID()));
                        if (selectedImage[0] != null) {
                            PreparedStatement preparedStatement1 = BMS.connection.prepareStatement("update tbl_customer set photo = ? where customer_id = ?");
                            preparedStatement1.setBytes(1, imageByte);
                            preparedStatement1.setInt(2, Teller.user.getCustomerID());
                            preparedStatement1.executeUpdate();
                        }
                        preparedStatement.executeUpdate();
                        EmployeeView.txFldFName.clear();
                        txFldBranchID.clear();
                        TellerView.txtFldConfirmPasswd.clear();
                        EmployeeView.txFldLName.clear();
                        EmployeeView.txFldGender.clear();
                        EmployeeView.txFldUName.clear();
                        EmployeeView.txFldPass.clear();
                        EmployeeView.txFldEmail.clear();
                        EmployeeView.txFldPhone.clear();
                        txtFldBalance.clear();
                        txtFldAccNumber.clear();
                        btnBrowse.setGraphic(AdminView.image);
                        create.setText("Register");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText("Insertion Alert ");
                        alert.setContentText("teller updated  successfully");
                        alert.showAndWait();
                        BMS.show(TellerController.seeUserInfo(), stage);
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
        return TellerView.tellerHome("create");
    }

    public static boolean isUsernameFound(String username) throws SQLException {
        PreparedStatement preparedStatement = BMS.connection.prepareStatement("select * from tbl_customer where user_name = ?");
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    public static boolean validateAccountNumber() {
        if (txtFldAccNumber.getText().isEmpty()) {
            txtFldAccNumber.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("Account Number is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result).isPresent() && result.get() == ButtonType.OK) {
                txtFldAccNumber.setStyle("-fx-border-color: black;");
            } else {
                txtFldAccNumber.setStyle("-fx-border-color: black;");
            }
        } else if (!txtFldAccNumber.getText().trim().matches(accountNumberRegex)) {
            txtFldAccNumber.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("Please enter number only Account Number");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result).isPresent() && result.get() == ButtonType.OK) {
                txtFldAccNumber.setStyle("-fx-border-color: black;");
            } else {
                txtFldAccNumber.setStyle("-fx-border-color: black;");
            }
        } else if (txtFldAccNumber.getText().length() != 5) {
            txtFldAccNumber.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("Account Number length should be 5");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result).isPresent() && result.get() == ButtonType.OK) {
                txtFldAccNumber.setStyle("-fx-border-color: black;");
            } else {
                txtFldAccNumber.setStyle("-fx-border-color: black;");
            }
        } else
            return true;
        return false;
    }

    public static boolean validateBalance() {
        if (txtFldAmount.getText().isEmpty()) {
            txtFldAmount.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("Balance is  is Empty");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result).isPresent() && result.get() == ButtonType.OK) {
                txtFldAmount.setStyle("-fx-border-color: black;");
            } else {
                txtFldAmount.setStyle("-fx-border-color: black;");
            }
        } else if (!txtFldAmount.getText().matches(balanceRegex)) {
            txtFldAmount.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Warning Alert");
            alert.setContentText("Please enter positive number only don\'t enter string or character for amount");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result).isPresent() && result.get() == ButtonType.OK) {
                txtFldAmount.setStyle("-fx-border-color: black;");
            } else {
                txtFldAmount.setStyle("-fx-border-color: black;");
            }
        } else
            return true;
        return false;
    }

    public static boolean checkTeller(String password, String username) throws SQLException, ClassNotFoundException {
        BMS.initializeDatabase();
        String sql = "select first_name, last_name,gender,email,phone, user_name, password ,teller_id ,photo from tbl_teller where user_name =?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        ResultSet resultset = preparedStatement.executeQuery();
        while (resultset.next())
            if (resultset.getString(6).equalsIgnoreCase(username) && resultset.getString(7).equalsIgnoreCase(password)) {
                Teller.teller.setPhone(resultset.getString(5));
                Teller.teller.setEmail(resultset.getString(4));
                Teller.teller.setFirstName(resultset.getString(1));
                Teller.teller.setSecondName(resultset.getString(2));
                Teller.teller.setGender(resultset.getString(3));
                Teller.teller.setPassword(resultset.getString(7));
                Teller.teller.setUsername(resultset.getString(6));
                Teller.teller.setPhoto(new Image(new ByteArrayInputStream(resultset.getBytes(9))));
                Teller.teller.setTeller_id(resultset.getInt(8));
                return true;
            }
        return false;
    }
}
