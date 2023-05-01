package com.example.webserver.dao;

import com.example.webserver.vao.DruzinskiZdravnik;
import com.example.webserver.vao.Obisk;
import com.example.webserver.vao.Pacient;
import jakarta.ejb.Local;
import jakarta.persistence.EntityManager;
import jakarta.transaction.UserTransaction;

import java.util.List;

@Local
public interface ObiskDAO {

    List<Obisk> vrniObiske(EntityManager em);

    Obisk najdiObisk(int stObiska, EntityManager em2);

    Obisk updateObisk(int stObiska, Obisk obisk, Pacient pacient, DruzinskiZdravnik zdravnik);

    void shraniObisk(Obisk obisk, String pacient, String zdravnik, EntityManager em, UserTransaction utx);

    void izbrisiObisk(int stObiska, EntityManager em);
}
