package com.example.webserver.dao;

import com.example.webserver.vao.DruzinskiZdravnik;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ZdravnikMemoryDAO implements ZdravnikDAO {

    private List<DruzinskiZdravnik> zdravniki = Collections.synchronizedList(new ArrayList<DruzinskiZdravnik>());

    private static ZdravnikMemoryDAO instance = new ZdravnikMemoryDAO();


    public static ZdravnikMemoryDAO getInstance() { return instance; }

    @Override
    public List<DruzinskiZdravnik> getZdravniki() {
        return zdravniki;
    }

    @Override
    public DruzinskiZdravnik najdiZdravnika(String mail) {
        for (DruzinskiZdravnik zdravnik: zdravniki) {
            if(zdravnik.getMail().equals(mail)) { return zdravnik; }
        }

        return null;
    }

    @Override
    public void shraniZdravnika(DruzinskiZdravnik zdravnik) {
        if(najdiZdravnika(zdravnik.getMail()) != null) { izbrisiZdravnika(zdravnik.getMail()); }

        zdravniki.add(zdravnik);
    }

    @Override
    public void izbrisiZdravnika(String mail) {
        for(Iterator<DruzinskiZdravnik> i = zdravniki.iterator(); i.hasNext();) {
            if(i.next().getMail().equals(mail)) { i.remove(); }
        }
    }

    public DruzinskiZdravnik najdiZdravnika(String ime, String priimek) {
        for (DruzinskiZdravnik zdravnik: zdravniki) {
            if(zdravnik.getIme().equals(ime) && zdravnik.getPriimek().equals(priimek)) { return zdravnik; }
        }
        return null;
    }
}
