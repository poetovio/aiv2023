package com.example.webserver;

import com.example.webserver.vao.DruzinskiZdravnik;
import com.example.webserver.vao.Pacient;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.Serializable;

@Named("mailFasada")
@SessionScoped
public class MailFacade implements Serializable {

    @Resource(mappedName = "java:jboss/mail/MojMail")

    private PosljiPacient posljiPacient;
    private PosljiZdravnik posljiZdravnik;
    private PosljiNapako posljiNapako;

    public MailFacade() {
        this.posljiPacient = new PosljiPacient();
        this.posljiZdravnik = new PosljiZdravnik();
        this.posljiNapako = new PosljiNapako();
    }

    public void sprejmiPacienta(Pacient pacient, DruzinskiZdravnik zdravnik) throws Exception {
        posljiPacient.posljiSporocilo(pacient, zdravnik);
        posljiZdravnik.posljiSporocilo(pacient, zdravnik);
    }

    public void zavrniPacienta(Pacient pacient, DruzinskiZdravnik zdravnik) throws Exception {
        posljiNapako.posljiSporocilo(pacient, zdravnik);
    }
}
