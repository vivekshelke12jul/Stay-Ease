package com.crio.stayEase.controller;

import com.crio.stayEase.exchanges.request.HotelRequest;
import com.crio.stayEase.model.Hotel;
import com.crio.stayEase.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/public/hotel")
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/public/hotel/{hotelId}")
    public Hotel getHotel(@PathVariable Integer hotelId) {
        return hotelService.getHotel(hotelId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/hotel")
    public Hotel createHotel(@RequestBody HotelRequest hotelRequest) {
        return hotelService.createHotel(hotelRequest);
    }

    @PreAuthorize("hasAuthority('HOTEL_MANAGER')")
    @PutMapping("/hotel/{hotelId}")
    public Hotel updateHotel(
            @PathVariable Integer hotelId,
            @RequestBody HotelRequest hotelRequest
    ) {
        return hotelService.updateHotel(hotelId, hotelRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/hotel/{hotelId}")
    public void deleteHotel(
            @PathVariable Integer hotelId
    ) {
        hotelService.deleteHotel(hotelId);
    }

}