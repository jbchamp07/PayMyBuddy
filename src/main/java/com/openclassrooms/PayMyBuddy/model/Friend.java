package com.openclassrooms.PayMyBuddy.model;

import javax.persistence.*;


@Table(name = "friendlist")
public class Friend {
/*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_giver")
    private int id_giver;

    @MapsId
    @JoinColumn(name = "id_receiver")
    private int id_receiver;*/
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_receiver", nullable = false)
    private Account account_receiver;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_giver", nullable = false)
    private Account account_giver;

    public Account getAccount_receiver() {
        return account_receiver;
    }

    public void setAccount_receiver(Account account_receiver) {
        this.account_receiver = account_receiver;
    }

    public Account getAccount_giver() {
        return account_giver;
    }

    public void setAccount_giver(Account account_giver) {
        this.account_giver = account_giver;
    }
}
