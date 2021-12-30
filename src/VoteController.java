import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class VoteController {


        
    private Stage stage; 
	private Scene scene; 
	private Parent root;
     

    @FXML
    private Button clearButton;

    @FXML
    private Button confButton;

    @FXML
    private Pane errorPane;

    @FXML
    void clearSelected(ActionEvent event) {

    }

    @FXML
    void confermaVoto(ActionEvent event) {

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

}
