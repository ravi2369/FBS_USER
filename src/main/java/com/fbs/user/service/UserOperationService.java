package com.fbs.user.service;

import com.fbs.user.exceptions.FBSException;
import com.fbs.user.model.BookTicket;
import com.fbs.user.model.FlightSchedule;
import com.fbs.user.model.dto.BookTicketDTO;

import java.util.List;

public interface UserOperationService {
    List<FlightSchedule> findFlightSchedule(String fromLocation, String toLocation) throws FBSException;

    BookTicket bookTicket(String flightNumber, BookTicketDTO bookTicketDTO);

    List<BookTicket> searchByEmail(String email) throws FBSException;

    List<BookTicket> searchByPnr(String pnrNo) throws FBSException;

    String cancelTicket(String pnrNo) throws FBSException;
}
