package com.example.webserver.dao;

import com.example.webserver.vao.DruzinskiZdravnik;
import com.example.webserver.vao.Pacient;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Stateless
public class ZdravnikMemoryDAO implements ZdravnikDAO {

    @PersistenceContext(unitName = "sample_pu")
    EntityManager em;

    // private List<DruzinskiZdravnik> zdravniki = Collections.synchronizedList(new ArrayList<DruzinskiZdravnik>());

    // pretvorba v Singleton

    private ZdravnikMemoryDAO() {} // privatni konstruktor

    private static ZdravnikMemoryDAO instance = null;

    // returna samo en instance
    public synchronized static ZdravnikMemoryDAO getInstance() {
        if (instance == null) { instance = new ZdravnikMemoryDAO(); }
        return instance;
    }

    @Override
    public List<DruzinskiZdravnik> getZdravniki() {
        return em.createQuery("select z from DruzinskiZdravnik z", DruzinskiZdravnik.class).getResultList();
    }

    @Override
    public DruzinskiZdravnik najdiZdravnika(String mail) {
        try {
            return em.createQuery("select z from DruzinskiZdravnik z where z.mail = :mail", DruzinskiZdravnik.class)
                    .setParameter("mail", mail).getSingleResult();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void shraniZdravnika(DruzinskiZdravnik zdravnik) {
        if(najdiZdravnika(zdravnik.getMail()) != null) { izbrisiZdravnika(zdravnik.getMail()); }
        em.persist(zdravnik);
    }

    @Override
    public DruzinskiZdravnik updateZdravnik(String mail, DruzinskiZdravnik zdravnik) {
        try {
            em.createQuery("update DruzinskiZdravnik z set z.ime = :ime, z.priimek = :priimek, z.kvota = :kvota where z.mail = :mail")
                    .setParameter("ime", zdravnik.getIme())
                    .setParameter("priimek", zdravnik.getPriimek())
                    .setParameter("kvota", zdravnik.getKvota())
                    .setParameter("mail", mail)
                    .executeUpdate();

            return em.createQuery("select z from DruzinskiZdravnik z where z.mail = :mail", DruzinskiZdravnik.class)
                    .setParameter("mail", mail).getSingleResult();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void izbrisiZdravnika(String mail) { DruzinskiZdravnik dead = najdiZdravnika(mail); if(dead != null) em.remove(dead); }

    @Override
    public DruzinskiZdravnik najdiZdravnika(String ime, String priimek) {
        try {
            return em.createQuery("select z from DruzinskiZdravnik z where z.ime = :ime and z.priimek = :priimek", DruzinskiZdravnik.class)
                    .setParameter("ime", ime).setParameter("priimek", priimek).getSingleResult();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
