package com.example.backend.repository;

import com.example.backend.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Hotel findHotelById(Long id);
    Hotel findHotelByBusinessCode(Long businessCode);
    void deleteById(Long id);
    List<Hotel> findHotelsByUser_Id(Long hostid);
}
