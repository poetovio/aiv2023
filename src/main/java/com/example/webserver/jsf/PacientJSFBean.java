package com.example.webserver.jsf;

import com.example.webserver.dao.PacientMemoryDAO;
import com.example.webserver.dao.ZdravnikMemoryDAO;
import com.example.webserver.vao.Pacient;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("pacienti")
@SessionScoped
public class PacientJSFBean implements Serializable {

    private static List<Pacient> pacients;

    private Pacient pacient = new Pacient();

    private String mail;

    private String imeZdravnika;

    private PacientMemoryDAO pacientDao = PacientMemoryDAO.getInstance();

    private ZdravnikMemoryDAO zdravnikDao = ZdravnikMemoryDAO.getInstance();

    // create operacija
    public void createPacient() {
        String[] parts = imeZdravnika.split(" ");
        pacient.setZdravnik(zdravnikDao.najdiZdravnika(parts[0], parts[1]));
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

    public void setMail(String mail) { this.mail = mail; }

    public String getImeZdravnika() {
        return imeZdravnika;
    }

    public void setImeZdravnika(String imeZdravnika) {
        this.imeZdravnika = imeZdravnika;
    }
}
