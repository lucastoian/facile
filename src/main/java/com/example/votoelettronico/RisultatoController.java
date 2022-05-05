package com.example.votoelettronico;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class RisultatoController implements Initializable {

    @FXML
    Text allertVot;
    @FXML
    AnchorPane ORDINALE_CATEGORICO, REFERENDUM;
    @FXML
    TableView<Candidato> tabellaCandidati;
    @FXML
    TableColumn<Candidato, String> nome, cognome, codFiscale;
    @FXML
    TableColumn<Candidato, Integer> punteggio;

    private Votazione votazione;
    private  Utente utente;
    private VotazioneDao votazioneDao;
    private UtenteDao utenteDao;
    private ObservableList<Candidato> candidatoList;
    public void goIndietro(ActionEvent actionEvent) throws IOException {
        VotazioniController vc = new VotazioniController();
        vc.setUtente(utente);
        Utils.changeScene(actionEvent,"VotazioniScene.fxml", vc );
    }
    public void Logout(ActionEvent actionEvent) throws IOException {
        Utils.changeScene(actionEvent, "LoginScene.fxml");
    }
    public void goToManager(ActionEvent actionEvent) throws IOException {
        PanoramicaCandidatiController pc = new PanoramicaCandidatiController();
        pc.setUtente(utente);
        pc.setVotazione(votazione);
        Utils.changeScene(actionEvent, "panoramicaCandidati.fxml", pc);
    }
    public void goToPartecipanti(ActionEvent actionEvent) throws IOException {

    }
    public void goToOption(ActionEvent actionEvent) throws IOException {

    }


    public void setUtenteVotazione(Utente u, Votazione v){
        this.utente = u;
        this.votazione = v;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            votazioneDao = new VotazioneDaoImpl();
            utenteDao = new UtenteDaoImpl();
            if(votazione.getStatus().equals("Draft")){
                allertVot.setText("La votazione è ancora in Draft, prima di vedere i risultati devi Confermarla");
                return;
            }
            LocalDateTime actualTime = LocalDateTime.now();
            Timestamp votationEndTime =votazioneDao.getVotazioneEndTime(votazione);
            if(Timestamp.valueOf(actualTime).after(votationEndTime) ){
                allertVot.setVisible(false);
                switch (votazione.getTipo()){
                    case "referendum":
                        REFERENDUM.setVisible(true);
                        break;
                    default:
                        ORDINALE_CATEGORICO.setVisible(true);
                        candidatoList = FXCollections.observableList(utenteDao.getAllCandidatoByIdVotazione(votazione));
                        nome.setCellValueFactory(new PropertyValueFactory<Candidato, String>("name"));
                        cognome.setCellValueFactory(new PropertyValueFactory<Candidato, String>("surname"));
                        codFiscale.setCellValueFactory(new PropertyValueFactory<Candidato, String>("codFiscale"));
                        punteggio.setCellValueFactory(new PropertyValueFactory<Candidato, Integer>("punteggio"));
                        tabellaCandidati.setItems(candidatoList);

                }
            }else{
                allertVot.setText("Il risultato sarà consultabile su questa pagina a partire dal: " + votationEndTime.toLocalDateTime().getDayOfMonth() + "/" + votationEndTime.toLocalDateTime().getMonthValue() + "/" + votationEndTime.toLocalDateTime().getYear()+ " alle ore "+ votationEndTime.toLocalDateTime().getHour()+":"+votationEndTime.toLocalDateTime().getMinute());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
