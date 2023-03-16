package com.example.webserver;

import com.example.webserver.vao.DruzinskiZdravnik;
import com.example.webserver.vao.Pacient;

public interface Poslji {
    void posljiSporocilo(Pacient pacient, DruzinskiZdravnik zdravnik) throws Exception;
}
