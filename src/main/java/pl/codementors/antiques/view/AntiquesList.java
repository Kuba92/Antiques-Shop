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
 * Bean for editing info about single antique
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

    public List<Antique> getAntiques() {
        if (antiques == null) {
            antiques = store.getAntiques();
        }
        return antiques;
    }

    public void addAntiqueToBasket(Antique antique) {
        if (!basketView.getAntiquesInBasket().contains(antique)) {
            basketView.addAntiqueToBasket(antique);
        }
    }

    public List<Antique> getAntiquesAddedToBasket() {
        return basketView.getAntiquesInBasket();
    }

    public boolean addedToBasket (Antique antique) {
        return (basketView.getAntiquesInBasket().contains(antique) || antique.getAvailability() == Antique.Availability.NOT_AVALIBLE);
    }

    public void removeAntiqueFromBasket(Antique antique) {
        basketView.removeAntiqueFromBasket(antique);
    }
}
