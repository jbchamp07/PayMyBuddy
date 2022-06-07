package com.openclassrooms.PayMyBuddy.controller;

import com.openclassrooms.PayMyBuddy.model.Friend;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.service.FriendService;
import com.openclassrooms.PayMyBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class AddController {

    @Autowired
    private UserService userService;
    @Autowired
    private FriendService friendService;
    private User user;

    @GetMapping("/add")
    public String addPage(){
        return "add";
    }

    @PostMapping("/add")
    public String addFriend(Principal user , @ModelAttribute("email")String email, Model model){
        this.user = userService.getUsertByEmail(user.getName()).get();
        if(userService.getUsertByEmail(email).get() == null){
            model.addAttribute("done","This email doesn't exist");
        }else{
            friendService.add(this.user.getAccount(),userService.getUsertByEmail(email).get().getAccount());
            model.addAttribute("done","Friend added");
        }
        return "add";
    }
}
