package com.example.webserver;

import com.example.webserver.vao.Pacient;

public interface StrategijaZakljucek {
    void posljiMailZakljucek(Pacient pacient, String sporocilo) throws Exception;
}
