package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //    @Transactional
//    @Query("Select u from User u left join fetch u.roles where u.name=:username")
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = getOptionalUser(username);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User '%s' not found.", username));
        }
        return optionalUser.get();
    }

    public Optional<User> getOptionalUser(String username) {
        return Optional.ofNullable(userRepository.findUserByName(username));
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean delete(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public User findByUsername(String name) {
        return userRepository.findUserByName(name);
    }

    public User findByUserid(Long id) {
        return userRepository.findUserById(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }

}