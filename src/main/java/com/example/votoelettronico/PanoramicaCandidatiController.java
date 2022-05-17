package com.example.votoelettronico;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PanoramicaCandidatiController implements Initializable {
    Votazione votazione;
    Utente utente;
    UtenteDao utenteDao;
    VotazioneDao votazioneDao;
    @FXML
    public Label  UserNameLabel, codFs, idElezione,DataInizio,DataDiFine;

    @FXML
    public Button NomeElezione;

    @FXML
    public TextField codFiscalefield,DataDiInizioField,DataDiFineField,IdElezioneField;
    @FXML
    public Button Aggiungi,Conferma;
    @FXML
    public Text allertCandidato,allertConfermaElezione,aggiungiUnCandidato;
    @FXML
    public TableView Ctable;
    @FXML
    private TableColumn<Utente, String> name,codFiscale;

    ObservableList<Utente> candidatoList = FXCollections.observableArrayList();


    public void goToManager(ActionEvent actionEvent) {
    }

    public void goToPartecipanti(ActionEvent actionEvent) {

    }
    public void goToRisultati(ActionEvent actionEvent) throws IOException {
        RisultatoController rc = new RisultatoController();
        rc.setUtenteVotazione(utente,votazione);
        Utils.changeScene(actionEvent, "Risultato.fxml",rc );
    }

    public void goToCandidati(ActionEvent actionEvent) {

    }

    public void goToOption(ActionEvent actionEvent) throws IOException {
        PanoramicaElezioniController oc = new PanoramicaElezioniController();
        oc.setUtenteEVotazione(utente,votazione);
        Utils.changeScene(actionEvent,"panoramicaElezioni.fxml", oc);
    }

    public void cambiaElezione(ActionEvent actionEvent) {
    }
    public void Indietro(ActionEvent actionEvent) throws IOException {
        VotazioniController vc = new VotazioniController();
        vc.setUtente(utente);
        Utils.changeScene(actionEvent, "VotazioniScene.fxml", vc);
    }
    public void AggiungiCandidato(ActionEvent actionEvent) {
        try {
            Utente u = utenteDao.getUtenteByCodFiscale(codFiscalefield.getText());
            utenteDao.addCandidato(u, votazione);
            candidatoList.add(u);
            allertCandidato.setVisible(false);
            aggiungiUnCandidato.setVisible(true);
        }catch (SQLException sqlException){
            allertCandidato.setText( Utils.gestioneConstraint(sqlException));
            allertCandidato.setVisible(true);
            aggiungiUnCandidato.setVisible(false);
        }catch (IllegalArgumentException s){
            allertCandidato.setText(s.getMessage());
            allertCandidato.setVisible(true);
            aggiungiUnCandidato.setVisible(false);
        }
    }
    public void ConfermaElezione(ActionEvent actionEvent) throws SQLException, IOException {
        if(candidatoList.size() <= 1){
            allertConfermaElezione.setText("Devi aggiungere almeno due candidati");
            allertConfermaElezione.setVisible(true);
        }else {
            votazioneDao = new VotazioneDaoImpl();
            votazioneDao.changeStatus(votazione, "Approvata");
            VotazioniController vc = new VotazioniController();
            vc.setUtente(utente);
            Utils.changeScene(actionEvent, "VotazioniScene.fxml", vc);

        }
    }

    public void Logout(ActionEvent actionEvent) throws IOException {

        Utils.changeScene(actionEvent, "LoginScene.fxml");
    }

    public void setUtenteEVotazione(Utente u, Votazione v){
        this.utente=u;
        this.votazione=v;
        System.out.println(v);
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            utenteDao = new UtenteDaoImpl();
            candidatoList = FXCollections.observableList(utenteDao.getAllUtentiByIdVotazione(votazione.getId()));
            System.out.println("candidato list = " + candidatoList);
        } catch (Exception e) {
            System.out.println("erroreeee");
        }

        if(votazione.getStatus().equals("Approvata")){
            codFiscalefield.setVisible(false);
            aggiungiUnCandidato.setVisible(false);
            Aggiungi.setVisible(false);
            Conferma.setVisible(false);
            codFs.setText("LISTA DEI CANDIDATI AGGIUNTI");
            codFs.setVisible(true);
            DataDiInizioField.setText(votazione.getInizio().toString());
            DataDiInizioField.setVisible(true);
            DataDiFineField.setText(votazione.getFine().toString());
            DataDiFineField.setVisible(true);
            IdElezioneField.setText(votazione.getId());
            IdElezioneField.setVisible(true);
            DataInizio.setVisible(true);
            DataDiFine.setVisible(true);
            idElezione.setVisible(true);
        }

        NomeElezione.setText(this.votazione.getNome());
        allertCandidato.setVisible(false);
        allertConfermaElezione.setVisible(false);

        name.setCellValueFactory(new PropertyValueFactory<Utente, String>("name"));
        codFiscale.setCellValueFactory(new PropertyValueFactory<Utente, String>("codFiscale"));
        Ctable.setItems(candidatoList);
    }

        public void setUtente(Utente u){
        this.utente = u;
        }

}
