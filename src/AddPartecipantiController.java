import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;

public class AddPartecipantiController {


            
    
    private Stage stage; 
	private Scene scene; 
	private Parent root; 

    @FXML
    private Label UserNameLabel;

    @FXML
    private Button candidatiButton;

  
    //#region NAVIGAZIONE


	
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

    


    //#endregion


}
