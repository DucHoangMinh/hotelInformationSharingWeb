package com.example.backend.dto;


import com.example.backend.model.Hotel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.sql.Date;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO{
    private Long id;
    private String fullname;
    private String email;
    private String role;
    private Date dob;
    private Long phone;
    private Boolean sex;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private Collection<Hotel> hotels;
}

