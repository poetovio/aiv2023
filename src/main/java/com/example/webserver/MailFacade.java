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

    private Session mySession;

    private String to;

    private String from;

    private String subject;

    private String body;

    public Session getMySession() {
        return mySession;
    }

    public void setMySession(Session mySession) {
        this.mySession = mySession;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void sprejmiPacienta(Pacient pacient, DruzinskiZdravnik zdravnik) throws Exception {
        from = "urbi.vizintin@gmail.com";

        Context context = new InitialContext();
        Session seja = (Session) context.lookup("java:jboss/mail/MojMail");

        System.out.println("LMAO");
        System.out.println(pacient.getMail());

        // pošlji sporočilo pacientu
        Message sporociloPacient = new MimeMessage(seja);
        System.out.println("LMAO1");
        sporociloPacient.setFrom(new InternetAddress(from));
        System.out.println("LMAO2");
        Address toAddress = new InternetAddress(pacient.getMail());
        System.out.println("LMAO3");
        sporociloPacient.addRecipient(Message.RecipientType.TO, toAddress);
        System.out.println("LMAO4");
        sporociloPacient.setSubject("Uspesno izbran zdravnik");
        System.out.println("LMAO5");

        body = "Uspesno ste izbrali zdravnika " + zdravnik.toString() + ".";

        System.out.println("LMAO6");
        sporociloPacient.setContent(body, "text/plain");
        System.out.println("LMAO7");
        Transport.send(sporociloPacient);
        System.out.println("LMAO8");

        // pošlji sporočilo zdravniku

        // pošlji sporočilo pacientu
        Message sporociloZdravnik = new MimeMessage(mySession);
        sporociloZdravnik.setFrom(new InternetAddress(from));
        Address toAddressZdravnik = new InternetAddress(zdravnik.getMail());
        sporociloZdravnik.addRecipient(Message.RecipientType.TO, toAddressZdravnik);
        sporociloZdravnik.setSubject("Novo vpisan pacient");

        body = "Pacient " + pacient.toString() + " vas je izbral kot osebnega zdravnika." +
                " Vasa kvota znasa zdaj " + zdravnik.getKvota() + ".";

        sporociloPacient.setContent(body, "text/plain");
        Transport.send(sporociloPacient);

    }

    public void zavrniPacienta(Pacient pacient, DruzinskiZdravnik zdravnik) throws Exception {
        from = "urbi.vizintin@gmail.com";

        Context context = new InitialContext();
        Session seja = (Session) context.lookup("java:jboss/mail/MojMail");

        // pošlji sporočilo pacientu
        Message sporociloPacient = new MimeMessage(seja);
        sporociloPacient.setFrom(new InternetAddress(from));
        Address toAddress = new InternetAddress(pacient.getMail());
        sporociloPacient.addRecipient(Message.RecipientType.TO, toAddress);
        sporociloPacient.setSubject("Napaka pri izbiri zdravnika");

        body = "Zdravnik" + zdravnik.toString() + " ima na zalost zapolnjeno kvoto. Prosimo, da si izberete" +
                " drugega zdravnika.";

        sporociloPacient.setContent(body, "text/plain");
        Transport.send(sporociloPacient);
    }
}
