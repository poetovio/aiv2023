package com.example.webserver.vao;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pacient {

    private String ime;
    private String priimek;
    private String mail;
    private LocalDate datumRojstva;
    private String posebnosti;

    public Pacient() { this("", "", ""); }

    public Pacient(String ime, String priimek, String mail) {
        this.ime = ime;
        this.priimek = priimek;
        this.mail = mail;
        this.datumRojstva = LocalDate.now();
        this.posebnosti = "";
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

}
