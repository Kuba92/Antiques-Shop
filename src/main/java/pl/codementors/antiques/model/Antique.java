package pl.codementors.antiques.model;


import javax.persistence.*;

/**
 * Antique class
 * @author Kuba
 */

@Entity
@Table(name = "antiques")
public class Antique {

    public enum Availability{
        AVALIBLE,
        NOT_AVALIBLE
    }

    /**
     * Antique ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Antique year of production
     */
    @Column
    private int yearOfProduction;

    /**
     * Antique country of origin
     */
    @ManyToOne
    @JoinColumn(name = "country", referencedColumnName = "id")
    private Country country;

    /**
     * Price of antique
     */
    @Column
    private int price;


    /**
     * Availability of antique
     */
    @Column
    @Enumerated(EnumType.STRING)
    private Availability availability;



    public Antique() {
    }

    public Antique(int yearOfProduction, Country country, int price, Availability availability) {
        this.yearOfProduction = yearOfProduction;
        this.country = country;
        this.price = price;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

}
