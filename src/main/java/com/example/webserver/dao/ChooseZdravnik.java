package com.example.webserver.dao;

import com.example.webserver.vao.DruzinskiZdravnik;
import com.example.webserver.vao.Pacient;
import jakarta.ejb.Remote;

import java.io.Serializable;

@Remote
public interface ChooseZdravnik extends Serializable  {
    void izberiZdravnika(String mail, String imeZdravnika) throws Exception;

    boolean prevzemPacienta(DruzinskiZdravnik zdravnik, int stPacientov, Pacient pacient);
}
