package com.example.votoelettronico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.*;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class LoginController implements Initializable{



    private static UtenteDao utenteDao = null;

    static {
        try {
            utenteDao = new UtenteDaoImpl();
        } catch (SQLException e) {
            System.out.println(Utils.gestioneConstraint(e));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    private TextField PasswordField, EmailField;
    @FXML
    private Text allert;


    public LoginController() throws Exception {

    }


    public void GoToManageScene(ActionEvent actionEvent) throws IOException, SQLException {
        String psw = PasswordField.getText();
        String email = EmailField.getText();
        if(utenteDao.loginUtente(email, psw)){
            System.out.println("log-in riuscito");

            Utils.changeScene(actionEvent, "VotazioniScene.fxml");


        }else{
            System.out.println("Log-in fallito");
            allert.setVisible(true);
        }

    }

    public void GoToRegisterScene(ActionEvent actionEvent) throws IOException {
        Utils.changeScene(actionEvent, "RegisterScene.fxml");
    }

    public void goToVote(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(LocalTime.MIDNIGHT);
        EmailField.setText("lucastoian@outlook.com");
        PasswordField.setText("topogigio");
        allert.setVisible(false);
    }

}
