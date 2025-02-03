package com.crio.stayEase.service;


import com.crio.stayEase.exchanges.request.HotelRequest;
import com.crio.stayEase.model.Hotel;

import java.util.List;

public interface HotelService {

    public boolean existbyId(Integer hotelId);
    public Hotel getHotel(Integer hotelId);
    public List<Hotel> getAllHotels();
    public Hotel createHotel(HotelRequest hotelRequest);
    public Hotel updateHotel(Integer hotelId, HotelRequest hotelRequest);
    public void deleteHotel(Integer hotelId);
}
