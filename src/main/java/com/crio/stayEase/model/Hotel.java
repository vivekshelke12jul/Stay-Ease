package com.crio.stayEase.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hotel {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String location;
    private String description;
    private Integer availableRooms;

    public Hotel(String name, String location, String description, Integer availableRooms) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.availableRooms = availableRooms;
    }

    public Hotel(Hotel hotel){
        this.name = hotel.getName();
        this.location = hotel.getLocation();
        this.description = hotel.getDescription();
        this.availableRooms = hotel.getAvailableRooms();
    }

    public void increaseAvaliableRooms() {
        this.availableRooms += 1;
    }

    public void decreaseAvaliableRooms() {
        this.availableRooms -= 1;
    }
}
