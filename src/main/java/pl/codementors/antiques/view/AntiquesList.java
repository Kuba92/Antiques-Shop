package pl.codementors.antiques.view;


import pl.codementors.antiques.AntiqueDataStore;
import pl.codementors.antiques.model.Antique;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Bean for seeing info about all antiques
 *
 * @author Kuba
 */

@Named
@ViewScoped
public class AntiquesList implements Serializable {

    @EJB
    private AntiqueDataStore store;

    @Inject
    private BasketView basketView;

    private List<Antique> antiques;

    /**
     *Returns all antiques
     */
    public List<Antique> getAntiques() {
        if (antiques == null) {
            antiques = store.getAntiques();
        }
        return antiques;
    }

    /**
     *Adds antique to basket
     */
    public void addAntiqueToBasket(Antique antique) {
        if (!basketView.getAntiquesInBasket().contains(antique)) {
            basketView.addAntiqueToBasket(antique);
        }
    }

    public List<Antique> getAntiquesAddedToBasket() {
        return basketView.getAntiquesInBasket();
    }

    /**
     *Returns true if antique is already in busket
     */
    public boolean addedToBasket (Antique antique) {
        return (basketView.getAntiquesInBasket().contains(antique) || antique.getAvailability() == Antique.Availability.NOT_AVALIBLE);
    }

    public void removeAntiqueFromBasket(Antique antique) {
        basketView.removeAntiqueFromBasket(antique);
    }
}
