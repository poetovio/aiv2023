package com.example.webserver.dao;

import com.example.webserver.vao.DruzinskiZdravnik;
import com.example.webserver.vao.Obisk;
import com.example.webserver.vao.Pacient;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface ObiskDAO {

    List<Obisk> vrniObiske();

    Obisk najdiObisk(int stObiska);

    Obisk updateObisk(int stObiska, Obisk obisk, Pacient pacient, DruzinskiZdravnik zdravnik);

    void shraniObisk(Obisk obisk);

    void izbrisiObisk(int stObiska);
}
