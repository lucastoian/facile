import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class OptionController implements Initializable {

    @FXML
    private Label UserNameLabel;

    @FXML
    private Button candidatiButton;

	@FXML
    private Label tipiLabel; 

    @FXML
    private ComboBox<String> tipiBox;
	private String[] tipiElezioni = {"ordinale","categorico","categorico con preferenze","referendum"};  


    
        
    private Stage stage; 
	private Scene scene; 
	private Parent root; 


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tipiBox.getItems().addAll(tipiElezioni);
		tipiBox.setOnAction(this::getType);
    }


    public void getType(ActionEvent event){
        String tipoScelto = tipiBox.getValue();
        tipiLabel.setText("Hai selezionato: "+ tipoScelto);
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
