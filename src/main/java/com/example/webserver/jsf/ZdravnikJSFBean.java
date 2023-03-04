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

    private String mail;

    private ZdravnikMemoryDAO zdravnikDao = ZdravnikMemoryDAO.getInstance();

    private String imeZdravnika;


    // create operacija
    public void createZdravnik()  { zdravnikDao.shraniZdravnika(zdravnik); }


    // read operacija
    public List<DruzinskiZdravnik> getAllZdravniki() { return zdravnikDao.getZdravniki(); }

    // update operacija

    public void updateZdravnik() {}

    // delete operacija
    public void deleteZdravnik(String mail)  { zdravnikDao.izbrisiZdravnika(mail); }

    public DruzinskiZdravnik getZdravnik() { return zdravnik; }

    public void setZdravnik(DruzinskiZdravnik zdravnik) { this.zdravnik = zdravnik; }

    public String getMail() { return mail; }

    public void setMail(String mail) {
        this.mail = mail;
        zdravnik = zdravnikDao.najdiZdravnika(mail);

        if(zdravnik == null) { zdravnik = new DruzinskiZdravnik(); }
    }

    public String getImeZdravnika() {
        return imeZdravnika;
    }

    public void setImeZdravnika(String imeZdravnika) {
        this.imeZdravnika = imeZdravnika;
    }
}
