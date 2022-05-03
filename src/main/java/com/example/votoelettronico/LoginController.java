package com.example.votoelettronico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    private Utente u;
    private VotazioneDao votazioneDao;
    private UtenteDao utenteDao;



    @FXML
    private TextField PasswordField, EmailField, CODField, VotazioneField;
    @FXML
    private Text allert,allertVot;



    public LoginController() throws Exception {

    }


    public void GoToManageScene(ActionEvent actionEvent) throws IOException, SQLException {
        String psw = PasswordField.getText();
        String email = EmailField.getText();
        if(utenteDao.loginUtente(email, psw)){
            System.out.println("log-in riuscito");
            VotazioniController vc = new VotazioniController();
            vc.setUtente(utenteDao.getUtenteByEmail(email));
            Utils.changeScene(actionEvent, "VotazioniScene.fxml", vc);


        }else{
            System.out.println("Log-in fallito");
            allert.setVisible(true);
        }

    }

    public void GoToRegisterScene(ActionEvent actionEvent) throws IOException {
        Utils.changeScene(actionEvent, "RegisterScene.fxml");
    }

    public void goToVote(ActionEvent actionEvent) throws IOException, SQLException {



        VoteController vc = new VoteController();
        votazioneDao = new VotazioneDaoImpl();
        Votazione v = votazioneDao.getVotazioneById(VotazioneField.getText());
        u = utenteDao.getUtenteByCodFiscale(CODField.getText());
        if(votazioneDao.haGiaVotato(u,v)){
            allertVot.setText("L'utente ha gia votato");
            allertVot.setVisible(true);
        }else {

            vc.setCodFiscaleAndVotazione(CODField.getText(), v);
            try {
                if (v.getStatus().equals("Approvata")) {
                    Utils.changeScene(actionEvent, "VoteScene.fxml", vc);
                } else {
                    allertVot.setText("L'id della votazione non è corretto oppure la votazione non è ancora iniziata");
                    allertVot.setVisible(true);
                }

            } catch (Exception e) {
                allertVot.setText("L'id della votazione non è corretto");
                allertVot.setVisible(true);
            }

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            utenteDao = new UtenteDaoImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(LocalTime.MIDNIGHT);
        VotazioneField.setText("12362");
        EmailField.setText("lucastoian@outlook.com");
        PasswordField.setText("topogigio");
        allert.setVisible(false);
        allertVot.setVisible(false);
    }

}
