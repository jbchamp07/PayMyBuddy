package com.openclassrooms.PayMyBuddy.repository;

import com.openclassrooms.PayMyBuddy.model.Account;
import com.openclassrooms.PayMyBuddy.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    private Account account;
    private User user;

    public void createAccount(){
        user = new User();
        user.setFirstName("firstNameTest");
        user.setLastName("lastNameTest");
        user.setPassword("passwordTest");
        user.setEmail("emailTest");
        account = new Account();
        account.setUser(user);
        account.setBalance(50);
        user.setAccount(account);
        accountRepository.save(account);
    }
    public void deleteAccount(){
        //account = accountRepository.findById(account.getId()).get();
        //accountRepository.delete(account);
        userRepository.delete(user);
    }

    @Test
    public void findAllFriendTest(){
        createAccount();
        account = accountRepository.findById(account.getId()).get();
        deleteAccount();
        assertEquals("[]",accountRepository.findAllFriend(new int[account.getId()]).toString());
    }

}
