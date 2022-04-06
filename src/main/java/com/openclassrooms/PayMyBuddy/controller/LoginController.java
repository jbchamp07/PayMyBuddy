package com.openclassrooms.PayMyBuddy.controller;

import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.security.Principal;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    private User user = new User();

    @GetMapping("/")
    public String index(Principal user, Model model){
        this.user = userService.getUsertByEmail(user.getName()).get();
        model.addAttribute("user",this.user);
        return "transfer";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
