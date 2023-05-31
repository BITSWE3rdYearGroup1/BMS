module com.example.bms {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.j;


    opens com.example.bms to javafx.fxml;
    exports com.example.bms;
}