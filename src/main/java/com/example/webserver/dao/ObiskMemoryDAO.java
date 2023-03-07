package com.example.webserver.dao;

import com.example.webserver.vao.Obisk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ObiskMemoryDAO implements ObiskDAO {

    private List<Obisk> obiski = Collections.synchronizedList(new ArrayList<Obisk>());

    // pretvorba v singleton

    private ObiskMemoryDAO() {} // privatni konstruktor

    private static ObiskMemoryDAO instance = null;

    public static ObiskMemoryDAO getInstance() {
        if(instance == null) { instance = new ObiskMemoryDAO(); }
        return instance;
    }

    @Override
    public List<Obisk> vrniObiske() { return obiski; }

    @Override
    public Obisk najdiObisk(int stObiska) {
        for (Obisk obisk : obiski) {
            if (obisk.getStObiska() == stObiska) { return obisk; }
        }
        return null;
    }

    @Override
    public void shraniObisk(Obisk obisk) {
        if(najdiObisk(obisk.getStObiska()) != null) { izbrisiObisk(obisk.getStObiska()); }
        obiski.add(obisk);
    }

    @Override
    public void izbrisiObisk(int stObiska) {
        for(Iterator<Obisk> i = obiski.iterator(); i.hasNext();) {
            if(i.next().getStObiska() == stObiska) { i.remove(); }
        }
    }
}
