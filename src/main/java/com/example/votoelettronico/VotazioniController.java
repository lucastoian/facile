package com.example.votoelettronico;

import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class VotazioniController implements Observer {


    @FXML
    Label UserNameLabel;

    Utente u;

    public void Logout(ActionEvent actionEvent) throws IOException {
        Utils.changeScene(actionEvent, "LoginScene.fxml");
    }

    public void goToCreateElection(ActionEvent actionEvent) throws IOException {
        Utils.changeScene(actionEvent, "CreateElectionScene.fxml");
    }

    public void goToElection(ActionEvent actionEvent) {
    }

    public void deleteElection(ActionEvent actionEvent) {
    }


    @Override
    public void update(Object o) {
        Utente u = (Utente) o;
        this.u = u;

        UserNameLabel.setText(u.getName());
    }
}
