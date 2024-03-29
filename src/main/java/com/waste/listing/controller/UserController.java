package com.waste.listing.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waste.listing.exception.UserRegistrationException;
import com.waste.listing.model.User;
import com.waste.listing.service.UserService;

@RestController
public class UserController {

	private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            // Register the user
            User registeredUser = userService.register(user);

            // Return the registered user in the response
            return ResponseEntity.ok(registeredUser);
        } catch (UserRegistrationException e) {
            // If registration fails, return an error message
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials) {
    	 String email = credentials.get("email");
    	 String password = credentials.get("password");
    	
    	try {
            // Attempt to login
            User user = userService.login(email, password);

            // Return the logged-in user in the response
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            // If login fails, return an error message
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
