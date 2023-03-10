package com.example.webserver.dao;

import com.example.webserver.vao.Pacient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PacientMemoryDAO implements PacientDAO {

    private List<Pacient> pacienti = Collections.synchronizedList(new ArrayList<Pacient>());

    // pretvorba v singleton

    private PacientMemoryDAO() {} // privatni konstruktor

    private static PacientMemoryDAO instance = null;

    public static PacientMemoryDAO getInstance() {
        if(instance == null) { instance = new PacientMemoryDAO(); }
        return instance;
    }

    @Override
    public List<Pacient> getPacienti() { return pacienti; }

    @Override
    public Pacient najdiPacienta(String mail) {
        for (Pacient pacient: pacienti) {
            if(pacient.getMail().equals(mail)) { return pacient; }
        }
        return null;
    }

    @Override
    public void shraniPacienta(Pacient pacient) {
        if(najdiPacienta(pacient.getMail()) != null) { izbrisiPacienta(pacient.getMail()); }

        pacienti.add(pacient);
    }

    @Override
    public void izbrisiPacienta(String mail) {
        for(Iterator<Pacient> i = pacienti.iterator(); i.hasNext();) {
            if(i.next().getMail().equals(mail)) { i.remove(); }
        }
    }
}
