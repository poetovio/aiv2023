package com.example.webserver.dao;

import com.example.webserver.vao.Pacient;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Stateless
public class PacientMemoryDAO implements PacientDAO {

    @PersistenceContext(unitName = "sample_pu")
    EntityManager em;

    // private List<Pacient> pacienti = Collections.synchronizedList(new ArrayList<Pacient>());

    // pretvorba v singleton

    private PacientMemoryDAO() {} // privatni konstruktor

    private static PacientMemoryDAO instance = null;

    public synchronized static PacientMemoryDAO getInstance() {
        if(instance == null) { instance = new PacientMemoryDAO(); }
        return instance;
    }

    @Override
    public List<Pacient> getPacienti() { return em.createQuery("select p from Pacient p ", Pacient.class).getResultList(); }

    @Override
    public Pacient najdiPacienta(String mail) {
        try {
            return em.createQuery("select p from Pacient p where p.mail = :mail", Pacient.class).setParameter("mail", mail).getSingleResult();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void shraniPacienta(Pacient pacient) {
        if(najdiPacienta(pacient.getMail()) != null) { izbrisiPacienta(pacient.getMail()); }
        em.persist(pacient);
    }

    @Override
    public void izbrisiPacienta(String mail) { Pacient dead = najdiPacienta(mail); if(dead != null) { em.remove(dead); } }
}
