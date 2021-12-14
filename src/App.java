
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
 
public class App extends Application {

    Button button;


    public static void main(String[] args) {
        System.out.println("hello");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {      
      

            Parent root = FXMLLoader.load(getClass().getResource("CreateElectionScene.fxml"));
            primaryStage.setTitle("Titolo della stage");
            primaryStage.setScene(new Scene(root,800,400));
            primaryStage.show();
    
        
    }
    
}

/**
CREATE TABLE candidati(

nome varchar(40) NOT NULL,
cognome varchar(40) NOT NULL
);

INSERT INTO candidati (nome, cognome) VALUES ('Matteo', 'Salvini');
INSERT INTO candidati (nome, cognome) VALUES ('Giorgia', 'Meloni');
 */