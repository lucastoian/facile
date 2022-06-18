package com.example.votoelettronico;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class VotazioniController implements Initializable {

    Utente u;

    @FXML
    Label UserNameLabel;

    @FXML
    private TableView<Votazione> tabVotazioni;


    @FXML
    private TableColumn<Votazione, String> fine, id, inizio, nome, tipologia, status;

    VotazioneDao votazioneDao;
    ObservableList<Votazione> votazioniList;
    Votazione selectedVotazione;

    public void Logout(ActionEvent actionEvent) throws IOException {
        Utils.changeScene(actionEvent, "LoginScene.fxml");
    }

    public void goToCreateElection(ActionEvent actionEvent) throws IOException {
        CreateElectionController c = new CreateElectionController();
        c.setUtente(u);
        Utils.changeScene(actionEvent, "CreateElectionScene.fxml", c);
    }

    public void goToElection(ActionEvent actionEvent) throws IOException {
        try {
            selectedVotazione = tabVotazioni.getSelectionModel().getSelectedItem();
            System.out.println("HAI SELEZIONATO: " + selectedVotazione);

            PanoramicaElezioniController pe = new PanoramicaElezioniController();
            pe.setUtenteEVotazione(u, selectedVotazione);
            Utils.changeScene(actionEvent, "panoramicaElezioni.fxml", pe);
        } catch (Exception e){
            System.out.println("errore");
        }



    }

    public void deleteElection(ActionEvent actionEvent) throws SQLException {
        selectedVotazione = tabVotazioni.getSelectionModel().getSelectedItem();
        votazioneDao.deleteVotazione(selectedVotazione);
        votazioniList.remove(selectedVotazione);

    }

    public Votazione getSelectedVotazione(){
        return this.selectedVotazione;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            UserNameLabel.setText(u.getName());

            votazioneDao = new VotazioneDaoImpl();
            votazioniList = FXCollections.observableList(votazioneDao.getAllVotazioniByCodFiscale(u.getCodFiscale()));
            Timestamp actualTimestamp = new Timestamp(System.currentTimeMillis());
            for (Votazione v: votazioniList) {
                if(!(v.getStatus().equals("Terminata")) && v.getFine().before(actualTimestamp)){
                    v.setStatus("Terminata");
                    votazioneDao.changeStatus(v, "Terminata");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        nome.setCellValueFactory(new PropertyValueFactory<Votazione, String>("nome"));
        id.setCellValueFactory(new PropertyValueFactory<Votazione, String>("id"));
        tipologia.setCellValueFactory(new PropertyValueFactory<Votazione, String>("tipo"));
        inizio.setCellValueFactory(new PropertyValueFactory<Votazione, String>("inizio"));
        fine.setCellValueFactory(new PropertyValueFactory<Votazione, String>("fine"));
        status.setCellValueFactory(new PropertyValueFactory<Votazione, String>("status"));

        tabVotazioni.setItems(votazioniList);
        System.out.println(votazioniList);

    }

    public void setUtente(Utente u){
        this.u = u;
    }

}
