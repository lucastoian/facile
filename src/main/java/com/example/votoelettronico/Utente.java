package com.example.votoelettronico;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Utente {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String codFiscale;



    public Utente(){
        
    }

    public Utente(String name, String surname, String email, String password, String codFiscale){
    
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.codFiscale = codFiscale;
        
    }


    public void setName(String name){
        this.name = name;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setCodFiscale(String codFiscale){
        this.codFiscale = codFiscale;
    }

    public void setPassword(String password){
        this.password = password;
    }




    public String getName(){
        return this.name;
    }

    public String getSurname(){
        return this.surname;
    }

    public String getCodFiscale(){
        return this.codFiscale;
    }
    
    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }
    
  

 
    @Override
    public String toString(){

        return this.name + " " + this.surname + " " + this.email + " " + this.codFiscale + " " + this.password;

    }

    @Override
    public boolean equals(Object o){

        if (!(o instanceof Utente)) {
            return false;
        }

        Utente u = (Utente) o ;
        return u.getCodFiscale().equals(this.codFiscale);
    }
}
