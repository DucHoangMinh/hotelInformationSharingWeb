package com.example.backend.controller;

import ch.qos.logback.core.model.Model;
import com.example.backend.dto.UserDTO;
import com.example.backend.exception.UserException;
import com.example.backend.model.User;
import com.example.backend.request.LoginRequest;
import com.example.backend.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class LoginController {
    @Autowired
    UserService userService;
    @GetMapping(path = "/")
    public String sendSuccessSignal(){
        return "Test Login Controller Successfully!";
    }

    @PostMapping(path = "/login")
    public String handleLogin(@Valid @ModelAttribute LoginRequest loginRequest, BindingResult result, HttpSession session){
        if(result.hasErrors()){
            return "notsuccess";
        }
        User user;
        try{
            user = userService.login(loginRequest.email(), loginRequest.password());
            session.setAttribute("user", new UserDTO(user.getId(), user.getEmail(), user.getFullname()));
        }catch (UserException ex){
            System.out.println(ex.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "success";
    }
}
