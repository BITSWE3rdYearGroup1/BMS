module com.example.bms {
    requires javafx.controls;
    requires javafx.fxml;
                                                 

    opens com.example.bms to javafx.fxml;
    exports com.example.bms;
}