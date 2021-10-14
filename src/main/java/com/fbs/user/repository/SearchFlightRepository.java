package com.fbs.user.repository;

import com.fbs.user.exceptions.FBSException;
import com.fbs.user.model.FlightSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SearchFlightRepository extends JpaRepository<FlightSchedule, Long> {
    @Query("SELECT FS FROM FlightSchedule FS WHERE FS.fromLocation LIKE ?1% and FS.toLocation LIKE ?2%")
    List<FlightSchedule> findScheduledFlights(String fromLocation, String toLocation) throws FBSException;

    @Query("SELECT FS FROM FlightSchedule FS WHERE FS.flightNumber = ?1")
    Optional<FlightSchedule> findScheduledFlight(String flightNumber) throws FBSException;
}
