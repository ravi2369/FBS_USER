package com.fbs.user.service;

import com.fbs.user.exceptions.FBSException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface UserService {
    ResponseEntity search(String fromLocation, String toLocation) throws FBSException;
}
