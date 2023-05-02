package com.example.webserver.dao;

import com.example.webserver.vao.Obisk;
import com.example.webserver.vao.Pacient;
import com.example.webserver.vao.Zdravilo;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class ZdraviloMemoryDAO implements ZdraviloDAO {

    @PersistenceContext(unitName = "sample_pu")
    EntityManager em;

    public ZdraviloMemoryDAO() {};

    private static ZdraviloMemoryDAO instance = null;

    public static ZdraviloMemoryDAO getInstance() {
        if(instance == null) { instance = new ZdraviloMemoryDAO(); }
        return instance;
    }

    @Override
    public List<Zdravilo> getZdravila() {
        return em.createQuery("select z from Zdravilo z", Zdravilo.class).getResultList();
    }

    @Override
    public Zdravilo getZdravilo(int stZdravila) {
        try {
            return em.createQuery("select z from Zdravilo z where z.stZdravila = :stZdravila", Zdravilo.class)
                    .setParameter("stZdravila", stZdravila).getSingleResult();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Zdravilo updateZdravilo(int stZdravila, String naziv, int kolicina) {
        try {
            em.createQuery("update Zdravilo z set z.naziv = :naziv, z.kolicina = :kolicina where z.stZdravila = :stZdravila")
                    .setParameter("naziv", naziv)
                    .setParameter("kolicina", kolicina)
                    .setParameter("stZdravila", stZdravila)
                    .executeUpdate();

            return em.createQuery("select z from Zdravilo z where z.stZdravila = :stZdravila", Zdravilo.class)
                    .setParameter("stZdravila", stZdravila).getSingleResult();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void shraniZdravilo(Zdravilo zdravilo) {
        if(getZdravilo(zdravilo.getStZdravila()) != null) { deleteZdravilo(zdravilo.getStZdravila()); };
        em.persist(zdravilo);
    }

    @Override
    public void deleteZdravilo(int stZdravila) { Zdravilo dead = getZdravilo(stZdravila); if(dead != null) em.remove(dead); }
}
