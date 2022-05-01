package com.example.votoelettronico;

import javafx.event.ActionEvent;

import java.io.IOException;

public class VotazioniController {
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
}
