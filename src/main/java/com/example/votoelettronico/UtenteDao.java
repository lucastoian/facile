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
    public void deleteUtente();

    /**
     * permette di fare il log-in
     *
     * @param email
     * @param password
     * @return true se esistono queste credenziali nel db
     */
    public Boolean loginUtente(String email, String password) throws SQLException;
}
