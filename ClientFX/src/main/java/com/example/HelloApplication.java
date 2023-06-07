package com.example;

import com.example.databases.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        Properties props = new Properties();
        try {
            props.load(new FileReader("C:\\Users\\filip\\OneDrive\\Desktop\\ISS_TEATRU\\db.config"));
            System.out.println("Properties set. ");
            System.out.println(props);
        }catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }
        UserHibernateDB userDB = new UserHibernateDB(props);
        SpectacoleHibernateDB spectacoleDB = new SpectacoleHibernateDB(props);
        RezervariHibernateDB rezervariDB = new RezervariHibernateDB(props);
        Service service = new Service(userDB, spectacoleDB, rezervariDB);


        Iterable<Spectacol> spectacole = spectacoleDB.findAll();
        System.out.println(spectacole);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login!");
        stage.setScene(scene);
        stage.show();
        Login login = fxmlLoader.getController();
        login.setService(service);





    }

    public static void main(String[] args) {
        launch();
    }
}