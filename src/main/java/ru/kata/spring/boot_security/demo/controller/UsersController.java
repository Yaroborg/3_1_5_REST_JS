package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin")
    public String getUsersPage(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "admin";
    }

    @GetMapping(value = "/edit")
    public String setAsAdmin(@RequestParam("id") Long id) {
        User user = userService.findByUserid(id);
        user.addRole(new Role(2L, "ROLE_ADMIN"));
        userService.save(user);

        return "redirect:/admin";
    }

    @GetMapping(value = "/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);

        return "redirect:/admin";
    }

    @GetMapping(value = "/user")
    public String showUserInfo(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

}
