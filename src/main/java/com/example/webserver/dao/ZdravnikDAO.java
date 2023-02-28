package com.example.webserver.dao;

import com.example.webserver.vao.DruzinskiZdravnik;
import java.util.List;

public interface ZdravnikDAO {

    List<DruzinskiZdravnik> getZdravniki();

    DruzinskiZdravnik najdiZdravnika(String mail);

    void shraniZdravnika(DruzinskiZdravnik zdravnik);

    void izbrisiZdravnika(String mail);
}
