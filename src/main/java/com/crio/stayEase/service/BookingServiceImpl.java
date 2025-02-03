package com.crio.stayEase.service;

import com.crio.stayEase.exchanges.request.BookingRequest;
import com.crio.stayEase.model.Booking;
import com.crio.stayEase.model.Hotel;
import com.crio.stayEase.model.User;
import com.crio.stayEase.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserService userService;


    @Override
    public Booking bookRoom(String jwt, Integer hotelId, BookingRequest request) {

        User user = (User) userService.getUserfromToken(jwt);

        Hotel hotel = hotelService.getHotel(hotelId);
        if(hotel.getAvailableRooms() == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No rooms available in this hotel");
        }
        hotel.decreaseAvaliableRooms();

        Booking booking = new Booking(
                hotel,
                user,
                request.getStartDate(),
                request.getEndDate()
        );
        return bookingRepository.save(booking);
    }

    @Override
    public void cancelBooking(Integer bookingId) {
        Hotel hotel = bookingRepository.findById(bookingId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found with id:"+ bookingId)
        ).getHotel();

        hotel.increaseAvaliableRooms();
        bookingRepository.deleteById(bookingId);
    }
}
