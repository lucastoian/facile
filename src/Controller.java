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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller implements Initializable{

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputSurname;

    @FXML
    private TableColumn<Candidato, String> name;

    @FXML
    private TableColumn<Candidato, String> surname;
   

    @FXML
    private TableView<Candidato> table;


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
        Candidato c = table.getSelectionModel().getSelectedItem();
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
        table.setEditable(true);
        name.setEditable(true);
        table.setItems(list);

        try {
            updateList();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }

}