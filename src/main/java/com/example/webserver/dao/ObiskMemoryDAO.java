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
    public void updateObisk(int stObiska, Obisk obisk, Pacient pacient, DruzinskiZdravnik zdravnik, String opisDiagnoze, String casObiska, String datumObiska, String posebnosti, boolean jeZakljucen, EntityManager em2) {
        System.out.println(stObiska);
        System.out.println(pacient);
        System.out.println(zdravnik);
        System.out.println(opisDiagnoze);
        System.out.println(casObiska);
        System.out.println(datumObiska);
        System.out.println(posebnosti);
        System.out.println(jeZakljucen);
        try {
            System.out.println("Pred nastavljanjem -> " + posebnosti + " " + jeZakljucen);
            em2.createQuery("update Obisk o set o.pacient = :pacient, o.zdravnik = :zdravnik, o.opisDiangoze = :opisDiagnoze, o.casObiska = :casObiska, o.datumObiska = :datumObiska where o.stObiska = :stObiska")
                    .setParameter("pacient", pacient)
                    .setParameter("zdravnik", zdravnik)
                    .setParameter("opisDiagnoze", opisDiagnoze)
                    .setParameter("casObiska", casObiska)
                    .setParameter("datumObiska", datumObiska)
                    .setParameter("stObiska", stObiska)
                    .executeUpdate();

            em2.createQuery("update Obisk o set o.posebnosti = :posebnosti, o.jeZakljucen = :jeZakljucen where o.stObiska = :stObiska")
                    .setParameter("posebnosti", posebnosti)
                    .setParameter("jeZakljucen", jeZakljucen)
                    .setParameter("stObiska", stObiska)
                    .executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void izbrisiObisk(int stObiska, EntityManager em2) { Obisk dead = najdiObisk(stObiska, em2); if(dead != null) em2.remove(dead);}
}
