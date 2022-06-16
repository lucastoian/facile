package com.example.votoelettronico;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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

public class VoteController implements Initializable {
    private Votazione v;
    private Utente u;
    private String codiceFiscale;
    private VotazioneDao votazioneDao;
    private UtenteDao utenteDao;
    ObservableList<Utente> candidatoList = FXCollections.observableArrayList();

    @FXML
    RadioButton si, no;

    @FXML
    Text nomeElezione;
    @FXML
    AnchorPane ORDINALE,CATEGORICA,REFERENDUM;

    //ORDINALE
    @FXML
    TableView<Utente> tabellaCandidati;
    @FXML
    TableColumn<Utente, String> nome, cognome, codFiscale;

    //CATEGORICO
    @FXML
    TableView<Utente> tabellaCandidati1;
    @FXML
    TableColumn<Utente, String> nome1, cognome1, codFiscale1;



    public void confermaVoto(ActionEvent actionEvent) throws IOException, SQLException {

        switch (v.getTipo()) {
            case "categorico":
                votazioneDao.Vota(u, v, tabellaCandidati1.getSelectionModel().getSelectedItem(), Timestamp.valueOf(LocalDateTime.now()));
                System.out.println("Votazione fatta");
                break;
                case "ordinale":
                votazioneDao.Vota(u,v,candidatoList,Timestamp.valueOf(LocalDateTime.now()));
                System.out.println("Votazione fatta");
                break;

            case "referendum":

                votazioneDao.Vota(u, v, -1, new Utente(), si.isSelected(), Timestamp.valueOf(LocalDateTime.now()));
        }
        Utils.changeScene(actionEvent,"LoginScene.fxml");


    }
    public void clearSelected(ActionEvent actionEvent) throws IOException {

    }
    public void Logout(ActionEvent actionEvent) throws IOException {
        Utils.changeScene(actionEvent, "LoginScene.fxml");
    }

    public void spostaSu(ActionEvent actionEvent) throws IOException {
        try {
            int selected = tabellaCandidati.getSelectionModel().getFocusedIndex();
            System.out.println(selected);
            Utente selectedUtente = candidatoList.get(selected);
            Utente over = candidatoList.get(selected - 1);
            candidatoList.set(selected - 1, selectedUtente);
            candidatoList.set(selected, over);
            tabellaCandidati.setItems(candidatoList);
        }catch (Exception e){}

    }

    public void spostaGiu(ActionEvent actionEvent) throws IOException {
        try {
            int selected = tabellaCandidati.getSelectionModel().getFocusedIndex();
            System.out.println(selected);
            Utente above = candidatoList.get(selected);
            Utente under = candidatoList.get(selected + 1);
            candidatoList.set(selected, under);
            candidatoList.set(selected + 1, above);
            tabellaCandidati.setItems(candidatoList);
        }catch (Exception e){}

    }

    public void setCodFiscaleAndVotazione(String codFiscale, Votazione v){
        this.codiceFiscale = codFiscale;
        this.v = v;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){

            try {

                nomeElezione.setText(v.getNome());
                votazioneDao = new VotazioneDaoImpl();
                utenteDao = new UtenteDaoImpl();
                u = utenteDao.getUtenteByCodFiscale(codiceFiscale);
                candidatoList = FXCollections.observableList(utenteDao.getAllUtentiByIdVotazione(v.getId()));//forse problemi per i referendum
                System.out.println("Candidato lsit = " + candidatoList);
                //qua schifo
                nome.setCellValueFactory(new PropertyValueFactory<Utente, String>("name"));
                cognome.setCellValueFactory(new PropertyValueFactory<Utente, String>("surname"));
                codFiscale.setCellValueFactory(new PropertyValueFactory<Utente, String>("codFiscale"));
                tabellaCandidati.setItems(candidatoList);
                nome1.setCellValueFactory(new PropertyValueFactory<Utente, String>("name"));
                cognome1.setCellValueFactory(new PropertyValueFactory<Utente, String>("surname"));
                codFiscale1.setCellValueFactory(new PropertyValueFactory<Utente, String>("codFiscale"));
                tabellaCandidati1.setItems(candidatoList);
                switch (v.getTipo()) {


                    case "categorico":
                        CATEGORICA.setVisible(true);
                        ORDINALE.setVisible(false);
                        REFERENDUM.setVisible(false);

                        break;
                    case "referendum":
                        REFERENDUM.setVisible(true);
                        CATEGORICA.setVisible(false);
                        ORDINALE.setVisible(false);
                        break;
                    case "ordinale":
                        ORDINALE.setVisible(true);
                        CATEGORICA.setVisible(false);
                        REFERENDUM.setVisible(false);
                        break;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

