package com.openclassrooms.PayMyBuddy.repository;

import com.openclassrooms.PayMyBuddy.ConfigDTB;
import com.openclassrooms.PayMyBuddy.model.Account;
import com.openclassrooms.PayMyBuddy.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private static ConfigDTB configDTB;

    private User user;

    @BeforeAll
    public static void createUser(){
        /*User user = new User();
        user.setFirstName("firstNameTest");
        user.setLastName("lastNameTest");
        user.setPassword("passwordTest");
        user.setEmail("emailTest");
        Account account = new Account();
        account.setUser(user);
        account.setBalance(0);
        user.setAccount(account);
        userRepository.save(user);*/
        configDTB.createUserAndAccount();
    }
    @AfterAll
    public static void deleteUser(){
        //user = userRepository.findByEmail("emailTest").get();
        /*userRepository.delete(user);*/
        configDTB.deleteUserAndAccount();
    }

    @Test
    public void findByEmailTest(){
        //createUser();
        user = userRepository.findByEmail("emailTest").get();
        //deleteUser();
        assertEquals("firstNameTest",user.getFirstName());
    }

}
