package com.fbs.user.controller;

import com.fbs.user.model.BookTicket;
import com.fbs.user.service.UserOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserOperationController {

    @Autowired
    private UserOperationService userOperationService;


    @PostMapping(value = "/search")
    public ResponseEntity search(@RequestParam String fromLocation, @RequestParam String toLocation) {
        try {
            return ResponseEntity.ok().body(userOperationService.findFlightSchedule(fromLocation, toLocation));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/book/{flightNumber}")
    public ResponseEntity book(@PathVariable String flightNumber, @RequestBody BookTicket bookTicket) {
        try {
            return ResponseEntity.ok().body(userOperationService.bookTicket(flightNumber, bookTicket));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
