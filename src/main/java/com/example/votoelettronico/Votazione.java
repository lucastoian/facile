package com.example.votoelettronico;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Votazione {

    private String proprietario,nome,id,tipo,domanda,status;
    private Timestamp inizio,fine;
    private List<Utente> candidati;

    
    public Votazione(String proprietario, String nome, String id, String tipo, Timestamp inizio, Timestamp fine, String domanda, String status){
        this.inizio = inizio;
        this.fine = fine;
        this.setId(id); 
        this.setProp(proprietario);
        this.setNome(nome); 
        this.setTipo(tipo);
        this.status = status;
        this.domanda = domanda;
        this.candidati = new ArrayList<>();
    }

    public Votazione(String prop, String nome, String id, String tipo, Timestamp inizio, Timestamp fine){
        this.inizio = inizio;
        this.fine = fine;
        this.setId(id); 
        this.setProp(prop); 
        this.setNome(nome); 
        this.setTipo(tipo);
        this.status = "Draft";
        this.candidati = new ArrayList<>();
    }


    public String getProp() {
        return proprietario;
    }

    public void addCandidato(Utente u){
        candidati.add(u);
    }

    public int getCandidatiSize(){
        return candidati.size();
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

    public Timestamp getInizio() {return this.inizio;}
    public Timestamp getFine() {return this.fine;}
    public void setStatus(String status){this.status=status;}




    public void setDomanda(String domanda) {
        this.domanda = domanda;
    }

    public String getDomanda(){return this.domanda;}

    public String getStatus(){return this.status;}

    @Override
    public String toString(){
        return " Proprietario: " + this.proprietario + " Nome: " + this.nome + " Id: " + this.id + " Tipo: " + this.tipo + " Inizio: " + this.inizio +" Fine: "+ this.fine + " Domanda: " + this.domanda + " Status: " + this.status;
    }

    @Override
    public boolean equals(Object o){
        if ( !(o instanceof Votazione)) return  false;
        Votazione v = (Votazione) o;
        return v.getId().equals(this.getId());
    }

    



    
}
