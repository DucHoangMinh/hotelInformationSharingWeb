package com.example.backend.controller;

import com.example.backend.mapper.UserMapper;
import com.example.backend.model.ResponseModel;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRepository userRepository;
    ModelMapper modelMapper = new ModelMapper();
    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> getUserById(@PathVariable Long id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseModel(
                            "error",
                            "Không tìm thấy thông tin người có id tương ứng",
                            ""
                    )
            );
        }
        else {
            return ResponseEntity.ok().body(
                    new ResponseModel(
                            "error",
                            "Lấy thông tin người dùng thành công",
                            userMapper.toUserDTO(user.get())
                    )
            );
        }
    }
}
