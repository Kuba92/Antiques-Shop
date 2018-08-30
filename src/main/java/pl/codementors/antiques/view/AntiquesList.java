package pl.codementors.antiques.view;


import pl.codementors.antiques.AntiqueDataStore;
import pl.codementors.antiques.model.Antique;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class AntiquesList implements Serializable {

    @EJB
    private AntiqueDataStore store;

    private List<Antique> antiques;

    public List<Antique> getAntiques() {
        if (antiques == null) {
            antiques = store.getAntiques();
        }
        return antiques;
    }
}
