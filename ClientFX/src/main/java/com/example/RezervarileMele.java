package com.example;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class RezervarileMele {

    @FXML
    private Button butonSterge;

    @FXML
    private TableColumn<Rezervare, Date> coloanaData;

    @FXML
    private TableColumn<Rezervare, String> coloanaLocuri;

    @FXML
    private TableColumn<Rezervare, String> coloanaNume;

    @FXML
    private TableView<Rezervare> tabelRezervari;

    private Service service;

    private Long idSelectat;

    private String username;
    private final ObservableList<Rezervare> rezervareModel = FXCollections.observableArrayList();
    public void setService(Service service) {
        this.service = service;
    }

    public void initializare(String username) throws SQLException {
        this.username = username;
        tabelRezervari.getItems().clear();
        List<Rezervare> rezervari = new ArrayList<>();
        for (Rezervare r:service.getRezervari()){
            if(Objects.equals(r.getUsername(), username)){
                rezervari.add(r);
            }
        }
        rezervareModel.setAll(rezervari);
        coloanaNume.setCellValueFactory(data -> new SimpleStringProperty(service.getSpectacol(data.getValue().getIdSpectacol()).getNume()));
        coloanaData.setCellValueFactory(data -> new SimpleObjectProperty<>(service.getSpectacol(data.getValue().getIdSpectacol()).getData()));
        coloanaLocuri.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getLocuriRezervate()));
        tabelRezervari.setItems(rezervareModel);
    }

    @FXML
    void selecteazaRezervare(MouseEvent event) {
        idSelectat = tabelRezervari.getSelectionModel().getSelectedItem().getId();
    }

    @FXML
    void sterge(ActionEvent event) throws SQLException {
        service.deleteRezervare(idSelectat);
        initializare(username);
    }

}

