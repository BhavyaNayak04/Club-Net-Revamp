package com.jsf.backend.service;
import com.jsf.backend.model.Admin;
import com.jsf.backend.repository.AdminRepository;
import com.jsf.backend.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

//    private final AdminRepository adminRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtTokenUtil jwtTokenUtil;
//
//    @Autowired
//    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
//        this.adminRepository = adminRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtTokenUtil = jwtTokenUtil;
//    }
//
//    public Admin loginAdmin(String username, String password) {
//        Admin existingAdmin = adminRepository.findByUsername(username).orElse(null);
//        if (existingAdmin == null || !passwordEncoder.matches(password, existingAdmin.getPassword())) {
//            return null; // Invalid credentials
//        }
//        return existingAdmin;
//    }
//
//    public String generateAccessToken(Admin admin) {
//        return jwtTokenUtil.generateAccessToken(admin);
//    }
//
//    public String generateRefreshToken(Admin admin) {
//        return jwtTokenUtil.generateRefreshToken(admin);
//    }
//
//    public boolean validateToken(String email, String refreshToken) {
//        return jwtTokenUtil.isTokenExpired(email, refreshToken);
//    }
//
//    public String refreshAccessToken(String email, String refreshToken) {
//        if (validateToken(email, refreshToken)) {
//            String username = jwtTokenUtil.extractUsername(refreshToken);
//            Admin admin = adminRepository.findByUsername(username).orElse(null);
//            if (admin != null) {
//                return generateAccessToken(admin);
//            }
//        }
//        return null;
//    }
//
    public void logout(String refreshToken) {
        // No need to do anything here, just let the refresh token expire
    }
}
