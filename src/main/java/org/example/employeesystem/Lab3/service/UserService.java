package org.example.employeesystem.Lab2.service;

import org.example.employeesystem.Lab2.dto.RegisterRequest;
import org.example.employeesystem.Lab2.Entity.User;
import org.example.employeesystem.Lab2.enums.Role;
import org.example.employeesystem.Lab2.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository ur, PasswordEncoder pe) {
        this.userRepository = ur;
        this.passwordEncoder = pe;
    }


    public User register(RegisterRequest req) {
        if (userRepository.existsByUsername(req.getUsername())) {
            throw new IllegalArgumentException("Username already taken");
        }
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        User user = new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        String role = (req.getRole() == null || req.getRole().isBlank()) ? "USER" : req.getRole().toUpperCase();
        user.setRole(Role.valueOf("ROLE_" + role));

        return userRepository.save(user);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }


    public Optional<User> updateUser(Long id, RegisterRequest req) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(req.getUsername());
            user.setEmail(req.getEmail());
            user.setPassword(passwordEncoder.encode(req.getPassword()));
            String role = (req.getRole() == null || req.getRole().isBlank()) ? "USER" : req.getRole().toUpperCase();
            user.setRole(Role.valueOf("ROLE_" + role));
            return userRepository.save(user);
        });
    }

    public boolean deleteUser(Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }


    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElse(null);
    }

}
