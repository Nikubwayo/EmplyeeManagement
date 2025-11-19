package org.example.employeesystem.Lab4.controller;

import org.example.employeesystem.Lab4.dto.AuthRequest;
import org.example.employeesystem.Lab4.dto.AuthResponse;
import org.example.employeesystem.Lab4.dto.RegisterRequest;
import org.example.employeesystem.Lab4.Entity.User;
import org.example.employeesystem.Lab4.enums.Role;
import org.example.employeesystem.Lab4.security.JwtUtil;
import org.example.employeesystem.Lab4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {


    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {


        String roleStr = (request.getRole() == null || request.getRole().isEmpty())
                ? "ROLE_USER"
                : (request.getRole().startsWith("ROLE_") ? request.getRole() : "ROLE_" + request.getRole());


        Role role = Role.valueOf(roleStr);


        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(role);

        User saved = userService.saveUser(newUser);


        String token = jwtUtil.generateToken(saved.getUsername(), saved.getRole().name());

        return ResponseEntity.ok(
                new AuthResponse(token, saved.getUsername(), saved.getRole().name(), "Registration successful")
        );
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userService.findByUsername(request.getUsername());


        Role role = user.getRole() != null ? user.getRole() : Role.ROLE_USER;

        String token = jwtUtil.generateToken(user.getUsername(), role.name());

        return ResponseEntity.ok(
                new AuthResponse(token, user.getUsername(), role.name(), "Login successful")
        );
    }

}
