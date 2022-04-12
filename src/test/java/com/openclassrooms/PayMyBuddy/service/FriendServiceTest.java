package com.openclassrooms.PayMyBuddy.service;

import com.openclassrooms.PayMyBuddy.ConfigDTB;
import com.openclassrooms.PayMyBuddy.model.Friend;
import com.openclassrooms.PayMyBuddy.repository.FriendRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FriendServiceTest {

    @Autowired
    private FriendService friendService;
    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private static ConfigDTB configDTB;

    private Friend friend;
/*
    @BeforeAll
    public static void init(){
        configDTB.createUserAndAccount();
        configDTB.createAnotherUserAndAccount();
    }
    @AfterAll
    public static void deleteall(){
        configDTB.deleteUserAndAccount();
        configDTB.deleteAnotherUserAndAccount();
        configDTB.deleteFriend(friend);
    }*/

    @Test
    public void addTest(){
        configDTB.createUserAndAccount();
        configDTB.createAnotherUserAndAccount();
        friend = new Friend();
        friend.setAccount_giver(configDTB.getAccount1());
        friend.setAccount_receiver(configDTB.getAccount2());
        friendService.add(friend);
        assertEquals(friend.getAccount_giver().getId(),friendRepository.findById(friend.getId()).get().getAccount_giver().getId());
        assertEquals(friend.getAccount_receiver().getId(),friendRepository.findById(friend.getId()).get().getAccount_receiver().getId());
        configDTB.deleteUserAndAccount();
        configDTB.deleteAnotherUserAndAccount();
        configDTB.deleteFriend(friend);
    }

}
