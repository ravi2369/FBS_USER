package com.fbs.user.controller;

import com.fbs.user.model.dto.BookTicketDTO;
import com.fbs.user.service.UserOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserOperationController {

    @Autowired
    private UserOperationService userOperationService;


    @GetMapping(value = "/search")
    public ResponseEntity search(@RequestParam String fromLocation, @RequestParam String toLocation) {
        try {
            return ResponseEntity.ok().body(userOperationService.findFlightSchedule(fromLocation, toLocation));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/book/{flightNumber}")
    public ResponseEntity book(@PathVariable String flightNumber, @RequestBody BookTicketDTO bookTicketDTO) {
        try {
            return ResponseEntity.ok().body(userOperationService.bookTicket(flightNumber, bookTicketDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/cancel/{pnrNo}")
    public ResponseEntity cancelTicket(@PathVariable String pnrNo) {
        try {
            return ResponseEntity.ok().body(userOperationService.cancelTicket(pnrNo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/search/email")
    public ResponseEntity searchByEmail(@RequestParam String email) {
        try {
            return ResponseEntity.ok().body(userOperationService.searchByEmail(email));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/search/pnrNo")
    public ResponseEntity searchByPnr(@RequestParam String pnrNo) {
        try {
            return ResponseEntity.ok().body(userOperationService.searchByPnr(pnrNo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
