package com.openclassrooms.PayMyBuddy.controller;

import com.openclassrooms.PayMyBuddy.model.Account;
import com.openclassrooms.PayMyBuddy.model.Transaction;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.service.AccountService;
import com.openclassrooms.PayMyBuddy.service.TransactionService;
import com.openclassrooms.PayMyBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class TransferController {

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;
    private User user = new User();

    @GetMapping("/transfer")
    public String transfer(Principal user, Model model){
        this.user = userService.getUsertByEmail(user.getName()).get();
        model.addAttribute("user",this.user);
        Iterable<Account> friendsaccount = accountService.getAllFriends(this.user.getId());
        model.addAttribute("friendsaccounts",friendsaccount);

        List<Transaction> transactionList = transactionService.findYourTransactions(this.user.getId());
        model.addAttribute("transactionList",transactionList);

        return "transfer";
    }

}
