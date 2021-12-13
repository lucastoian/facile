import java.io.IOException;
import java.sql.SQLException;

import org.postgresql.util.PSQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
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
    private Button VoteButton,LoginButton, RegisterButton;
    

    @FXML
    void GoToElectionScene(ActionEvent event) {

    }

    
    @FXML
    public void GoToRegisterScene(ActionEvent event) throws IOException {
		
		try {
                //carico la pagina di gestione 
                FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterScene.fxml"));
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


    public void GoToManageScene(ActionEvent event) throws IOException, SQLException {
	
			email = EmailField.getText();
            psw = PasswordField.getText();
            
            if(PostgreSQL.login(email, psw)){
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




}
