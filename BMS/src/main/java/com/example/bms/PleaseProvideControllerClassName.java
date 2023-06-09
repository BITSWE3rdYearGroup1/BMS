package com.example.bms;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PleaseProvideControllerClassName implements Initializable {

    @FXML
    private AnchorPane cyanView;

    @FXML
    private Button nineBtn;

    @FXML
    private AnchorPane nineView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void changeView(ActionEvent event) {
        if (event.getSource() == nineBtn) {
            nineView.setVisible(true);
            cyanView.setVisible(false);
        } else {
            nineView.setVisible(false);
            cyanView.setVisible(true);
        }
    }
}

