package com.fbs.user.model;

import lombok.Data;

import java.util.List;

@Data
public class BookingRequest {
    private String departureDate;
    private String fromPlace;
    private String toPlace;
    private String email;
    private String name;
    private int seats;
    private String meal;
    private List<Passenger> passengers;
}
