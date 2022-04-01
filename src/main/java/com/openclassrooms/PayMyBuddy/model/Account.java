package com.openclassrooms.PayMyBuddy.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="balance")
    private double balance;

    @OneToMany(mappedBy = "account_giver", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Transaction> transactions1;

    @OneToMany(mappedBy = "account_receiver", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Transaction> transactions2;

    public List<Transaction> getTransactions2() {
        return transactions2;
    }

    public void setTransactions2(List<Transaction> transactions2) {
        this.transactions2 = transactions2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions1() {
        return transactions1;
    }

    public void setTransactions1(List<Transaction> transactions) {
        this.transactions1 = transactions;
    }
}
