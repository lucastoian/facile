package com.example.votoelettronico;

public class Candidato extends Utente{

        private String id;
        private int punteggio;

        public Candidato(String name,String surname,String codFiscale, String id,int punteggio){
            super(name,surname,null,null,codFiscale);
            this.id = id;
            this.punteggio = punteggio;


        }

        public String getId(){
            return this.id;
        }
    public void setId(String id){
        this.id = id;
    }


    public int getPunteggio(){
            return this.punteggio;
        }

    public void setPunteggio(int p){
        this.punteggio=p;
    }



    }



