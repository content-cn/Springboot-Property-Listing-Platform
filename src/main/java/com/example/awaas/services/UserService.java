package com.example.awaas.services;

import com.example.awaas.dtos.PropertyDTO;
import com.example.awaas.dtos.UserDTO;
import com.example.awaas.managers.PropertyManager;
import com.example.awaas.managers.UserManager;
import com.example.awaas.mappers.PropertyMapper;
import com.example.awaas.mappers.UserMapper;
import com.example.awaas.requests.LoginRequest;
import com.example.awaas.requests.UserRequest;
import com.example.awaas.response.LoginResponse;
import com.example.awaas.response.PropertyResponse;
import com.example.awaas.response.UserResponse;
import com.example.awaas.utilities.EmailUtility;
import com.example.awaas.utilities.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserManager userManager;

    @Autowired
    private PropertyManager propertyManager;

    @Autowired
    private EmailUtility emailUtility;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtility jwtTokenUtil;

    public String signup(UserRequest userRequest) {
        UserDTO userDTO = userManager.getByEmail(userRequest.getEmail());
        if (userDTO != null) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        UserDTO user = new UserDTO();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));
        user.setPhone(userRequest.getPhone());
        user.setVerified(false);

        // Generate OTP
        String otp = String.valueOf(new Random().nextInt(9000) + 1000);
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());

        // Save user with OTP
        userManager.save(user);

        // Send OTP via email
        emailUtility.sendEmail(user.getEmail(), "Email Verification", "Your OTP is: " + otp);

        return "Signup successful! Please verify your email.";
    }

    public String verifyOtp(String email, String otp) {
        UserDTO userDTO = userManager.getByEmail(email);
        if (userDTO == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Check OTP and expiration
        if (userDTO.getOtp().equals(otp)) {
            if (userDTO.getOtpGeneratedTime().isBefore(LocalDateTime.now().minusMinutes(10))) {
                throw new IllegalArgumentException("OTP has expired.");
            }
            userDTO.setVerified(true);
            userDTO.setOtp(null); // Clear OTP after successful verification
            userDTO.setOtpGeneratedTime(null);
            userManager.save(userDTO);
            return "Email verified successfully!";
        } else {
            throw new IllegalArgumentException("Invalid OTP.");
        }
    }

    public LoginResponse login(LoginRequest loginRequest) {
        UserDTO userDTO = userManager.getByEmail(loginRequest.getEmail());
        if (userDTO == null) {
            return new LoginResponse(404, "User Not Found", null);
        }
        if (!passwordEncoder.matches(loginRequest.getPassword(), userDTO.getPassword())) {
            return new LoginResponse(401, "Invalid Password", null);
        }
        String token = jwtTokenUtil.generateToken(userDTO.getEmail());
        return new LoginResponse(200, "Login Successful", token);
    }

    public UserResponse getUserProfile(String email) {
        UserDTO user = userManager.getByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return UserMapper.INSTANCE.toUserResponse(user);
    }

    public void updateUserProfile(String email, UserRequest updateRequest) {
        UserDTO user = userManager.getByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        // Apply updates
        user.setName(updateRequest.getName());
        user.setPhone(updateRequest.getPhone());

        // Save updated user
        userManager.save(user);
    }

    public List<PropertyResponse> getUserProperties(String email) {
        UserDTO user = userManager.getByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        List<PropertyDTO> properties = propertyManager.getPropertiesByOwner(user.getId());
        return PropertyMapper.INSTANCE.toPropertyResponseList(properties);
    }

}
