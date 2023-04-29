package com.example.webserver.vao;

import com.example.webserver.Observer;
import com.example.webserver.dao.IOpazovalec;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pacient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ime;
    private String priimek;
    private String mail;
    private LocalDate datumRojstva;
    private String posebnosti;
    @ManyToOne
    private DruzinskiZdravnik zdravnik;

    @Transient
    private List<IOpazovalec> observers;

    public Pacient() { this("", "", ""); }

    public Pacient(String ime, String priimek, String mail) {
        this.ime = ime;
        this.priimek = priimek;
        this.mail = mail;
        this.datumRojstva = LocalDate.now();
        this.posebnosti = "";
        this.zdravnik = null;
        this.observers = new ArrayList<>();
        this.addObserver(new Observer());
    }


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

    public LocalDate getDatumRojstva() {
        return datumRojstva;
    }

    public void setDatumRojstva(LocalDate datumRojstva) {
        this.datumRojstva = datumRojstva;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPosebnosti() {
        return posebnosti;
    }

    public void setPosebnosti(String posebnosti) {
        this.posebnosti = posebnosti;
    }

    public DruzinskiZdravnik getZdravnik() {
        return zdravnik;
    }

    public void setZdravnik(DruzinskiZdravnik zdravnik) {
        this.zdravnik = zdravnik;
    }

    @Override
    public String toString() { return ime + " " + priimek; }

    public void addObserver(IOpazovalec observer) { this.observers.add(observer); }

    public void removeObserver(IOpazovalec observer) { this.observers.remove(observer); }

    public void alertDodajanje(DruzinskiZdravnik zdravnik) throws Exception {
        for (IOpazovalec opazovalec: observers) {
            opazovalec.prostObvesti(zdravnik, this);
        }
    }

    public void alertOdstranjevanje(DruzinskiZdravnik zdravnik) throws Exception {
        for (IOpazovalec opazovalec: observers) {
            opazovalec.zasedenObvesti(zdravnik, this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<IOpazovalec> getObservers() {
        return observers;
    }

    public void setObservers(List<IOpazovalec> observers) {
        this.observers = observers;
    }
}
