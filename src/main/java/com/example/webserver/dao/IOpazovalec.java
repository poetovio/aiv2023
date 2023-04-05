package com.example.webserver.dao;

import com.example.webserver.MailFacade;
import com.example.webserver.vao.DruzinskiZdravnik;
import com.example.webserver.vao.Pacient;

import java.io.Serializable;

public interface IOpazovalec extends Serializable {

    void prostObvesti(DruzinskiZdravnik zdravnik, Pacient pacient) throws Exception;

    void zasedenObvesti(DruzinskiZdravnik zdravnik, Pacient pacient) throws Exception;
}
