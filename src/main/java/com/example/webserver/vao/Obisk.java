package com.example.webserver.vao;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Entity
public class Obisk {

    public Obisk() {}

    public Obisk(int stObiska, Pacient pacient, DruzinskiZdravnik zdravnik, String casObiska, String opisDiangoze) {
        this.stObiska = stObiska;
        this.pacient = pacient;
        this.zdravnik = zdravnik;
        this.casObiska = casObiska;
        this.opisDiangoze = opisDiangoze;
        this.datumObiska = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int stObiska;
    @ManyToOne
    private Pacient pacient;
    private String casObiska;
    @ManyToOne
    private DruzinskiZdravnik zdravnik;
    private String opisDiangoze;
    private String datumObiska;

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

    public String getCasObiska() { return casObiska; }

    public void setCasObiska(String casObiska) { this.casObiska = casObiska; }

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

    public String getDatumObiska() {
        return datumObiska;
    }

    public void setDatumObiska(String datumObiska) {
        this.datumObiska = datumObiska;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
