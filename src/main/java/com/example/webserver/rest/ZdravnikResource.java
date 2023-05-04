package com.example.webserver.rest;

import com.example.webserver.dao.ZdravnikDAO;
import com.example.webserver.vao.DruzinskiZdravnik;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/zdravniki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ZdravnikResource {

    @EJB
    private ZdravnikDAO zdravnikDao;

    @GET
    public List<DruzinskiZdravnik> getZdravniki() {
        return zdravnikDao.getZdravniki();
    }
}
