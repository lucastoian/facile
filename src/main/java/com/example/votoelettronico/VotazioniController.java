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
import java.util.ResourceBundle;

public class VotazioniController implements Initializable {


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
        Utils.changeScene(actionEvent, "CreateElectionScene.fxml");
    }

    public void goToElection(ActionEvent actionEvent) throws IOException {
        selectedVotazione = tabVotazioni.getSelectionModel().getSelectedItem();

        switch (selectedVotazione.getTipo()){
            case "referendum":
                PanoramicaReferendumController pcr = new PanoramicaReferendumController();
                pcr.setVotazione(selectedVotazione);
                Utils.changeScene(actionEvent, "panoramicaReferendum.fxml",pcr);
                break;
            case "ordinale":
            case "categorico":
                PanoramicaCandidatiController pc = new PanoramicaCandidatiController();
                pc.setVotazione(selectedVotazione);
                Utils.changeScene(actionEvent, "panoramicaCandidati.fxml", pc);
                break;

        }


    }

    public void deleteElection(ActionEvent actionEvent) {
    }

    public Votazione getSelectedVotazione(){
        return this.selectedVotazione;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            votazioneDao = new VotazioneDaoImpl();
            votazioniList = FXCollections.observableList(votazioneDao.getAllVotazioni());


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
    }


}
