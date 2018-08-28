package pl.codementors.antiques.model;


import javax.persistence.*;

@Entity
@Table(name = "antiques")
public class Antique {

    public enum availability{
        AVALIBLE,
        NOT_AVALIBLE,
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int yearOfProduction;

    @ManyToOne
    @JoinColumn(name = "country", referencedColumnName = "id")
    private Country country;

    @Column
    private int price;

    @Column
    @Enumerated(EnumType.STRING)
    private availability availability;



    public Antique() {
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

    public Antique.availability getAvailability() {
        return availability;
    }

    public void setAvailability(Antique.availability availability) {
        this.availability = availability;
    }

}
