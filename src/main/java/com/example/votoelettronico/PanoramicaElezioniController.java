package com.example.votoelettronico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class PanoramicaElezioniController implements Initializable {

    private Utente utente;
    private Votazione votazione;

    private VotazioneDao votazioneDao;

    @FXML
    Text allertVot;


    public void goIndietro(ActionEvent actionEvent) throws IOException {

    }
    public void SalvaButton(ActionEvent actionEvent) throws IOException {

    }
    public void TerminaSubitoButton(ActionEvent actionEvent) throws IOException, SQLException {
    votazioneDao= new VotazioneDaoImpl();
    votazioneDao.changeEndDate(votazione, Timestamp.valueOf(LocalDateTime.now()));
    allertVot.setText("La votazione è terminata!");
    allertVot.setVisible(true);

    }


    @FXML
    public Button NomeElezione;

    @FXML
    public Label UserNameLabel;







    public void cambiaElezione(ActionEvent actionEvent) throws IOException{

    }

    public void goToRisultato(ActionEvent actionEvent) throws IOException{

    }

    public void goToPartecipanti(ActionEvent actionEvent) throws IOException {
        PartecipantiController pc = new PartecipantiController();
        pc.setVotazione(votazione);
        Utils.changeScene(actionEvent, "Partecipanti.fxml",pc);
    }


    public void goToPanoramica(ActionEvent actionEvent) throws IOException {
        PanoramicaElezioniController op = new PanoramicaElezioniController();
        op.setUtenteEVotazione(utente,votazione);
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NomeElezione.setText(this.votazione.getNome());
        NomeElezione.setText(this.votazione.getNome());
        UserNameLabel.setText(this.utente.getName());
    }

    public void setUtenteEVotazione(Utente u, Votazione v){
        this.utente=u;
        this.votazione=v;
    }

}
