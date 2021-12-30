package votazione;

public class Votazione {

    private String prop; 
    private String nome; 
    private String id; 
    private String tipo; 
    private String inizio; 
    private String fine; 

    
    public Votazione(String prop,String nome,String id,String tipo,String inizio,String fine,String domanda){
        this.setFine(fine); 
        this.setInizio(inizio); 
        this.setId(id); 
        this.setProp(prop); 
        this.setNome(nome); 
        this.setTipo(tipo); 
    }

    public Votazione(String prop,String nome,String id,String tipo,String inizio,String fine){
        this.setFine(fine); 
        this.setInizio(inizio); 
        this.setId(id); 
        this.setProp(prop); 
        this.setNome(nome); 
        this.setTipo(tipo); 
    }


    public String getProp() {
        return prop;
    }


    public void setProp(String prop) {
        this.prop = prop;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getTipo() {
        return tipo;
    }


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public String getInizio() {
        return inizio;
    }


    public void setInizio(String inizio) {
        this.inizio = inizio;
    }


    public String getFine() {
        return fine;
    }


    public void setFine(String fine) {
        this.fine = fine;
    }


    



    
}
