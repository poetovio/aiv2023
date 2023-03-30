package com.example.webserver.dao;

import com.example.webserver.vao.Pacient;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface PacientDAO {

    List<Pacient> getPacienti();

    Pacient najdiPacienta(String mail);

    void shraniPacienta(Pacient pacient);

    void izbrisiPacienta(String mail);
}
