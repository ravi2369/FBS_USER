package com.fbs.user.model.dto;

import com.fbs.user.model.Passenger;
import lombok.Data;

import java.util.List;

@Data
public class BookTicketDTO {
    private Long ticketId;
    private String pnrNo;
    private String flightNumber;
    private String departureDate;
    private String arrivalDate;
    private String fromPlace;
    private String toPlace;
    private String email;
    private String name;
    private int seats;
    private String meal;
    private String status;
    private List<Passenger> passengers;
}
