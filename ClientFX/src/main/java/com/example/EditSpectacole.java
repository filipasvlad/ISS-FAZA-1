package com.example;

import com.example.databases.SpectacoleDB;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
public class EditSpectacole {

    @FXML
    private Button butonAdauga;

    @FXML
    private Button butonModifica;

    @FXML
    private Button butonSterge;

    @FXML
    private TableColumn<Spectacol, Date> coloanaData;

    @FXML
    private TableColumn<Spectacol, String> coloanaNume;

    @FXML
    private TableView<Spectacol> tabelSpectacole;

    @FXML
    private DatePicker textData;

    @FXML
    private TextField textNume;

    private Service service;

    private Long idSelectat = null;


    private final ObservableList<Spectacol> spectacolModel = FXCollections.observableArrayList();
    public void setService(Service service) {
        this.service = service;
    }

    public void initializare() throws SQLException {
        tabelSpectacole.getItems().clear();
        List<Spectacol> spectacole = (List<Spectacol>) service.getAllSpectacole();
        spectacolModel.setAll(spectacole);
        coloanaNume.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNume()));
        coloanaData.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getData()));
        tabelSpectacole.setItems(spectacolModel);
    }

    @FXML
    void adauga(ActionEvent event) throws FileNotFoundException, SQLException {
        String nume = textNume.getText();
        Date data = java.util.Date.from(textData.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(data.getTime());

        Spectacol spectacol = new Spectacol(nume, sqlDate);
        System.out.println(spectacol);
        service.addSpectacol(spectacol);
        initializare();
    }

    @FXML
    void modifica(ActionEvent event) throws FileNotFoundException, SQLException {
        String nume = textNume.getText();
        Date data = java.util.Date.from(textData.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(data.getTime());
        Spectacol spectacol = new Spectacol(nume, sqlDate);
        service.deleteSpectacol(idSelectat);
        service.addSpectacol(spectacol);
        initializare();
    }

    @FXML
    void sterge(ActionEvent event) throws SQLException {
        service.deleteSpectacol(idSelectat);
        initializare();
    }

    @FXML
    void getIdSelectat(MouseEvent event) {
        idSelectat = tabelSpectacole.getSelectionModel().getSelectedItem().getId();
    }
}
