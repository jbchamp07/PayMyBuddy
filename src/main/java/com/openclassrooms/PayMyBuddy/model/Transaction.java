package com.openclassrooms.PayMyBuddy.model;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="id_receiver")
    private int id_receiver;

    @Column(name="id_giver")
    private int id_giver;

    @Column(name="amount")
    private double amount;

    @Column(name="description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_receiver() {
        return id_receiver;
    }

    public void setId_receiver(int id_receiver) {
        this.id_receiver = id_receiver;
    }

    public int getId_giver() {
        return id_giver;
    }

    public void setId_giver(int id_giver) {
        this.id_giver = id_giver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
