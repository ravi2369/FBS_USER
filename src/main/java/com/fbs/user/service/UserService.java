package com.fbs.user.service;

import com.fbs.user.exceptions.FBSException;
import com.fbs.user.model.BookingRequest;


public interface UserService {
    String search(String fromLocation, String toLocation) throws FBSException;

    String book(String flightNumber, BookingRequest request);
}
