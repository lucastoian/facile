package com.example.votoelettronico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class OptionController {

    private Utente utente;
    private Votazione votazione;
    private VotazioneDao votazioneDao;
    @FXML
    Text allertVot;

    public void Logout(ActionEvent actionEvent) throws IOException {
        Utils.changeScene(actionEvent, "LoginScene.fxml");
    }
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
    public void goToManager(ActionEvent actionEvent) throws IOException {

    }
    public void goToRisulti(ActionEvent actionEvent) throws IOException {

    }
    public void goToR(ActionEvent actionEvent) throws IOException {

    }
    public void goToOption(ActionEvent actionEvent) throws IOException {

    }
    public void goToPartecipanti(ActionEvent actionEvent) throws IOException {
    }

    public void setUtenteEVotazione(Utente u, Votazione v){
        this.utente=u;
        this.votazione=v;
    }
}
