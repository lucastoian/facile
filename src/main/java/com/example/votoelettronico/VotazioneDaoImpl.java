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

    }

    private static Connection openConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    private static void closeConnection(Connection con) throws SQLException {
        con.close();
    }

    @Override
    public List<Votazione> getAllVotazioni() throws SQLException {
        List<Votazione> v = new ArrayList<>();
        String query = "SELECT * FROM votazione ORDER BY status";
        Connection con= openConnection();
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet result = pst.executeQuery();

        while(result.next()){
            Votazione vot = new Votazione(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getTimestamp(5), result.getTimestamp(6), result.getString(7), result.getString(8));
            v.add(vot);
        }
        closeConnection(con);
        return v;
    }

    @Override
    public void addVotazione(Votazione v) throws SQLException {

        String query = "INSERT INTO votazione VALUES(?, ?, default, ?, ?, ?, ?, ?)";

        Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, v.getProp());
        pst.setString(2, v.getNome());
        //pst.setString(3, "default");
        pst.setString(3, v.getTipo());
        pst.setTimestamp(4, v.getInizio());
        pst.setTimestamp(5, v.getFine());
        pst.setString(6, v.getDomanda());
        pst.setString(7, v.getStatus());


        pst.executeUpdate();
        votazioni.add(v);
        System.out.println("Votazione aggiunta");
        con.close();

    }

    @Override
    public void deleteVotazione(Votazione v) {

    }

    @Override
    public void changeStatus(Votazione v, String status) throws SQLException {
        String query = "UPDATE votazione SET status = ? WHERE id =?";
        Connection con = openConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, status);
        pst.setString(2, v.getId());
        pst.executeUpdate();
        System.out.println("Status aggiornato");
        v.setStatus(status);
        con.close();


    }
}
