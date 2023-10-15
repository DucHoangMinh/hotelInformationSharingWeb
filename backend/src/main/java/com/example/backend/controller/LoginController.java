package com.example.backend.controller;

import com.example.backend.model.CustomUserDetail;
import com.example.backend.model.ResponseModel;
import com.example.backend.model.User;
import com.example.backend.payload.LoginRequest;
import com.example.backend.payload.LoginResponse;
import com.example.backend.repository.UserRepository;
import com.example.backend.security.JWTtokenProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JWTtokenProvider jwTtokenProvider;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/test")
    public String returnTest(){
        return "testApiSuccessfully";
    }
    @PostMapping("/login")
    public LoginResponse authenticateUser(@RequestBody LoginRequest loginRequest) {
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
    @PostMapping("/register")
    ResponseEntity<ResponseModel> handleRegisterNewAccount(@RequestBody User insertUser){
        System.out.println("Get register request!!");
        Optional<User> new_user = Optional.ofNullable(userRepository.findByEmail(insertUser.getEmail()));
        if(new_user.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseModel(
                            "error",
                            "Email has been used by another account",
                            ""
                    )
            );
        }
        else {
            insertUser.setPassword(passwordEncoder.encode(insertUser.getPassword()));
            return ResponseEntity.ok().body(
                    new ResponseModel(
                            "ok",
                            "Register account successfully",
                            userRepository.save(insertUser)
                    )
            );
        }
    }
}
