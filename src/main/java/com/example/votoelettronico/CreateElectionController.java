package com.example.votoelettronico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;
import java.util.ResourceBundle;

public class CreateElectionController implements Initializable {

    Utente u;

    @FXML
    private DatePicker finalDateField, initialDateField;
    @FXML
    private TextField nameField;
    @FXML
    private Label UserNameLabel;
    @FXML
    private Text allertVot;
    @FXML
    private ComboBox<String> tipiBox;

    private String[] tipiElezioni = {"ordinale","categorico","categorico con preferenze","referendum"};
    public void Logout(ActionEvent actionEvent) throws IOException {
        Utils.changeScene(actionEvent, "LoginScene.fxml");
    }


    public void goIndietro(ActionEvent actionEvent) throws IOException {
        VotazioniController vc = new VotazioniController();
        vc.setUtente(u);
        Utils.changeScene(actionEvent, "VotazioniScene.fxml", vc);
    }



    public void createElection(ActionEvent actionEvent)throws IOException {
        try {

            LocalDate initialDate = finalDateField.getValue();
            LocalDate finalDate = initialDateField.getValue();
            Random r = new Random();
            Votazione v = new Votazione(u.getCodFiscale(), nameField.getText(), String.valueOf(r.nextInt(1000000)), tipiBox.getValue(), Timestamp.valueOf(LocalDateTime.of(initialDate, LocalTime.now())), Timestamp.valueOf(LocalDateTime.of(finalDate, LocalTime.of(23,59,0))));
            VotazioneDaoImpl vt = new VotazioneDaoImpl();
            vt.addVotazione(v);
            v.setId(vt.getMaxId());
            //qui problema id

                    PanoramicaElezioniController pec = new PanoramicaElezioniController();
                    pec.setUtenteEVotazione(u,v);

                    Utils.changeScene(actionEvent, "panoramicaElezioni.fxml", pec);



        }catch (SQLException sqlException){
            allertVot.setText(Utils.gestioneConstraint(sqlException));
            allertVot.setVisible(true);
        } catch (NullPointerException np){
            allertVot.setText("Compila tutti i campi");
            allertVot.setVisible(true);
        }

    }

    public void setUtente(Utente u){
        this.u = u;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allertVot.setVisible(false);
        tipiBox.getItems().addAll(tipiElezioni);
        UserNameLabel.setText(u.getName());
    }
}
