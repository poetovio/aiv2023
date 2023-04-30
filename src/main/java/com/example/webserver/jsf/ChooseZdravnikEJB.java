package com.example.webserver.jsf;

import com.example.webserver.MailFacade;
import com.example.webserver.dao.ChooseZdravnik;
import com.example.webserver.dao.PacientDAO;
import com.example.webserver.dao.ZdravnikDAO;
import com.example.webserver.vao.DruzinskiZdravnik;
import com.example.webserver.vao.Pacient;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ChooseZdravnikEJB implements ChooseZdravnik, Serializable {

    @PersistenceContext(unitName = "sample_pu")
    EntityManager em;

    @EJB
    private PacientDAO pacientDao;

    @EJB
    private ZdravnikDAO zdravnikDao;

    @Override
    public void izberiZdravnika(String mail, String imeZdravnika) throws Exception {
        Pacient bolnik = pacientDao.najdiPacienta(mail);

        MailFacade fasada = new MailFacade();

        String[] parts = imeZdravnika.split(" ");

        if(bolnik.getZdravnik() != null) {
            bolnik.getZdravnik().odstraniPacienta(bolnik);
            bolnik.alertOdstranjevanje(bolnik.getZdravnik());
        }

        DruzinskiZdravnik dohtar = zdravnikDao.najdiZdravnika(parts[0], parts[1]);

        dohtar.setPacienti(new ArrayList<Pacient>());

        List<Pacient> izBaze = pacientDao.getPacienti();

        if(izBaze != null) {
            for (Pacient pacient : izBaze) { if (pacient.getZdravnik() != null && pacient.getZdravnik().equals(dohtar)) { dohtar.getPacienti().add(pacient); } }
        }

        if(prevzemPacienta(dohtar, dohtar.getPacienti().size(), bolnik)) {
            fasada.sprejmiPacienta(bolnik, dohtar);
            bolnik.alertDodajanje(dohtar);
        } else {
            fasada.zavrniPacienta(bolnik, dohtar);
        }

        if(bolnik.getZdravnik() != null && !bolnik.getZdravnik().zeImaPacienta(bolnik)) { bolnik.getZdravnik().getPacienti().add(bolnik); }
    }

    @Override
    public boolean prevzemPacienta(DruzinskiZdravnik zdravnik, int stPacientov, Pacient pacient) {
        if(stPacientov < zdravnik.getKvota()) { zdravnik.getPacienti().add(pacient); pacient.setZdravnik(zdravnik); return true; }
        return false;
    }
}
