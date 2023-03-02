package com.example.webserver.vao;

import java.time.LocalDate;
import java.util.ArrayList;

public class Obisk {

    public Obisk() {}

    public Obisk(int stObiska, Pacient pacient, DruzinskiZdravnik zdravnik, LocalDate datum, String opisDiangoze) {
        this.stObiska = stObiska;
        this.pacient = pacient;
        this.zdravnik = zdravnik;
        this.datum = LocalDate.now();
        this.opisDiangoze = opisDiangoze;
        this.zdravila = new ArrayList<String>();
    }

    private int stObiska;
    private Pacient pacient;
    private LocalDate datum;
    private DruzinskiZdravnik zdravnik;
    private String opisDiangoze;
    private ArrayList<String> zdravila;

    public Pacient getPacient() {
        return pacient;
    }

    public int getStObiska() {
        return stObiska;
    }

    public void setStObiska(int stObiska) {
        this.stObiska = stObiska;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public DruzinskiZdravnik getZdravnik() {
        return zdravnik;
    }

    public void setZdravnik(DruzinskiZdravnik zdravnik) {
        this.zdravnik = zdravnik;
    }

    public String getOpisDiangoze() {
        return opisDiangoze;
    }

    public void setOpisDiangoze(String opisDiangoze) {
        this.opisDiangoze = opisDiangoze;
    }

    public ArrayList<String> getZdravila() {
        return zdravila;
    }

    public void setZdravila(ArrayList<String> zdravila) {
        this.zdravila = zdravila;
    }
}
