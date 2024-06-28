package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Collections;

@Service
@Transactional
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public RegistrationService(UserRepository userRepository,
                               PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public void register(User user) {

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(encoder.encode(user.getPassword()));

        userRepository.save(user);
    }
}