package com.example.webserver;


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

@Named("mail")
@SessionScoped
public class MailSender implements Serializable {

    @Resource(mappedName = "java:jboss/mail/MojMail")

    private Session mySession;

    private String to;

    private String from;

    private String subject;

    private String body;

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

    public void send() throws Exception {
        Message message = new MimeMessage(mySession);
        message.setFrom(new InternetAddress(from));
        Address toAddress = new InternetAddress(to);
        message.addRecipient(Message.RecipientType.TO, toAddress);
        message.setSubject(subject);
        message.setContent(body, "text/plain");
        Transport.send(message);
    }

    public void testPoslji() throws Exception {
        Context context = new InitialContext();
        Session seja = (Session) context.lookup("java:jboss/mail/MojMail");

        to = "urbi.vizintin@gmail.com";
        from = "urbi.vizintin@gmail.com";
        subject = "test";
        body = "Lep pozdrav";

        // send();


        Message message = new MimeMessage(seja);
        message.setFrom(new InternetAddress("urbi.vizintin@gmail.com"));
        Address toAddress = new InternetAddress("urbi.vizintin@gmail.com");
        message.addRecipient(Message.RecipientType.TO, toAddress);
        message.setSubject("test");
        message.setContent("Lep pozdrav", "text/plain");
        Transport.send(message);
    }
}
