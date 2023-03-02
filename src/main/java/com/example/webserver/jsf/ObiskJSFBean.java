package com.example.webserver.jsf;

import com.example.webserver.dao.ObiskMemoryDAO;
import com.example.webserver.vao.Obisk;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named("obiski")
public class ObiskJSFBean implements Serializable {

    private static List<Obisk> obiski;

    private Obisk obisk = new Obisk();

    private int stObiska;

    private ObiskMemoryDAO obiskDao = ObiskMemoryDAO.getInstance();

    // create operacija

    public void createObisk() { obiskDao.shraniObisk(obisk); }

    // read operacija

    public List<Obisk> getObiski() { return obiskDao.vrniObiske(); }

    public Obisk getObisk(int stObiska) { return obiskDao.najdiObisk(stObiska); }

    // update operacija

    public void updateObisk(Obisk obisk) {}

    // delete operacija

    public void deleteObisk(int stObiska) { obiskDao.izbrisiObisk(stObiska); }

    public Obisk getObisk() {
        return obisk;
    }

    public void setObisk(Obisk obisk) {
        this.obisk = obisk;
    }

    public ObiskMemoryDAO getObiskDao() {
        return obiskDao;
    }

    public void setObiskDao(ObiskMemoryDAO obiskDao) {
        this.obiskDao = obiskDao;
    }

    public int getStObiska() {
        return stObiska;
    }

    public void setStObiska(int stObiska) {
        this.stObiska = stObiska;
    }
}
