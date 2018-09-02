package pl.codementors.antiques.model;


import javax.persistence.*;

/**
 * Client class
 * @author Kuba
 */
@Entity
@Table(name = "clients")
public class Client {

    /**
     * Client ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Client name
     */
    @Column
    private String name;

    /**
     * Client surname
     */
    @Column
    private String surname;

    public Client() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
