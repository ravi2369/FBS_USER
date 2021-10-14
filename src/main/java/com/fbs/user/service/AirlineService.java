package com.fbs.user.service;

import com.fbs.user.exceptions.FBSException;
import com.fbs.user.model.Airline;
import com.fbs.user.model.dto.AirlineDTO;

import java.util.List;
import java.util.Optional;

public interface AirlineService {
    Airline addAirline(AirlineDTO airlineDTO) throws FBSException;

    Airline updateAirline(AirlineDTO airlineDTO) throws FBSException;

    Optional<AirlineDTO> findAirline(String airLineCode) throws FBSException;

    List<AirlineDTO> findAllAirlines();

    String deleteAirline(String airportCode) throws FBSException;
}
