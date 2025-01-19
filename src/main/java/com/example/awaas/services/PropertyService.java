package com.example.awaas.services;

import com.example.awaas.dtos.PropertyDTO;
import com.example.awaas.dtos.UserDTO;
import com.example.awaas.managers.PropertyManager;
import com.example.awaas.managers.UserManager;
import com.example.awaas.mappers.PropertyMapper;
import com.example.awaas.requests.CreatePropertyRequest;
import com.example.awaas.response.PropertyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PropertyService {
    @Autowired
    private PropertyManager propertyManager;

    @Autowired
    private UserManager userManager;

    // Path to save uploaded images locally (adjust as needed)
    private final String UPLOAD_DIR = "/Users/omrajsharma/Documents/coding-ninjas/cn-awaas-vishwa-spring-be/uploads/";

    public PropertyResponse createProperty(CreatePropertyRequest request, MultipartFile image, String ownerEmail) throws IOException {
        // Find owner by email
        UserDTO owner = userManager.getByEmail(ownerEmail);
        if (owner == null) {
            throw new IllegalArgumentException("Owner not found");
        }

        // Save image file locally
        String imagePath = null;
        if (image != null && !image.isEmpty()) {
            // Get the original file name and sanitize it (replace spaces and special chars)
            String originalFileName = image.getOriginalFilename();
            String sanitizedFileName = originalFileName.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");

            // Generate a unique file name based on the current timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
            String uniqueFileName = timestamp + "_" + sanitizedFileName;

            // Combine directory path and unique file name
            String filePath = UPLOAD_DIR + uniqueFileName;

            // Save the image to the server
            image.transferTo(new File(filePath));
            imagePath = filePath;
        }

        // Create and save property
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setTitle(request.getTitle());
        propertyDTO.setDescription(request.getDescription());
        propertyDTO.setPrice(request.getPrice());
        propertyDTO.setLocation(request.getLocation());
        propertyDTO.setType(request.getType());
        propertyDTO.setOwner(owner);
        propertyDTO.setImageUrl(imagePath);

        return PropertyMapper.INSTANCE.toPropertyResponse(propertyManager.save(propertyDTO));
    }
}
