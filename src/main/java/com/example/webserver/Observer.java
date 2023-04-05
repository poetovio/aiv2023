package com.example.webserver;

import com.example.webserver.dao.IOpazovalec;
import com.example.webserver.vao.DruzinskiZdravnik;
import com.example.webserver.vao.Pacient;

public class Observer implements IOpazovalec {

    private MailFacade fasada;

    public Observer() {
        this.fasada = new MailFacade();
    }

    @Override
    public void prostObvesti(DruzinskiZdravnik zdravnik, Pacient pacient) throws Exception {
        fasada.sendMessage(pacient, zdravnik, "Dodan zdravnik", "Dodeljen vam je bil nov zdravnik -> " + zdravnik.toString());
    }

    @Override
    public void zasedenObvesti(DruzinskiZdravnik zdravnik, Pacient pacient) throws Exception {
        fasada.sendMessage(pacient, zdravnik, "Odstranjen zdravnik", "Izbrali si boste novega zdravnika. Zdravnik, ki je bil odstranjen -> " + zdravnik.toString());
    }
}
