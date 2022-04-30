package com.example.votoelettronico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField CodFiscaleField,EmailField,NameField,SurnameField, PasswordField;

    public void RegisterAndGoToLoginScene(ActionEvent actionEvent) throws Exception {
        Utente u = new Utente(NameField.getText(), SurnameField.getText(), EmailField.getText(), PasswordField.getText(), CodFiscaleField.getText());
        try{
            new UtenteDaoImpl().addUtente(u);
            Utils.changeScene(actionEvent, "VotazioniScene.fxml");
        } catch (Exception e){
            System.out.println("Parametri sbagliati");
        }

    }

    public void GoToLoginScene(ActionEvent actionEvent) throws IOException {
        Utils.changeScene(actionEvent, "LoginScene.fxml");
    }
}
