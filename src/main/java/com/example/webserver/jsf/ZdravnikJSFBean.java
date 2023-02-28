package com.example.webserver.jsf;

import com.example.webserver.dao.ZdravnikMemoryDAO;
import com.example.webserver.vao.DruzinskiZdravnik;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.util.List;
import java.io.Serializable;

@Named("dohtarji")
@SessionScoped
public class ZdravnikJSFBean implements Serializable {

    private static List<DruzinskiZdravnik> zdravniki;

    private DruzinskiZdravnik zdravnik = new DruzinskiZdravnik();

    private ZdravnikMemoryDAO zdravnikDao = ZdravnikMemoryDAO.getInstance();


    // create operacija
    public void createZdravnik()  { zdravnikDao.shraniZdravnika(zdravnik); }


    // read operacija
    public List<DruzinskiZdravnik> getAllZdravniki() { return zdravnikDao.getZdravniki(); }

    // update operacija

    public void updateZdravnik() {}

    // delete operacija
    public void deleteZdravnik(String mail)  { zdravnikDao.izbrisiZdravnika(mail); }
}
