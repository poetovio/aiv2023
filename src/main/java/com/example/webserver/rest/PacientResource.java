package com.example.webserver.rest;

import com.example.webserver.dao.PacientDAO;
import com.example.webserver.vao.Pacient;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/pacienti")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacientResource {

    @EJB
    private PacientDAO pacientDao;

    @GET
    public List<Pacient> getAllPacienti() {
        return pacientDao.getPacienti();
    }

    @GET
    @Path("/{mail}")
    public Pacient getPacient(@PathParam("mail") String mail) {
        return pacientDao.najdiPacienta(mail);
    }

    @POST
    public void addPacient(Pacient p) throws Exception {
        pacientDao.shraniPacienta(p);
    }

    @PUT
    public Pacient updatePacient(String mail, Pacient p) throws Exception {
        if(pacientDao.najdiPacienta(mail) == null) {
            throw new Exception("Ta pacient se ne obstaja.");
        }
        return pacientDao.updatePacient(mail, p);
    }

}
