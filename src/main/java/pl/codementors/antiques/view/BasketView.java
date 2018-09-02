package pl.codementors.antiques.view;

import pl.codementors.antiques.model.Antique;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
//@Stateless
public class BasketView implements Serializable {

    private List<Antique> antiquesInBasket = new ArrayList<>();

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
}
