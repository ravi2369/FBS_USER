package com.fbs.user.model.dto;

import com.fbs.user.model.Passenger;
import lombok.Data;

import java.util.List;

@Data
public class BookTicketDTO {
    private String PNR;
    private String startDate;
    private String fromPlace;
    private String toPlace;
    private String email;
    private String name;
    private int seats;
    private String meal;
    private List<Passenger> passengers;
}
