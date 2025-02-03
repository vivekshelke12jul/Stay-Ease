package com.crio.stayEase.exchanges.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelRequest {
    private String name;
    private String location;
    private String description;
    private Integer availableRooms;
}
