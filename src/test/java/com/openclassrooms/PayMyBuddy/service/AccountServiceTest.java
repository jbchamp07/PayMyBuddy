package com.openclassrooms.PayMyBuddy.service;

import com.openclassrooms.PayMyBuddy.ConfigDTB;
import com.openclassrooms.PayMyBuddy.model.Account;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.AccountRepository;
import com.openclassrooms.PayMyBuddy.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConfigDTB configDTB;

    private Account account;

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
    public void addTest(){
        User user = new User();
        user.setFirstName("firstNameTest");
        user.setLastName("lastNameTest");
        user.setPassword("passwordTest");
        user.setEmail("emailTest");
        account = new Account();
        account.setUser(user);
        account.setBalance(100);
        user.setAccount(account);
        accountService.add(account);
        assertEquals("firstNameTest",accountService.getById(account.getId()).getUser().getFirstName());
        //accountRepository.delete(account);
        userRepository.delete(user);
    }
    @Test
    public void getByIdTest(){
        configDTB.createUserAndAccount();
        assertEquals("firstNameTest",accountService.getById(configDTB.getAccount1().getId()).getUser().getFirstName());
        configDTB.deleteUserAndAccount();
    }
    @Test
    public void moneyTest(){
        configDTB.createUserAndAccount();
        configDTB.getAccount1().setBalance(150);
        accountService.money(configDTB.getAccount1());
        assertEquals(150,accountService.getById(configDTB.getAccount1().getId()).getBalance());
        configDTB.deleteUserAndAccount();
    }

    @Test
    public void getAllFriendsTest(){
        configDTB.createUserAndAccount();
        configDTB.createAnotherUserAndAccount();
        configDTB.addFriend();
        assertEquals("[]",accountService.getAllFriends(configDTB.getAccount1().getUser().getId()).toString());
        configDTB.deleteUserAndAccount();
        configDTB.deleteAnotherUserAndAccount();
        configDTB.deleteFriend();
    }
}
