package com.example.webserver.jsf;

import com.example.webserver.dao.ObiskDAO;
import com.example.webserver.dao.ZdraviloDAO;
import com.example.webserver.vao.Obisk;
import com.example.webserver.vao.Zdravilo;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;

import java.io.Serializable;
import java.util.List;

@Named("zdravila")
@SessionScoped
public class ZdraviloJSFBean implements Serializable {

    @PersistenceContext(unitName = "sample_pu", type = PersistenceContextType.EXTENDED)
    EntityManager em;

    private Zdravilo zdravilo = new Zdravilo();

    private Long id;

    private int stObiska;

    private int stZdravila;

    private String naziv;

    private int kolicina;

    @EJB
    private ZdraviloDAO zdraviloDao;

    @EJB
    private ObiskDAO obiskDao;

    public void nastaviVrednosti() {
        this.stZdravila = 0;
        this.naziv = "";
        this.kolicina = 0;
    }

    public void createZdravilo(int stObiska) {
        Zdravilo medicine = new Zdravilo();

        medicine.setStZdravila(stZdravila);
        medicine.setKolicina(kolicina);
        medicine.setNaziv(naziv);
        Obisk obisk = obiskDao.najdiObisk(stObiska, em);
        medicine.setObisk(obisk);

        zdraviloDao.shraniZdravilo(medicine);
    }

    public List<Zdravilo> getAllZdravila() { return zdraviloDao.getZdravila(); }

    public void updateZdravilo() { zdraviloDao.updateZdravilo(stZdravila, naziv, kolicina); }

    public void deleteZdravilo(int stZdravila) { zdraviloDao.deleteZdravilo(stZdravila); }

    public Zdravilo getZdravilo() {
        return zdravilo;
    }

    public void setZdravilo(Zdravilo zdravilo) {
        this.zdravilo = zdravilo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStZdravila() {
        return stZdravila;
    }

    public void setStZdravila(int stZdravila) {
        this.stZdravila = stZdravila;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public int getStObiska() {
        return stObiska;
    }

    public void setStObiska(int stObiska) {
        this.stObiska = stObiska;
    }
}
