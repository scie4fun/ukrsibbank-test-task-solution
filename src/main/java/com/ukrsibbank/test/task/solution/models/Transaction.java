package com.ukrsibbank.test.task.solution.models;

import javax.persistence.*;

@Entity
@Table(name = "transactions", schema = "ukrsibbank_db")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "place", nullable = false, length = 30)
    private String place;
    @Column(name = "amount", nullable = false, length = 10)
    private double amount;
    @Column(name = "currency", nullable = false, length = 3)
    private String currency;
    @Column(name = "card", nullable = false, length = 16)
    private String card;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
