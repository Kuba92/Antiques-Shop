package pl.codementors.antiques.view;


import pl.codementors.antiques.AntiqueDataStore;
import pl.codementors.antiques.model.Antique;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean to editing and adding new antiques
 */
@Named
@ViewScoped
public class AntiqueEdit implements Serializable {

    @EJB
    private AntiqueDataStore store;

    private Antique antique;

    private List<SelectItem> countries;

    private List<SelectItem> availabilityList;

    private int antiqueId;

    /**
     * Returns antique ID
     * @return antique ID
     */
    public int getAntiqueId() {
        return antiqueId;
    }

    /**
     * Sets antique ID
     * @param antiqueId
     */
    public void setAntiqueId(int antiqueId) {
        this.antiqueId = antiqueId;
    }

    /**
     * Gets single antique
     */
    public Antique getAntique() {
        if (antique == null) {
            antique = store.getAntique(antiqueId);
        }
        return antique;
    }

    /**
     * Returns countries by name as Select Item list
     */
    public List<SelectItem> getCountries() {
        if (countries == null) {
            countries = new ArrayList<SelectItem>();
            countries.add(new SelectItem(null, "---"));
            store.getCountries().forEach(country -> {
                countries.add(new SelectItem(country, country.getName()));
            });
        }
        return countries;
    }

    /**
     *Returns availability as Select Item list
     */
    public List<SelectItem> getAvailability () {
        if (availabilityList == null) {
            availabilityList = new ArrayList<>();
            for (Antique.Availability availability : Antique.Availability.values()) {
                availabilityList.add(new SelectItem(availability, availability.name()));
            }
        }
        return availabilityList;
    }

    /**
     * Saves antique
     */
    public void saveAntique() {
        if (antique.getId() == 0) {
            store.createNewAntique(antique);
        } else if (antique.getId() != 0) {
            store.updateAntique(antique);
        }
    }
}
