package com.example.backend.repository;

import com.example.backend.model.User;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public interface UserRepo extends JpaRepository {

//    Dùng để lưu trữ thông tin về người dùng một cách an toàn, do Spring Boot hỗ trợ.
    private ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

//    Không lưu trực tiếp pass vào trong hệ thống mà lưu cái đã được băm
    public User addUser(String email, String hashed_password, String role){
        String id = UUID.randomUUID().toString();
        User user = User.builder()
            .id(id)
            .email(email)
            .hashed_password(hashed_password)
            .role(role)
            .build();

        //Thêm user vừa tạo vào danh sách
        users.put(id,user);
        return user;
    }

    //Kiểm tra một email đã tồn tại trong hệ thống hay chưa
    public boolean isEmailExist(String email){
        return users.values().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email)).count() > 0;
    }

    //Sử dụng Optional để tránh trường hợp là null thì nó báo lỗi, mà sẽ được xử lý một cách an toàn.
    public Optional<User> findByEmail(String email){
        return users.values().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email)).findFirst();
    }
}
