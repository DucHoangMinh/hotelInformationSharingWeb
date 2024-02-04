package com.example.backend.controller;

import com.example.backend.dto.UserDTO;
import com.example.backend.mapper.UserMapper;
import com.example.backend.model.ChangePassword;
import com.example.backend.model.ResponseModel;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
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

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

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

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> putUserById(@PathVariable Long id, @RequestBody UserDTO userDTO){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseModel(
                            "error",
                            "Không tìm thấy thông tin người có id tương ứng",
                            ""
                    )
            );
        } else {
            User editUser = user.get();
            editUser.setFullname(userDTO.getFullname());
            editUser.setEmail(userDTO.getEmail());
            editUser.setPhone(userDTO.getPhone());
            editUser.setSex(userDTO.getSex());
            editUser.setDob(userDTO.getDob());
            return ResponseEntity.ok().body(
                    new ResponseModel(
                            "ok",
                            "Thay đổi thông tin thành công",
                            userRepository.save(editUser)
                    )
            );
        }
    }

    @PutMapping("/password/{id}")
    public ResponseEntity<ResponseModel> putPasswordById(@PathVariable Long id, @RequestBody ChangePassword changePassword){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseModel(
                            "error",
                            "Không tìm thấy thông tin người có id tương ứng",
                            ""
                    )
            );
        } else {
            String currPass = user.get().getPassword();
            String reqOldPass = changePassword.getOldPassword();
            String reqNewPass = changePassword.getNewPassword();
            Boolean compare = bCryptPasswordEncoder.matches(reqOldPass.toString(), currPass);
            if(!compare){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new ResponseModel(
                                "error",
                                "Mật khẩu cũ không hợp lệ",
                                null
                        )
                );
            } else {
                User changeUser = user.get();
                changeUser.setPassword(bCryptPasswordEncoder.encode(reqNewPass));
                return ResponseEntity.ok().body(
                        new ResponseModel(
                                "ok",
                                "Đổi mật khẩu thành công",
                                userRepository.save(changeUser)
                        )
                );
            }
        }
    }
}
