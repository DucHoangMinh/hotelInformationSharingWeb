package com.example.backend.model;

import com.example.backend.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.sql.Array;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String hotelname;
    private String province;
    private String district;
    private String ward;
    private String detailLocation;
    private String convenience;
    private Time opentime; //Gio nhan khach vao
    private Time closetime; //Gio tra khach ra
    private Integer priceByHour;
    private Integer priceOverNight;
    private Integer priceByDay;
    private Long businessCode;
    private String imageLinks;
    @ManyToOne
    @JoinColumn(name = "host_id")
    @EqualsAndHashCode.Exclude
    private User user;
}
