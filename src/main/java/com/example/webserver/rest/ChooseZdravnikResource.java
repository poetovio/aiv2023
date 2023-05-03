package com.example.webserver.rest;

import com.example.webserver.dao.ChooseZdravnik;
import com.example.webserver.dao.PacientDAO;
import com.example.webserver.dao.ZdravnikDAO;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/pick")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChooseZdravnikResource {

    @EJB
    private ChooseZdravnik chooseZdravnikDao;

    @EJB
    private ZdravnikDAO zdravnikDao;

    @EJB
    private PacientDAO pacientDao;

    @GET
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/info")
    public String info() {
        return "Izbira zdravnika";
    }

    @POST
    @Path("/{mail}/{ime}/{priimek}")
    public void izberiZdravnika(@PathParam("mail") String mail, @PathParam("ime") String ime, @PathParam("priimek") String priimek) throws Exception {
        if(pacientDao.najdiPacienta(mail) == null) {
            throw new Exception("Pacient se ne obstaja.");
        }
        if(zdravnikDao.najdiZdravnika(ime, priimek) == null) {
            throw new Exception("Zdravnik se ne obstaja.");
        }

        chooseZdravnikDao.izberiZdravnika(mail, ime + " " + priimek);
    }
}
