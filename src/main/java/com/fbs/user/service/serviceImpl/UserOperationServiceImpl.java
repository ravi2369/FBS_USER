package com.fbs.user.service.serviceImpl;

import com.fbs.user.exceptions.FBSException;
import com.fbs.user.model.Airline;
import com.fbs.user.model.BookTicket;
import com.fbs.user.model.Flight;
import com.fbs.user.model.FlightSchedule;
import com.fbs.user.model.dto.BookTicketDTO;
import com.fbs.user.repository.AirlineRepository;
import com.fbs.user.repository.BookTicketRepository;
import com.fbs.user.repository.FlightRepository;
import com.fbs.user.repository.SearchFlightRepository;
import com.fbs.user.service.UserOperationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.fbs.user.util.DateUtility.convertToFbsFormat;

@Service
public class UserOperationServiceImpl implements UserOperationService {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private SearchFlightRepository searchFlightRepository;

    @Autowired
    private BookTicketRepository bookTicketRepository;

    @Override
    public List<FlightSchedule> findFlightSchedule(String fromLocation, String toLocation) throws FBSException {
        if (fromLocation != null && toLocation != null) {
            List<FlightSchedule> flightSchedule = searchFlightRepository.findScheduledFlights(fromLocation, toLocation);
            List<FlightSchedule> flightSchedules = new ArrayList<>();
            if (!flightSchedule.isEmpty()) {
                for (FlightSchedule fs : flightSchedule) {
                    FlightSchedule schedule = new FlightSchedule();
                    if (fs.getAirLineCode() != null) {
                        Optional<Airline> airline = airlineRepository.findById(fs.getAirLineCode());
                        if (airline.isPresent()) {
                            if (airline.get().getStatus().toString().equalsIgnoreCase("false")) {
                                flightSchedules.add(fs);
                            }
                        }
                    }
                }
                return flightSchedules;
            } else
                throw new FBSException("flight schedule is not available");
        } else {
            throw new FBSException("Please select  fromLocation & toLocation");
        }
    }

    @Override
    public BookTicket bookTicket(String flightNumber, BookTicketDTO bookTicketDTO) {
        if (flightNumber != null) {
            BookTicket ticket = new BookTicket();
            Optional<FlightSchedule> flightSchedule = searchFlightRepository.findScheduledFlight(flightNumber);
            if (flightSchedule.isPresent()) {
                BeanUtils.copyProperties(bookTicketDTO, ticket, bookTicketDTO.getDepartureDate());
                ticket.setPnrNo("PNR" + new Random().nextInt());
                ticket.setFlightNumber(flightSchedule.get().getFlightNumber());
                ticket.setArrivalDate(flightSchedule.get().getEndDateTime());
                ticket.setStatus("Booked");
                if (bookTicketDTO.getDepartureDate() != null) {
                    ticket.setDepartureDate((LocalDateTime) convertToFbsFormat(bookTicketDTO.getDepartureDate()));
                }
                Optional<Flight> flight = flightRepository.findById(flightNumber);
                if (flight.isPresent()) {
                    flight.get().setAvailableSeats(flight.get().getAvailableSeats() - bookTicketDTO.getSeats());
                    flightRepository.save(flight.get());
                }
                return bookTicketRepository.save(ticket);
            } else {
                throw new FBSException("scheduled flight not found with this " + flightNumber + " flight number, please take another flight number.");
            }
        } else {
            throw new FBSException("Flight number required to book ticket.");
        }
    }

    @Override
    public List<BookTicket> searchByEmail(String email) throws FBSException {
        return bookTicketRepository.searchByEmail(email);
    }

    @Override
    public List<BookTicket> searchByPnr(String pnrNo) throws FBSException {
        return bookTicketRepository.searchByPnr(pnrNo);
    }

    @Override
    public String cancelTicket(String pnrNo, String status) throws FBSException {
        if (status.equalsIgnoreCase("cancel")) {
            int count = bookTicketRepository.cancelTicket(pnrNo, "Cancelled");
            if (count == 1) {
                List<BookTicket> bookTickets = bookTicketRepository.searchByPnr(pnrNo);
                if (bookTickets != null && !bookTickets.isEmpty()) {
                    bookTickets.forEach(bookTicket -> {
                        if (bookTicket.getFlightNumber() != null && !bookTickets.isEmpty()) {
                            Optional<Flight> flight = flightRepository.findById(bookTicket.getFlightNumber());
                            if (flight.isPresent()) {
                                flight.get().setAvailableSeats(bookTicket.getSeats() + flight.get().getAvailableSeats());
                                flightRepository.save(flight.get());
                            }
                        }
                    });
                }
                status = "ticket cancelled";
            }
        }
        return status;
    }
}
