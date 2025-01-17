package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();

    User getUserById(Long id);

    void addUser(User user);

    void removeUser(Long id);

    void updateUser(User user);

    User findByUsername(String username);

}