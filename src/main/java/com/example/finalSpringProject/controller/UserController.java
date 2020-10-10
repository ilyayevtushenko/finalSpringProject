package com.example.finalSpringProject.controller;

import com.example.finalSpringProject.model.domain.Account;
import com.example.finalSpringProject.model.domain.CreditCard;
import com.example.finalSpringProject.model.service.AccountService;
import com.example.finalSpringProject.model.service.CreditCardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final CreditCardService creditCardService;
    private final AccountService accountService;

    @GetMapping("/user")
    public String view(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        Set<CreditCard> creditCardSet = creditCardService.getAllCardsByUserName(name);
        Set<Account> accounts = accountService.findAllByName(name);
        model.addAttribute("creditcards", creditCardSet);
        model.addAttribute("accounts", accounts);
        log.info("\n\n in the user controller");
            return "/user";
    }
}
