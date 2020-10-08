package com.example.finalSpringProject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class UserController {
    @GetMapping("/user")
    public ModelAndView viewCards() {
        log.info("\n\n in the user controller");
        return new ModelAndView("/user");
    }
}
