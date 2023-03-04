package com.example.webserver.jsf;

import com.example.webserver.dao.PacientMemoryDAO;
import com.example.webserver.dao.ZdravnikMemoryDAO;
import com.example.webserver.vao.Pacient;
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

    private PacientMemoryDAO pacientDao = PacientMemoryDAO.getInstance();

    private ZdravnikMemoryDAO zdravnikDao = ZdravnikMemoryDAO.getInstance();

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // create operacija
    public void createPacient() {
        String[] parts = imeZdravnika.split(" ");
        pacient.setZdravnik(zdravnikDao.najdiZdravnika(parts[0], parts[1]));
        pacient.setDatumRojstva(LocalDate.parse(datumRojstva, dtf));
        pacientDao.shraniPacienta(pacient);
    }

    // read operacija

    public List<Pacient> getAllPacienti() { return pacientDao.getPacienti(); }

    // update operacija

    public void updatePacient() {}

    // delete operacija

    public void deletePacient(String mail) { pacientDao.izbrisiPacienta(mail); }

    public Pacient getPacient() { return pacient; }


    public void setPacient(Pacient pacient) { this.pacient = pacient; }

    public String getMail() { return mail; }

    public void setMail(String mail) {
        this.mail = mail;
        pacient = pacientDao.najdiPacienta(mail);

        if(pacient == null) { datumRojstva = ""; pacient = new Pacient(); }
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

    public List<Pacient> pacientiBrezZdravnika() {
        List<Pacient> pacienti = Collections.synchronizedList(new ArrayList<Pacient>());

        for(Pacient pacient: pacientDao.getPacienti()) {
            if(pacient.getZdravnik() == null) { pacienti.add(pacient); }
        }

        return pacienti;
    }
}
