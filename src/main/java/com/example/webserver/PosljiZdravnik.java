package com.example.webserver;

import com.example.webserver.vao.DruzinskiZdravnik;
import com.example.webserver.vao.Pacient;
import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import javax.naming.Context;
import javax.naming.InitialContext;

public class PosljiZdravnik implements Poslji {
    @Override
    public void posljiSporocilo(Pacient pacient, DruzinskiZdravnik zdravnik) throws Exception {
        String from = "urbi.vizintin@gmail.com";

        Context context = new InitialContext();
        Session sejaZdravnik = (Session) context.lookup("java:jboss/mail/MojMail");

        Message sporociloZdravnik = new MimeMessage(sejaZdravnik);
        sporociloZdravnik.setFrom(new InternetAddress(from));
        Address toAddressZdravnik = new InternetAddress(zdravnik.getMail());
        sporociloZdravnik.addRecipient(Message.RecipientType.TO, toAddressZdravnik);
        sporociloZdravnik.setSubject("Novo vpisan pacient");

        String body = "Pacient " + pacient.toString() + " vas je izbral kot osebnega zdravnika." +
                " Stevilo vpisanih pacientov znasa " + zdravnik.getPacienti().size() + ".";

        sporociloZdravnik.setContent(body, "text/plain");
        Transport.send(sporociloZdravnik);
    }
}
