package com.example.finalSpringProject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
@Slf4j
public class AdminController {
    @GetMapping("/admin")
    public ModelAndView admin() {
        log.info("\n\n in the admin controller");
        return new ModelAndView("/admin");
    }
}
