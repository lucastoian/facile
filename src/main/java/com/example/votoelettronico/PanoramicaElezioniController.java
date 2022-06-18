package com.example.votoelettronico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;

public class PanoramicaElezioniController implements Initializable {

    private Utente utente;
    private Votazione votazione;

    private VotazioneDao votazioneDao;

    private int numCandidati;

    @FXML
    private Text allertVot;

    @FXML
    private TextField nameField, dataInizioField, dataFineField, oraInizioField, oraFineField;
    @FXML
    private Label statusLabel, idLabel;

    @FXML
    private Label candidatiLabel, tipologiaLabel;
    @FXML
    Button candidati,conferma,termina,Salva;



    public void goIndietro(ActionEvent actionEvent) throws IOException {
        VotazioniController vc = new VotazioniController();
        vc.setUtente(utente);
        Utils.changeScene(actionEvent,"VotazioniScene.fxml", vc );
    }
    public void SalvaButton(ActionEvent actionEvent) throws IOException {

        try {
            String dataInizio = dataInizioField.getText();
            String[] oraInizio = oraInizioField.getText().split(":");

            String dataFine = dataFineField.getText();
            String[] oraFine = oraFineField.getText().split(":");

            DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
            Date inizio = formatter1.parse(dataInizio);
            Date fine = formatter1.parse(dataFine);
            votazione.setNome(nameField.getText());
            NomeElezione.setText(nameField.getText());

            // int year,
            //    int month,
            //    int date,
            //    int hour,
            //    int minute,
            //    int second,
            //    int nano
            Timestamp i = new Timestamp(inizio.getYear(), inizio.getMonth(), inizio.getDate(), Integer.parseInt(oraInizio[0]), Integer.parseInt(oraInizio[1]), 0, 0);
            Timestamp f = new Timestamp(fine.getYear(), fine.getMonth(), fine.getDate(), Integer.parseInt(oraFine[0]), Integer.parseInt(oraFine[1]), 0, 0);

            votazioneDao.updateOrari(i, f, votazione);
        } catch (ParseException p){

            allertVot.setText("Il formato inserito non è corretto. \nIl formato per le date è : GIORNO/MESE/ANNO \nil formato per le ore è: ORA/MINUTI");
            allertVot.setVisible(true);

        } catch (SQLException s){
            allertVot.setText(Utils.gestioneConstraint(s));
            allertVot.setVisible(true);
        }

        /**
        String oraInizio = oraInizioField.getText();
        Timestamp inizio = new Timestamp();
         */




    }
    public void TerminaSubitoButton(ActionEvent actionEvent) throws IOException, SQLException {
    votazioneDao= new VotazioneDaoImpl();
    votazioneDao.changeEndDate(votazione, Timestamp.valueOf(LocalDateTime.now()));
    allertVot.setText("La votazione è terminata! \n Puoi consultare i risultati nella sezione 'Risultati'");
    allertVot.setVisible(true);
    }


    @FXML
    public Button NomeElezione;

    @FXML
    public Label UserNameLabel;





    public void cambiaElezione(ActionEvent actionEvent) throws IOException{

    }
    public void goToCandidati(ActionEvent actionEvent) throws IOException {

        switch(votazione.getTipo()){
            case "referendum":
                candidati.setText("Domanda");
                PanoramicaReferendumController pec = new PanoramicaReferendumController();
                pec.setUtenteEVotazione(utente,votazione);
                Utils.changeScene(actionEvent, "panoramicaReferendum.fxml",pec);
                break;
            default:
                PanoramicaCandidatiController pcc = new PanoramicaCandidatiController();
                pcc.setUtenteEVotazione(utente,votazione);
                Utils.changeScene(actionEvent, "panoramicaCandidati.fxml",pcc);

        }

    }

    public void goToRisultato(ActionEvent actionEvent) throws IOException{
        RisultatoController rc = new RisultatoController();
        rc.setUtenteEVotazione(utente,votazione);
        Utils.changeScene(actionEvent, "Risultato.fxml",rc);
    }

    public void goToPartecipanti(ActionEvent actionEvent) throws IOException {
        PartecipantiController pc = new PartecipantiController();
        pc.setUtenteEVotazione(utente,votazione);
        Utils.changeScene(actionEvent, "Partecipanti.fxml",pc);
    }


    public void goToManager(ActionEvent actionEvent) throws IOException {
        System.out.println("panoramica");
        PanoramicaElezioniController op = new PanoramicaElezioniController();
        op.setUtenteEVotazione(utente,votazione);
        Utils.changeScene(actionEvent, "panoramicaElezioni.fxml",op);
    }



    public void Logout(ActionEvent actionEvent) throws IOException {
        Utils.changeScene(actionEvent, "LoginScene.fxml");
    }
    public void ConfermaElezione(ActionEvent actionEvent) throws IOException, SQLException {
                switch(votazione.getTipo()){
                    case "referendum":
                        if(votazione.getDomanda()==null){
                                allertVot.setText("DEVI INSERIRE UNA DOMANDA");
                                allertVot.setVisible(true);
                            return;
                        }
                        break;
                    default:
                        if(numCandidati<2){
                                allertVot.setText("DEVI AGGIUNGERE ALMENO 2 CANDIDATI");
                            allertVot.setVisible(true);
                            return;
                        }

                }
                VotazioneDao vd = new VotazioneDaoImpl();
                vd.changeStatus(votazione, "Approvata");
                statusLabel.setText("Status: Approvata");
                conferma.setVisible(false);




    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        candidatiLabel.setText("casd");

        try {
            votazioneDao = new VotazioneDaoImpl();
            numCandidati = votazioneDao.getNumeroCandidatiById(votazione);
            System.out.println("Numero candidati = " + numCandidati);
            candidatiLabel.setText("Numero candidati: "+ numCandidati);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        termina.setVisible(false);

        switch(votazione.getTipo()){
            case "referendum":
                candidati.setText("Domanda");
                candidatiLabel.setVisible(false);
                break;
            default:
                candidatiLabel.setVisible(true);
        }

        if(!(votazione.getStatus().equals("Draft"))){
            conferma.setVisible(false);
        }

        if((votazione.getStatus().equals("Approvata"))){
            termina.setVisible(true);
        }
        if((votazione.getStatus().equals("Terminata"))){
            Salva.setVisible(false);
        }

        allertVot.setVisible(false);
        idLabel.setText("Id: "+ votazione.getId());
        tipologiaLabel.setText("Tipologia: " + votazione.getTipo());
        candidatiLabel.setText("Candidati iscritti: " + votazione.getCandidatiSize());
        NomeElezione.setText(this.votazione.getNome());
        UserNameLabel.setText(this.utente.getName());
        nameField.setText(votazione.getNome());
        Timestamp votationEndTime = votazione.getFine();
        String dataFine = votationEndTime.toLocalDateTime().getDayOfMonth() + "/" + votationEndTime.toLocalDateTime().getMonthValue() + "/"  + votationEndTime.toLocalDateTime().getYear();
        String oraFine = votationEndTime.toLocalDateTime().getHour()+":"+votationEndTime.toLocalDateTime().getMinute();

        Timestamp votationStartTime = votazione.getInizio();
        String dataInizio = votationStartTime.toLocalDateTime().getDayOfMonth() + "/" + votationStartTime.toLocalDateTime().getMonthValue() +"/" + votationStartTime.toLocalDateTime().getYear();
        String oraInizio = votationStartTime.toLocalDateTime().getHour()+":"+votationStartTime.toLocalDateTime().getMinute();
        dataFineField.setText(dataFine);
        oraFineField.setText(oraFine);
        dataInizioField.setText(dataInizio);
        oraInizioField.setText(oraInizio);
        statusLabel.setText("STATUS: " + votazione.getStatus());



    }

    public void setUtenteEVotazione(Utente u, Votazione v){
        this.utente=u;
        this.votazione=v;
    }

}
