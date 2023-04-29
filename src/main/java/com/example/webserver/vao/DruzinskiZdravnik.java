package com.example.webserver.vao;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Iterator;

@Entity
public class DruzinskiZdravnik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ime;
    private String priimek;
    private String mail;
    private int kvota;
    @Transient
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

    public boolean zeImaPacienta(Pacient pacient) {
        for(Pacient human: pacienti) {
            if(human.equals(pacient)) { return true; }
        }
        return false;
    }

    public void odstraniPacienta(Pacient pacient) {
        for(Iterator<Pacient> i = pacienti.iterator(); i.hasNext();) {
            if(i.next().equals(pacient)) { i.remove(); }
        }
    }

    @Override
    public String toString() { return ime + " " + priimek; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
