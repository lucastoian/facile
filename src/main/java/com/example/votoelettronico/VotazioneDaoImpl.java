package com.example.votoelettronico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VotazioneDaoImpl implements VotazioneDao{
    //credenziali per accedere al db hostato su heroku
    private static final String url = "jdbc:postgresql://ec2-34-255-134-200.eu-west-1.compute.amazonaws.com:5432/du00brnb1t9ok";
    private static final String user = "ykeiygtowzihlm";
    private static final String password = "70c908bb89427bd76a11350372a669f02b455e056c3fd572f4a94d1d2b65d48c";
    private List<Votazione> votazioni;
    public VotazioneDaoImpl() throws SQLException {
        this.votazioni = new ArrayList<>();
        String query = "SELECT * FROM votazione";
        Connection con= openConnection();
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet result = pst.executeQuery();

        while(result.next()){
            Votazione v = new Votazione(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getTimestamp(5), result.getTimestamp(6));
            votazioni.add(v);
        }
        closeConnection(con);
    }

    private static Connection openConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    private static void closeConnection(Connection con) throws SQLException {
        con.close();
    }

    @Override
    public List<Votazione> getAllVotazioni() {
        return votazioni;
    }

    @Override
    public void addVotazione(Votazione v) throws SQLException {

        String query = "INSERT INTO votazione VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, v.getProp());
        pst.setString(2, v.getNome());
        pst.setString(3, v.getId());
        pst.setString(4, v.getTipo());
        pst.setTimestamp(5, v.getInizio());
        pst.setTimestamp(6, v.getFine());
        pst.setString(7, v.getDomanda());
        pst.setString(8, v.getStatus());


        pst.executeUpdate();
        votazioni.add(v);
        System.out.println("Votazione aggiunta");
        con.close();

    }

    @Override
    public void deleteVotazione(Votazione v) {

    }

    @Override
    public void changeStatus(Votazione v) {

    }
}
