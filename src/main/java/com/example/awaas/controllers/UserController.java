package com.example.awaas.controllers;

import com.example.awaas.requests.UserRequest;
import com.example.awaas.response.PropertyResponse;
import com.example.awaas.response.UserResponse;
import com.example.awaas.services.UserService;
import com.example.awaas.utilities.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtility jwtUtility;

    // Fetch user profile
    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(@RequestHeader("Authorization") String token) {
        try {
            String email = jwtUtility.extractEmail(token);
            UserResponse userResponse = userService.getUserProfile(email);
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    // Update user profile
    @PutMapping("/profile")
    public ResponseEntity<?> updateUserProfile(
            @RequestHeader("Authorization") String token,
            @RequestBody UserRequest updateRequest) {
        try {
            String email = jwtUtility.extractEmail(token);
            userService.updateUserProfile(email, updateRequest);
            return ResponseEntity.ok("Profile updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/properties")
    public ResponseEntity<?> getUserProperties(@RequestHeader("Authorization") String token) {
        try {
            String email = jwtUtility.extractEmail(token);
            List<PropertyResponse> properties = userService.getUserProperties(email);
            return ResponseEntity.ok(properties);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
