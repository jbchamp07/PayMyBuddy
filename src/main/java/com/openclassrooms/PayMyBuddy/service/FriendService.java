package com.openclassrooms.PayMyBuddy.service;

import com.openclassrooms.PayMyBuddy.model.Account;
import com.openclassrooms.PayMyBuddy.model.Friend;
import com.openclassrooms.PayMyBuddy.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;


    public void add(Account giver, Account receiver) {
        Friend friend = new Friend();
        friend.setAccount_giver(giver);
        friend.setAccount_receiver(receiver);
        friendRepository.save(friend);
    }
}
