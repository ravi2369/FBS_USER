package com.fbs.user.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "book_ticket")
@Data
public class BookTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String PNR;
    private String flightNumber;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private String fromPlace;
    private String toPlace;
    private String email;
    private String name;
    private int seats;
    private String meal;
    @OneToMany(targetEntity = Passenger.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "PNR",referencedColumnName = "PNR")
    private List<Passenger> passengers;

    @Transient
    private String startDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (startDate != null) {
            this.departureDate = LocalDateTime.parse(startDate, formatter);
        }
        this.startDate = startDate;
    }
}
