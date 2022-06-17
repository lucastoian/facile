package com.example.votoelettronico;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface VotazioneDao {

    public List<Votazione> getAllVotazioni() throws SQLException;
    public List<Votazione> getAllVotazioniByCodFiscale(String codFiscale) throws SQLException;
    public void addVotazione(Votazione v) throws SQLException;
    public void deleteVotazione(Votazione v) throws SQLException;

    int getNumeroCandidatiById(Votazione v) throws SQLException;

    public void changeStatus(Votazione v, String status) throws SQLException;
    public Votazione getVotazioneById(String id) throws SQLException;

    void Vota(Utente u, Votazione v, Utente votato, Timestamp data) throws SQLException;

    void Vota(Utente u, Votazione v, List<Utente> utentiVotatiInOrdine,Timestamp data) throws SQLException;

    void Vota(Utente u, Votazione v, int punteggio, Utente votato, Boolean favorevole,Timestamp data) throws SQLException;

    void setDomanda(Votazione v, String domanda) throws SQLException;

    public boolean haGiaVotato(Utente u, Votazione v) throws SQLException;

    boolean checkIfInTime(Timestamp t, Votazione v) throws SQLException;
    public  Timestamp getVotazioneEndTime(Votazione v) throws SQLException;
    public void changeEndDate(Votazione v, Timestamp t) throws SQLException;

    int[] getRisultatoReferendum(Votazione v) throws SQLException;
}
