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


 

    public void goToCandidati(ActionEvent event) throws IOException {
		
		try {
                //carico la pagina di gestione 
                FXMLLoader loader = new FXMLLoader(getClass().getResource("mainframe.fxml"));
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
