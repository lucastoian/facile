import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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


    ObservableList<Candidato> list = FXCollections.observableArrayList(
        new Candidato("Luca", "Stoian"),
        new Candidato("Damon", "Bianchi")
    );

    @FXML
    void add(ActionEvent event) {
        String name = inputName.getText();
        String surname = inputSurname.getText();
        list.add(new Candidato(name, surname));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        name.setCellValueFactory(new PropertyValueFactory<Candidato, String>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<Candidato, String>("surname"));
        table.setItems(list);
    }

}