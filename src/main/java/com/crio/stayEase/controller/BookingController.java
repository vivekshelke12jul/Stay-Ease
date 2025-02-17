package com.crio.stayEase.controller;

import com.crio.stayEase.exchanges.request.BookingRequest;
import com.crio.stayEase.model.Booking;
import com.crio.stayEase.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/hotel/{hotelId}/booking")
    public Booking bookRoom(
            @PathVariable Integer hotelId,
            @RequestBody BookingRequest request,
            @RequestHeader("Authorization") String jwt
    ) throws Exception{
        return bookingService.bookRoom(jwt, hotelId, request);
    }

    @PreAuthorize("hasAuthority('HOTEL_MANAGER')")
    @DeleteMapping("/booking/{bookingId}")
    public void cancelBooking(@PathVariable Integer bookingId) {
        bookingService.cancelBooking(bookingId);
    }


}
