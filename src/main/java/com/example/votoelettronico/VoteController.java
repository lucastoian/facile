package com.example.votoelettronico;

import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class VoteController {
    public Pane errorPane;
    private Votazione v;
    private String codFiscale;

    public void confermaVoto(ActionEvent actionEvent) throws IOException {

    }
    public void clearSelected(ActionEvent actionEvent) throws IOException {

    }
    public void Logout(ActionEvent actionEvent) throws IOException {
        Utils.changeScene(actionEvent, "LoginScene.fxml");
    }

    public void setCodFiscaleAndVotazione(String codFiscale, Votazione v){
        this.codFiscale = codFiscale;
        this.v = v;
    }

}
