package com.example.awaas.controllers;

import com.example.awaas.requests.UserRequest;
import com.example.awaas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserRequest userRequest) {
        String message = userService.signup(userRequest);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        String message = userService.verifyOtp(email, otp);
        return ResponseEntity.ok(message);
    }
}
