package com.example.webserver.jsf;

import com.example.webserver.dao.ZdravnikDAO;
import com.example.webserver.dao.ZdravnikMemoryDAO;
import com.example.webserver.vao.DruzinskiZdravnik;
import com.example.webserver.vao.Pacient;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.Serializable;

@Named("dohtarji")
@SessionScoped
public class ZdravnikJSFBean implements Serializable {

    @PersistenceContext(unitName = "sample_pu")
    EntityManager em;

    private static List<DruzinskiZdravnik> zdravniki;

    private DruzinskiZdravnik zdravnik = new DruzinskiZdravnik();

    private String mail;

    @EJB
    private ZdravnikDAO zdravnikDao;

    private String imeZdravnika;

    private int kvota;

    public int getKvota() {
        return kvota;
    }

    public void setKvota(int kvota) {
        this.kvota = kvota;
    }

    // create operacija
    public void createZdravnik()  { zdravnik.setKvota(kvota); zdravnik.setPacienti(new ArrayList<Pacient>()); zdravnikDao.shraniZdravnika(zdravnik); }


    // read operacija
    public List<DruzinskiZdravnik> getAllZdravniki() { return zdravnikDao.getZdravniki(); }

    // update operacija

    public void updateZdravnik() {}

    // delete operacija
    public void deleteZdravnik(String mail)  { zdravnikDao.izbrisiZdravnika(mail); }

    public DruzinskiZdravnik getZdravnik() { return zdravnik; }

    public void setZdravnik(DruzinskiZdravnik zdravnik) { this.zdravnik = zdravnik; }

    public String getMail() { return mail; }

    public void setMail(String mail) {
        this.mail = mail;
        zdravnik = zdravnikDao.najdiZdravnika(mail);

        if(zdravnik == null) { zdravnik = new DruzinskiZdravnik(); zdravnik.setPacienti(new ArrayList<Pacient>());}
    }

    // funkcija za prikazovanje imena zdravnikov ter dodajanje pacienta brez dodanega zdravnika

    public List<String> getImenaZdravnikov() {
        List<String> imena = Collections.synchronizedList(new ArrayList<String>());

        imena.add("Brez zdravnika");

        for(DruzinskiZdravnik zdravnik: zdravnikDao.getZdravniki()) { imena.add(zdravnik.getIme() + " " + zdravnik.getPriimek()); }

        return imena;
    }

    // funkcija, ki vrne število pacientov, ki jih ima nek zdravnik

    public int steviloPacientov(DruzinskiZdravnik zdravnik) {
        List<Pacient> stPacientov = em.createQuery("select p from Pacient p where p.zdravnik = :id", Pacient.class)
            .setParameter("id", zdravnik).getResultList();

        if(stPacientov != null) { return stPacientov.size(); } else { return 0; }
    }

    public String getImeZdravnika() {
        return imeZdravnika;
    }

    public void setImeZdravnika(String imeZdravnika) {
        this.imeZdravnika = imeZdravnika;
    }
}
