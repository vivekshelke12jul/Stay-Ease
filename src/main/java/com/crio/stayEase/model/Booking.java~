package com.crio.stayEase.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {
    @Id
    @GeneratedValue
    private Integer bookingId;

    @OneToOne
    private Hotel hotel;

    @OneToOne
    private User user;

    private LocalDate startDate;
    private LocalDate endDate;


    public Booking(Hotel hotel, User user, LocalDate startDate, LocalDate endDate) {
        this.hotel = hotel;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
