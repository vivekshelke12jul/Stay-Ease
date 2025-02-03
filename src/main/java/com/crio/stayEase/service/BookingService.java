package com.crio.stayEase.service;

import com.crio.stayEase.exchanges.request.BookingRequest;
import com.crio.stayEase.model.Booking;

public interface BookingService {

    public Booking bookRoom(String jwt, Integer hotelId, BookingRequest request);
    public void cancelBooking(Integer bookingId);
}
