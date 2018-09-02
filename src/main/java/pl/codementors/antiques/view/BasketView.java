package pl.codementors.antiques.view;

import pl.codementors.antiques.AntiqueDataStore;
import pl.codementors.antiques.model.Antique;
import pl.codementors.antiques.model.Client;
import pl.codementors.antiques.model.Order;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Named
@ApplicationScoped
//@Stateless
public class BasketView implements Serializable {

    @EJB
    private AntiqueDataStore store;

    private List<Antique> antiquesInBasket = new ArrayList<>();

    private Client client = new Client();

    public List<Antique> getAntiquesInBasket() {
        return antiquesInBasket;
    }

    public void setAntiquesInBasket(List<Antique> antiquesInBasket) {
        this.antiquesInBasket = antiquesInBasket;
    }

    public void addAntiqueToBasket(Antique antique) {
        if (!antiquesInBasket.contains(antique)) {
            antiquesInBasket.add(antique);
        }
    }

    public void removeAntiqueFromBasket(Antique antique) {
        antiquesInBasket.remove(antique);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void submitOrder() {
        Optional<Client> clientByFullName = store.findClientByFullName(client.getName(), client.getSurname());
        if (clientByFullName.isPresent()) {
            client = clientByFullName.get();
        } else {
            store.createNewClient(client);
        }
        for (Antique antique : antiquesInBasket) {
            antique.setAvailability(Antique.Availability.NOT_AVALIBLE);
            store.updateAntique(antique);
            Order order = new Order();
            order.setAntique(antique);
            order.setClient(client);
            store.createNewOrder(order);
        }
        antiquesInBasket.clear();
    }
}
