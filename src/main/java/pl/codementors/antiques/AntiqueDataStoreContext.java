package pl.codementors.antiques;


import pl.codementors.antiques.model.Antique;
import pl.codementors.antiques.model.Country;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.logging.Logger;

/**
 * Class that adds content to database
 * @author Kuba
 */
@Singleton
@Startup
public class AntiqueDataStoreContext {

    /**
     * Logger
     */
    private static final Logger log = Logger.getLogger(AntiqueDataStoreContext.class.getName());

    /**
     * New Entity Manager
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Method that adds content to DB, only if it is empty
     */
    @PostConstruct
    public void init() {
        if (sizeOfDatabase() == 0) {
            Country argentina = new Country("Argentina");
            em.persist(argentina);
            Country italy = new Country("Italy");
            em.persist(italy);
            Country china = new Country("China");
            em.persist(china);
            Country usa = new Country("USA");
            em.persist(usa);
            Country gb = new Country("Great Britain");
            em.persist(gb);
            Country france = new Country("France");
            em.persist(france);

            Antique firstAntique = new Antique(1507, italy, 100000, Antique.Availability.AVALIBLE);
            em.persist(firstAntique);
            Antique secondAntique = new Antique(1811, argentina, 1234555, Antique.Availability.AVALIBLE);
            em.persist(secondAntique);
            Antique thirdAntique = new Antique(1410, china, 642, Antique.Availability.AVALIBLE);
            em.persist(thirdAntique);
        }
    }

    /**
     * Method for checking the number of rows in the database in Antique table
     * @return size of antique table
     */
    public int sizeOfDatabase() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Antique> query = cb.createQuery(Antique.class);
        query.from(Antique.class);
        List<Antique> antiques = em.createQuery(query).getResultList();
        return antiques.size();
    }
}
