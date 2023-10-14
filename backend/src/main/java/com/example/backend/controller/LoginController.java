package com.example.backend.controller;

import com.example.backend.model.CustomUserDetail;
import com.example.backend.payload.LoginRequest;
import com.example.backend.payload.LoginResponse;
import com.example.backend.security.JWTtokenProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTtokenProvider jwTtokenProvider;

    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Trả về jwt cho người dùng.
        String jwt = jwTtokenProvider.generateToken((CustomUserDetail) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }
    @GetMapping("/test")
    public String randomStuff(){
        return ("JWT Hợp lệ mới có thể thấy được message này");
    }

}
