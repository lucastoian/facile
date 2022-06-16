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
    public List<Votazione> getAllVotazioniByCodFiscale(String codFiscale) throws SQLException {
        List<Votazione> v = new ArrayList<>();
        String query = "SELECT * FROM votazione WHERE proprietario = ? ORDER BY status";
        Connection con= openConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, codFiscale);
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
        //votazioni.add(v);
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

    @Override
    public Votazione getVotazioneById(String id) throws SQLException {
        String query = "SELECT * FROM votazione WHERE id = ?";
        Connection con= openConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, id);
        ResultSet result = pst.executeQuery();
        con.close();
        if(result.next()){
            Votazione vot = new Votazione(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getTimestamp(5), result.getTimestamp(6), result.getString(7), result.getString(8));
            return vot;
        }else{

            return null;
        }


    }



    @Override
    public void Vota(Utente u, Votazione v, Utente votato, Timestamp data)  { //categorico
        try {
            String query = "INSERT INTO votanti VALUES (?,?,?,?,?,?)";
            Connection con = openConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, u.getCodFiscale());
            pst.setString(2, votato.getCodFiscale());
            pst.setString(3, v.getId());
            pst.setBoolean(4, true);
            pst.setInt(5, 1);
            pst.setTimestamp(6, data);
            pst.executeUpdate();
            con.close();
            System.out.println("Ho votato con successo");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void Vota(Utente u, Votazione v, List<Utente> utentiVotatiInOrdine, Timestamp data) throws SQLException { //ordinale
        String query = "INSERT INTO votanti VALUES (?,?,?,?,?,?)";
        Connection con = openConnection();
        for(int i = utentiVotatiInOrdine.size()-1; i>=0; i--){
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, u.getCodFiscale());
            pst.setString(2, utentiVotatiInOrdine.get(i).getCodFiscale());
            pst.setString(3, v.getId());
            pst.setBoolean(4,true);
            pst.setInt(5, (i-utentiVotatiInOrdine.size())*-1);
            pst.setTimestamp(6,data);
            pst.executeUpdate();
        }
        con.close();


    }

    @Override
    public void Vota(Utente u, Votazione v, int punteggio, Utente votato, Boolean favorevole, Timestamp data) throws SQLException {
        String query = "INSERT INTO votanti VALUES (?,?,?,?,?,?)";
        Connection con = openConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, u.getCodFiscale());
        pst.setString(2, votato.getCodFiscale());
        pst.setString(3, v.getId());
        pst.setBoolean(4, favorevole);
        pst.setInt(5, punteggio);
        pst.setTimestamp(6,data);
        pst.executeUpdate();
        con.close();
        System.out.println("Ho votato con successo");

    }

    @Override
    public void setDomanda(Votazione v, String domanda) throws SQLException {
        String sql = "UPDATE votazione SET domanda = ? WHERE id = ?";
        Connection con = openConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, domanda);
        pst.setString(2, v.getId());
        pst.executeUpdate();
        con.close();
    }

    @Override
    public boolean haGiaVotato(Utente u, Votazione v) throws SQLException {
        String query = "SELECT * FROM votanti WHERE votante = ? AND id = ?";
        Connection con = openConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, u.getCodFiscale());
        pst.setString(2, v.getId());
        ResultSet result = pst.executeQuery();
        if(result.next()) return true;
        return false;
    }

    @Override
    public boolean checkIfInTime(Timestamp t, Votazione v) throws SQLException {
        String query = "SELECT inizio, fine FROM votazione WHERE id = ?";
        Connection con = openConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, v.getId());
        ResultSet result = pst.executeQuery();
        if(result.next()) {
            Timestamp inizio = result.getTimestamp(1);
            Timestamp fine = result.getTimestamp(2);
            con.close();
            if (t.after(inizio)&& t.before(fine)) return  true;
        }
        con.close();
        return false;
    }

    @Override
    public Timestamp getVotazioneEndTime(Votazione v) throws SQLException {
        String query = "SELECT fine FROM votazione WHERE id = ?";
        Connection con = openConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, v.getId());
        ResultSet result = pst.executeQuery();
        if(result.next()) {
            Timestamp inizio = result.getTimestamp(1);
            con.close();
            return inizio;
        }
        return null;
    }



    public void changeEndDate(Votazione v, Timestamp t) throws SQLException {
        String sql = "UPDATE votazione SET fine = ? WHERE id = ?";
        Connection con = openConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setTimestamp(1, t);
        pst.setString(2, v.getId());
        pst.executeUpdate();
        con.close();
    }


    @Override
    /**
     * @return restituisce [TotaleVotanti, TotaleVotiFavorevoli]
     */
    public int[] getRisultatoReferendum(Votazione v) throws SQLException {

        int[] queryResult = {0, 0};

        String query = "SELECT count(*), sum(favorevolecontrario::int) FROM public.votanti where id = ?";  //casto il boolean in un intero e li conto
        Connection con = openConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, v.getId());
        ResultSet result = pst.executeQuery();
        con.close();
        result.next();
        queryResult[0] = result.getInt(1);

        queryResult[1] = result.getInt(2);

        return queryResult;
    }

}
