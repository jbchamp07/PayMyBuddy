package com.openclassrooms.PayMyBuddy.model;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="amount")
    private double amount;

    @Column(name="description")
    private String description;

    @Column(name="date")
    private String date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_receiver", nullable = false)
    private Account account_receiver;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_giver", nullable = false)
    private Account account_giver;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount_receiver() {
        return account_receiver;
    }

    public void setAccount_receiver(Account account_receiver) {
        this.account_receiver = account_receiver;
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

    public Account getAccount_giver() {
        return account_giver;
    }

    public void setAccount_giver(Account account) {
        this.account_giver = account;
    }
}
