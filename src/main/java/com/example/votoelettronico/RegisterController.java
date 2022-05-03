package com.example.votoelettronico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {

    @FXML
    private TextField CodFiscaleField,EmailField,NameField,SurnameField, PasswordField;
    @FXML
    private Text allert1;

    public void RegisterAndGoToLoginScene(ActionEvent actionEvent) throws Exception {
        Utente u = new Utente(NameField.getText(), SurnameField.getText(), EmailField.getText(), PasswordField.getText(), CodFiscaleField.getText());
        try{
            new UtenteDaoImpl().addUtente(u);
            VotazioniController vc = new VotazioniController();
            vc.setUtente(u);
            Utils.changeScene(actionEvent, "VotazioniScene.fxml", vc);
        } catch (SQLException e){
            allert1.setText(Utils.gestioneConstraint(e));
        }

    }

    public void GoToLoginScene(ActionEvent actionEvent) throws IOException {
        Utils.changeScene(actionEvent, "LoginScene.fxml");
    }
}
