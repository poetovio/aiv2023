package com.example.webserver.jsf;

import com.example.webserver.dao.ObiskMemoryDAO;
import com.example.webserver.dao.PacientMemoryDAO;
import com.example.webserver.dao.ZdravnikMemoryDAO;
import com.example.webserver.vao.Obisk;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named("obiski")
public class ObiskJSFBean implements Serializable {

    private PacientMemoryDAO pacientDao = PacientMemoryDAO.getInstance();
    private ZdravnikMemoryDAO zdravnikDao = ZdravnikMemoryDAO.getInstance();

    private static List<Obisk> obiski;

    private Obisk obisk = new Obisk();

    private int stObiska;

    private String mailPacienta;

    private String mailZdravnika;

    private String opisDiagnoze;

    private ObiskMemoryDAO obiskDao = ObiskMemoryDAO.getInstance();

    // create operacija

    public void createObisk() {
        // System.out.println(pacientDao.najdiPacienta(mailPacienta));
        obisk.setPacient(pacientDao.najdiPacienta(mailPacienta));
        obisk.setZdravnik(zdravnikDao.najdiZdravnika(mailZdravnika));
        obiskDao.shraniObisk(obisk);
    }

    // read operacija

    public List<Obisk> getObiski() { return obiskDao.vrniObiske(); }

    public Obisk getObisk(int stObiska) { return obiskDao.najdiObisk(stObiska); }

    // update operacija

    public void updateObisk(Obisk obisk) {}

    // delete operacija

    public void deleteObisk(int stObiska) { obiskDao.izbrisiObisk(stObiska); }

    public Obisk getObisk() {
        return obisk;
    }

    public void setObisk(Obisk obisk) {
        this.obisk = obisk;
    }

    public ObiskMemoryDAO getObiskDao() {
        return obiskDao;
    }

    public void setObiskDao(ObiskMemoryDAO obiskDao) {
        this.obiskDao = obiskDao;
    }

    public int getStObiska() {
        return stObiska;
    }

    public void setStObiska(int stObiska) {
        this.stObiska = stObiska;

        obisk = obiskDao.najdiObisk(stObiska);

        if(obisk == null) { obisk = new Obisk(); mailPacienta = ""; mailZdravnika = "";}
        else { mailPacienta = obisk.getPacient().getMail(); mailZdravnika = obisk.getPacient().getMail(); }
    }

    public String getMailPacienta() {
        return mailPacienta;
    }

    public void setMailPacienta(String mailPacienta) {
        this.mailPacienta = mailPacienta;
    }

    public String getMailZdravnika() {
        return mailZdravnika;
    }

    public void setMailZdravnika(String mailZdravnika) {
        this.mailZdravnika = mailZdravnika;
    }

    public String getOpisDiagnoze() {
        return opisDiagnoze;
    }

    public void setOpisDiagnoze(String opisDiagnoze) {
        this.opisDiagnoze = opisDiagnoze;
    }
}
