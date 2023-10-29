package com.example.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;
import java.util.Collection;


@Data
@Builder //Dùng để tạo một đối tượng theo khuôn mẫu có sẵn.
@Entity
@AllArgsConstructor
@Table(name = "user") 
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullname;
    private String email;
    private String password;
    private String role;
    private Date dob;
    private Long phone;
    private Boolean sex;
    public User() {

    }
}
