package pl.codementors.antiques.converter;

import pl.codementors.antiques.AntiqueDataStore;
import pl.codementors.antiques.model.Country;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("countriesConverter")
public class CountriesConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        AntiqueDataStore store = CDI.current().select(AntiqueDataStore.class).get();
        if (value == null || value.equals("null")) {
            return null;
        }
        return store.getCountry(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value == null) {
            return "null";
        }
        return Integer.toString(((Country)value).getId());
    }
}
