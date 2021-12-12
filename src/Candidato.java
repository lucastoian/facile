import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Candidato {
    private String name;
    private String surname;
    private Button button;


    public Candidato(String name, String surname){
    
        this.name = name;
        this.surname = surname;
        this.button = new Button("Delete");
        
    }

    public String getName(){
        return this.name;
    }

    public String getSurname(){
        return this.surname;
    }
    
    public Button getButton(){
        return this.button;
        
    }

 
    @Override
    public String toString(){

        return this.name + " " + this.surname;

    }
}
