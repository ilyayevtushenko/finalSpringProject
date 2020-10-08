package com.example.finalSpringProject.controller;

import com.example.finalSpringProject.model.domain.User;
import com.example.finalSpringProject.model.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RegistrationController {

    private final UserService userService;

    @GetMapping("/registration")
    public String registrationView(Model model) {
        model.addAttribute("user", new User());
        return "/registration";
    }

    @PostMapping("/signUp")
    public ModelAndView registration(HttpSession session, Model model, @ModelAttribute User user) {



        try {
            user = userService.registration(user);
            model.addAttribute("user", user);
            return new ModelAndView("/user");

        } catch (RuntimeException e){  //todo: create custom exeption
            model.addAttribute("badCredentials", true);
            return new ModelAndView("/registration");
        }

    }
}

