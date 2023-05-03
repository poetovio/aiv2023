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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
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

    private String casObiska;

    private String mailPacienta;

    private String mailZdravnika;

    private String opisDiagnoze;

    private String stringDatum;

    private String posebnosti;

    private boolean jeZakljucen;

    private ObiskMemoryDAO obiskDao = ObiskMemoryDAO.getInstance();

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
            obisk.setCasObiska(casObiska);
            obisk.setOpisDiangoze(opisDiagnoze);
            obisk.setDatumObiska(stringDatum);
            obisk.setPosebnosti("Brez posebnosti");
            obisk.setJeZakljucen(false);

            em.persist(obisk);

            obiskDao.shraniObisk(obisk, mailPacienta, mailZdravnika, em, utx);

            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // read operacija

    public List<Obisk> getObiski() { return obiskDao.vrniObiske(em); }

    public List<Obisk> getNezakljuceniObiski() {
        List<Obisk> nezakljuceni = Collections.synchronizedList(new ArrayList<Obisk>());

        for(Obisk visit: getObiski()) {
            System.out.println("Nezakljuceni -> " + visit.isJeZakljucen());
            if(!visit.isJeZakljucen()) {
                nezakljuceni.add(visit);
            }
        }

        return nezakljuceni;
    }

    public List<Obisk> getZakljuceniObiski() {
        List<Obisk> zakljuceni = Collections.synchronizedList(new ArrayList<Obisk>());

        for(Obisk visit: getObiski()) {
            System.out.println("Zakljuceni -> " + visit.isJeZakljucen());
            if(visit.isJeZakljucen()) {
                zakljuceni.add(visit);
            }
        }

        return zakljuceni;
    }

    public Obisk getObisk(int stObiska) { return obiskDao.najdiObisk(stObiska, em); }

    // update operacija

    public void updateObisk(boolean over) {
            try {
                utx.begin();
                Pacient editPacient = em.createQuery("select p from Pacient p where p.mail = :mail", Pacient.class)
                    .setParameter("mail", mailPacienta)
                    .getSingleResult();

                DruzinskiZdravnik editZdravnik = em.createQuery("select z from DruzinskiZdravnik z where z.mail = :mail", DruzinskiZdravnik.class)
                        .setParameter("mail", mailZdravnika).getSingleResult();

                obisk.setJeZakljucen(over);

                obiskDao.updateObisk(obisk.getStObiska(), obisk, editPacient,
                        editZdravnik, opisDiagnoze, casObiska, stringDatum, posebnosti, over, em);

                utx.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    // delete operacija

    public void deleteObisk(int stObiska) {
        try {
            utx.begin();
            obiskDao.izbrisiObisk(stObiska, em);
            utx.commit();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

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

    public String getCasObiska() {
        return casObiska;
    }

    public void setCasObiska(String casObiska) {
        this.casObiska = casObiska;
    }

    public String getStringDatum() {
        return stringDatum;
    }

    public void setStringDatum(String stringDatum) {
        this.stringDatum = stringDatum;
    }

    public String getPosebnosti() {
        return posebnosti;
    }

    public void setPosebnosti(String posebnosti) {
        this.posebnosti = posebnosti;
    }

    public boolean isJeZakljucen() {
        return jeZakljucen;
    }

    public void setJeZakljucen(boolean jeZakljucen) {
        this.jeZakljucen = jeZakljucen;
    }
}
