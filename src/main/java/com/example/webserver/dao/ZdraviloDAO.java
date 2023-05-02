package com.example.webserver.dao;

import com.example.webserver.vao.Obisk;
import com.example.webserver.vao.Zdravilo;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface ZdraviloDAO {

    List<Zdravilo> getZdravila();

    Zdravilo getZdravilo(int stZdravila);

    Zdravilo updateZdravilo(int stZdravila, String naziv, int kolicina);

    void shraniZdravilo(Zdravilo zdravilo);

    void deleteZdravilo(int stZdravila);
}
