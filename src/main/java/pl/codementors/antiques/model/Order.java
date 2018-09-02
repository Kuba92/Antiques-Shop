package pl.codementors.antiques.model;


import javax.persistence.*;

/**
 * Order class
 */
@Entity
@Table(name = "orders")
public class Order {

    /**
     * Order ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Client order
     */
    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "id")
    private Client client;

    /**
     * Antique, whose order applies
     */
    @OneToOne
    @JoinColumn(name = "antique", referencedColumnName = "id")
    private Antique antique;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Antique getAntique() {
        return antique;
    }

    public void setAntique(Antique antique) {
        this.antique = antique;
    }
}
