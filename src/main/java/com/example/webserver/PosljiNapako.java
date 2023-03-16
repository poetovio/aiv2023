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

public class PosljiNapako implements Poslji {
    @Override
    public void posljiSporocilo(Pacient pacient, DruzinskiZdravnik zdravnik) throws Exception {
        String from = "urbi.vizintin@gmail.com";

        Context context = new InitialContext();
        Session seja = (Session) context.lookup("java:jboss/mail/MojMail");

        // pošlji sporočilo pacientu
        Message sporociloPacient = new MimeMessage(seja);
        sporociloPacient.setFrom(new InternetAddress(from));
        Address toAddress = new InternetAddress(pacient.getMail());
        sporociloPacient.addRecipient(Message.RecipientType.TO, toAddress);
        sporociloPacient.setSubject("Uspesno izbran zdravnik");

        String body = "Zdravnik" + zdravnik.toString() + " ima na zalost zapolnjeno kvoto. Prosimo, da si izberete" +
                " drugega zdravnika.";

        sporociloPacient.setContent(body, "text/plain");
        Transport.send(sporociloPacient);
    }
}
