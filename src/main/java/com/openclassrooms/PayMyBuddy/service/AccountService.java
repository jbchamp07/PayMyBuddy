package com.openclassrooms.PayMyBuddy.service;

import com.openclassrooms.PayMyBuddy.model.Account;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserService userService;

    public Iterable<Account> getAllFriends(int id) {
        int[] accountsId = accountRepository.friendsId(id);
        Iterable<Account> allFriends = accountRepository.findAllFriend(accountsId);
        return allFriends;
    }

    public Account getById(int friendId) {
        return accountRepository.findById(friendId).get();
    }

    public void add(Account account){
        accountRepository.save(account);
    }

    public void money(Account account) {
        accountRepository.save(account);
    }

    public void addAccount(User user) {

        Account account = new Account();
        account.setBalance(0);
        account.setUser(user);
        add(account);
        user.setAccount(account);
        userService.save(user);

    }
}
