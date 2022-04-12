package com.openclassrooms.PayMyBuddy;

import com.openclassrooms.PayMyBuddy.model.Account;
import com.openclassrooms.PayMyBuddy.model.Friend;
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

    private User user1;
    private Account account1;
    private User user2;
    private Account account2;

    public void createUserAndAccount(){
        user1 = new User();
        user1.setFirstName("firstNameTest");
        user1.setLastName("lastNameTest");
        user1.setPassword("passwordTest");
        user1.setEmail("emailTest");
        account1 = new Account();
        account1.setUser(user1);
        account1.setBalance(0);
        user1.setAccount(account1);
        userRepository.save(user1);
    }
    public void createAnotherUserAndAccount(){
        user2 = new User();
        user2.setFirstName("firstNameTest2");
        user2.setLastName("lastNameTest2");
        user2.setPassword("passwordTest2");
        user2.setEmail("emailTest2");
        account2 = new Account();
        account2.setUser(user2);
        account2.setBalance(0);
        user2.setAccount(account2);
        userRepository.save(user2);
    }
    public void deleteFriend(Friend friend){
        friendRepository.delete(friend);
    }

    public void deleteUserAndAccount(){
        userRepository.delete(user1);
    }
    public void deleteAnotherUserAndAccount(){
        userRepository.delete(user2);
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public Account getAccount1() {
        return account1;
    }

    public void setAccount1(Account account1) {
        this.account1 = account1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public Account getAccount2() {
        return account2;
    }

    public void setAccount2(Account account2) {
        this.account2 = account2;
    }
}
