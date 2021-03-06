package pl.codementors.antiques.model;


import javax.persistence.*;
import java.util.Objects;

/**
 * Country class
 * @author Kuba
 */
@Entity
@Table(name = "countries")
public class Country {

    /**
     * Country ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Country name
     */
    @Column
    private String name;

    public Country(String name) {
        this.name = name;
    }

    public Country() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id == country.id &&
                Objects.equals(name, country.name);
    }

    /**
     * Contry to hashcode method
     * @return Country object as hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    /**
     * TO string method
     * @return Country as String
     */
    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
