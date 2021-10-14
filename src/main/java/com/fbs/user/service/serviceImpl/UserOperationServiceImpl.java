package com.fbs.user.service.serviceImpl;

import com.fbs.user.exceptions.FBSException;
import com.fbs.user.model.BookTicket;
import com.fbs.user.model.Flight;
import com.fbs.user.model.FlightSchedule;
import com.fbs.user.repository.BookTicketRepository;
import com.fbs.user.repository.FlightRepository;
import com.fbs.user.repository.SearchFlightRepository;
import com.fbs.user.service.UserOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserOperationServiceImpl implements UserOperationService {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private SearchFlightRepository searchFlightRepository;

    @Autowired
    private BookTicketRepository bookTicketRepository;

    @Override
    public List<FlightSchedule> findFlightSchedule(String fromLocation, String toLocation) throws FBSException {
        if (fromLocation != null && toLocation != null) {
            List<FlightSchedule> flightSchedule = searchFlightRepository.findScheduledFlights(fromLocation, toLocation);
            if (!flightSchedule.isEmpty()) {
                return flightSchedule;
            } else
                throw new FBSException("flight schedule is not available");
        } else {
            throw new FBSException("Please select  fromLocation & toLocation");
        }
    }

    @Override
    public BookTicket bookTicket(String flightNumber, BookTicket bookTicket) {
        if (flightNumber != null) {
            Optional<FlightSchedule> flightSchedule = searchFlightRepository.findScheduledFlight(flightNumber);
            if (flightSchedule.isPresent()) {
                bookTicket.setPNRNumber("PNR" + new Random().nextInt());
                bookTicket.setFlightNumber(flightSchedule.get().getFlightNumber());
                bookTicket.setToPlace(flightSchedule.get().getToLocation());
                bookTicket.setArrivalDate(flightSchedule.get().getEndDateTime());

                Optional<Flight> flight = flightRepository.findById(flightNumber);
                if (flight.isPresent()) {
                    flight.get().setAvailableSeats(flight.get().getAvailableSeats() - bookTicket.getSeats());
                    flightRepository.save(flight.get());
                }
                return bookTicketRepository.save(bookTicket);
            } else {
                throw new FBSException("scheduled flight not found with this " + flightNumber + " flight number, please take another flight number");
            }
        } else {
            throw new FBSException("Flight number required to book ticket.");
        }
    }
}
