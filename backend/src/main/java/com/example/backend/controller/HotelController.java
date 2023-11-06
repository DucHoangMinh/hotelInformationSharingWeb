package com.example.backend.controller;

import com.example.backend.mapper.UserMapper;
import com.example.backend.model.CustomUserDetail;
import com.example.backend.model.Hotel;
import com.example.backend.model.ResponseModel;
import com.example.backend.model.User;
import com.example.backend.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/hotel")
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    UserMapper userMapper;
    @PreAuthorize("hasRole('ROLE_HOST')")
    @PostMapping("/")
    public ResponseEntity<ResponseModel> postHotel(@RequestBody Hotel newhotel, Authentication authentication){
        System.out.println("Get post hotel request");
        System.out.println(newhotel.toString());
        newhotel.setUser(((CustomUserDetail)authentication.getPrincipal()).getUser());
        //Add hostid vao thong tin khach san
        Optional<Hotel> checkHotel = Optional.ofNullable(hotelRepository.findHotelByBusinessCode(newhotel.getBusinessCode()));
        if(checkHotel.isPresent()){
            return ResponseEntity.ok().body(
                    new ResponseModel(
                            "error",
                            "Địa đểm này đã được thêm, vui lòng kiêm tra lại",
                            ""
                    )
            );
        }
        else {
            return ResponseEntity.ok().body(
                    new ResponseModel(
                            "ok",
                            "Thêm khác sạn mới thành công",
                            hotelRepository.save(newhotel)
                    )
            );
        }
    }
    @PreAuthorize("hasRole('ROLE_HOST')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> deleteHotel(@PathVariable Long id, Authentication authentication){
        Optional<Hotel> checkhotel = Optional.ofNullable(hotelRepository.findHotelById(id));
        if(!checkhotel.isPresent()){
            return ResponseEntity.ok().body(
                    new ResponseModel(
                            "error",
                            "Khách sạn bạn muốn xóa không tồn tại",
                            ""
                    )
            );
        }
        else {
            try{
                hotelRepository.deleteById(id);
            }catch (Exception e){
                System.out.println("Loi khi xoa khach san" + e);
            }
            return ResponseEntity.ok().body(
                    new ResponseModel(
                            "ok",
                            "Xóa khách sạn thành công",
                            ""
                    )
            );
        }
    }

    @PreAuthorize("hasRole('ROLE_HOST')")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> putHotel(@PathVariable Long id, @RequestBody Hotel hotel){
        Optional<Hotel> checkhotel = Optional.ofNullable(hotelRepository.findHotelById(id));
        if(!checkhotel.isPresent()){
            return ResponseEntity.ok().body(
                    new ResponseModel(
                            "error",
                            "Khách sạn bạn muốn thay đổi thông tin",
                            ""
                    )
            );
        }
        else {
            checkhotel.map(hotel1 -> {
                hotel1.setHotelname(hotel.getHotelname());
                hotel1.setClosetime(hotel.getClosetime());
                hotel1.setBusinessCode(hotel.getBusinessCode());
                hotel1.setConvenience(hotel.getConvenience());
                hotel1.setDistrict(hotel.getDistrict());
                hotel1.setProvince(hotel.getProvince());
                hotel1.setWard(hotel.getWard());
                hotel1.setPriceByDay(hotel.getPriceByDay());
                hotel1.setPriceByHour(hotel.getPriceByHour());
                hotel1.setImageLinks(hotel.getImageLinks());
                hotel1.setDetailLocation(hotel.getDetailLocation());
                return hotelRepository.save(hotel1);
            });
            return ResponseEntity.ok().body(
                    new ResponseModel(
                            "ok",
                            "Thay đổi thông tin khách sạn thành công",
                            checkhotel
                    )
            );
        }
    }

    @GetMapping("/host/{hostid}")
    public ResponseEntity<ResponseModel> getHotelByHostId(@PathVariable Long hostid){
        return ResponseEntity.ok().body(
                new ResponseModel(
                        "ok",
                        "Lấy danh sách khách sạn thành công",
                        hotelRepository.findHotelsByUser_Id(hostid)
                )
        );
    }
}
