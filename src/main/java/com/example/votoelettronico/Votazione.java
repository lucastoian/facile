package com.example.votoelettronico;

public class Votazione {

    private String proprietario;
    private String nome; 
    private String id; 
    private String tipo; 
    private String inizio; 
    private String fine; 

    
    public Votazione(String proprietario, String nome, String id, String tipo, String inizio, String fine, String domanda){
        this.setFine(fine); 
        this.setInizio(inizio); 
        this.setId(id); 
        this.setProp(proprietario);
        this.setNome(nome); 
        this.setTipo(tipo); 
    }

    public Votazione(String prop, String nome, String id, String tipo, String inizio, String fine){
        this.setFine(fine); 
        this.setInizio(inizio); 
        this.setId(id); 
        this.setProp(prop); 
        this.setNome(nome); 
        this.setTipo(tipo); 
    }


    public String getProp() {
        return proprietario;
    }


    public void setProp(String prop) {
        this.proprietario = prop;
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

    @Override
    public String toString(){
        return " Proprietario: " + this.proprietario + " Nome: " + this.nome + " Id: " + this.id + " Tipo: " + this.tipo + " Inizio: " + this.inizio +" Fine: "+ this.fine;
    }

    @Override
    public boolean equals(Object o){
        if ( !(o instanceof Votazione)) return  false;
        Votazione v = (Votazione) o;
        return v.getId().equals(this.getId());
    }

    



    
}
