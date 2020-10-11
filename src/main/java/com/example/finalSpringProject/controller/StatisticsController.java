package com.example.finalSpringProject.controller;

import com.example.finalSpringProject.model.domain.Account;
import com.example.finalSpringProject.model.entity.AccountEntity;
import com.example.finalSpringProject.model.service.AccountService;
import com.example.finalSpringProject.model.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StatisticsController {

    private final AccountService accountService;
    private final PaymentService paymentService;
    private final static int pageSize = 2;

    @GetMapping("/statistics")
    public String addBalanceView (Model model,
                                  @RequestParam(defaultValue = "1") Integer currentPage,
                                  @RequestParam(defaultValue = "name") String sortBy,
                                  @RequestParam(defaultValue = "") String searchBy) {

        Page<AccountEntity> accounts = accountService.resolvePagesAccountUser(currentPage, pageSize, sortBy, searchBy);
        model.addAttribute("accounts", accounts);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("searchBy", searchBy);

        accountService.findPageNumbers(accounts.getTotalPages())
                .ifPresent(pageNumbers -> model.addAttribute("pageNumbers", pageNumbers));

        return "statistics";
    }
}
