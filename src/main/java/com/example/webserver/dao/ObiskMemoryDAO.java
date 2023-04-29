package com.example.webserver.dao;

import com.example.webserver.vao.DruzinskiZdravnik;
import com.example.webserver.vao.Obisk;
import jakarta.ejb.Stateless;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Stateless
public class ObiskMemoryDAO implements ObiskDAO {

    @PersistenceContext(unitName = "sample_pu")
    EntityManager em;

    // private List<Obisk> obiski = Collections.synchronizedList(new ArrayList<Obisk>());

    // pretvorba v singleton

    private ObiskMemoryDAO() {} // privatni konstruktor

    private static ObiskMemoryDAO instance = null;

    public static ObiskMemoryDAO getInstance() {
        if(instance == null) { instance = new ObiskMemoryDAO(); }
        return instance;
    }

    @Override
    public List<Obisk> vrniObiske() { return em.createQuery("select o from Obisk o", Obisk.class).getResultList(); }

    @Override
    public Obisk najdiObisk(int stObiska) {
        try {
            return em.createQuery("select o from Obisk o where o.stObiska = :stObiska", Obisk.class)
                    .setParameter("stObiska", stObiska).getSingleResult();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void shraniObisk(Obisk obisk) {
        if(najdiObisk(obisk.getStObiska()) != null) { izbrisiObisk(obisk.getStObiska()); }
        em.persist(obisk);
    }

    @Override
    public void izbrisiObisk(int stObiska) { Obisk dead = najdiObisk(stObiska); if(dead != null) em.remove(dead);}
}
