package com.example.webserver;

import com.example.webserver.vao.DruzinskiZdravnik;
import com.example.webserver.vao.Pacient;

public interface SendMail {
    void sendMessage(DruzinskiZdravnik zdravnik, Pacient pacient, String zadeva, String sporocilo) throws Exception;
}
