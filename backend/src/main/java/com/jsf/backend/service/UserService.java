package com.jsf.backend.service;
import com.jsf.backend.model.User;
import com.jsf.backend.repository.UserRepository;
import com.jsf.backend.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public User registerUser(User user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFullName(user.getFullName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setCreatedAt(new Date());
        newUser.setSemester(user.getSemester());
        newUser.setBranch(user.getBranch());
        newUser.setTags(user.getTags());
        newUser.setUsn(user.getUsn());
        return userRepository.save(newUser);
    }

    public String generateAccessToken(User user) {
        return jwtTokenUtil.generateAccessToken(user);
    }

    public String generateRefreshToken(User user) {
        return jwtTokenUtil.generateRefreshToken(user);
    }

    public boolean isEmailTaken(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean isUsnTaken(String usn) {
        return userRepository.existsByUsn(usn);
    }


    public boolean validateToken(String token) {
        return jwtTokenUtil.isTokenExpired(token);
    }

    public String refreshAccessToken(String refreshToken) {
        if (validateToken(refreshToken)) {
            String email = jwtTokenUtil.getEmailFromToken(refreshToken);
            User user = userRepository.findByEmail(email).orElse(null);
            if (user != null) {
                return generateAccessToken(user);
            }
        }
        return null;
    }

    public User loginUser(String email, String password) {
        User existingUser = userRepository.findByEmail(email).orElse(null);
        if (existingUser == null || !passwordEncoder.matches(password, existingUser.getPassword())) {
            return null; 
        }
        return existingUser;
    }
}