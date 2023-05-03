package com.example.bms;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BMSController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}