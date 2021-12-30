import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import votazione.Votazione;

public class VotazioniController implements Initializable{

    private Stage stage; 
	private Scene scene; 
	private Parent root; 

    @FXML
    private Button DeleteElectionButton;

    @FXML
    private Button ElectionButton;

    @FXML
    private Label UserNameLabel;

    @FXML
    private Button createButton;

    @FXML
    private TableView<Votazione> tabVotazioni; 


    @FXML
    private TableColumn<Votazione, String> fine;

    @FXML
    private TableColumn<Votazione, String> id;

    @FXML
    private TableColumn<Votazione, String> inizio;

    @FXML
    private TableColumn<Votazione, String> nome;

    @FXML
    private TableColumn<Votazione, String> tipologia;




    ObservableList<Votazione> votazioniList = FXCollections.observableArrayList(
        new Votazione("damon","default", "31234481","categorico","10/12/2012","20/12/2012")
    );


    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nome.setCellValueFactory(new PropertyValueFactory<Votazione,String>("nome"));
        id.setCellValueFactory(new PropertyValueFactory<Votazione,String>("id"));
        tipologia.setCellValueFactory(new PropertyValueFactory<Votazione,String>("tipo"));
        inizio.setCellValueFactory(new PropertyValueFactory<Votazione,String>("inizio"));
        fine.setCellValueFactory(new PropertyValueFactory<Votazione,String>("fine"));

        tabVotazioni.setItems(votazioniList);
    }




    

    @FXML
    private void deleteElection(ActionEvent event) {

    }



    //#region navigazione
    @FXML
    private void goToCreateElection(ActionEvent event) {
          
	    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/CreateElectionScene.fxml"));            
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


    //per ora va semplicemente alla schermata di management principale
    @FXML
    void goToElection(ActionEvent event) {
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


    @FXML
    void Logout(ActionEvent event) {
          
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
