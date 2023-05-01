package com.example.webserver.jsf;

import com.example.webserver.dao.ObiskMemoryDAO;
import com.example.webserver.dao.PacientMemoryDAO;
import com.example.webserver.dao.ZdravnikMemoryDAO;
import com.example.webserver.vao.DruzinskiZdravnik;
import com.example.webserver.vao.Obisk;
import com.example.webserver.vao.Pacient;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.transaction.UserTransaction;

import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named("obiski")
public class ObiskJSFBean implements Serializable {


    @Resource
    UserTransaction utx;

    @PersistenceContext(unitName = "sample_pu", type = PersistenceContextType.EXTENDED)
    EntityManager em;

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

        try {
            utx.begin();

            Pacient addPacient = em.createQuery("select p from Pacient p where p.mail = :mail", Pacient.class)
                    .setParameter("mail", mailPacienta)
                    .getSingleResult();

            DruzinskiZdravnik addZdravnik = em.createQuery("select z from DruzinskiZdravnik z where z.mail = :mail", DruzinskiZdravnik.class)
                    .setParameter("mail", mailZdravnika)
                    .getSingleResult();

            obisk.setPacient(addPacient);
            obisk.setZdravnik(addZdravnik);

            em.persist(obisk);

            obiskDao.shraniObisk(obisk, mailPacienta, mailZdravnika, em, utx);

            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // read operacija

    public List<Obisk> getObiski() { return obiskDao.vrniObiske(em); }

    public Obisk getObisk(int stObiska) { return obiskDao.najdiObisk(stObiska, em); }

    // update operacija

    public void updateObisk(Obisk obisk) {
            obiskDao.updateObisk(stObiska, obisk, pacientDao.najdiPacienta(mailPacienta), zdravnikDao.najdiZdravnika(mailZdravnika));
    }

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

        obisk = obiskDao.najdiObisk(stObiska, em);

        if(obisk == null) { obisk = new Obisk(); mailPacienta = ""; mailZdravnika = "";}
        else { mailPacienta = obisk.getPacient().getMail(); mailZdravnika = obisk.getZdravnik().getMail(); }
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
