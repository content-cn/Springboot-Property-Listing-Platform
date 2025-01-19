package com.example.awaas.controllers;

import com.example.awaas.entities.Property;
import com.example.awaas.requests.CreatePropertyRequest;
import com.example.awaas.response.PropertyResponse;
import com.example.awaas.services.PropertyService;
import com.example.awaas.utilities.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private JwtUtility jwtUtility;

    @PostMapping
    public ResponseEntity<?> createProperty(
            @RequestPart("property") CreatePropertyRequest request,
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestHeader("Authorization") String token) {
        try {

            String ownerEmail = jwtUtility.extractEmail(token);

            PropertyResponse response = propertyService.createProperty(request, image, ownerEmail);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
