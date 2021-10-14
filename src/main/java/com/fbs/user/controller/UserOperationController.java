package com.fbs.user.controller;

import com.fbs.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserOperationController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/search")
    public ResponseEntity search(@RequestParam String fromLocation, @RequestParam String toLocation) {
        try {
            return ResponseEntity.ok().body(userService.search(fromLocation, toLocation));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
