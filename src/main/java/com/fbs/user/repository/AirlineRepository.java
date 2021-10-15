package com.fbs.user.repository;

import com.fbs.user.model.Airline;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends CrudRepository<Airline, String> {
}
