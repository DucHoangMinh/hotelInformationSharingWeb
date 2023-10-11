package com.example.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class LoginController {
    @GetMapping(path = "/")
    public String sendSuccessSignal(){
        return "Test Login Controller Successfully!";
    }
}
