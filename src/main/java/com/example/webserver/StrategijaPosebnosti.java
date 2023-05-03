package com.example.webserver;

import com.example.webserver.vao.Pacient;

public class StrategijaPosebnosti implements StrategijaZakljucek {
    MailFacade fasada = new MailFacade();

    @Override
    public void posljiMailZakljucek(Pacient pacient, String sporocilo) throws Exception {
        fasada.sendMessage(pacient, null, "Posebnosti", sporocilo);
    }
}
