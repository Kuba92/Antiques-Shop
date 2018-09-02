package pl.codementors.antiquesrest;

import pl.codementors.antiques.model.Antique;
import pl.codementors.antiques.model.Country;

import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Country country = new Country("Finland");
        Antique antiqueFromFinland = new Antique(123, country, 12344, Antique.Availability.AVALIBLE);

        Client client1 = ClientBuilder.newClient();
        WebTarget target = client1.target("http://localhost:8080/Antiques-1.0-SNAPSHOT/api/antiques");

        Invocation.Builder postRequest = target.request();
        postRequest.post(Entity.json(antiqueFromFinland));

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/Antiques-1.0-SNAPSHOT/api/antiques");
        Invocation.Builder request = webTarget.request();
        Response response = request.get();
        List<Antique> antiques = response.readEntity(new GenericType<List<Antique>>() {
        });

        for (Antique antique : antiques) {
            System.out.println(antique.getId() + " " + antique.getPrice() + " " + antique.getYearOfProduction() + " "
                    + antique.getCountry().getName() + " " + antique.getAvailability());
        }
    }
}
