package com.example.votoelettronico;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.IOException;

public class Utils {
    public static void changeScene(ActionEvent event, String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(Utils.class.getResource(fxml));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
