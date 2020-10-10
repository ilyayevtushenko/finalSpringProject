package com.example.finalSpringProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    @GetMapping("/add_balance")
    public String addBalanceView (Model model) {
        return "add_balance";
    }

    @PostMapping("/add_balance")
    public String addBalance (){
        return "add_balance";
    }
}
