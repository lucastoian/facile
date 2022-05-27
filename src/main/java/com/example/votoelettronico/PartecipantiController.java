package com.example.votoelettronico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PartecipantiController implements Initializable {




    @FXML
    private Text allertVot;

    @FXML
    public Button NomeElezione;

    @FXML
    public Label UserNameLabel;

    private Votazione votazione;
    private Utente utente;

    public void cambiaElezione(ActionEvent actionEvent) throws IOException{

    }

    public void goToOption(ActionEvent actionEvent) throws IOException {

    }


    public void goToManager(ActionEvent actionEvent) throws IOException {
        System.out.println("panoramica");
        PanoramicaElezioniController op = new PanoramicaElezioniController();
        op.setUtenteEVotazione(utente,votazione);
        Utils.changeScene(actionEvent, "panoramicaElezioni.fxml",op);
    }

    public void goToPartecipanti(ActionEvent actionEvent) throws IOException {
        PartecipantiController pc = new PartecipantiController();
        pc.setUtenteEVotazione(utente,votazione);
        Utils.changeScene(actionEvent, "Partecipanti.fxml",pc);
    }


    public void goToCandidati(ActionEvent actionEvent) throws IOException {
        PanoramicaCandidatiController ca = new PanoramicaCandidatiController();
        ca.setUtenteEVotazione(utente,votazione);
        Utils.changeScene(actionEvent, "PanoramicaCandidati.fxml",ca);
    }

    public void goToRisultato(ActionEvent actionEvent) throws IOException{
        RisultatoController rc = new RisultatoController();
        rc.setUtenteEVotazione(utente,votazione);
        Utils.changeScene(actionEvent, "Risultato.fxml",rc);
    }

    public void Logout(ActionEvent actionEvent) throws IOException {
        Utils.changeScene(actionEvent, "LoginScene.fxml");
    }

    public void setUtenteEVotazione(Utente u, Votazione v){
        this.utente=u;
        this.votazione=v;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NomeElezione.setText(this.votazione.getNome());
        UserNameLabel.setText(this.utente.getName());
    }

}



