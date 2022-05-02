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

public class PanoramicaCandidatiController implements Initializable {
    Votazione v;
    UtenteDao utenteDao;
    @FXML
    public Label NomeElezione, UserNameLabel;
    @FXML
    public TextField codFiscalefield;
    @FXML
    public Button Aggiungi;
    @FXML
    public Text allertCandidato,allertConfermaElezione,aggiungiUnCandidato;
    @FXML
    public TableView Ctable;
    @FXML
    private TableColumn<Utente, String> name,codFiscale;
    @FXML
    public Button Conferma;
    ObservableList<Utente> candidatoList = FXCollections.observableArrayList();


    public void goToManager(ActionEvent actionEvent) {
    }

    public void goToPartecipanti(ActionEvent actionEvent) {
    }

    public void goToCandidati(ActionEvent actionEvent) {
    }

    public void goToOption(ActionEvent actionEvent) {
    }

    public void cambiaElezione(ActionEvent actionEvent) {
    }
    public void AggiungiCandidato(ActionEvent actionEvent) throws SQLException {
           Utente u = utenteDao.getUtenteByCodFiscale(codFiscalefield.getText());
           utenteDao.addCandidato(u, v);
           candidatoList.add(u);
    }
    public void ConfermaElezione(ActionEvent actionEvent) {
    }

    public void Logout(ActionEvent actionEvent) throws IOException {

        Utils.changeScene(actionEvent, "LoginScene.fxml");
    }



    public void setVotazione(Votazione v){
        this.v = v;

        System.out.println(v);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initialize");
        try {
            utenteDao = new UtenteDaoImpl();
            candidatoList = FXCollections.observableList(utenteDao.getAllUtentiByIdVotazione(v.getId()));
            System.out.println("candidato list = " + candidatoList);
        } catch (Exception e) {
            System.out.println("erroreeee");
        }

        NomeElezione.setText(this.v.getNome());
        allertCandidato.setVisible(false);
        allertConfermaElezione.setVisible(false);

        name.setCellValueFactory(new PropertyValueFactory<Utente, String>("name"));
        codFiscale.setCellValueFactory(new PropertyValueFactory<Utente, String>("codFiscale"));
        Ctable.setItems(candidatoList);
    }

}
