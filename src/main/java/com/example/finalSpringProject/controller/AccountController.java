package com.example.finalSpringProject.controller;

import com.example.finalSpringProject.model.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/add_balance")
    public String addBalanceView (Model model) {
        return "add_balance";
    }

    @PostMapping("/add_balance")
    public String addBalance (@RequestParam (value = "number") String number,
                              @RequestParam(value = "balance") BigDecimal balance){
        log.info("number: " + number);
        log.info("balance: " + balance);

        accountService.addBalance(number, balance);

        return "redirect:/user";
    }
}
