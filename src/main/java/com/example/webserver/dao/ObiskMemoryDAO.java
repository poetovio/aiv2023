package com.example.webserver.dao;

import com.example.webserver.vao.DruzinskiZdravnik;
import com.example.webserver.vao.Obisk;
import com.example.webserver.vao.Pacient;
import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.transaction.UserTransaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ObiskMemoryDAO implements ObiskDAO {

    @PersistenceContext(unitName = "sample_pu")
    EntityManager em;

    // private List<Obisk> obiski = Collections.synchronizedList(new ArrayList<Obisk>());

    // pretvorba v singleton

    public ObiskMemoryDAO() {} // privatni konstruktor

    private static ObiskMemoryDAO instance = null;

    public static ObiskMemoryDAO getInstance() {
        if(instance == null) { instance = new ObiskMemoryDAO(); }
        return instance;
    }

    @Override
    public List<Obisk> vrniObiske(EntityManager em2) { return em2.createQuery("select o from Obisk o", Obisk.class).getResultList(); }

    @Override
    public Obisk najdiObisk(int stObiska, EntityManager em2) {
        try {
            return em2.createQuery("select o from Obisk o where o.stObiska = :stObiska", Obisk.class)
                    .setParameter("stObiska", stObiska).getSingleResult();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void shraniObisk(Obisk obisk, String pacient, String zdravnik, EntityManager em2, UserTransaction utx) {

        try {
            utx.begin();

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("sample_pu");
            EntityManager em3 = emf.createEntityManager();

            em3.persist(obisk);

            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Obisk updateObisk(int stObiska, Obisk obisk, Pacient pacient, DruzinskiZdravnik zdravnik) {
        try {
            em.createQuery("update Obisk o set o.pacient = :pacient, o.zdravnik = :zdravnik where o.stObiska = :stObiska")
                    .setParameter("pacient", pacient)
                    .setParameter("zdravnik", zdravnik)
                    .setParameter("stObiska", stObiska)
                    .executeUpdate();

            return em.createQuery("select o from Obisk o where o.stObiska = :stObiska", Obisk.class)
                    .setParameter("stObiska", stObiska).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void izbrisiObisk(int stObiska) { Obisk dead = najdiObisk(stObiska, em); if(dead != null) em.remove(dead);}
}
