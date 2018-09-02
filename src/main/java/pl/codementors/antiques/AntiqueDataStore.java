package pl.codementors.antiques;


import pl.codementors.antiques.model.Antique;
import pl.codementors.antiques.model.Client;
import pl.codementors.antiques.model.Country;
import pl.codementors.antiques.model.Order;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Class for database management
 */
@Stateless
public class AntiqueDataStore {

    private static final Logger log = Logger.getLogger(AntiqueDataStore.class.getName());

    /**
     * New Entity Manager
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Method that returns list of all antiques in DB
     * @return list of antiques
     */
    public List<Antique> getAntiques() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Antique> query = cb.createQuery(Antique.class);
        query.from(Antique.class);
        return em.createQuery(query).getResultList();
    }

    /**
     * Method that returns single antique indentified by id
     * @param id to find antique
     * @return single antique by id
     */
    public Antique getAntique(int id) {
        Optional<Antique> antique = Optional.ofNullable(em.find(Antique.class, id));
        if (antique.isPresent()) {
            em.detach(antique.get());
            return antique.get();
        } else {
            return new Antique();
        }
    }

    /**
     * Method that returns list of all countries in DB
     * @return List of all countries in DB
     */
    public List<Country> getCountries() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Country> query = cb.createQuery(Country.class);
        query.from(Country.class);
        return em.createQuery(query).getResultList();
    }

    /**
     * Creates new antique in DB
     * @param antique to be saved
     */
    public void createNewAntique(Antique antique) {
        em.persist(antique);
    }

    /**
     * Updates data about single antique
     * @param antique to be updated
     */
    public void updateAntique(Antique antique) {
        em.merge(antique);
    }

    /**
     * Method that returns single country indentified by id
     * @param id to find country
     * @return country by id
     */
    public Country getCountry(int id) {
        return em.find(Country.class, id);
    }

    /**
     * Creates new order
     * @param order to be saved in DB
     */
    public void createNewOrder(Order order) {
        em.persist(order);
    }

    /**
     * Creates new client
     * @param client to be saved in DB
     */
    public void createNewClient(Client client) {
        em.persist(client);
    }

    /**
     * Method that searches the DB to find a specific client
     * @param name Namme of client
     * @param surname Surname of client
     * @return Client object if found it, if it does not, creates a new one
     */
    public Optional<Client> findClientByFullName (String name, String surname) {
        TypedQuery<Client> query = em.createQuery("select a from Client a where a.name = :name and a.surname = :surname", Client.class);
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
}
