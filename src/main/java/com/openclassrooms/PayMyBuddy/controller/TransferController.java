package com.openclassrooms.PayMyBuddy.controller;

import com.openclassrooms.PayMyBuddy.model.Account;
import com.openclassrooms.PayMyBuddy.model.Transaction;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.service.AccountService;
import com.openclassrooms.PayMyBuddy.service.TransactionService;
import com.openclassrooms.PayMyBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public String transfer(Principal user, Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        this.user = userService.getUsertByEmail(user.getName()).get();
        model.addAttribute("user",this.user);
        Iterable<Account> friendsaccount = accountService.getAllFriends(this.user.getId());
        model.addAttribute("friendsaccounts",friendsaccount);


        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Sort sort = Sort.by(Sort.Order.asc("date"));
        Page<Transaction> transactionList = transactionService.findYourTransactions(this.user.getAccount().getId(), PageRequest.of(currentPage - 1, pageSize, sort));
        int totalPages = transactionList.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        //model.addAttribute("transactionList", transactionService.findYourTransactions(this.user.getAccount().getId(), PageRequest.of(currentPage - 1, pageSize, sort)));
        model.addAttribute("transactionList", transactionList);
        return "transfer";
    }

    @PostMapping("/transfer")
    public String postPay(Principal user,@ModelAttribute("friendid")int friendId,@ModelAttribute("amount")double amount,@ModelAttribute("description")String description){
        this.user = userService.getUsertByEmail(user.getName()).get();
        User friendUser = userService.getById(friendId);
        if(this.user.getAccount().getBalance() < amount){

            //TODO 5% a l'application
            User applicationUser = userService.getUsertByEmail("PayMyBuddy@gmail.com").get();
            applicationUser.getAccount().setBalance(applicationUser.getAccount().getBalance() + (amount * 0.05));
            amount = amount * 0.95;

            this.user.getAccount().setBalance(this.user.getAccount().getBalance() - amount);
            friendUser.getAccount().setBalance(friendUser.getAccount().getBalance() + amount);
            accountService.money(this.user.getAccount());
            accountService.money(friendUser.getAccount());
            Transaction transaction = new Transaction();
            transaction.setAccount_giver(this.user.getAccount());
            transaction.setAccount_receiver(friendUser.getAccount());
            transaction.setAmount(amount);
            transaction.setDate(Date.from(Instant.now()));
            transaction.setDescription(description);
            transactionService.add(transaction);
        }
        return "index";
    }

}
