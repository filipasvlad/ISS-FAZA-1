package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class Login {

    @FXML
    private Button butonInregistrare;

    @FXML
    private Button butonLogin;

    @FXML
    private TextField textPass;

    @FXML
    private TextField textUser;

    private Service service;

    public void setService(Service service) {
        this.service = service;
    }

    public void inregistrare() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/inregistrare.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Inregistrare!");
        stage.setScene(scene);
        stage.show();
        Inregistrare inregistrare = fxmlLoader.getController();
        inregistrare.setService(service);
    }

    public void logare() throws IOException, SQLException {
        String username = textUser.getText();
        String pass = textPass.getText();
        User user = new User(username, pass, "utilizator");
        User admin = new User(username, pass, "administrator");
        User user_db = service.getUser(username);
        if (Objects.equals(user_db, user)){
            System.out.println("AM INTRAT IN USER");
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/rezerva_locuri.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("REZERVA LOCURI!");
            stage.setScene(scene);
            stage.show();
            RezervaLocuri rezervaLocuri = fxmlLoader.getController();
            rezervaLocuri.setService(service);
            rezervaLocuri.initializare(username);
        } else if(Objects.equals(user_db, admin)){
            System.out.println("AM INTRAT IN ADMIN");
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/edit_spectacole.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("EDITEAZA SPECTACOLE!");
            stage.setScene(scene);
            stage.show();
            EditSpectacole editSpectacole = fxmlLoader.getController();
            editSpectacole.setService(service);
            editSpectacole.initializare();
        }

    }

}
