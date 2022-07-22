package com.example.votoelettronico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonDbConnection {
    private static SingletonDbConnection single_instance = null;

    private static final String url = "jdbc:postgresql://ec2-34-255-134-200.eu-west-1.compute.amazonaws.com:5432/du00brnb1t9ok";
    private static final String user = "ykeiygtowzihlm";
    private static final String password = "70c908bb89427bd76a11350372a669f02b455e056c3fd572f4a94d1d2b65d48c";

    public Connection c;



    SingletonDbConnection() throws SQLException {

        c = DriverManager.getConnection(url, user, password);

    }

    public static SingletonDbConnection getInstance() throws SQLException {
        if (single_instance == null)
            single_instance = new SingletonDbConnection();

        return single_instance;
    }

    /**
     * return the Connection to the database
     */
    Connection getConnection(){
        return this.c;
    }
}
