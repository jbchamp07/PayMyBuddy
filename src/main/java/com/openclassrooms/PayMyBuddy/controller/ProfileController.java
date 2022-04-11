package com.openclassrooms.PayMyBuddy.controller;

import com.openclassrooms.PayMyBuddy.model.Account;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.service.AccountService;
import com.openclassrooms.PayMyBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ProfileController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    private User user;

    @GetMapping("/profile")
    public String profile(Principal user, Model model){
        this.user = userService.getUsertByEmail(user.getName()).get();
        if(!(this.user.getAccount() == null)){
            model.addAttribute("balance",this.user.getAccount().getBalance());
        }
        model.addAttribute("userConnected",this.user);
        return "profile";
    }
    @PostMapping("/addMoney")
    public String addMoney(Principal user,@ModelAttribute("amount")double amount){
        this.user = userService.getUsertByEmail(user.getName()).get();
        doesAccountExist();
        this.user.getAccount().setBalance(this.user.getAccount().getBalance() + amount);
        accountService.money(this.user.getAccount());
        return "index";
    }

    private void doesAccountExist() {
        if(this.user.getAccount() == null){
            Account account = new Account();
            account.setBalance(0);
            account.setUser(this.user);
            accountService.add(account);
        }
    }

    @PostMapping("/recoverMoney")
    public String recoverMoney(Principal user,@ModelAttribute("amount")double amount, Model model){
        this.user = userService.getUsertByEmail(user.getName()).get();
        doesAccountExist();
        if(amount <= this.user.getAccount().getBalance()){
            this.user.getAccount().setBalance(this.user.getAccount().getBalance() - amount);
            accountService.money(this.user.getAccount());
            model.addAttribute("invalidAmount","");
            return "index";
        }else{
            model.addAttribute("invalidAmount","Vous ne pouver pas retirer ce montant");
            return "profile";
        }
    }

}
