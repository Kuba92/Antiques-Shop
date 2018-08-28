package pl.codementors.antiques.model;


import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "id")
    private Client client;

    @OneToOne
    @JoinColumn(name = "antique", referencedColumnName = "id")
    private Antique antique;
}
