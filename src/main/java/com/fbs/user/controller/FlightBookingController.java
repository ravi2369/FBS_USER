package com.fbs.user.controller;

import com.fbs.user.model.BookingRequest;
import com.fbs.user.service.FlightBookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/flight/booking")
public class FlightBookingController {

    @RequestMapping(value = "/bookFlight", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String bookFlight(BookingRequest bookingRequest) {
        log.info("bookFlight() invoked with the BookingRequest: " + bookingRequest.toString());
        return "reservation/reservationConfirmation";
    }
}
