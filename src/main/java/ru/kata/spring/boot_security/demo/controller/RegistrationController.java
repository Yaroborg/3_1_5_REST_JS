package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RegistrationService;
import ru.kata.spring.boot_security.demo.utils.UserValidator;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserValidator validator;
    private final RegistrationService service;

    @Autowired
    public RegistrationController(UserValidator validator, RegistrationService service) {
        this.validator = validator;
        this.service = service;
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") @Valid User user,
                               BindingResult bindingResult) {

        validator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        service.register(user);

        return "redirect:/login";
    }
}
