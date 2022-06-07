package com.openclassrooms.PayMyBuddy.service;

import com.openclassrooms.PayMyBuddy.model.Account;
import com.openclassrooms.PayMyBuddy.model.Transaction;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final CountDownLatch countDownLatch  = new CountDownLatch(1);

    public Optional<User> getUsertByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User getById(int friendId) {
        return userRepository.findById(friendId).get();
    }

    @Transactional
    public void doTransaction(String userEmail, int friendId, double amount,String description) {


        User user = getUsertByEmail(userEmail).get();
        User friendUser = getById(friendId);
        User applicationUser = getUsertByEmail("PayMyBuddy@gmail.com").get();


        applicationUser.getAccount().setBalance(applicationUser.getAccount().getBalance() + (amount * 0.05));
        amount = amount * 0.95;

        accountService.money(user.getAccount());
        accountService.money(friendUser.getAccount());
        Transaction transaction = new Transaction();
        transaction.setAccount_giver(user.getAccount());
        transaction.setAccount_receiver(friendUser.getAccount());
        transaction.setAmount(amount);
        transaction.setDate(Date.from(Instant.now()));
        transaction.setDescription(description);

        //my first thread
        double finalAmount = amount;
        Thread t1 = new Thread(){
            @SneakyThrows
            public void run(){

                countDownLatch.await();
                user.getAccount().setBalance(user.getAccount().getBalance() - finalAmount);
            }
        };
        //my second thread
        double finalAmount1 = amount;
        Thread t2 = new Thread(){
            @SneakyThrows
            public void run(){

                countDownLatch.await();
                friendUser.getAccount().setBalance(friendUser.getAccount().getBalance() + finalAmount1);
            }
        };
        Thread t3 = new Thread(){
            @SneakyThrows
            public void run(){

                countDownLatch.await();
                transactionService.add(transaction);
            }
        };
        t1.start();
        t2.start();
        t3.start();
        countDownLatch.countDown();



    }
}
