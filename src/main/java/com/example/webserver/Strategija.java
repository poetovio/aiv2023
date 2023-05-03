package com.example.webserver;

import com.example.webserver.vao.Pacient;
import com.example.webserver.vao.Zdravilo;

import java.util.List;

public class Strategija {

    private Pacient pacient;
    private String posebnosti;
    private List<Zdravilo> zdravila;

    private StrategijaZakljucek strategija;

    public Strategija() {}

    public void posljiMail(String sporocilo) {
        try {
            strategija.posljiMailZakljucek(pacient, sporocilo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public String getPosebnosti() {
        return posebnosti;
    }

    public void setPosebnosti(String posebnosti) {
        this.posebnosti = posebnosti;
    }

    public List<Zdravilo> getZdravila() {
        return zdravila;
    }

    public void setZdravila(List<Zdravilo> zdravila) {
        this.zdravila = zdravila;
    }

    public StrategijaZakljucek getStrategija() {
        return strategija;
    }

    public void setStrategija(StrategijaZakljucek strategija) {
        this.strategija = strategija;
    }
}
