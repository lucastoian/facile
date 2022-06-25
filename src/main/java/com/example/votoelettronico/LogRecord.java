package com.example.votoelettronico;

import java.sql.*;

public class LogRecord {

    private static final String url = "jdbc:postgresql://ec2-34-255-134-200.eu-west-1.compute.amazonaws.com:5432/du00brnb1t9ok";
    private static final String user = "ykeiygtowzihlm";
    private static final String password = "70c908bb89427bd76a11350372a669f02b455e056c3fd572f4a94d1d2b65d48c";


    private static Connection openConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    private static void closeConnection(Connection con) throws SQLException {
        con.close();
    }


    private String action;
    private String state;
    private int logid;
    private String votazione ;
    private Timestamp data;


    public LogRecord(int logid,Timestamp data,String votazione,String action,String state) {
        this.action = action;
        this.logid = logid;
        this.votazione = votazione;
        this.state = state;
        this.data = data;
    }

    public LogRecord(Timestamp data,String votazione,String action,String state) {
        this.action = action;
        //this.logid = ;
        this.votazione = votazione;
        this.state = state;
        this.data = data;
    }

    public static void fakeRecord() throws SQLException {
        System.out.println("prima della query");
        String query = "INSERT INTO LOG VALUES(default,NOW(), ? ,? , ?)";

        Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement pst = con.prepareStatement(query);
        //pst.setString(1, "default");
        //pst.setString(1, this.getLogid());
        //pst.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
        pst.setString(1, "");
        pst.setString(2, "");
        pst.setString(3, "");

        pst.executeUpdate();
        System.out.println("log aggiunto");
        con.close();
    }

    public void createRecord() throws SQLException {

        String query = "INSERT INTO log VALUES(default,? , ?, ?, ?)";

        Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement pst = con.prepareStatement(query);

        //pst.setString(1, "default");
        //pst.setString(1, this.getLogid());
        pst.setTimestamp(2, this.getData());
        pst.setString(3, this.getVotazione());
        pst.setString(4, this.getAction());
        pst.setString(5, this.getState());

        pst.executeUpdate();
        System.out.println("log aggiunto");
        con.close();
    }

    public LogRecord getRecord(){
        return null;
    }

    public String getState() {
        return state;
    }

    public Timestamp getData() {
        return data;
    }

    public String getAction() {
        return action;
    }

    public String getVotazione() {
        return votazione;
    }






}
