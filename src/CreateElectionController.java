import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateElectionController implements Initializable{

         
    
    private Stage stage; 
	private Scene scene; 
	private Parent root;
     
    private String[] tipiElezioni = {"ordinale","categorico","categorico con preferenze","referendum"};  


    @FXML
    private Label UserNameLabel;

    @FXML
    private Button candidatiButton;

    @FXML
    private Button createButton;
    
    @FXML
    private Label tipiLabel; 

    @FXML
    private ComboBox<String> tipiBox;


    @FXML
    private DatePicker finalDateField;

    @FXML
    private DatePicker initialDateField;

    @FXML
    private TextField nameField;



    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tipiBox.getItems().addAll(tipiElezioni);
		tipiBox.setOnAction(this::getType);
    }


    public void getType(ActionEvent event){
        String tipoScelto = tipiBox.getValue();
        tipiLabel.setText("Hai selezionato: "+ tipoScelto);
    }





    //#region navigazione
    @FXML
    void createElection(ActionEvent event)throws IOException{
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
