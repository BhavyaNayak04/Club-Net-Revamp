package com.jsf.backend.controller;
import com.jsf.backend.dto.LoginRequest;
import com.jsf.backend.model.User;
import com.jsf.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        if (userService.isEmailTaken(user.getEmail())) {
            return ResponseEntity.status(409).body("Email is already in use");
        }
        if (userService.isUsnTaken(user.getUsn())) {
            return ResponseEntity.status(409).body("USN is already in use");
        }
        User registeredUser = userService.registerUser(user);
        String accessToken = userService.generateAccessToken(registeredUser);
        String refreshToken = userService.generateRefreshToken(registeredUser);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);
        return new ResponseEntity<>(tokens, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        User loggedInUser = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        if (loggedInUser == null) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        String accessToken = userService.generateAccessToken(loggedInUser);
        String refreshToken = userService.generateRefreshToken(loggedInUser);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);
        return new ResponseEntity<>(tokens, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        String email = request.get("email");
        String newAccessToken = userService.refreshAccessToken(email, refreshToken);
        if (newAccessToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", newAccessToken);
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/details")
    public ResponseEntity<?> getUserDetails() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserDetails(email);
        if (user == null) {
            System.out.println("User not found for email: " + email);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.ok(user);
    }
}
