package pl.codementors.antiquesrest;

import pl.codementors.antiques.model.Antique;
import pl.codementors.antiques.model.Country;

import javax.ws.rs.client.*;


public class PostMain {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient(); //obiekt do wolania uslug
        WebTarget target = client.target("http://localhost:8080/Antiques-1.0-SNAPSHOT/api/antiques");

//        Country country = new Country("Finland");
        Antique antique = new Antique();
        Invocation.Builder postRequest = target.request();
        postRequest.post(Entity.json(antique));
    }

}
