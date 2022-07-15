package com.example.votoelettronico;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    private String logid;
    private String votazione ;
    private Timestamp data;


    public LogRecord(String votazione,String action,String state) {
        this.action = action;
        this.votazione = votazione;
        this.state = state;
    }


    public LogRecord(String logid,Timestamp data,String votazione,String action,String state) {
        this.action = action;
        this.logid = logid;
        this.votazione = votazione;
        this.state = state;
        this.data = data;
    }

/*    public static void fakeRecord() throws SQLException {
        System.out.println("prima della query");
        String query = "INSERT INTO LOG VALUES(default,NOW(), ? ,? , ?)";

        Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, "");
        pst.setString(2, "");
        pst.setString(3, "");

        pst.executeUpdate();
        System.out.println("log aggiunto");
        con.close();
    }*/

    public void createRecord() throws SQLException, IOException {
        String query = "INSERT INTO LOG VALUES(default,NOW() , ?, ?, ?)";
        Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement pst = con.prepareStatement(query);
        //pst.setString(1, "default");
        //pst.setString(2, "NOW());
        pst.setString(1, this.getVotazione());
        pst.setString(2, this.getAction());
        pst.setString(3, this.getState());

        pst.executeUpdate();
        System.out.println("nuovo record sul log");
        con.close();
        //writeToTxt();
    }



    public static List<LogRecord> getAllRecords() throws SQLException {
        List<LogRecord> log = new ArrayList<>();
        String query = "SELECT * FROM log ORDER BY data";
        Connection con= openConnection();
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet result = pst.executeQuery();

        while(result.next()){
            LogRecord record = new LogRecord(result.getString(1), result.getTimestamp(2), result.getString(3), result.getString(4), result.getString(5));
            log.add(record);
        }
        closeConnection(con);
        return log;
    }

    public static void writeToTxt() throws SQLException, IOException {
        List<LogRecord> log = getAllRecords(); ;

        BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt"));

        for (LogRecord l: log) {
            writer.write(l.getData()+" | "+l.getAction()+" | "+l.getVotazione()+" | "+l.getState()+"\n");
        }

        writer.close();
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
