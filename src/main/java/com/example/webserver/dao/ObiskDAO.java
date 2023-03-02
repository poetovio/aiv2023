package com.example.webserver.dao;

import com.example.webserver.vao.Obisk;

import java.util.List;

public interface ObiskDAO {

    List<Obisk> vrniObiske();

    Obisk najdiObisk(int stObiska);

    void shraniObisk(Obisk obisk);

    void izbrisiObisk(int stObiska);
}
