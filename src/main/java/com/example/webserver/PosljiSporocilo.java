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

public class PosljiSporocilo implements SendMail {

    @Override
    public void sendMessage(DruzinskiZdravnik zdravnik, Pacient pacient, String zadeva, String sporocilo) throws Exception {
        String from = "urbi.vizintin@gmail.com";

        Context context = new InitialContext();
        Session seja = (Session) context.lookup("java:jboss/mail/MojMail");

        // pošlji sporočilo pacientu
        Message sporociloPacient = new MimeMessage(seja);
        sporociloPacient.setFrom(new InternetAddress(from));
        Address toAddress = new InternetAddress(pacient.getMail());
        sporociloPacient.addRecipient(Message.RecipientType.TO, toAddress);
        sporociloPacient.setSubject(zadeva);

        sporociloPacient.setContent(sporocilo, "text/plain");
        Transport.send(sporociloPacient);
    }
}
