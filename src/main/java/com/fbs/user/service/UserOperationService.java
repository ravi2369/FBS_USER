package com.fbs.user.service;

import com.fbs.user.exceptions.FBSException;
import com.fbs.user.model.BookTicket;
import com.fbs.user.model.FlightSchedule;

import java.util.List;

public interface UserOperationService {
    List<FlightSchedule> findFlightSchedule(String fromLocation, String toLocation) throws FBSException;

    BookTicket bookTicket(String flightNumber, BookTicket bookTicket);
}
