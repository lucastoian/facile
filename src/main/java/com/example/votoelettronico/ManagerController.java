package com.example.votoelettronico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerController implements Initializable {
    Votazione v;

    @FXML
    Label UserNameLabel,NomeElezione;
    public void goToManager(ActionEvent actionEvent) {
    }

    public void goToPartecipanti(ActionEvent actionEvent) {
    }

    public void goToCandidati(ActionEvent actionEvent) {
    }

    public void goToOption(ActionEvent actionEvent) {
    }

    public void cambiaElezione(ActionEvent actionEvent) {
    }

    public void Logout(ActionEvent actionEvent) throws IOException {

        Utils.changeScene(actionEvent, "LoginScene.fxml");
    }

    public void setVotazione(Votazione v){
        this.v = v;

        System.out.println(v);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NomeElezione.setText(this.v.getNome());
    }
}
