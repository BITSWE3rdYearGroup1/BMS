package com.example.bms;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;

public class TellerController {
    public static Parent createAcc() throws SQLException, ClassNotFoundException {
        TellerView.btnBrowse.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Photo");
            File initialDirectory = new File(System.getProperty("user.home"));
            fileChooser.setInitialDirectory(initialDirectory);
            FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter(
                    "Image Files", "*.jpg", "*.jpeg", "*.png");
            fileChooser.getExtensionFilters().add(imageFilter);
            File selectedFile = fileChooser.showOpenDialog(new Stage());
            if (selectedFile != null) {
                System.out.println("Selected photo: " + selectedFile.getAbsolutePath());
            }
        });
        TellerView.back.setOnAction(e -> {
            new BMS().show(new BMS().tellerPage(BMS.stage), BMS.stage);
        });
        TellerView.create.setOnAction(e->{
            try {
                BMS.initializeDatabase();
                String sql = "insert into tbl_customer values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = BMS.connection.prepareStatement(sql);
                preparedStatement.setInt(1,Integer.parseInt(TellerView.txtFldID.getText()));
                preparedStatement.setString(2,EmployeeView.txFldFName.getText());
                preparedStatement.setString(3,EmployeeView.txFldLName.getText());
                preparedStatement.setString(4,EmployeeView.txFldGender.getText());
                preparedStatement.setString(5,EmployeeView.txFldUName.getText());
                preparedStatement.setString(6,EmployeeView.txFldPass.getText());
                preparedStatement.setInt(7,Integer.parseInt(TellerView.txtFldAccNumber.getText()));
                preparedStatement.setFloat(8, 0.0F);
                preparedStatement.setString(9,TellerView.phone.getText());
                preparedStatement.setString(10,EmployeeView.email.getText());
                LocalDate currentDate = LocalDate.now();
                preparedStatement.setDate(11, Date.valueOf(currentDate.now()));
//                File file = new File("C:\\Users\\ezrat\\Desktop\\ap\\BMS\\BMS\\src\\main\\resources\\com\\example\\bms\\34-modified.png");
//                BufferedImage bufferedImage = ImageIO.read(file);
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                ImageIO.write(bufferedImage, "jpg", baos);
//                byte[] imageBytes = baos.toByteArray();
                preparedStatement.setString(12,"34-modified.png");
                preparedStatement.setInt(13,Integer.parseInt(TellerView.tellerIdTxt.getText()));
                preparedStatement.executeUpdate();
                BMS.statement =BMS.connection.createStatement();
                ResultSet resultSet =  BMS.statement.executeQuery("select * from tbl_customer");
                while (resultSet.next())
                    System.out.println(resultSet.getInt(1)+"     "+resultSet.getString(2)+ "  "+resultSet.getString(3));

            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        return TellerView.tellerHome("create");
    }

}
