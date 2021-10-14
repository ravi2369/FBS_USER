package com.fbs.user.service;


import com.fbs.user.exceptions.FBSException;
import com.fbs.user.model.Flight;

public interface FlightService {
    Flight addFlight(Flight flight) throws FBSException;

    Flight updateFlight(Flight flight) throws FBSException;

    Flight findFlight(String flightNumber) throws FBSException;

    Iterable<Flight> findAllFlights();

    String deleteFlights(String flightNumber) throws FBSException;
}
