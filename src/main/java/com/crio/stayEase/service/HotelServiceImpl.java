package com.crio.stayEase.service;

import com.crio.stayEase.exchanges.request.HotelRequest;
import com.crio.stayEase.model.Hotel;
import com.crio.stayEase.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private UserService userService;

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(HotelRequest hotelRequest) {
        Hotel hotel = new Hotel(
                hotelRequest.getName(),
                hotelRequest.getLocation(),
                hotelRequest.getDescription(),
                hotelRequest.getAvailableRooms()
        );
        return hotelRepository.save(hotel);
    }

    @Override
    public boolean existbyId(Integer hotelId) {
        boolean exists = hotelRepository.existsById(hotelId);
        if(!exists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found with id:"+ hotelId);
        }
        return exists;
    }

    @Override
    public Hotel getHotel(Integer hotelId) throws ResponseStatusException {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if(hotel.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found with id:"+ hotelId);
        }
        return hotel.get();

//        return
//                .orElseThrow(() ->
//                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found with id:"+ hotelId)
//                );
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel updateHotel(Integer hotelId, HotelRequest hotelRequest) {
        Hotel hotel = getHotel(hotelId);
        hotel.setName(hotelRequest.getName());
        hotel.setLocation(hotelRequest.getLocation());
        hotel.setDescription(hotelRequest.getDescription());
        hotel.setAvailableRooms(hotelRequest.getAvailableRooms());
        return hotelRepository.save(hotel);
    }

    @Override
    public void deleteHotel(Integer hotelId) {
        existbyId(hotelId);
        hotelRepository.deleteById(hotelId);
    }
}