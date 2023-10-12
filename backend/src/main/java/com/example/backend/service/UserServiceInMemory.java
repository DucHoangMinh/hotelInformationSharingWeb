package com.example.backend.service;

import com.example.backend.exception.UserException;
import com.example.backend.hash.Hashing;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

//Tại sao lại phải implement interface: Có thể thay đổi cái implementation nhưng interface giữ nguyên
//Trong một hệ thống, chúng ta phải có rất nhiều QUY CHUẨN, còn thực hiện quy chuẩn thế nào có thể tùy biến được
// ==> Giúp chúng ta linh động, dễ dàng switching giữa việc thực hiện các quy chuẩn
@Service
@AllArgsConstructor
public class UserServiceInMemory implements UserService{
    private UserRepo userRepo;
    private Hashing hashing;
    @Override
    public User login(String email, String password) throws Exception {
        //Trả về các mã lỗi nếu không tìm thấy user và trả về user khi validate password thành công
        Optional<User> o_user = userRepo.findByEmail(email);
        if(!o_user.isPresent()){
            throw new UserException("User is not found!");
        }
        if(hashing.validatePassword(password, o_user.get().getHashed_password())) return o_user.get();
        else throw new Exception("Password is incorrect!");
    }

    @Override
    public boolean logout(String email) {
        return false;
    }

    @Override
    public User addUser(String fullname, String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return userRepo.addUser(fullname,email,hashing.hashPassword(password));
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
