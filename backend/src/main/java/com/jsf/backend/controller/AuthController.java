package com.jsf.backend.controller;

import com.jsf.backend.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    AdminService adminService;

    @PostMapping("/logout")
    public ResponseEntity<?> adminLogout(@RequestBody Map<String, String> request) {
        adminService.logout(request.get("refreshToken"));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
