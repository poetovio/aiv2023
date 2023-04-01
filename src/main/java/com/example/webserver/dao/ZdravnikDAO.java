package com.example.webserver.dao;

import com.example.webserver.vao.DruzinskiZdravnik;
import com.example.webserver.vao.Pacient;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface ZdravnikDAO {

    List<DruzinskiZdravnik> getZdravniki();

    DruzinskiZdravnik najdiZdravnika(String mail);

    DruzinskiZdravnik najdiZdravnika(String ime, String priimek);

    void shraniZdravnika(DruzinskiZdravnik zdravnik);

    void izbrisiZdravnika(String mail);

}
