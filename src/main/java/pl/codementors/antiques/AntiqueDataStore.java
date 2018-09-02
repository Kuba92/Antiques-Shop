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

@Stateless
public class AntiqueDataStore {

    private static final Logger log = Logger.getLogger(AntiqueDataStore.class.getName());

    @PersistenceContext
    private EntityManager em;

    public List<Antique> getAntiques() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Antique> query = cb.createQuery(Antique.class);
        query.from(Antique.class);
        return em.createQuery(query).getResultList();
    }

    public Antique getAntique(int id) {
        Optional<Antique> antique = Optional.ofNullable(em.find(Antique.class, id));
        if (antique.isPresent()) {
            em.detach(antique.get());
            return antique.get();
        } else {
            return new Antique();
        }
    }

    public List<Country> getCountries() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Country> query = cb.createQuery(Country.class);
        query.from(Country.class);
        return em.createQuery(query).getResultList();
    }

    public void createNewAntique(Antique antique) {
        em.persist(antique);
    }

    public void updateAntique(Antique antique) {
        em.merge(antique);
    }

    public Country getCountry(int id) {
        return em.find(Country.class, id);
    }

    public void createNewOrder(Order order) {
        em.persist(order);
    }

    public void createNewClient(Client client) {
        em.persist(client);
    }

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
