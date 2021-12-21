import java.util.jar.Attributes.Name;

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
    void RegisterAndGoToLoginScene(ActionEvent event) {
        
        try {
            //carico la pagina di gestione 
            
            Utente u = new Utente(NameField.getText(), SurnameField.getText(), PasswordField.getText(), EmailField.getText(), CodFiscaleField.getText());
            UtenteDaoImpl utenteDao = new UtenteDaoImpl();
            utenteDao.addUtente(u);
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