import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController {

    String email,psw; 
    String emailPredefinita = "email@gmail.com"; 
    String pswPredefinita = "password";
    
    
    private Stage stage; 
	private Scene scene; 
	private Parent root; 
	

    @FXML
    private TextField CODField;

    @FXML
    private Pane errorPane; 

    @FXML
    private TextField ElettoreField;

    @FXML
    private TextField SurnameField,VotazioneField,PasswordField,NameField,EmailField;


    @FXML
    private Button VoteButton,LoginButton;

    @FXML
    void GoToElectionScene(ActionEvent event) {

    }


    public void GoToManageScene(ActionEvent event) throws IOException {
		
		try {
			email = EmailField.getText();
            psw = PasswordField.getText();
            
            System.out.println(email +" "+psw);
			
			if( !email.equals(emailPredefinita)  ||  !psw.equals(pswPredefinita)) {	
                //mostra un messaggio di errore se la mail è errata
                errorPane.setVisible(true);
            }else{
                //carico la pagina di gestione 
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ManageElectionScene.fxml"));
				root = loader.load();


                //creo istanza del controller della scena di management dell'elezione
                ManagerController manController = loader.getController();
				manController.displayName(email);
				
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root); 
				stage.setScene(scene);
				stage.show();

            }
        }	
		catch(Exception e) {
			System.out.println(e);
		}
	}




}
