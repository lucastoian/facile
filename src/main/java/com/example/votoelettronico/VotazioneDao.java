package com.example.votoelettronico;

import java.sql.SQLException;
import java.util.List;

public interface VotazioneDao {

    public List<Votazione> getAllVotazioni() throws SQLException;
    public void addVotazione(Votazione v) throws SQLException;
    public void deleteVotazione(Votazione v);
    public void changeStatus(Votazione v, String status) throws SQLException;

}
