package com.example.votoelettronico;


import java.util.ArrayList;
import java.util.List;


public class ObservableActiveUser extends Utente {
    private Utente u;
    private List<Observer> observers = new ArrayList<>();

    public ObservableActiveUser(){
        this.u = null;
    }

    public void addObserver(Observer o) {
        //notifico il risultato iniziale non appena un observer si sottoscrive
        o.update(this.u);
        this.observers.add(o);
    }
    public void removeObserver(Observer o) {
        this.removeObserver(o);
    }

    public void setActiveUser(Utente u){
        this.u = u;
        for (Observer o : observers) {
            o.update(this.u);
        }
    }


 }
