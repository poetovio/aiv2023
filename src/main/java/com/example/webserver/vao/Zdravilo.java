package com.example.webserver.vao;

import jakarta.persistence.*;

@Entity
public class Zdravilo {

    public Zdravilo() {}

    public Zdravilo(int stZdravila, Obisk obisk, String naziv, int kolicina) {
        this.stZdravila = stZdravila;
        this.obisk = obisk;
        this.naziv = naziv;
        this.kolicina = kolicina;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int stZdravila;

    @ManyToOne
    private Obisk obisk;

    private String naziv;

    private int kolicina;

    public Obisk getObisk() {
        return obisk;
    }

    public void setObisk(Obisk obisk) {
        this.obisk = obisk;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public int getStZdravila() {
        return stZdravila;
    }

    public void setStZdravila(int stZdravila) {
        this.stZdravila = stZdravila;
    }

    @Override
    public String toString() {
        return "Zdravilo " + this.naziv + " s številko " + this.stZdravila +
                " je predpisano za " + this.kolicina + " škatel.";
    }
}
