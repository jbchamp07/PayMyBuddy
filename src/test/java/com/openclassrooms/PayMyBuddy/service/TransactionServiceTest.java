package com.openclassrooms.PayMyBuddy.service;

import com.openclassrooms.PayMyBuddy.ConfigDTB;
import com.openclassrooms.PayMyBuddy.model.Transaction;
import com.openclassrooms.PayMyBuddy.repository.TransactionRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ConfigDTB configDTB;
    @Autowired
    private TransactionRepository transactionRepository;

    private Transaction transaction;
    /*
    @BeforeAll
    public static void init(){
        configDTB.createUserAndAccount();
        configDTB.createAnotherUserAndAccount();
    }
    @AfterAll
    public static void deleteAll(){
        configDTB.deleteUserAndAccount();
        configDTB.deleteAnotherUserAndAccount();
        configDTB.deleteFriend(friend);
    }*/
    @Test
    @Transactional
    public void addTest(){
        configDTB.createUserAndAccount();
        configDTB.createAnotherUserAndAccount();
        transaction = new Transaction();
        transaction.setAmount(50);
        transaction.setDescription("descriptionTest");
        transaction.setDate(Date.from(Instant.now()));
        transaction.setAccount_giver(configDTB.getAccount1());
        transaction.setAccount_receiver(configDTB.getAccount2());
        transactionService.add(transaction);
        configDTB.getAccount1().setBalance(configDTB.getAccount1().getBalance() - 50);
        configDTB.getAccount2().setBalance(configDTB.getAccount2().getBalance() + 50);
        assertEquals("firstNameTest",transactionRepository.getById(transaction.getId()).getAccount_giver().getUser().getFirstName());
        assertEquals("firstNameTest2",transactionRepository.getById(transaction.getId()).getAccount_receiver().getUser().getFirstName());
        configDTB.deleteUserAndAccount();
        configDTB.deleteAnotherUserAndAccount();
    }
    @Test
    @Transactional
    public void findYourTransactionsTest(){
        configDTB.createUserAndAccount();
        configDTB.createAnotherUserAndAccount();
        configDTB.addTransaction();
        assertEquals("firstNameTest2",transactionService.findYourTransactions(configDTB.getAccount1().getId(), PageRequest.of(0, 5, Sort.by(Sort.Order.asc("date")))).get().findFirst().get().getAccount_receiver().getUser().getFirstName());
        configDTB.deleteUserAndAccount();
        configDTB.deleteAnotherUserAndAccount();
    }

}
