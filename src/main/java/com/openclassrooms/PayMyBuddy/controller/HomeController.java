package com.openclassrooms.PayMyBuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String home(){
        //TODO pareil que tranfer
        return "transfer";
    }

}
