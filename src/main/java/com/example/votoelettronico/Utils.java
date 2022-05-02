package com.example.votoelettronico;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class Utils {


    public static void changeScene(ActionEvent event, String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(Utils.class.getResource(fxml));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Cambia una scena utilizzando un controller creato prima, utile se si vuole passare argomenti al controller
     * @param event
     * @param fxml
     * @param controller
     * @throws IOException
     */
    public static void changeScene(ActionEvent event, String fxml, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(Utils.class.getResource(fxml));
        loader.setController(controller);
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




    /**
     * data un eccezione sql, prova a vedere se si tratta di un constraint violato e restituisce
     * un messaggio sotto forma di stringa
     * @param e eccezione sollevata
     * @return un messaggio sotto forma di stringa
     */
    public static String gestioneConstraint(SQLException e) {
        ServerErrorMessage postgresError = ((PSQLException) e).getServerErrorMessage();
        //System.out.println(postgresError);
        if (postgresError != null) {
            String constraint = postgresError.getConstraint();
            System.out.println("CONSTRAINT GENERATO = " + constraint);
            if ("utente_cognome_check".equalsIgnoreCase(constraint)) {

                return "Il cognome deve avere almeno 4 caratteri";
            }
            if ("utente_email_check".equalsIgnoreCase(constraint)) {
                return "L'email deve avere almeno 6 caratteri";
            }
            if ("utente_nome_check".equalsIgnoreCase(constraint)) {
                return "Il nome deve avere almeno 4 caratteri";
            }
            if ("utente_password_check".equalsIgnoreCase(constraint)) {
                return "La password deve avere almeno 9 caratteri";
            }
            if ("utente_codfiscale_check".equalsIgnoreCase(constraint)) {
                return "Il codice fiscale deve essere formato da 16 caratteri";
            }

            if ("utente_pkey".equalsIgnoreCase(constraint)) {
                return "Questo codice fiscale risulta gia registrato";
            }
            if ("utente_email_key".equalsIgnoreCase(constraint)) {
                return "Questa email risulta gia registrata";
            }
        }
        return "Eccezione sql, constarint non identificato";
    }

}
