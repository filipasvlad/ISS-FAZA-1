package com.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.util.Objects;

public class Inregistrare {

    @FXML
    private Button inregistrare;

    @FXML
    private TextField textPass1;

    @FXML
    private TextField textPass2;

    @FXML
    private TextField textUser;

    private Service service;

    public void setService(Service service) {
        this.service = service;
    }

    @FXML
    void inregistrare(ActionEvent event) throws FileNotFoundException {
        String username = textUser.getText();
        String pass1 = textPass1.getText();
        String pass2 = textPass2.getText();
        if(Objects.equals(pass1, pass2)){
            User user = new User(username, pass1, "utilizator");
            service.addUser(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("TE-AI INREGISTRAT CU SECCES!");
            alert.showAndWait();
        }
    }

}
