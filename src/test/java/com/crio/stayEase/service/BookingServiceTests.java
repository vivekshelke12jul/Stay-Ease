package com.crio.stayEase.service;


import com.crio.stayEase.exchanges.request.BookingRequest;
import com.crio.stayEase.model.Booking;
import com.crio.stayEase.model.Hotel;
import com.crio.stayEase.model.Role;
import com.crio.stayEase.model.User;
import com.crio.stayEase.repository.BookingRepository;
import com.crio.stayEase.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class BookingServiceTests {

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private HotelService hotelService;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void bookRoomTest() {

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1MUBtYWlsLmNvbSIsImlhdCI6MTczODU5NDM1NCwiZXhwIjoxNzM4NjgwNzU0fQ.xKbrTEN-o9VSauPIk7_ffVCOAD2v-bsFSpndnzrgNjk";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(1);
        BookingRequest request = new BookingRequest(startDate, endDate);

        User user1 = new User(1, "vivek", "v@123", "vivek", "boss", Role.USER);
        Hotel hotel1 = new Hotel(1, "tajHotel", "mumbai", "best hotel", 100);
        Booking booking1 = new Booking(1, hotel1, user1, startDate, endDate);

        when(userService.getUserfromToken(ArgumentMatchers.anyString()))
                .thenReturn(user1);

        when(hotelService.getHotel(ArgumentMatchers.anyInt()))
                .thenReturn(hotel1);

        when(bookingRepository.save(ArgumentMatchers.any()))
                .thenReturn(booking1);

        User updatedUser = new User(1, "vivek", "v@123", "vivek", "boss", Role.USER);
        Hotel updateHotel = new Hotel(1, "tajHotel", "mumbai", "best hotel", 99);
        Booking newBooking = new Booking(1, updateHotel, updatedUser, startDate, endDate);


        Booking resuttedBooking = bookingService.bookRoom(token, 1, request);

        assertEquals(newBooking, resuttedBooking);
    }
}
