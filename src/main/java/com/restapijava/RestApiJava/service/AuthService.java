package com.restapijava.RestApiJava.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.restapijava.RestApiJava.model.User;
import com.restapijava.RestApiJava.repository.UserRepository;
import com.restapijava.RestApiJava.util.CustomException;
import com.restapijava.RestApiJava.util.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public Map<String, Object> login(User loginUser) {
        User user = userRepository.findByEmail(loginUser.getEmail())
                .orElseThrow(() -> {
                    System.out.println("Email not found: " + loginUser.getEmail());
                    return new CustomException("Invalid email or password");
                });

        if (!passwordEncoder.matches(loginUser.getPassword(), user.getPassword())) {
            System.out.println("Password mismatch for email: " + loginUser.getEmail());
            System.out.println("Password GetPassword LoginUser: " + loginUser.getPassword());
            System.out.println("Password GetPassword USER: " + user.getPassword());
            throw new CustomException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        System.out.println("Token generated successfully for email: " + loginUser.getEmail());
        // return user data and email
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        // response.put("email", user.getEmail());
        response.put("userData", user);
        return response;
        

    }

}
