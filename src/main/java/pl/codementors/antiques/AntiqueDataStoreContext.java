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

@Singleton
@Startup
public class AntiqueDataStoreContext {

    private static final Logger log = Logger.getLogger(AntiqueDataStoreContext.class.getName());

    @PersistenceContext
    private EntityManager em;

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

            Antique firstAntique = new Antique(1507, italy, 100000, Antique.availability.AVALIBLE);
            em.persist(firstAntique);
            Antique secondAntique = new Antique(1811, argentina, 1234555, Antique.availability.AVALIBLE);
            em.persist(secondAntique);
            Antique thirdAntique = new Antique(1410, china, 642, Antique.availability.AVALIBLE);
            em.persist(thirdAntique);
        }
    }


    public boolean databaseIsEmpty() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Antique> query = cb.createQuery(Antique.class);
        query.from(Antique.class);
        if (em.createQuery(query).getResultList().isEmpty()) {
            return true;
        }
        return false;
    }

    public int sizeOfDatabase() {
        CriteriaBuilder cb = em.getCriteriaBuilder();//Builder for creating a query.
        CriteriaQuery<Antique> query = cb.createQuery(Antique.class);//Query object declaring query return object.
        query.from(Antique.class);//From which entity (table) data will be fetched.
        List<Antique> antiques = em.createQuery(query).getResultList();
        return antiques.size();
    }
}
