package com.example.webserver.dao;

import com.example.webserver.vao.Pacient;

import java.util.List;

public interface PacientDAO {

    List<Pacient> getPacienti();

    Pacient najdiPacienta(String mail);

    void shraniPacienta(Pacient pacient);

    void izbrisiPacienta(String mail);
}
