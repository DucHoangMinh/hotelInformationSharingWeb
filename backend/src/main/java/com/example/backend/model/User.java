package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder //Dùng để tạo một đối tượng theo khuôn mẫu có sẵn.
public class User {
    private String id;
    private String email;
    private String hashed_password;
    private String role;
}
