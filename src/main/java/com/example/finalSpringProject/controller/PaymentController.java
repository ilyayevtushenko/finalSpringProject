package com.example.finalSpringProject.controller;

import com.example.finalSpringProject.model.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/payment")
    public String paymentView() {

        return "/payment";
    }

    @PostMapping("/payment")
    public String createPayment (@RequestParam(value = "sender") String sender,
                              @RequestParam(value = "receiver") String receiver,
                              @RequestParam(value = "sum") BigDecimal sum){

        log.info("sender: " + sender);
        log.info("receiver: " + receiver);
        log.info("sum: " + sum);
        paymentService.createPayment(sender, receiver, sum);
        return "redirect:/user";
    }
}
