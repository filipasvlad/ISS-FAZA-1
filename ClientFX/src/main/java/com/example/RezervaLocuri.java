package com.example;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class RezervaLocuri {

    @FXML
    private Button butonCauta;

    @FXML
    private Button butonRezerva;

    @FXML
    private ComboBox<String> comboData;

    @FXML
    private ComboBox<String> comboSpectacol;

    @FXML
    private Button l1;

    @FXML
    private Button l2;

    @FXML
    private Button l3;

    @FXML
    private Button l4;

    @FXML
    private Button l5;

    @FXML
    private Button l6;

    @FXML
    private Button l7;

    @FXML
    private Button l8;

    @FXML
    private Button butonRezervarileMele;

    private Service service;

    public void setService(Service service) {
        this.service = service;
    }

    private Long idSpectacol;
    private String username;

    public void initializare(String username) throws SQLException {
        this.username = username;
        for (Spectacol spectacol : service.getAllSpectacole()) {
            comboData.getItems().add(spectacol.getData().toString());
        }
    }

    public void reseteazaButoane(){
        l1.setStyle("-fx-background-color: green;");
        l2.setStyle("-fx-background-color: green;");
        l3.setStyle("-fx-background-color: green;");
        l4.setStyle("-fx-background-color: green;");
        l5.setStyle("-fx-background-color: green;");
        l6.setStyle("-fx-background-color: green;");
        l7.setStyle("-fx-background-color: green;");
        l8.setStyle("-fx-background-color: green;");
    }



    @FXML
    void cauta() throws SQLException {
        String data = comboData.getValue();
        String numeSpectacol = comboSpectacol.getValue();
        for (Spectacol spectacol : service.getAllSpectacole()) {
            if(Objects.equals(spectacol.getData().toString(), data) && Objects.equals(spectacol.getNume(), numeSpectacol)){
                idSpectacol = spectacol.getId();
            }
        }
        for (Rezervare rezervare : service.getRezervari()) {
            if(Objects.equals(rezervare.getIdSpectacol(), idSpectacol)){
                List<String> locuri = List.of(rezervare.getLocuriRezervate().split(","));
                if(locuri.contains("1")){
                    l1.setStyle("-fx-background-color: red;");
                }if(locuri.contains("2")){
                    l2.setStyle("-fx-background-color: red;");
                }if(locuri.contains("3")){
                    l3.setStyle("-fx-background-color: red;");
                }if(locuri.contains("4")){
                    l4.setStyle("-fx-background-color: red;");
                }if(locuri.contains("5")) {
                    l5.setStyle("-fx-background-color: red;");
                }if(locuri.contains("6")) {
                    l6.setStyle("-fx-background-color: red;");
                }if(locuri.contains("7")) {
                    l7.setStyle("-fx-background-color: red;");
                }if(locuri.contains("8")) {
                    l8.setStyle("-fx-background-color: red;");
                }
            }
        }
    }

    @FXML
    void dataAleasa(ActionEvent event) throws SQLException {
        reseteazaButoane();
        comboSpectacol.getItems().clear();
        String data = comboData.getValue();
        for (Spectacol spectacol : service.getAllSpectacole()) {
            if(Objects.equals(spectacol.getData().toString(), data))
                comboSpectacol.getItems().add(spectacol.getNume());
        }
    }

    @FXML
    void coloreaza(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if(!clickedButton.getStyle().contains("-fx-background-color: red;")){
            clickedButton.setStyle("-fx-background-color: blue;");
        }

    }

    @FXML
    void rezerva(ActionEvent event) throws FileNotFoundException, SQLException {
        String locuri = "";
        if(l1.getStyle().contains("-fx-background-color: blue;")){
            locuri += "1,";
        }if(l2.getStyle().contains("-fx-background-color: blue;")){
            locuri += "2,";
        }if(l3.getStyle().contains("-fx-background-color: blue;")){
            locuri += "3,";
        }if(l4.getStyle().contains("-fx-background-color: blue;")){
            locuri += "4,";
        }if(l5.getStyle().contains("-fx-background-color: blue;")){
            locuri += "5,";
        }if(l6.getStyle().contains("-fx-background-color: blue;")){
            locuri += "6,";
        }if(l7.getStyle().contains("-fx-background-color: blue;")){
            locuri += "7,";
        }if(l8.getStyle().contains("-fx-background-color: blue;")){
            locuri += "8,";
        }
        if (locuri.length() != 0){
            Rezervare rezervare = new Rezervare(locuri, username, idSpectacol);
            service.addRezervare(rezervare);
            cauta();
        }
    }

    @FXML
    void rezervarileMele(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/rezervarile_mele.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("REZERVARILE MELE!");
        stage.setScene(scene);
        stage.show();
        RezervarileMele rezervarileMele = fxmlLoader.getController();
        rezervarileMele.setService(service);
        rezervarileMele.initializare(username);
    }

}
