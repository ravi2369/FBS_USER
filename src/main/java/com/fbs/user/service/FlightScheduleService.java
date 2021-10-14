package com.fbs.user.service;

import com.fbs.user.exceptions.FBSException;
import com.fbs.user.model.FlightSchedule;
import com.fbs.user.model.dto.FlightScheduleDTO;

import java.util.List;

;

public interface FlightScheduleService {
    FlightSchedule addFlightSchedule(FlightScheduleDTO flightScheduleDTO) throws FBSException;

    FlightSchedule updateFlightSchedule(FlightScheduleDTO flightScheduleDTO) throws FBSException;

    String deleteFlightSchedule(Long id) throws FBSException;

    List<FlightSchedule> findFlightSchedule(String fromLocation, String toLocation) throws FBSException;

    List<FlightSchedule> findAllFlightSchedules();
}
