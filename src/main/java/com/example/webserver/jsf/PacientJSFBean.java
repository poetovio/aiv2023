package com.example.webserver.jsf;

import com.example.webserver.MailFacade;
import com.example.webserver.dao.PacientDAO;
import com.example.webserver.dao.PacientMemoryDAO;
import com.example.webserver.dao.ZdravnikDAO;
import com.example.webserver.dao.ZdravnikMemoryDAO;
import com.example.webserver.vao.DruzinskiZdravnik;
import com.example.webserver.vao.Pacient;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Named("pacienti")
@SessionScoped
public class PacientJSFBean implements Serializable {

    private static List<Pacient> pacients;

    private Pacient pacient = new Pacient();

    private String mail;

    private String imeZdravnika;

    private String datumRojstva;

    @EJB
    private PacientDAO pacientDao;

    @EJB
    private ZdravnikDAO zdravnikDao;

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // create operacija
    public void createPacient() {
        String[] parts = imeZdravnika.split(" ");

        if(pacient.getZdravnik() != null) { pacient.getZdravnik().odstraniPacienta(pacient); }

        pacient.setZdravnik(zdravnikDao.najdiZdravnika(parts[0], parts[1]));
        pacient.setDatumRojstva(LocalDate.parse(datumRojstva, dtf));
        pacientDao.shraniPacienta(pacient);

        if(pacient.getZdravnik() != null && !pacient.getZdravnik().zeImaPacienta(pacient)) { pacient.getZdravnik().getPacienti().add(pacient); }
    }

    // read operacija

    public List<Pacient> getAllPacienti() { return pacientDao.getPacienti(); }

    // update operacija

    public void updatePacient() {}

    // delete operacija

    public void deletePacient(String mail) {
        Pacient pacient = pacientDao.najdiPacienta(mail);

        if(pacient.getZdravnik() != null) { pacient.getZdravnik().odstraniPacienta(pacient); }

        pacientDao.izbrisiPacienta(mail);
    }

    public Pacient getPacient() { return pacient; }


    public void setPacient(Pacient pacient) { this.pacient = pacient; }

    public String getMail() { return mail; }

    public void setMail(String mail) {
        this.mail = mail;
        pacient = pacientDao.najdiPacienta(mail);

        if(pacient == null) { datumRojstva = ""; pacient = new Pacient(); imeZdravnika = "Brez zdravnika"; }
        else {
            datumRojstva = pacient.getDatumRojstva().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if(pacient.getZdravnik() != null) {

                imeZdravnika = pacient.getZdravnik().toString();
            } else {
                imeZdravnika = "Brez zdravnika";
            }
        }
    }

    public String getImeZdravnika() {
        return imeZdravnika;
    }

    public void setImeZdravnika(String imeZdravnika) {
        this.imeZdravnika = imeZdravnika;
    }

    public String getDatumRojstva() {
        return datumRojstva;
    }

    public void setDatumRojstva(String datum) {
        this.datumRojstva = datum;
    }

    // pacienti, ki imajo zdravnika

    public List<Pacient> pacientiZZdravnikom() {
        List<Pacient> pacienti = Collections.synchronizedList(new ArrayList<Pacient>());

        for(Pacient pacient: pacientDao.getPacienti()) {
            if(pacient.getZdravnik() != null) { pacienti.add(pacient); }
        }

        return pacienti;
    }

    // pacienti, ki nimajo zdravnika

    public List<Pacient> pacientiBrezZdravnika() {
        List<Pacient> pacienti = Collections.synchronizedList(new ArrayList<Pacient>());

        for(Pacient pacient: pacientDao.getPacienti()) {
            if(pacient.getZdravnik() == null) { pacienti.add(pacient); }
        }

        return pacienti;
    }

    // izberi zdravnika

    public void izberiZdravnika() throws Exception {

        Pacient bolnik = pacientDao.najdiPacienta(mail);

        System.out.println("Bolnik z imenom " + bolnik.toString());
        System.out.println("Ime izbranega zdravnika: " + imeZdravnika);

        MailFacade fasada = new MailFacade();

        String[] parts = imeZdravnika.split(" ");

        if(bolnik.getZdravnik() != null) { bolnik.getZdravnik().odstraniPacienta(bolnik); }

        DruzinskiZdravnik dohtar = zdravnikDao.najdiZdravnika(parts[0], parts[1]);

        if(zdravnikDao.prevzemPacienta(dohtar, dohtar.getPacienti().size(), bolnik)) {
            fasada.sprejmiPacienta(bolnik, dohtar);
        } else {
            fasada.zavrniPacienta(bolnik, dohtar);
        }

        if(bolnik.getZdravnik() != null && !bolnik.getZdravnik().zeImaPacienta(bolnik)) { bolnik.getZdravnik().getPacienti().add(bolnik); }
    }
}
