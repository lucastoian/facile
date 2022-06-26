package com.example.votoelettronico;

import java.sql.SQLException;
import java.util.List;

public interface UtenteDao {
    /**
     *
     * @return lista di tutti gli utenti presenti nel db
     */
    public List<Utente> getAllUtenti();
    public void addUtente(Utente u) throws SQLException;
    public void deleteUtente(String email) throws SQLException;

    public Boolean loginUtente(String email, String password) throws SQLException;

    public Utente getUtente(String email, String password) throws SQLException;

    public void addCandidato(Utente u, Votazione v) throws  SQLException;
    public Utente getUtenteByCodFiscale(String codFiscale) throws  SQLException;
    public Utente getUtenteByEmail(String email) throws SQLException;
    public List<Utente> getAllUtentiByIdVotazione(String id) throws SQLException;//DOVREBBE CHIAMARSI getAllCandidatiByIdVotazione
    public List<Candidato> getAllCandidatoByIdVotazione(Votazione v) throws SQLException;//DOVREBBE CHIAMARSI getAllCandidatiByIdVotazione
}
