
public class Candidato {
    private String name;
    private String surname;


    public Candidato(String name, String surname){
    
        this.name = name;
        this.surname = surname;
    }

    public String getName(){
        return this.name;
    }

    public String getSurname(){
        return this.surname;
    }
    

 
    @Override
    public String toString(){

        return this.name + " " + this.surname;

    }
}
