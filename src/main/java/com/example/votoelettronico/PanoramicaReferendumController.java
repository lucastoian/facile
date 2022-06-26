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
    public Text allertConfermaElezione,testo;
    @FXML
    public Button Conferma,conferma;

    private Utente utente;
    private Votazione votazione;



    public void goIndietro(ActionEvent actionEvent) throws IOException {
        VotazioniController vc = new VotazioniController();
        vc.setUtente(utente);
        Utils.changeScene(actionEvent,"VotazioniScene.fxml", vc );
    }


    public void goToManager(ActionEvent actionEvent) throws IOException {
        PanoramicaElezioniController prc = new PanoramicaElezioniController();
        prc.setUtenteEVotazione(utente,votazione);
        System.out.println("refresh");
        Utils.changeScene(actionEvent, "panoramicaElezioni.fxml",prc);
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

    public void salvaDomanda(ActionEvent actionEvent) throws IOException, SQLException {
        PanoramicaElezioniController pec = new PanoramicaElezioniController();
        pec.setUtenteEVotazione(utente,votazione);

        VotazioneDao vd = new VotazioneDaoImpl();
        votazione.setDomanda(DomandaReferendum.getText());

        vd.setDomanda(votazione, DomandaReferendum.getText());

        LogRecord rec = new LogRecord(votazione.getNome(),"change-referendum-question",votazione.getStatus());
        rec.createRecord();

        Utils.changeScene(actionEvent, "panoramicaElezioni.fxml", pec);
    }

    public void ConfermaElezione(ActionEvent actionEvent) throws IOException, SQLException {

        if(votazione.getTipo().equals("referendum") && votazione.getDomanda()==null){
            allertConfermaElezione.setText("NON PUOI CONFERMARE UN REFERENDUM SENZA UNA DOMANDA");
            allertConfermaElezione.setVisible(true);
            return;
        }


        PanoramicaElezioniController pec = new PanoramicaElezioniController();
        pec.setUtenteEVotazione(utente,votazione);
        VotazioneDao vd = new VotazioneDaoImpl();
        vd.changeStatus(votazione, "Approvata");

        LogRecord rec = new LogRecord(votazione.getNome(),"approve-election",votazione.getStatus());
        rec.createRecord();

        Utils.changeScene(actionEvent, "PanoramicaElezioni.fxml",pec);

    }


    public void setUtenteEVotazione(Utente u, Votazione v){
        this.utente=u;
        this.votazione=v;
    }

    public void goToRisultato(ActionEvent actionEvent) throws IOException{
        RisultatoController rc = new RisultatoController();
        rc.setUtenteEVotazione(utente,votazione);
        Utils.changeScene(actionEvent, "Risultato.fxml",rc);
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
        DomandaReferendum.setText(votazione.getDomanda());

        if( !(votazione.getStatus().equals("Draft"))){
            Conferma.setVisible(false);
            conferma.setVisible(false);
            testo.setText("LA DOMANDA DI QUESTO REFERENDUM E':");
        }
    }




}
