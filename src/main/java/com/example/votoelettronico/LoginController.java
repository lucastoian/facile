package com.example.votoelettronico;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private static final String url = "jdbc:postgresql://ec2-34-255-134-200.eu-west-1.compute.amazonaws.com:5432/du00brnb1t9ok";
    private static final String user = "ykeiygtowzihlm";
    private static final String password = "70c908bb89427bd76a11350372a669f02b455e056c3fd572f4a94d1d2b65d48c";

    public void GoToManageScene(ActionEvent actionEvent) {
    }

    public void GoToRegisterScene(ActionEvent actionEvent) {
    }

    public void goToVote(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        com.example.votoelettronico.Utente u = new com.example.votoelettronico.Utente("Luca", "Stoian", "lucasd@asd.com", "asdasd", "asdasd");
        System.out.println(u);

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement("Select * from utente");
            ResultSet result = pst.executeQuery();
            while(result.next()){
                System.out.println(result.getString(1));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
