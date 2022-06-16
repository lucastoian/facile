package com.example.votoelettronico;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PanoramicaReferendumController implements Initializable {

    UtenteDao utenteDao;
    @FXML
    public Label UserNameLabel;

    @FXML
    public TextField DomandaReferendum;
    @FXML
    public Button Aggiungi,NomeElezione;
    @FXML
    public Text allertConfermaElezione;
    @FXML
    public Button Conferma;

    private Utente utente;
    private Votazione votazione;

    public void goToManager(ActionEvent actionEvent) throws IOException {
        PanoramicaReferendumController prc = new PanoramicaReferendumController();
        prc.setUtenteEVotazione(utente,votazione);
        System.out.println("refresh");
        Utils.changeScene(actionEvent, "panoramicaReferendum.fxml",prc);
    }

    public void goToPartecipanti(ActionEvent actionEvent) throws IOException {
        PartecipantiController pc = new PartecipantiController();
        pc.setUtenteEVotazione(utente,votazione);
        Utils.changeScene(actionEvent, "Partecipanti.fxml",pc);
    }


    public void Logout(ActionEvent actionEvent) throws IOException {
        Utils.changeScene(actionEvent, "LoginScene.fxml");
    }


    public void cambiaElezione(ActionEvent actionEvent) throws IOException{
        VotazioniController vc = new VotazioniController();
        Utils.changeScene(actionEvent, "VotazioniScene.fxml",vc);
    }

    public void ConfermaElezione(ActionEvent actionEvent) throws IOException, SQLException {
        RisultatoController rc= new RisultatoController();
        rc.setUtenteEVotazione(utente,votazione);
        VotazioneDao vd = new VotazioneDaoImpl();
        votazione.setDomanda(DomandaReferendum.getText());

        vd.changeStatus(votazione, "Approvata");
        vd.setDomanda(votazione, DomandaReferendum.getText());
        Utils.changeScene(actionEvent, "Risultato.fxml", rc);
    }




    public void setUtenteEVotazione(Utente u, Votazione v){
        this.utente=u;
        this.votazione=v;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        try {
            utenteDao = new UtenteDaoImpl();
        } catch (Exception e) {

        }

        NomeElezione.setText(this.votazione.getNome());
        allertConfermaElezione.setVisible(false);
        UserNameLabel.setText(this.utente.getName());

    }




}
