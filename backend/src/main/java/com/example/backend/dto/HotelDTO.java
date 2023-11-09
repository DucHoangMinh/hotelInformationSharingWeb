package com.example.backend.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {
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
}
