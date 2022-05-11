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
    Votazione v;
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
        try {
            utenteDao = new UtenteDaoImpl();

        } catch (Exception e) {

        }

        NomeElezione.setText(this.v.getNome());
        allertConfermaElezione.setVisible(false);

    }

}
