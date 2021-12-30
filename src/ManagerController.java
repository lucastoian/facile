
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ManagerController {
    
        
    
    private Stage stage; 
	private Scene scene; 
	private Parent root; 

    @FXML
    private Label UserNameLabel; 

    @FXML
    private Button candidatiBuotton; 

    //cambia il testo della label del nome, per ora mette la mail 
    public void displayName(String email) {
        UserNameLabel.setText(email);
    }


 




    //#region NAVIGAZIONE



    //carica la pagina per aggiungere i candidati 
    public void goToCandidati(ActionEvent event) throws IOException {
		
		try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/AddCandidateScene.fxml"));
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

	public void cambiaElezione(ActionEvent event) throws IOException {
		
		try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/VotazioniScene.fxml"));
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



    //carica la pagina delle impostazioni
    public void goToOption(ActionEvent event) throws IOException {
		
		try { 
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/OptionScene.fxml"));
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


    public void goToManager(ActionEvent event) throws IOException {
		
		try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/ManageElectionScene.fxml"));
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



    //carica la pagina creazione delle elezioni
    public void goToPartecipanti(ActionEvent event) throws IOException {
		
		try { 
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/AddPartecipantiScene.fxml"));
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



	
    public void Logout(ActionEvent event) {
          
	    try {
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

    


    //#endregion







    
}
