package com.example.votoelettronico;

import java.sql.SQLException;
import java.util.List;

public interface VotazioneDao {

    public List<Votazione> getAllVotazioni() throws SQLException;
    public List<Votazione> getAllVotazioniByCodFiscale(String codFiscale) throws SQLException;
    public void addVotazione(Votazione v) throws SQLException;
    public void deleteVotazione(Votazione v);
    public void changeStatus(Votazione v, String status) throws SQLException;
    public Votazione getVotazioneById(String id) throws SQLException;

    void Vota(Utente u, Votazione v, Utente votato) throws SQLException;

    void Vota(Utente u, Votazione v, List<Utente> utentiVotatiInOrdine) throws SQLException;

    void Vota(Utente u, Votazione v, int punteggio, Utente votato, Boolean favorevole) throws SQLException;
    public boolean haGiaVotato(Utente u, Votazione v) throws SQLException;
}
