package com.example.backend.service;

import com.example.backend.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

//Tại sao lại phải implement interface: Có thể thay đổi cái implementation nhưng interface giữ nguyên
//Trong một hệ thống, chúng ta phải có rất nhiều QUY CHUẨN, còn thực hiện quy chuẩn thế nào có thể tùy biến được
// ==> Giúp chúng ta linh động, dễ dàng switching giữa việc thực hiện các quy chuẩn
@Service
public class UserServiceInMemory implements UserService{

    @Override
    public User login(String email, String password) {
        return null;
    }

    @Override
    public boolean logout(String email) {
        return false;
    }

    @Override
    public User addUser(String fullname, String email, String password) {
        return null;
    }

    @Override
    public Boolean activateUser(String activation_code) {
        return null;
    }

    @Override
    public Boolean updatePassword(String email, String password) {
        return null;
    }

    @Override
    public Boolean updateEmail(String email, String newEmail) {
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public User findById(String id) {
        return null;
    }
}
