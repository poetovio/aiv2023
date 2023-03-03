package com.example.webserver.vao;

import java.util.ArrayList;

public class DruzinskiZdravnik {

    private String ime;
    private String priimek;
    private String mail;
    private int kvota;
    private ArrayList<Pacient> pacienti;

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getKvota() {
        return kvota;
    }

    public void setKvota(int kvota) {
        this.kvota = kvota;
    }

    public ArrayList<Pacient> getPacienti() {
        return pacienti;
    }

    public void setPacienti(ArrayList<Pacient> pacienti) {
        this.pacienti = pacienti;
    }

    @Override
    public String toString() { return ime + " " + priimek; }


}
