package com.example.votoelettronico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PartecipantiController {


    @FXML
    private Label UserNameLabel;

    @FXML
    private Text allertVot;

    @FXML
    public Button NomeElezione;

    private Votazione votazione;
    private Utente utente;

    public void goToManager(ActionEvent actionEvent) throws IOException {
        PanoramicaReferendumController prc = new PanoramicaReferendumController();
        prc.setUtenteEVotazione(utente,votazione);
        System.out.println("refresh");
        Utils.changeScene(actionEvent, "panoramicaReferendum.fxml",prc);
    }

    public void goToPartecipanti(ActionEvent actionEvent) throws IOException {
        PartecipantiController pc = new PartecipantiController();
        pc.setVotazione(votazione);
        Utils.changeScene(actionEvent, "Partecipanti.fxml",pc);
    }

    public void goToOption(ActionEvent actionEvent) throws IOException {
        PanoramicaElezioniController op = new PanoramicaElezioniController();
        op.setUtenteEVotazione(utente ,votazione);
        Utils.changeScene(actionEvent, "panoramicaElezioni.fxml",op);
    }

    public void goToCandidati(ActionEvent actionEvent) throws IOException {
        PanoramicaCandidatiController ca = new PanoramicaCandidatiController();
        ca.setUtenteEVotazione(utente,votazione);
        Utils.changeScene(actionEvent, "PanoramicaCandidati.fxml",ca);
    }

    public void Logout(ActionEvent actionEvent) throws IOException {
        Utils.changeScene(actionEvent, "LoginScene.fxml");
    }

    public void setVotazione(Votazione v){
        this.votazione = v;
        System.out.println(v);
    }

    public void initialize(URL location, ResourceBundle resources) {
        NomeElezione.setText(this.votazione.getNome());
    }

}



