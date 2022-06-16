package com.example.votoelettronico;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class RisultatoController implements Initializable {

    @FXML
    Text allertVot;
    @FXML
    AnchorPane ORDINALE_CATEGORICO, REFERENDUM;
    @FXML
    TableView<Candidato> tabellaCandidati;
    @FXML
    TableColumn<Candidato, String> nome, cognome, codFiscale;
    @FXML
    TableColumn<Candidato, Integer> punteggio;
    @FXML
    TextArea testoReferendum;
    @FXML
    TextField numeroVotantiReferendum,numeroVotantiFavorevoliReferendum,numeroVotantiContrariReferendum;

    @FXML
    public Button NomeElezione;

    @FXML
    public Label UserNameLabel;

    private Votazione votazione;
    private Utente utente;

    private VotazioneDao votazioneDao;
    private UtenteDao utenteDao;
    private ObservableList<Candidato> candidatoList;


    public void goIndietro(ActionEvent actionEvent) throws IOException {
        VotazioniController vc = new VotazioniController();
        vc.setUtente(utente);
        Utils.changeScene(actionEvent,"VotazioniScene.fxml", vc );
    }

    public void goToRisultato(ActionEvent actionEvent) throws IOException{
        RisultatoController rc = new RisultatoController();
        rc.setUtenteEVotazione(utente,votazione);
        Utils.changeScene(actionEvent, "Partecipanti.fxml",rc);
    }

    public void goToPartecipanti(ActionEvent actionEvent) throws IOException {
        PartecipantiController pc = new PartecipantiController();
        pc.setUtenteEVotazione(utente,votazione);
        Utils.changeScene(actionEvent, "Partecipanti.fxml",pc);
    }


    public void goToManager(ActionEvent actionEvent) throws IOException {
        System.out.println("panoramica");
        PanoramicaElezioniController op = new PanoramicaElezioniController();
        op.setUtenteEVotazione(utente,votazione);
        Utils.changeScene(actionEvent, "panoramicaElezioni.fxml",op);
    }

    public void goToCandidati(ActionEvent actionEvent) throws IOException {
        PanoramicaCandidatiController ca = new PanoramicaCandidatiController();
        ca.setUtenteEVotazione(utente,votazione);
        Utils.changeScene(actionEvent, "PanoramicaCandidati.fxml",ca);
    }

    public void goToOption(ActionEvent actionEvent) throws IOException {

    }

    public void cambiaElezione(ActionEvent actionEvent) throws IOException{

    }





    public void Logout(ActionEvent actionEvent) throws IOException {
        Utils.changeScene(actionEvent, "LoginScene.fxml");
    }



    public void setUtenteEVotazione(Utente u, Votazione v){
        this.utente = u;
        this.votazione = v;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        NomeElezione.setText(this.votazione.getNome());
        UserNameLabel.setText(this.utente.getName());

        try {
            votazioneDao = new VotazioneDaoImpl();
            utenteDao = new UtenteDaoImpl();
            if(votazione.getStatus().equals("Draft")){
                allertVot.setText("La votazione è ancora in Draft, prima di vedere i risultati devi Confermarla");
                return;
            }
            LocalDateTime actualTime = LocalDateTime.now();
            Timestamp votationEndTime =votazioneDao.getVotazioneEndTime(votazione);
            if(Timestamp.valueOf(actualTime).after(votationEndTime) ){
                allertVot.setVisible(false);
                switch (votazione.getTipo()){
                    case "referendum":
                        REFERENDUM.setVisible(true);
                        testoReferendum.setText(votazione.getDomanda());
                        int[] risultato = votazioneDao.getRisultatoReferendum(votazione);
                        numeroVotantiReferendum.setText("votanti totali: " + risultato[0]);
                        numeroVotantiFavorevoliReferendum.setText("favorevoli: " + risultato[1]);
                        numeroVotantiContrariReferendum.setText("contrari: " + (risultato[0]- risultato[1]));
                        break;
                    default:
                        ORDINALE_CATEGORICO.setVisible(true);
                        candidatoList = FXCollections.observableList(utenteDao.getAllCandidatoByIdVotazione(votazione));
                        nome.setCellValueFactory(new PropertyValueFactory<Candidato, String>("name"));
                        cognome.setCellValueFactory(new PropertyValueFactory<Candidato, String>("surname"));
                        codFiscale.setCellValueFactory(new PropertyValueFactory<Candidato, String>("codFiscale"));
                        punteggio.setCellValueFactory(new PropertyValueFactory<Candidato, Integer>("punteggio"));
                        tabellaCandidati.setItems(candidatoList);

                }
            }else{
                allertVot.setText("Il risultato sarà consultabile su questa pagina a partire dal: " + votationEndTime.toLocalDateTime().getDayOfMonth() + "/" + votationEndTime.toLocalDateTime().getMonthValue() + "/" + votationEndTime.toLocalDateTime().getYear()+ " alle ore "+ votationEndTime.toLocalDateTime().getHour()+":"+votationEndTime.toLocalDateTime().getMinute());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        NomeElezione.setText(this.votazione.getNome());
        UserNameLabel.setText(this.utente.getName());

    }
}
