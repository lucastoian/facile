
import java.io.IOException;
import java.sql.SQLException;
import java.util.jar.Attributes.Name;

import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;

import Utente.Utente;
import Utente.UtenteDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;


public class RegisterController {

    private Stage stage; 
	private Scene scene; 
	private Parent root; 
    
    @FXML
    private TextField CodFiscaleField;

    @FXML
    private TextField EmailField;

    @FXML
    private TextField NameField;

    @FXML
    private TextField PasswordField;

    @FXML
    private Button RegisterButton;

    @FXML
    private TextField SurnameField;

    @FXML
    private Button LoginButton;
    @FXML
    private Text allert1;

    @FXML
    void GoToLoginScene(ActionEvent event) {
        try {
            //carico la pagina di gestione 
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/LoginScene.fxml"));
            root = loader.load();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root); 
            stage.setScene(scene);
            stage.show();
    }	
    catch(Exception e) {
        System.out.println(e);
    }
    
    }

    @FXML
    void RegisterAndGoToLoginScene(ActionEvent event) throws IOException {
        
        try {
           
            //questa è una porcata incredibile, non riesco a fare il catch del constarint che dovrebbe lanciare postgres
            if(PasswordField.getText().length()<8){
                allert1.setText("La password deve avere almeno 9 caratteri");
                return;
            }

            Utente u = new Utente(NameField.getText(), SurnameField.getText(), EmailField.getText(), PasswordField.getText(),CodFiscaleField.getText().toUpperCase());
            UtenteDaoImpl utenteDao = new UtenteDaoImpl();
            utenteDao.addUtente(u);
             //carico la pagina di gestione 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/LoginScene.fxml"));
            root = loader.load();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root); 
            stage.setScene(scene);
            stage.show();
    }	
    catch (SQLException e) {
            
        // "23514" indica la violazione di una check constraint
        // https://www.postgresql.org/docs/9.2/errcodes-appendix.html
       
            System.out.println(e.getSQLState());
           ServerErrorMessage postgresError = ((PSQLException) e).getServerErrorMessage();
           System.out.println(postgresError);
           if (postgresError != null) {
               String constraint = postgresError.getConstraint();
               System.out.println("CONSTRAINT GENERATO = " + constraint);
               if ("utente_cognome_check".equalsIgnoreCase(constraint)) {
                   
                   allert1.setText("Il cognome deve avere almeno 4 caratteri");
               }
               if ("utente_email_check".equalsIgnoreCase(constraint)) {
                allert1.setText("L'email deve avere almeno 6 caratteri");
               }
               if ("utente_nome_check".equalsIgnoreCase(constraint)) {
                allert1.setText("Il nome deve avere almeno 4 caratteri");
               }
               if ("utente_password_check".equalsIgnoreCase(constraint)) {
                allert1.setText("La password deve avere almeno 9 caratteri");
               }
               if ("utente_codfiscale_check".equalsIgnoreCase(constraint)) {
                allert1.setText("Il codice fiscale deve essere formato da 16 caratteri");
               }

               if ("utente_pkey".equalsIgnoreCase(constraint)) {
                allert1.setText("Questo codice fiscale risulta gia registrato");
               }
               if ("utente_email_key".equalsIgnoreCase(constraint)) {
                allert1.setText("Questa email risulta gia registrata");
               }
               
           }
       
       
   }
        

    }

}


/**
 
    CREATE TABLE utente(

    nome varchar(40) NOT NULL,
    cognome varchar(40) NOT NULL,
    email  varchar(50) UNIQUE NOT NULL,
    password varchar(50) NOT NULL,
    codFiscale char(16) PRIMARY KEY,
    privilegi boolean DEFAULT false
    );


    INSERT INTO UTENTE VALUES('Luca', 'Stoian', 'lucastoian@outlook.com', 'topogigio', 'STNLCU00H23Z129G')
    
    SELECT * FROM utente;



 */