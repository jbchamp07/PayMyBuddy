package com.openclassrooms.PayMyBuddy.service;

import com.openclassrooms.PayMyBuddy.model.Transaction;
import com.openclassrooms.PayMyBuddy.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Page<Transaction> findYourTransactions(int id,PageRequest pageRequest){
        //return transactionRepository.findYourTransactions(id,pageRequest);
        //TODO if id=id
        Page<Transaction> transactionPage = transactionRepository.findYourTransactions(id,pageRequest);
        double amount;
        for (Transaction transaction: transactionPage) {
            if(transaction.getAccount_giver().getId() == id){
                amount = transaction.getAmount();
                transaction.setAmount(-amount);
            }
        }
        return transactionPage;
    }


    public void add(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
