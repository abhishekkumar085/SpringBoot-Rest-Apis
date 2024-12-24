package com.restapijava.RestApiJava.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.restapijava.RestApiJava.model.User;
import com.restapijava.RestApiJava.service.AuthService;
import com.restapijava.RestApiJava.util.ApiResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ApiResponse<Map<String, Object>> login(@RequestBody User user) {
        try {
            return new ApiResponse<>(true, "Login successfully", authService.login(user));
        } catch (Exception e) {
            return new ApiResponse<>(false, e.getMessage(), null);
        }
    }


    
}
