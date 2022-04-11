package com.openclassrooms.PayMyBuddy;

import com.openclassrooms.PayMyBuddy.model.Account;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.AccountRepository;
import com.openclassrooms.PayMyBuddy.repository.FriendRepository;
import com.openclassrooms.PayMyBuddy.repository.TransactionRepository;
import com.openclassrooms.PayMyBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigDTB {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private FriendRepository friendRepository;

    private User user;
    private Account account;

    public void createUserAndAccount(){
        user = new User();
        user.setFirstName("firstNameTest");
        user.setLastName("lastNameTest");
        user.setPassword("passwordTest");
        user.setEmail("emailTest");
        account = new Account();
        account.setUser(user);
        account.setBalance(0);
        user.setAccount(account);
        userRepository.save(user);
    }

    public void deleteUserAndAccount(){
        userRepository.delete(user);
    }

}
