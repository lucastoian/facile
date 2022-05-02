package com.example.votoelettronico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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

    @FXML
    private DatePicker finalDateField, initialDateField;
    @FXML
    private TextField nameField;
    @FXML
    private ComboBox<String> tipiBox;

    private String[] tipiElezioni = {"ordinale","categorico","categorico con preferenze","referendum"};
    public void Logout(ActionEvent actionEvent) throws IOException {
        Utils.changeScene(actionEvent, "LoginScene.fxml");
    }

    public void createElection(ActionEvent actionEvent) throws SQLException {
        LocalDate initialDate = initialDateField.getValue();
        LocalDate finalDate = finalDateField.getValue();
        Random r = new Random();
        Votazione v = new Votazione("STNLCU00H23Z129G", nameField.getText(),String.valueOf(r.nextInt(1000000)), tipiBox.getValue(), Timestamp.valueOf(LocalDateTime.of(initialDate , LocalTime.now())), Timestamp.valueOf(LocalDateTime.of(finalDate , LocalTime.now())));
        VotazioneDaoImpl vt = new VotazioneDaoImpl();
        vt.addVotazione(v);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tipiBox.getItems().addAll(tipiElezioni);
    }
}
