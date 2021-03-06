package com.example.finalSpringProject.controller;

import com.example.finalSpringProject.model.domain.CreditCard;
import com.example.finalSpringProject.model.service.CreditCardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CreditCardController {

    private final CreditCardService creditCardService;

    @GetMapping("/card")
    public String cardView() {
        return "/card";
    }
    @PostMapping("/card")
    public String addCreditCard (@ModelAttribute CreditCard creditCard) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        creditCardService.addCreditCard(creditCard, name);
        return "redirect:/user";
    }

}
