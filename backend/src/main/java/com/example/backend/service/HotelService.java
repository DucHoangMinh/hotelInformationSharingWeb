package com.example.backend.service;

import com.example.backend.repository.HotelRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class HotelService {
    @Autowired
    HotelRepository hotelRepository;
}
