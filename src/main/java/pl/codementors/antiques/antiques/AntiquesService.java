package pl.codementors.antiques.antiques;


import pl.codementors.antiques.model.Antique;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Class that allows getting and adding json files with antiques
 */
@Stateless
@Path("antiques")
public class AntiquesService {

    @PersistenceContext
    private EntityManager em;

    /**
     * Method that returns list of all antiques in DB in json files
     * @return list of antiques
     */
    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Antique> findAntiques() {
        return em.createQuery("select a from Antique a").getResultList();
    }

    /**
     * Method that returns single antique indentified by ID
     * @param id ID of antique
     * @return antique with a specific ID
     */
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Antique findAntique(@PathParam("id") int id) {
        return em.createQuery("select a from Antique a where a.id = :id", Antique.class).setParameter("id", id).getSingleResult();
    }

    /**
     * Method that saves antique from request
     * @param antique antique sent in json file
     */
    @Path("")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveAntique(Antique antique) {
        em.persist(antique);
    }
}
