package com.openclassrooms.PayMyBuddy.service;

import com.openclassrooms.PayMyBuddy.model.Account;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    private User user;


    public void createUser(){
        user = new User();
        user.setFirstName("firstNameTest");
        user.setLastName("lastNameTest");
        user.setPassword("passwordTest");
        user.setEmail("emailTest");
        Account account = new Account();
        account.setUser(user);
        account.setBalance(0);
        user.setAccount(account);
        userService.save(user);
    }
    public void deleteUser(){
        userRepository.delete(user);
    }
    @Test
    public void saveTest(){
        createUser();
        assertEquals("firstNameTest",userService.getUsertByEmail("emailTest").get().getFirstName());
        assertEquals("firstNameTest",userService.getById(user.getId()).getFirstName());
        deleteUser();
    }

}
