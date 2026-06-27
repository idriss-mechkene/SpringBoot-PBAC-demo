package org.example.authproject.controller;

import org.example.authproject.dto.LoginRequest;
import org.example.authproject.util.JWTUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            return jwtUtil.generateToken(loginRequest.getEmail());

        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid login credentials");
        }
    }
    
    

    
}
