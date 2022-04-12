package com.openclassrooms.PayMyBuddy.service;

import com.openclassrooms.PayMyBuddy.ConfigDTB;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserDetailsServiceImplTest {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private ConfigDTB configDTB;

    @Test
    public void loadUserByUsername(){
        configDTB.createUserAndAccount();
        assertEquals("emailTest",userDetailsService.loadUserByUsername(configDTB.getUser1().getEmail()).getUsername());
        assertEquals("passwordTest",userDetailsService.loadUserByUsername(configDTB.getUser1().getEmail()).getPassword());
        configDTB.deleteUserAndAccount();
    }

}
