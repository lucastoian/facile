import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;

public class AddCandidateController implements Initializable{


        
    private Stage stage; 
	private Scene scene; 
	private Parent root; 

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputSurname;

    @FXML
    private TableColumn<Candidato, String> name;

    @FXML
    private TableColumn<Candidato, String> surname;
   

    @FXML
    private TableView<Candidato> Ctable;


    ObservableList<Candidato> list = FXCollections.observableArrayList();

    @FXML
    void add(ActionEvent event) throws SQLException {
        String name = inputName.getText();
        String surname = inputSurname.getText();
        

        PostgreSQL.writeToDatabase(name, surname);

        updateList();

        
    }


    @FXML
    void clearAll(ActionEvent event) throws SQLException {
            PostgreSQL.queryToDatabase("DELETE FROM candidati");
            updateList();
    }


    @FXML
    void deleteSelected(ActionEvent event) throws SQLException {
        Candidato c = Ctable.getSelectionModel().getSelectedItem();
        PostgreSQL.DeleteCandidatoFromDatabase(c.getName(), c.getSurname());
        updateList();
    }


    public void updateList() throws SQLException{
        list.clear();
        ResultSet result = PostgreSQL.queryToDatabase("Select * From candidati");
        while(result.next()){
            list.add(new Candidato(result.getString(1),result.getString(2)));
        }
        
        System.out.println(list);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        name.setCellValueFactory(new PropertyValueFactory<Candidato, String>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<Candidato, String>("surname"));
        Ctable.setEditable(true);
        name.setEditable(true);
        Ctable.setItems(list);

        try {
            updateList();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }







    
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