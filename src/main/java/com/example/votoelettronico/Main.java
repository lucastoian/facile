package com.example.votoelettronico;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        LogRecord.writeToTxt();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("VotoFacile");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}